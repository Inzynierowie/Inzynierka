package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.User;

public class Users {

    public static User userDoctorNull = User.builder()
            .id(null)
            .name("Tom")
            .surname("Kowalsky")
            .email("dsadsadsadsa@osom.com")
            .password("1I@wssssdddas")
            .role("ROLE_DOCTOR")
            .isActive(true)
            .build();

    public static User userDoctor1 = User.builder()
            .id(1L)
            .name("Tom")
            .surname("Kowalsky")
            .email("dsadsadsadsa@osom.com")
            .password("1I@wssssdddas")
            .role("ROLE_DOCTOR")
            .isActive(true)
            .build();

    public static User userDoctor2 = User.builder()
            .id(2L)
            .name("Tom")
            .surname("Kowalsky")
            .email("dsaqqqdsadsa@osom.com")
            .password("1I@wsaaadas")
            .role("ROLE_DOCTOR")
            .isActive(true)
            .build();

    public static User userDoctor3 = User.builder()
            .id(3L)
            .name("Tom")
            .surname("Kowalsky")
            .email("wqeeeeeqeq@osom.com")
            .password("1I@wsdasss")
            .role("ROLE_DOCTOR")
            .isActive(true)
            .build();

    public static User userPatient1 = User.builder()
            .id(4L)
            .name("Hans")
            .surname("Kowalsky")
            .email("wqeeedsadsaeeqeq@osom.com")
            .password("1I@wsdsadsadasss")
            .role("ROLE_PATIENT")
            .isActive(true)
            .build();

    public static User userPatient2 = User.builder()
            .id(5L)
            .name("Hans")
            .surname("Kowalsky")
            .email("wqeeeesdagggeqeq@osom.com")
            .password("1I@wcxzsdasss")
            .role("ROLE_PATIENT")
            .isActive(true)
            .build();

    public static User userPatient3 = User.builder()
            .id(6L)
            .name("Hans")
            .surname("Kowalsky")
            .email("wqeeenbvnvbnheeqeq@osom.com")
            .password("1I@wswwwrrasdasss")
            .role("ROLE_PATIENT")
            .isActive(true)
            .build();
}
