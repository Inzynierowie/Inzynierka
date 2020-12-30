package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.MedicalFacilityController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.serviceImpl.MedicalFacilityServiceImpl;
import com.engineering.thesis.backend.testObj.MedicalFacilities;
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
    private MedicalFacilityServiceImpl medicalFacilityServiceImpl;

    @Test
    public void selectAllShouldReturnMedicalFacilitiesWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        when(medicalFacilityServiceImpl.selectAll()).thenReturn(List.of(MedicalFacilities.medicalFacility1, MedicalFacilities.medicalFacility2));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select medical facility with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/medicalFacility/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Test"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].localization").value("Somewhere"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Test"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].localization").value("Somewhere"));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/medicalFacility/select").with(Role))
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests: \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnMedicalFacilitiesWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        when(medicalFacilityServiceImpl.selectMedicalFacilityById(id)).thenReturn(java.util.Optional.of(MedicalFacilities.medicalFacility1));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById price with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/medicalFacility/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.localization").value("Somewhere"));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/api/medicalFacility/1").with(Role))
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests: \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void createMedicalFacilitiesShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create medical facility with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    post("/api/medicalFacility/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(medicalFacilityServiceImpl, atLeast(1)).create(any(MedicalFacility.class));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    post("/api/medicalFacility/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests: \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void deleteMedicalFacilitiesShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete medical facility with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(delete("/api/medicalFacility/delete/1").with(Role))
                            .andExpect(status().isOk());
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(delete("/api/medicalFacility/delete/1").with(Role))
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests: \n");
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updateMedicalFacilitiesShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update medical facility with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    put("/api/medicalFacility/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(medicalFacilityServiceImpl, atLeast(1)).update(any(MedicalFacility.class));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    put("/api/medicalFacility/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Hospital\", \"localization\": \"New York\", \"doctorCount\": 3, \"patientCount\": 4, \"contactNumber\": \"627329649\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isForbidden());
                    System.out.println("Forbidden status for invalid role as expected \n");
                }
            } catch (Exception e) {
                System.out.println("Unexpected exception during tests: \n");
                e.printStackTrace();
            }
        });
    }
}