package com.engineering.thesis.backend.testObj;

import com.engineering.thesis.backend.model.MedicalFacility;

public class MedicalFacilities {

    public static MedicalFacility medicalFacilityNull = MedicalFacility.builder()
            .id(null)
            .name("Test")
            .localization("Somewhere")
            .doctorCount(1L)
            .patientCount(1L)
            .contactNumber("123123123")
            .build();

    public static MedicalFacility medicalFacility1 = MedicalFacility.builder()
            .id(1L)
            .name("Test")
            .localization("Somewhere")
            .doctorCount(1L)
            .patientCount(1L)
            .contactNumber("123123123")
            .build();

    public static MedicalFacility medicalFacility2 = MedicalFacility.builder()
            .id(2L)
            .name("Test")
            .localization("Somewhere")
            .doctorCount(1L)
            .patientCount(1L)
            .contactNumber("123123123")
            .build();

    public static MedicalFacility medicalFacility3 = MedicalFacility.builder()
            .id(3L)
            .name("Test")
            .localization("Somewhere")
            .doctorCount(1L)
            .patientCount(1L)
            .contactNumber("123123123")
            .build();
}
