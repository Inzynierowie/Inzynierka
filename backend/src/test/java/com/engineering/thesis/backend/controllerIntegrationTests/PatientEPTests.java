package com.engineering.thesis.backend.controllerIntegrationTests;

//We ned to check later why we can't use PriceController directly (only wild card works).

import com.engineering.thesis.backend.controller.*;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.serviceImpl.PatientServiceImpl;
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
@WebMvcTest(value = PatientController.class)
public class PatientEPTests extends MockConfiguration {

    @MockBean
    private PatientServiceImpl patientService;

    @Test
    public void selectAllShouldReturnPatientsWhenProperRoleIsSelected() throws Exception {
        final User user1 = new User(1l, "Tom", "Kowalsky", "dsadsa@osom.com", "1I@wssssdddas", "ROLE_PATIENT", true);
        final User user2 = new User(2l, "Tom", "Kowalsky", "dsadsadsa@osom.com", "1I@wsaaadas", "ROLE_PATIENT", true);
        when(patientService.selectAll())
                .thenReturn(List.of(
                        new Patient(1L, user1, true),
                        new Patient(2L, user2, true)
                ));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select patient with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patient/select").with(Role))
                            .andDo(print())
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].insured").value(true))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].insured").value(true));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patient/select").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnPatientWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        final User user1 = new User(1l, "Tom", "Kowalsky", "dsadsa@osom.com", "1I@wssssdddas", "ROLE_PATIENT", true);
        when(patientService.selectById(id))
                .thenReturn(new Patient(1L, user1, true));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById patient with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patient/select/1").with(Role)
                                    .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.insured").value(true));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patient/select/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updatePatientShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update patient with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(
                                    put("/api/patient/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"user\": { \"id\": 2}, \"insured\": false}")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(patientService, atLeast(1)).update(any(Patient.class));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(
                                    put("/api/patient/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"user\": { \"id\": 2}, \"insured\": false}")
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