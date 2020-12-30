package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.exception.DeleteObjException;
import com.engineering.thesis.backend.model.PatientMedicalData;
import com.engineering.thesis.backend.repository.PatientMedicalDataRepository;
import com.engineering.thesis.backend.serviceImpl.PatientMedicalDataServiceImpl;
import com.engineering.thesis.backend.testObj.PatientMedicalDatas;
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
public class PatientMedDataCrudTests {

    @Mock(lenient = true)
    private PatientMedicalDataRepository patientMedDataRepository;

    @InjectMocks
    private PatientMedicalDataServiceImpl patientMedDataServiceImpl;

    @Test
    void shouldSavedPatientMedDataSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientMedDataServiceImpl.selectPatientMedicalDataById(PatientMedicalDatas.patientMedicalDataNull.getId()))
                .willReturn(Optional.empty());
        given(patientMedDataRepository.save(PatientMedicalDatas.patientMedicalDataNull)).willAnswer(invocation -> invocation.getArgument(0));
        PatientMedicalData savedMedFac = patientMedDataRepository.save(PatientMedicalDatas.patientMedicalDataNull);
        assertThat(savedMedFac).isNotNull();
        verify(patientMedDataRepository).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePatientMedDataWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientMedDataRepository.findById(PatientMedicalDatas.patientMedicalData1.getId())).willReturn(Optional.of(PatientMedicalDatas.patientMedicalData1));
        assertThrows(CreateObjException.class,() -> patientMedDataServiceImpl.create(PatientMedicalDatas.patientMedicalData1));
        verify(patientMedDataRepository, never()).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldUpdatePatientMedData() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientMedDataRepository.save(PatientMedicalDatas.patientMedicalData1)).willReturn(PatientMedicalDatas.patientMedicalData1);
        final PatientMedicalData expected = patientMedDataServiceImpl.update(PatientMedicalDatas.patientMedicalData1);
        assertThat(expected).isNotNull();
        verify(patientMedDataRepository).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<PatientMedicalData> patientMedicalDatas = new ArrayList();
        patientMedicalDatas.add(PatientMedicalDatas.patientMedicalData1);
        patientMedicalDatas.add(PatientMedicalDatas.patientMedicalData2);
        patientMedicalDatas.add(PatientMedicalDatas.patientMedicalData3);
        given(patientMedDataRepository.findAll()).willReturn(patientMedicalDatas);
        List<PatientMedicalData> expected = patientMedDataServiceImpl.selectAll();
        assertEquals(expected, patientMedicalDatas);
    }

    @Test
    void shouldFindPatientMedDataById(){
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(patientMedDataRepository.findById(id)).willReturn(Optional.of(PatientMedicalDatas.patientMedicalData1));
        final Optional<PatientMedicalData> expected  = patientMedDataServiceImpl.selectPatientMedicalDataById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDeleteWithNonexistentID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        assertThrows(DeleteObjException.class, () -> patientMedDataServiceImpl.deleteById(PatientMedicalDatas.patientMedicalData1.getId()));
        verify(patientMedDataRepository, never()).deleteById(PatientMedicalDatas.patientMedicalData1.getId());
    }
}