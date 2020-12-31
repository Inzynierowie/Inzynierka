package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MedicalFacilityCrudTests {

    @Mock(lenient = true)
    private MedicalFacilityRepository medFacRepository;

    @InjectMocks
    private MedicalFacilityServiceImpl medFacServiceImpl;

    @Test
    void shouldSavedMedFacSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(medFacRepository.findById(MedicalFacilities.medicalFacilityNull.getId())).willReturn(Optional.of(MedicalFacilities.medicalFacilityNull));
        given(medFacRepository.save(MedicalFacilities.medicalFacilityNull)).willReturn(MedicalFacilities.medicalFacilityNull);
        assertThat(medFacRepository.save(MedicalFacilities.medicalFacilityNull)).isNotNull();
        verify(medFacRepository).save(any(MedicalFacility.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveMedFacWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(medFacRepository.findById(MedicalFacilities.medicalFacility1.getId())).willReturn(Optional.of(MedicalFacilities.medicalFacility1));
        assertThrows(ResourceNotFoundException.class,() -> medFacServiceImpl.create(MedicalFacilities.medicalFacility1));
        verify(medFacRepository, never()).save(any(MedicalFacility.class));
    }

    @Test
    void shouldThrowExceptionWhileUpdateMedFac() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(medFacRepository.save(MedicalFacilities.medicalFacility1)).willReturn(MedicalFacilities.medicalFacility1);
        assertThrows(ResourceNotFoundException.class, () -> medFacServiceImpl.update(MedicalFacilities.medicalFacility1));
        verify(medFacRepository, never()).save(any(MedicalFacility.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<MedicalFacility> medicalFacilities = new ArrayList<>();
        medicalFacilities.add(MedicalFacilities.medicalFacility1);
        medicalFacilities.add(MedicalFacilities.medicalFacility2);
        medicalFacilities.add(MedicalFacilities.medicalFacility3);
        given(medFacRepository.findAll()).willReturn(medicalFacilities);
        List<MedicalFacility> expected = medFacServiceImpl.selectAll();
        assertEquals(expected, medicalFacilities);
    }

    @Test
    void shouldFindMedFacById() throws ResourceNotFoundException {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(medFacRepository.findById(id)).willReturn(Optional.of(MedicalFacilities.medicalFacility1));
        final Optional<MedicalFacility> expected  = medFacServiceImpl.selectMedicalFacilityById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDeleteWithNonexistentID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        assertThrows(ResourceNotFoundException.class, () -> medFacServiceImpl.deleteById(MedicalFacilities.medicalFacility1.getId()));
        verify(medFacRepository, never()).deleteById(MedicalFacilities.medicalFacility1.getId());
    }
}