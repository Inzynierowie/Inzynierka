package com.engineering.thesis.backend.ControllerIntegrationTests;

import com.engineering.thesis.backend.config.jwt.JwtToken;
import com.engineering.thesis.backend.config.jwt.UnauthorizedHandler;
import com.engineering.thesis.backend.controller.UserController;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.UserRepository;
import com.engineering.thesis.backend.serviceImpl.user.UserDetailsServiceImpl;
import com.engineering.thesis.backend.serviceImpl.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.engineering.thesis.backend.ControllerIntegrationTests.SecurityMockMvcRequestPostProcessors.RulesMap;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = UserController.class)
class UserEPTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtToken jwtToken;

    @MockBean
    private  UserRepository userRepository;

    @MockBean
    private UserServiceImpl userService;

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
    public void selectAllUsersShouldReturnPriceWhenProperRoleIsSelected() throws Exception {
        when(userService.findAllUsers())
                .thenReturn(List.of(
                        new User(1l,"Tom","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true),
                        new User(2l,"Tom","Janusz","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true)
                ));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing selectAll user with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/users").with(Role))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].surname").value("Janusz"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Tom"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname").value("Kowalsky"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Tom"));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(MockMvcRequestBuilders.get("/api/users").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void selectByIdShouldReturnUserWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        final User user = new User(1l,"Tom","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        when(userService.findById(id)).thenReturn(ResponseEntity.ok().body(user));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing findUserById with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(get("/api/users/" + id).with(Role)
                            .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Tom"))
                            .andExpect(MockMvcResultMatchers.jsonPath("$.surname").value("Kowalsky"));
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(get("/api/users/" + id).with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void deleteUserShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing delete user with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(delete("/api/users/1").with(Role))
                            .andExpect(status().isOk());
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(delete("/api/users/1").with(Role))
                            .andExpect(status().isForbidden());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void updateUserShouldBePossibleWhenProperRoleIsSelected() throws Exception {
        final Long id = 1L;
        final User user = new User(1l,"Test","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        when(userService.updateUserById(id,user)).thenReturn(ResponseEntity.ok().body(user));
        RulesMap().forEach((name, Role) -> {
            try {
                System.out.println("Performing update user with role: " + name);
                if(name=="Doctor" || name=="Patient"){
                    this.mockMvc
                            .perform(
                                    put("/api/users/1")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Test\", \"surname\": \"Kowalsky\", \"email\": \"dsadzx1xczsa@osom.com\",\"password\": \"1I@wsdas\", \"role\": \"ROLE_DOCTOR\", \"active\": true }")
                                            .with(Role)
                            )
                            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test"))
                            .andExpect(status().isOk());
                }
                if(name=="Invalid"){
                    this.mockMvc
                            .perform(
                                    put("/api/users/1")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{ \"id\": 1, \"name\": \"Test\", \"surname\": \"Kowalsky\", \"email\": \"dsadzx1xczsa@osom.com\",\"password\": \"1I@wsdas\", \"role\": \"ROLE_DOCTOR\", \"active\": true }")
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
