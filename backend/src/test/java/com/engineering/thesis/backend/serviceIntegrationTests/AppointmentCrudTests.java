package com.engineering.thesis.backend.serviceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.exception.DeleteObjException;
import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.repository.AppointmentRepository;
import com.engineering.thesis.backend.serviceImpl.AppointmentServiceImpl;
import com.engineering.thesis.backend.testObj.Appointments;
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
public class AppointmentCrudTests {

    @Mock(lenient = true)
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentServiceImpl appointmentServiceImpl;

    @Test
    void shouldSavedAppointmentSuccessFully() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(appointmentServiceImpl.selectAppointmentById(Appointments.appointmentNull.getId()))
                .willReturn(Optional.empty());
        given(appointmentRepository.save(Appointments.appointmentNull)).willAnswer(invocation -> invocation.getArgument(0));
        Appointment savedMedFac = appointmentRepository.save(Appointments.appointmentNull);
        assertThat(savedMedFac).isNotNull();
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveAppointmentWithExistingID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(appointmentRepository.findById(Appointments.appointment1.getId())).willReturn(Optional.of(Appointments.appointment1));
        assertThrows(CreateObjException.class, () -> appointmentServiceImpl.create(Appointments.appointment1));
        verify(appointmentRepository, never()).save(any(Appointment.class));
    }

    @Test
    void shouldUpdateAppointment() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        given(appointmentRepository.save(Appointments.appointment1)).willReturn(Appointments.appointment1);
        final Appointment expected = appointmentServiceImpl.update(Appointments.appointment1);
        assertThat(expected).isNotNull();
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void shouldReturnSelectAll() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        List<Appointment> appointments = new ArrayList();
        appointments.add(Appointments.appointment1);
        appointments.add(Appointments.appointment2);
        appointments.add(Appointments.appointment3);
        given(appointmentRepository.findAll()).willReturn(appointments);
        List<Appointment> expected = appointmentServiceImpl.selectAll();
        assertEquals(expected, appointments);
    }

    @Test
    void shouldFindAppointmentById() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        final Long id = 1L;
        given(appointmentRepository.findById(id)).willReturn(Optional.of(Appointments.appointment1));
        final Optional<Appointment> expected = appointmentServiceImpl.selectAppointmentById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldThrowExceptionWhenDeleteWithNonexistentID() {
        System.out.println("Running test -> " + Thread.currentThread().getStackTrace()[1].getMethodName());
        assertThrows(DeleteObjException.class, () -> appointmentServiceImpl.deleteById(Appointments.appointment1.getId()));
        verify(appointmentRepository, never()).deleteById(Appointments.appointment1.getId());
    }
}