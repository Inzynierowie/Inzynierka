package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.repository.PatientRepository;
import com.engineering.thesis.backend.serviceImpl.PatientServiceImpl;
import com.engineering.thesis.backend.testObj.Patients;
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
public class PatientCruTests {

    @Mock(lenient = true)
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Test
    void shouldSavedPatientSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientRepository.save(Patients.patientNull)).willReturn(Patients.patientNull);
        assertThat(patientRepository.save(Patients.patientNull)).isNotNull();
        verify(patientRepository).save(any(Patient.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePatientWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientRepository.findById(Patients.patient1.getId())).willReturn(Optional.of(Patients.patient1));
        assertThrows(ResourceNotFoundException.class,() -> patientServiceImpl.create(Patients.patient1));
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    void shouldThrowExceptionWhileUpdatePatient() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientRepository.save(Patients.patient1)).willReturn(Patients.patient1);
        assertThrows(ResourceNotFoundException.class, () -> patientServiceImpl.update(Patients.patient1));
        verify(patientRepository, never()).save(any(Patient.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<Patient> patients = new ArrayList<>();
        patients.add(Patients.patient1);
        patients.add(Patients.patient2);
        patients.add(Patients.patient3);
        given(patientRepository.findAll()).willReturn(patients);
        List<Patient> expected = patientServiceImpl.selectAll();
        assertEquals(expected, patients);
    }

    @Test
    void shouldFindPatientById() throws ResourceNotFoundException {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(patientRepository.findById(id)).willReturn(Optional.of(Patients.patient1));
        final Patient expected = patientServiceImpl.selectById(id);
        assertThat(expected).isNotNull();
    }
}