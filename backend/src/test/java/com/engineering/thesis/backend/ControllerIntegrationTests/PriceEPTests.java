package com.engineering.thesis.backend.ControllerIntegrationTests;

import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.config.jwt.UnauthorizedHandler;
import com.engineering.thesis.backend.controller.PriceController;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.serviceImpl.PriceServiceImpl;
import com.engineering.thesis.backend.serviceImpl.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.engineering.thesis.backend.ControllerIntegrationTests.SecurityMockMvcRequestPostProcessors.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PriceController.class)
class PriceEPTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtToken jwtToken;

    @MockBean
    private PriceServiceImpl priceServiceImpl;

    @MockBean
    private UnauthorizedHandler unauthorizedHandler;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void selectAllPricesShouldReturn2Prices() throws Exception {
        when(priceServiceImpl.selectAll())
                .thenReturn(List.of(new Price(1L, "test",1000L),
                                    new Price(2L, "Consultation",500L)));
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/price/select").with(DoctorRole()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].treatment").value("Consultation"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].treatment").value("test"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(1000));
    }

    @Test
    public void selectAllPricesShouldNotReturn2PricesDueToAuthorization() throws Exception {
        when(priceServiceImpl.selectAll())
                .thenReturn(List.of(new Price(1L, "test",1000L),
                        new Price(2L, "Consultation",500L)));
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/price/select").with(InvalidRole()))
                .andExpect(status().isForbidden());
    }


    @Test
    public void selectByIdShouldReturnPriceWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        when(priceServiceImpl.selectPriceById(id)).thenReturn(java.util.Optional.of(new Price(1L, "Biopsy", 1000L)));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing Select price with role: " + name);
                if(name=="Doctor"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/price/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.treatment").value("Biopsy"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000));
                }
                if(name=="Patient"){
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
    public void createPriceShouldBePossible() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/price/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                .with(DoctorRole())
                )
                .andExpect(status().isOk());
        verify(priceServiceImpl).create(any(Price.class));
    }

    @Test
    public void createPriceShouldBeImpossibleDueToAuthorization() throws Exception {
        this.mockMvc
                .perform(
                        post("/api/price/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                .with(InvalidRole())
                )
                .andExpect(status().isForbidden());
    }

    @Test
    public void deletePriceShouldBePossible() throws Exception {
        this.mockMvc
                .perform(delete("/api/price/delete/1").with(DoctorRole()))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePriceShouldBeImpossibleDueToAuthorization() throws Exception {
        this.mockMvc
                .perform(delete("/api/price/delete/1").with(InvalidRole()))
                .andExpect(status().isForbidden());
    }

    @Test
    public void updatePriceShouldBePossible() throws Exception {
        this.mockMvc
                .perform(
                        put("/api/price/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                .with(DoctorRole())
                )
                .andExpect(status().isOk());
        verify(priceServiceImpl).update(any(Price.class));
    }

    @Test
    public void updatePriceShouldNotBePossibleDueToAuthorization() throws Exception {
        this.mockMvc
                .perform(
                        put("/api/price/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\": 1, \"treatment\":\"test\", \"cost\":1000}")
                                .with(InvalidRole())
                )
                .andExpect(status().isForbidden());
    }
}