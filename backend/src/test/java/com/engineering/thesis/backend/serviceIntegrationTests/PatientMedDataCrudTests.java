package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.PatientMedicalDataRepository;
import com.engineering.thesis.backend.serviceImpl.PatientMedicalDataServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
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
public class PatientMedDataCrudTests {

    @Mock(lenient = true)
    private PatientMedicalDataRepository patientMedDataService;

    @InjectMocks
    private PatientMedicalDataServiceImpl patientMedDataServiceImpl;

    @Test
    void shouldSavedPatientMedDataSuccessFully() {
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1l, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1l, userPatient,true);
        final PatientMedicalData patientMedicalData = new PatientMedicalData(null,patient,doctor,LocalDateTime.now(),"Biopsy","Funny patient LOL like him tho");
        given(patientMedDataServiceImpl.selectPatientMedicalDataById(patientMedicalData.getId()))
                .willReturn(Optional.empty());
        given(patientMedDataService.save(patientMedicalData)).willAnswer(invocation -> invocation.getArgument(0));
        PatientMedicalData savedMedFac = patientMedDataService.save(patientMedicalData);
        assertThat(savedMedFac).isNotNull();
        verify(patientMedDataService).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePatientMedDataWithExistingID() {
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1l, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1l, userPatient,true);
        final PatientMedicalData patientMedicalData = new PatientMedicalData(1L,patient,doctor,LocalDateTime.now(),"Biopsy","Funny patient LOL like him tho");
        given(patientMedDataService.findById(patientMedicalData.getId())).willReturn(Optional.of(patientMedicalData));
        assertThrows(CreateObjException.class,() -> {
            patientMedDataServiceImpl.create(patientMedicalData);
        });
        verify(patientMedDataService, never()).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldUpdatePatientMedData() {
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1l, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1l, userPatient,true);
        final PatientMedicalData patientMedicalData = new PatientMedicalData(1L,patient,doctor,LocalDateTime.now(),"Biopsy","Funny patient LOL like him tho");
        given(patientMedDataService.save(patientMedicalData)).willReturn(patientMedicalData);
        final PatientMedicalData expected = patientMedDataServiceImpl.update(patientMedicalData);
        assertThat(expected).isNotNull();
        verify(patientMedDataService).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldReturnSelectAll() {
        final User userDoctor1 = new User(1l,"Tom","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final User userDoctor2 = new User(2l,"Tom","Kowalsky","dsadzx2xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final User userDoctor3 = new User(3l,"Tom","Kowalsky","dsadzx3xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor1 = new Doctor(1l, userDoctor1,"Cardiology");
        final Doctor doctor2 = new Doctor(2l, userDoctor2,"Cardiology");
        final Doctor doctor3 = new Doctor(3l, userDoctor3,"Cardiology");
        final User userPatient1 = new User(4l,"Tom","Kowalsky","dsad12ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final User userPatient2 = new User(5l,"Tom","Kowalsky","dsad32ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final User userPatient3 = new User(6l,"Tom","Kowalsky","2122asdadsa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient1 = new Patient(1l, userPatient1,true);
        final Patient patient2 = new Patient(2l, userPatient2,true);
        final Patient patient3 = new Patient(3l, userPatient3,true);
        List<PatientMedicalData> patientMedicalDatas = new ArrayList();
        patientMedicalDatas.add(new PatientMedicalData(1L,patient1,doctor1,LocalDateTime.now(),"Biopsy","Funny patient LOL like him tho"));
        patientMedicalDatas.add(new PatientMedicalData(2L,patient2,doctor2,LocalDateTime.now(),"Biopsy","Funny patient LOL like him tho"));
        patientMedicalDatas.add(new PatientMedicalData(3L,patient3,doctor3,LocalDateTime.now(),"Biopsy","Funny patient LOL like him tho"));
        given(patientMedDataService.findAll()).willReturn(patientMedicalDatas);
        List<PatientMedicalData> expected = patientMedDataServiceImpl.selectAll();
        assertEquals(expected, patientMedicalDatas);
    }

    @Test
    void shouldFindPatientMedDataById(){
        final Long id = 1L;
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1l, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(1l, userPatient,true);
        final PatientMedicalData patientMedicalData = new PatientMedicalData(1L, patient, doctor, LocalDateTime.now(), "Biopsy", "Funny patient LOL like him tho");
        given(patientMedDataService.findById(id)).willReturn(Optional.of(patientMedicalData));
        final Optional<PatientMedicalData> expected  = patientMedDataServiceImpl.selectPatientMedicalDataById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldBeDelete() {
        final Long patientMedDataId=1L;
        patientMedDataServiceImpl.deleteById(patientMedDataId);
        patientMedDataServiceImpl.deleteById(patientMedDataId);
        verify(patientMedDataService, times(2)).deleteById(patientMedDataId);
    }

}
