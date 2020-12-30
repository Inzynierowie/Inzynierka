package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.exception.DeleteObjException;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.repository.MedicalFacilityRepository;
import com.engineering.thesis.backend.serviceImpl.MedicalFacilityServiceImpl;
import com.engineering.thesis.backend.testObj.MedicalFacilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicalFacilityCrudTests {

    @Mock(lenient = true)
    private MedicalFacilityRepository medFacRepository;

    @InjectMocks
    private MedicalFacilityServiceImpl medFacServiceImpl;

    @Test
    void shouldSavedMedFacSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(medFacServiceImpl.selectMedicalFacilityById(MedicalFacilities.medicalFacilityNull.getId()))
                .willReturn(Optional.empty());
        given(medFacRepository.save(MedicalFacilities.medicalFacilityNull)).willAnswer(invocation -> invocation.getArgument(0));
        MedicalFacility savedMedFac = medFacRepository.save(MedicalFacilities.medicalFacilityNull);
        assertThat(savedMedFac).isNotNull();
        verify(medFacRepository).save(any(MedicalFacility.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveMedFacWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(medFacRepository.findById(MedicalFacilities.medicalFacility1.getId())).willReturn(Optional.of(MedicalFacilities.medicalFacility1));
        assertThrows(CreateObjException.class,() -> medFacServiceImpl.create(MedicalFacilities.medicalFacility1));
        verify(medFacRepository, never()).save(any(MedicalFacility.class));
    }

    @Test
    void shouldUpdateMedFac() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(medFacRepository.save(MedicalFacilities.medicalFacility1)).willReturn(MedicalFacilities.medicalFacility1);
        final MedicalFacility expected = medFacServiceImpl.update(MedicalFacilities.medicalFacility1);
        assertThat(expected).isNotNull();
        verify(medFacRepository).save(any(MedicalFacility.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<MedicalFacility> medicalFacilities = new ArrayList();
        medicalFacilities.add(MedicalFacilities.medicalFacility1);
        medicalFacilities.add(MedicalFacilities.medicalFacility2);
        medicalFacilities.add(MedicalFacilities.medicalFacility3);
        given(medFacRepository.findAll()).willReturn(medicalFacilities);
        List<MedicalFacility> expected = medFacServiceImpl.selectAll();
        assertEquals(expected, medicalFacilities);
    }

    @Test
    void shouldFindMedFacById(){
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(medFacRepository.findById(id)).willReturn(Optional.of(MedicalFacilities.medicalFacility1));
        final Optional<MedicalFacility> expected  = medFacServiceImpl.selectMedicalFacilityById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDeleteWithNonexistentID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        assertThrows(DeleteObjException.class, () -> medFacServiceImpl.deleteById(MedicalFacilities.medicalFacility1.getId()));
        verify(medFacRepository, never()).deleteById(MedicalFacilities.medicalFacility1.getId());
    }
}