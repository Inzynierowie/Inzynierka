package com.engineering.thesis.backend.ControllerIntegrationTests;

import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.config.jwt.UnauthorizedHandler;
import com.engineering.thesis.backend.controller.AppointmentController;
import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.serviceImpl.AppointmentServiceImpl;
import com.engineering.thesis.backend.serviceImpl.user.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
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

import java.time.LocalDateTime;
import java.util.List;

import static com.engineering.thesis.backend.ControllerIntegrationTests.SecurityMockMvcRequestPostProcessors.RulesMap;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = AppointmentController.class)
class AppointmentEPTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtToken jwtToken;

    @MockBean
    private AppointmentServiceImpl appointmentService;

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
    public void selectAllAppointmentsShouldReturnAppointmentWhenProperRoleIsSelected() throws Exception {
        final User userDoctor1 = new User(1l,"Tom","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final User userDoctor2 = new User(2l,"Tom","Kowalsky","dsadzx2xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor1 = new Doctor(1l, userDoctor1,"Cardiology");
        final Doctor doctor2 = new Doctor(2l, userDoctor2,"Cardiology");
        final User userPatient1 = new User(4l,"Tom","Kowalsky","dsad12ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final User userPatient2 = new User(5l,"Tom","Kowalsky","dsad32ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient1 = new Patient(1l, userPatient1,true);
        final Patient patient2 = new Patient(2l, userPatient2,true);
        when(appointmentService.selectAll())
                .thenReturn(List.of(
                        new Appointment(1l,patient1,doctor1,1000L, LocalDateTime.now()),
                        new Appointment(2l,patient2,doctor2,2000, LocalDateTime.now())
                ));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing select appointment with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].cost").value(2000))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].cost").value(1000));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnAppointmentWhenProperRoleIsSelected() throws Exception {
        final User userDoctor1 = new User(1l,"Tom","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor1 = new Doctor(1l, userDoctor1,"Cardiology");
        final User userPatient1 = new User(2l,"Tom","Kowalsky","dsad12ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient1 = new Patient(1l, userPatient1,true);
        final Long id = 1L;
        when(appointmentService.selectAppointmentById(id))
                .thenReturn(java.util.Optional.of(
                        new Appointment(1l,patient1,doctor1,1000L, LocalDateTime.now())));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectById appointment with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select/1").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.cost").value(1000))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/appointment/select/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void createAppointmentShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing create appointment with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(
                                    post("/api/appointment/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(appointmentService, atLeast(1)).create(any(Appointment.class));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(
                                    post("/api/appointment/create")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 3, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
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
    public void deleteAppointmentShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete appointment with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(delete("/api/appointment/delete/1").with(Role))
                            .andExpect(status().isOk());
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(delete("/api/appointment/delete/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updateAppointmentShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update appointment with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(
                                    put("/api/appointment/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 1, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
                                            .with(Role)
                            )
                            .andExpect(status().isOk());
                    verify(appointmentService, atLeast(1)).update(any(Appointment.class));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(
                                    put("/api/appointment/update")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"id\": 2, \"appointmentDate\":\"2014-04-28T16:00:49.455\", \"cost\":1 , \"patient\": {\"id\": 1}, \"doctor\": {\"id\": 1} }")
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
