package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.AppointmentController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.serviceImpl.AppointmentServiceImpl;
import com.engineering.thesis.backend.testObj.Appointments;
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
@WebMvcTest(value = AppointmentController.class)
class AppointmentEPTests extends MockConfiguration {

    @MockBean
    private AppointmentServiceImpl appointmentServiceImpl;

    @Test
    public void selectAllShouldReturnAppointmentWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        when(appointmentServiceImpl.selectAll()).thenReturn(List.of(Appointments.appointment1, Appointments.appointment2));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select appointment with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].cost").value(1000))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].cost").value(1000));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select").with(Role))
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
    public void selectByIdShouldReturnAppointmentWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        when(appointmentServiceImpl.selectAppointmentById(id)).thenReturn(java.util.Optional.of(Appointments.appointment1));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById appointment with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(1000))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select/1").with(Role))
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
    public void createAppointmentShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create appointment with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    post("/api/appointment/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(appointmentServiceImpl, atLeast(1)).create(any(Appointment.class));
                    System.out.println("All data received properly as expected \n");

                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    post("/api/appointment/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 3, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
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
    public void deleteAppointmentShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete appointment with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(delete("/api/appointment/delete/1").with(Role))
                            .andExpect(status().isOk());
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(delete("/api/appointment/delete/1").with(Role))
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
    public void updateAppointmentShouldBePossibleWhenProperRoleIsSelected() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update appointment with role: " + name);
                if (name.equals("Doctor") || name.equals("Patient")) {
                    this.mockMvc
                            .perform(
                                    put("/api/appointment/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(appointmentServiceImpl, atLeast(1)).update(any(Appointment.class));
                    System.out.println("All data received properly as expected \n");
                }
                if (name.equals("Invalid")) {
                    this.mockMvc
                            .perform(
                                    put("/api/appointment/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 2, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
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