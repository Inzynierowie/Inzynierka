package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.PriceController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
import com.engineering.thesis.backend.testObj.Prices;
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
    public void selectAllShouldReturnPricesWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        when(priceServiceImpl.selectAll()).thenReturn(List.of(Prices.price1, Prices.price2));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select price with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].treatment").value("Biopsy"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(1000))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].treatment").value("Biopsy"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(1000));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select").with(Role))
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnPriceWhenProperRoleIsSelected() throws ResourceNotFoundException {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        when(priceServiceImpl.selectPriceById(id)).thenReturn(java.util.Optional.of(Prices.price1));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById price with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.treatment").value("Biopsy"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select/1").with(Role))
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void createPriceShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create price with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    post("/api/price/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(priceServiceImpl, atLeast(1)).create(any(Price.class));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    post("/api/price/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 3, \"treatment\":\"biopsy\", \"cost\":2000}")
                                            .with(Role)
                            )
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void deletePriceShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete price with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(delete("/api/price/delete/1").with(Role))
                            .andExpect(status().isOk());
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(delete("/api/price/delete/1").with(Role))
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updatePriceShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update price with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    put("/api/price/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(priceServiceImpl, atLeast(1)).update(any(Price.class));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    put("/api/price/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                            .with(Role)
                            )
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests \n");
                e.printStackTrace();
            }
        });
    }
}