package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.PatientRepository;
import com.engineering.thesis.backend.serviceImpl.PatientServiceImpl;
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
    private PatientRepository patientService;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    @Test
    void shouldSavedPatientSuccessFully() {
        final User user = new User(1l,"Tom","Kowalsky","dsadsa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(null, user,true);
        given(patientServiceImpl.selectByUserId(patient.getId()))
                .willReturn(Optional.empty());
        given(patientService.save(patient)).willAnswer(invocation -> invocation.getArgument(0));
        Patient savedUser = patientService.save(patient);
        assertThat(savedUser).isNotNull();
        verify(patientService).save(any(Patient.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePatientWithExistingID() {
        final User user = new User(1l,"Tom","Kowalsky","dsadsa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1L, user,true);
        given(patientService.findById(patient.getId())).willReturn(Optional.of(patient));
        assertThrows(CreateObjException.class,() -> {
            patientServiceImpl.create(patient);
        });
        verify(patientService, never()).save(any(Patient.class));
    }

    @Test
    void shouldUpdatePatient() {
        final User user = new User(1l,"Tom","Kowalsky","dsadsa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1L, user,true);
        given(patientService.save(patient)).willReturn(patient);
        final Patient expected = patientServiceImpl.update(patient);
        assertThat(expected).isNotNull();
        verify(patientService).save(any(Patient.class));
    }

    @Test
    void shouldReturnSelectAll() {
        final User user1 = new User(1l,"Tom","Kowalsky","dsadsa@osom.com","1I@wssssdddas","ROLE_PATIENT",true);
        final User user2 = new User(2l,"Tom","Kowalsky","dsadsadsa@osom.com","1I@wsaaadas","ROLE_PATIENT",true);
        final User user3= new User(3l,"Tom","Kowalsky","wqeqeq@osom.com","1I@wsdasss","ROLE_PATIENT",true);
        List<Patient> patients = new ArrayList();
        patients.add(new Patient(1L, user1,true));
        patients.add(new Patient(2L, user2, true));
        patients.add(new Patient(3L, user3,true));
        given(patientService.findAll()).willReturn(patients);
        List<Patient> expected = patientServiceImpl.selectAll();
        assertEquals(expected, patients);
    }

    @Test
    void shouldFindPatientById(){
        final Long id = 1L;
        final User user = new User(1l,"Tom","Kowalsky","dsadsa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1L, user,true);
        given(patientService.findById(id)).willReturn(Optional.of(patient));
        final Optional<Patient> expected  = patientServiceImpl.selectByUserId(id);
        assertThat(expected).isNotNull();
    }
}
