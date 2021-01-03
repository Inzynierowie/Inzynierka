package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PatientMedDataCrudTests {

    @Mock(lenient = true)
    private PatientMedicalDataRepository patientMedDataRepository;

    @InjectMocks
    private PatientMedicalDataServiceImpl patientMedDataServiceImpl;

    @Test
    void shouldSavedPatientMedDataSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientMedDataRepository.findById(PatientMedicalDatas.patientMedicalDataNull.getId())).willReturn(Optional.of(PatientMedicalDatas.patientMedicalDataNull));
        given(patientMedDataRepository.save(PatientMedicalDatas.patientMedicalDataNull)).willReturn(PatientMedicalDatas.patientMedicalDataNull);
        assertThat(patientMedDataRepository.save(PatientMedicalDatas.patientMedicalDataNull)).isNotNull();
        verify(patientMedDataRepository).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldThrowExceptionWhenSavePatientMedDataWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientMedDataRepository.findById(PatientMedicalDatas.patientMedicalData1.getId())).willReturn(Optional.of(PatientMedicalDatas.patientMedicalData1));
        assertThrows(ResourceNotFoundException.class,() -> patientMedDataServiceImpl.create(PatientMedicalDatas.patientMedicalData1));
        verify(patientMedDataRepository, never()).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldThrowExceptionWhileUpdatePatientMedData() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(patientMedDataRepository.save(PatientMedicalDatas.patientMedicalData1)).willReturn(PatientMedicalDatas.patientMedicalData1);
        assertThrows(ResourceNotFoundException.class, () -> patientMedDataServiceImpl.update(PatientMedicalDatas.patientMedicalData1));
        verify(patientMedDataRepository, never()).save(any(PatientMedicalData.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<PatientMedicalData> patientMedicalDatas = new ArrayList<>();
        patientMedicalDatas.add(PatientMedicalDatas.patientMedicalData1);
        patientMedicalDatas.add(PatientMedicalDatas.patientMedicalData2);
        patientMedicalDatas.add(PatientMedicalDatas.patientMedicalData3);
        given(patientMedDataRepository.findAll()).willReturn(patientMedicalDatas);
        List<PatientMedicalData> expected = patientMedDataServiceImpl.selectAll();
        assertEquals(expected, patientMedicalDatas);
    }

    @Test
    void shouldFindPatientMedDataById() throws ResourceNotFoundException {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(patientMedDataRepository.findById(id)).willReturn(Optional.of(PatientMedicalDatas.patientMedicalData1));
        final Optional<PatientMedicalData> expected  = patientMedDataServiceImpl.selectPatientMedicalDataById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDeleteWithNonexistentID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        assertThrows(ResourceNotFoundException.class, () -> patientMedDataServiceImpl.deleteById(PatientMedicalDatas.patientMedicalData1.getId()));
        verify(patientMedDataRepository, never()).deleteById(PatientMedicalDatas.patientMedicalData1.getId());
    }
}