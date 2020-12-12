package com.engineering.thesis.backend.ServiceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.MedicalFacility;
import com.engineering.thesis.backend.model.Price;
import com.engineering.thesis.backend.repository.MedicalFacilityRepository;
import com.engineering.thesis.backend.serviceImpl.MedicalFacilityServiceImpl;
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
    private MedicalFacilityRepository medFacService;

    @InjectMocks
    private MedicalFacilityServiceImpl medFacServiceImpl;

    @Test
    void shouldSavedMedFacSuccessFully() {
        MedicalFacility medicalFacility = new MedicalFacility(1L, "Test", "Somewhere",1L,1L,"123123123");
        given(medFacServiceImpl.selectMedicalFacilityById(medicalFacility.getId()))
                .willReturn(Optional.empty());
        given(medFacService.save(medicalFacility)).willAnswer(invocation -> invocation.getArgument(0));
        MedicalFacility savedMedFac = medFacService.save(medicalFacility);
        assertThat(savedMedFac).isNotNull();
        verify(medFacService).save(any(MedicalFacility.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveMedFacWithExistingID() {
        final MedicalFacility medicalFacility = new MedicalFacility(1L, "Test", "Somewhere",1L,1L,"123123123");
        given(medFacService.findById(medicalFacility.getId())).willReturn(Optional.of(medicalFacility));
        assertThrows(CreateObjException.class,() -> {
            medFacServiceImpl.create(medicalFacility);
        });
        verify(medFacService, never()).save(any(MedicalFacility.class));
    }

    @Test
    void shouldUpdateMedFac() {
        MedicalFacility medicalFacility = new MedicalFacility(1L, "Test", "Somewhere",1L,1L,"123123123");
        given(medFacService.save(medicalFacility)).willReturn(medicalFacility);
        final MedicalFacility expected = medFacServiceImpl.update(medicalFacility);
        assertThat(expected).isNotNull();
        verify(medFacService).save(any(MedicalFacility.class));
    }

    @Test
    void shouldReturnSelectAll() {
        List<MedicalFacility> medicalFacilities = new ArrayList();
        medicalFacilities.add(new MedicalFacility(1L, "Test", "Somewhere",1L,1L,"123123123"));
        medicalFacilities.add(new MedicalFacility(2L, "Test", "Somewhere",1L,1L,"123123123"));
        medicalFacilities.add(new MedicalFacility(3L, "Test", "Somewhere",1L,1L,"123123123"));
        given(medFacService.findAll()).willReturn(medicalFacilities);
        List<MedicalFacility> expected = medFacServiceImpl.selectAll();
        assertEquals(expected, medicalFacilities);
    }

    @Test
    void shouldFindMedFacById(){
        final Long id = 1L;
        MedicalFacility medicalFacility = new MedicalFacility(1L, "Test", "Somewhere",1L,1L,"123123123");
        given(medFacService.findById(id)).willReturn(Optional.of(medicalFacility));
        final Optional<MedicalFacility> expected  = medFacServiceImpl.selectMedicalFacilityById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldBeDelete() {
        final Long medFacId=1L;
        medFacServiceImpl.deleteById(medFacId);
        medFacServiceImpl.deleteById(medFacId);
        verify(medFacService, times(2)).deleteById(medFacId);
    }
}
