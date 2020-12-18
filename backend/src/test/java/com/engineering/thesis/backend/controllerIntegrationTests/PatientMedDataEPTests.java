package com.engineering.thesis.backend.controllerIntegrationTests;

import com.engineering.thesis.backend.controller.PatientMedicalDataController;
import com.engineering.thesis.backend.controllerIntegrationTests.configration.MockConfiguration;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.serviceImpl.PatientMedicalDataServiceImpl;
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

import java.time.LocalDateTime;
import java.util.List;

import static com.engineering.thesis.backend.controllerIntegrationTests.role.RoleProcessors.RulesMap;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = PatientMedicalDataController.class)
public class PatientMedDataEPTests extends MockConfiguration {

    @MockBean
    private PatientMedicalDataServiceImpl patientMedicalDataService;

    @Test
    public void selectAllShouldReturnPatientMedDataWhenProperRoleIsSelected() throws Exception {
        final User userDoctor1 = new User(1l, "Tom", "Kowalsky", "dsadzx1xczsa@osom.com", "1I@wsdas", "ROLE_DOCTOR", true);
        final User userDoctor2 = new User(2l, "Tom", "Kowalsky", "dsadzx2xczsa@osom.com", "1I@wsdas", "ROLE_DOCTOR", true);
        final Doctor doctor1 = new Doctor(1l, userDoctor1, "Cardiology");
        final Doctor doctor2 = new Doctor(2l, userDoctor2, "Cardiology");
        final User userPatient1 = new User(4l, "Tom", "Kowalsky", "dsad12ssssa@osom.com", "1I@wsdas", "ROLE_PATIENT", true);
        final User userPatient2 = new User(5l, "Tom", "Kowalsky", "dsad32ssssa@osom.com", "1I@wsdas", "ROLE_PATIENT", true);
        final Patient patient1 = new Patient(1l, userPatient1, true);
        final Patient patient2 = new Patient(2l, userPatient2, true);
        when(patientMedicalDataService.selectAll())
                .thenReturn(List.of(
                        new PatientMedicalData(1L, patient1, doctor1, LocalDateTime.now(), "Biopsy", "Funny patient LOL like him tho"),
                        new PatientMedicalData(2L, patient2, doctor2, LocalDateTime.now(), "Treatment", "Funny patient LOL like him tho"))
                );
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectAll patientMedData with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patientMedicalData/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patientMedicalData/select").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnPatientMedDataWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        final User userDoctor1 = new User(1l, "Tom", "Kowalsky", "dsadzx1xczsa@osom.com", "1I@wsdas", "ROLE_DOCTOR", true);
        final Doctor doctor1 = new Doctor(1l, userDoctor1, "Cardiology");
        final User userPatient1 = new User(4l, "Tom", "Kowalsky", "dsad12ssssa@osom.com", "1I@wsdas", "ROLE_PATIENT", true);
        final Patient patient1 = new Patient(1l, userPatient1, true);
        when(patientMedicalDataService.selectPatientMedicalDataById(id))
                .thenReturn(java.util.Optional.of(
                        new PatientMedicalData(1L, patient1, doctor1, LocalDateTime.now(), "Biopsy", "Funny patient LOL like him tho")));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById patientMedData with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patientMedicalData/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.medicalProcedure").value("Biopsy"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.additionalNotes").value("Funny patient LOL like him tho"));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/patientMedicalData/select/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void createPatientMedDataShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create patientMedicalData with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(
                                    post("/api/patientMedicalData/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"patient\": { \"id\": 18 }, \"doctor\": { \"id\": 19 }, \"treatmentDate\": \"2000-12-26T00:00:00\", \"medicalProcedure\": \"Cataract surgery\", \"additionalNotes\": \"didnt pay in time\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(patientMedicalDataService, atLeast(1)).create(any(PatientMedicalData.class));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(
                                    post("/api/patientMedicalData/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 2, \"patient\": { \"id\": 18 }, \"doctor\": { \"id\": 19 }, \"treatmentDate\": \"2000-12-26T00:00:00\", \"medicalProcedure\": \"Cataract surgery\", \"additionalNotes\": \"didnt pay in time\" }")
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
    public void deletePatientMedDataShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete PatientMedData with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(delete("/api/patientMedicalData/delete/1").with(Role))
                            .andExpect(status().isOk());
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(delete("/api/patientMedicalData/delete/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updatePatientMedDataShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update patientMedicalData with role: " + name);
                if (name == "Doctor" || name == "Patient") {
                    this.mockMvc
                            .perform(
                                    put("/api/patientMedicalData/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"patient\": { \"id\": 18 }, \"doctor\": { \"id\": 19 }, \"treatmentDate\": \"2000-12-26T00:00:00\", \"medicalProcedure\": \"Cataract surgery\", \"additionalNotes\": \"didnt pay in time\" }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(patientMedicalDataService, atLeast(1)).update(any(PatientMedicalData.class));
                }
                if (name == "Invalid") {
                    this.mockMvc
                            .perform(
                                    put("/api/patientMedicalData/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"patient\": { \"id\": 18 }, \"doctor\": { \"id\": 19 }, \"treatmentDate\": \"2000-12-26T00:00:00\", \"medicalProcedure\": \"Cataract surgery\", \"additionalNotes\": \"didnt pay in time\" }")
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
