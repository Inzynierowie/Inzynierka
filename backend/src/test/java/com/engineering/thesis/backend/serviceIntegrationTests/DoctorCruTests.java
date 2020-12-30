package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.repository.DoctorRepository;
import com.engineering.thesis.backend.serviceImpl.DoctorServiceImpl;
import com.engineering.thesis.backend.testObj.Doctors;
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
public class DoctorCruTests {

    @Mock(lenient = true)
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    @Test
    void shouldSavedDoctorSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(doctorServiceImpl.selectByUserId(Doctors.doctorNull.getId()))
                .willReturn(Optional.empty());
        given(doctorRepository.save(Doctors.doctorNull)).willAnswer(invocation -> invocation.getArgument(0));
        Doctor savedUser = doctorRepository.save(Doctors.doctorNull);
        assertThat(savedUser).isNotNull();
        verify(doctorRepository).save(any(Doctor.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveDoctorWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(doctorRepository.findById(Doctors.doctor1.getId())).willReturn(Optional.of(Doctors.doctor1));
        assertThrows(CreateObjException.class,() -> doctorServiceImpl.create(Doctors.doctor1));
        verify(doctorRepository, never()).save(any(Doctor.class));
    }

    @Test
    void shouldUpdateDoctor() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(doctorRepository.save(Doctors.doctor1)).willReturn(Doctors.doctor1);
        final Doctor expected = doctorServiceImpl.update(Doctors.doctor1);
        assertThat(expected).isNotNull();
        verify(doctorRepository).save(any(Doctor.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<Doctor> doctors = new ArrayList();
        doctors.add(Doctors.doctor1);
        doctors.add(Doctors.doctor2);
        doctors.add(Doctors.doctor3);
        given(doctorRepository.findAll()).willReturn(doctors);
        List<Doctor> expected = doctorServiceImpl.selectAll();
        assertEquals(expected, doctors);
    }

    @Test
    void shouldFindDoctorById(){
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(doctorRepository.findById(id)).willReturn(Optional.of(Doctors.doctor1));
        final Optional<Doctor> expected  = doctorServiceImpl.selectByUserId(id);
        assertThat(expected).isNotNull();
    }
}