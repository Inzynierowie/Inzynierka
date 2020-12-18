package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.PriceController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.engineering.thesis.backend.controllerIntegrationTests.role.RoleProcessors.RulesMap;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = PriceController.class)
class PriceEPTests extends MockConfiguration {

    @MockBean
    private PriceServiceImpl priceServiceImpl;

    @Test
    public void selectAllShouldReturnPricesWhenProperRoleIsSelected() throws Exception {
        when(priceServiceImpl.selectAll())
                .thenReturn(List.of(
                        new Price(1L, "test",1000L),
                        new Price(2L, "Consultation",500L)
                ));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select price with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].treatment").value("Consultation"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(500))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].treatment").value("test"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(1000));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnPriceWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        when(priceServiceImpl.selectPriceById(id))
                .thenReturn(java.util.Optional.of(
                        new Price(1L, "Biopsy", 1000L)));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById price with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.treatment").value("Biopsy"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void createPriceShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create price with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(
                                    post("/api/price/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(priceServiceImpl, atLeast(1)).create(any(Price.class));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(
                                    post("/api/price/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 3, \"treatment\":\"biopsy\", \"cost\":2000}")
                                            .with(Role)
                            )
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void deletePriceShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete price with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(delete("/api/price/delete/1").with(Role))
                            .andExpect(status().isOk());
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(delete("/api/price/delete/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updatePriceShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update price with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(
                                    put("/api/price/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(priceServiceImpl, atLeast(1)).update(any(Price.class));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(
                                    put("/api/price/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                            .with(Role)
                            )
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}