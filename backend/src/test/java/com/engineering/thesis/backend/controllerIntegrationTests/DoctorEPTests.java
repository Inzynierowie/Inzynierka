package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.DoctorController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.serviceImpl.DoctorServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = DoctorController.class)
public class DoctorEPTests extends MockConfiguration {

    @MockBean
    private DoctorServiceImpl doctorService;

    @Test
    public void selectAllShouldReturnDoctorsWhenProperRoleIsSelected() throws Exception {
        final User user1 = new User(1l, "Tom", "Kowalsky", "dsadsa@osom.com", "1I@wssssdddas", "ROLE_DOCTOR", true);
        final User user2 = new User(2l, "Tom", "Kowalsky", "dsadsadsa@osom.com", "1I@wsaaadas", "ROLE_DOCTOR", true);
        when(doctorService.selectAll())
                .thenReturn(List.of(
                        new Doctor(1L, user1, "Cardiology"),
                        new Doctor(2L, user2, "Psychology")
                ));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select doctor with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select").with(Role))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].specialist").value("Psychology"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].specialist").value("Cardiology"));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnDoctorWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        final User user1 = new User(1l, "Tom", "Kowalsky", "dsadsa@osom.com", "1I@wssssdddas", "ROLE_DOCTOR", true);
        when(doctorService.selectById(id))
                .thenReturn(new Doctor(1L, user1, "Cardiology"));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById doctor with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select/1").with(Role)
                                    .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.specialist").value("Cardiology"));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/doctor/select/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updateDoctorShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update doctor with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(
                                    put("/api/doctor/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"user\": { \"id\": 2}, \"specialist\": \"Cardiology\"}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(doctorService, atLeast(1)).update(any(Doctor.class));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(
                                    put("/api/doctor/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"user\": { \"id\": 2}, \"specialist\": \"Cardiology\"}")
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
