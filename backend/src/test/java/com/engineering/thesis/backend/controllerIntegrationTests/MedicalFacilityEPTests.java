package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.MedicalFacilityController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.serviceImpl.MedicalFacilityServiceImpl;
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
@WebMvcTest(value = MedicalFacilityController.class)
public class MedicalFacilityEPTests extends MockConfiguration {

    @MockBean
    private MedicalFacilityServiceImpl medicalFacilityService;

    @Test
    public void selectAllShouldReturnMedicalFacilitiesWhenProperRoleIsSelected() throws Exception {
        when(medicalFacilityService.selectAll())
                .thenReturn(List.of(
                        new MedicalFacility(1L, "San", "Somewhere", 1L, 1L, "123123123"),
                        new MedicalFacility(2L, "Andreas", "Nowhere", 1L, 1L, "123123123")
                ));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select medical facility with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/medicalFacility/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Andreas"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].localization").value("Nowhere"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("San"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].localization").value("Somewhere"));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/medicalFacility/select").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnMedicalFacilitiesWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        when(medicalFacilityService.selectMedicalFacilityById(id))
                .thenReturn(java.util.Optional.of(
                        new MedicalFacility(1L, "San", "Somewhere", 1L, 1L, "123123123")));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById price with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/medicalFacility/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("San"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.localization").value("Somewhere"));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/api/medicalFacility/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void createMedicalFacilitiesShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create medical facility with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(
                                    post("/api/medicalFacility/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(medicalFacilityService, atLeast(1)).create(any(MedicalFacility.class));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(
                                    post("/api/medicalFacility/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
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
    public void deleteMedicalFacilitiesShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete medical facility with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(delete("/api/medicalFacility/delete/1").with(Role))
                            .andExpect(status().isOk());
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(delete("/api/medicalFacility/delete/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updateMedicalFacilitiesShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update medical facility with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(
                                    put("/api/medicalFacility/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(medicalFacilityService, atLeast(1)).update(any(MedicalFacility.class));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(
                                    put("/api/medicalFacility/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
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
