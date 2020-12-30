package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.DoctorController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.serviceImpl.DoctorServiceImpl;
import com.engineering.thesis.backend.testObj.Doctors;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = DoctorController.class)
public class DoctorEPTests extends MockConfiguration {

    @MockBean
    private DoctorServiceImpl doctorServiceImpl;

    @Test
    public void selectAllShouldReturnDoctorsWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        when(doctorServiceImpl.selectAll())
                .thenReturn(List.of(Doctors.doctor1, Doctors.doctor2));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select doctor with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].specialist").value("Cardiology"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].specialist").value("Cardiology"));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select").with(Role))
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
    public void selectByIdShouldReturnDoctorWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        when(doctorServiceImpl.selectById(id)).thenReturn(Doctors.doctor1);
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById doctor with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select/1").with(Role)
                                    .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.specialist").value("Cardiology"));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select/1").with(Role))
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
    public void updateDoctorShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update doctor with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    put("/api/doctor/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"user\": { \"id\": 2}, \"specialist\": \"Cardiology\"}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(doctorServiceImpl, atLeast(1)).update(any(Doctor.class));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    put("/api/doctor/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"user\": { \"id\": 2}, \"specialist\": \"Cardiology\"}")
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