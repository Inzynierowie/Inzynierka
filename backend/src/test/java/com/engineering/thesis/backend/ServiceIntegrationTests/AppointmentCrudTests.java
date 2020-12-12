package com.engineering.thesis.backend.ServiceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.*;
import com.engineering.thesis.backend.repository.AppointmentRepository;
import com.engineering.thesis.backend.repository.MedicalFacilityRepository;
import com.engineering.thesis.backend.serviceImpl.AppointmentServiceImpl;
import com.engineering.thesis.backend.serviceImpl.MedicalFacilityServiceImpl;
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
public class AppointmentCrudTests {

    @Mock(lenient = true)
    private AppointmentRepository appointmentService;

    @InjectMocks
    private AppointmentServiceImpl appointmentServiceImpl;

    @Test
    void shouldSavedAppointmentSuccessFully() {
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(null, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(null, userPatient,true);
        final Appointment appointment = new Appointment(null,patient,doctor,1000L, LocalDateTime.now());
        given(appointmentServiceImpl.selectAppointmentById(appointment.getId()))
                .willReturn(Optional.empty());
        given(appointmentService.save(appointment)).willAnswer(invocation -> invocation.getArgument(0));
        Appointment savedMedFac = appointmentService.save(appointment);
        assertThat(savedMedFac).isNotNull();
        verify(appointmentService).save(any(Appointment.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveAppointmentWithExistingID() {
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(null, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(null, userPatient,true);
        final Appointment appointment = new Appointment(1l,patient,doctor,1000L, LocalDateTime.now());
        given(appointmentService.findById(appointment.getId())).willReturn(Optional.of(appointment));
        assertThrows(CreateObjException.class,() -> {
            appointmentServiceImpl.create(appointment);
        });
        verify(appointmentService, never()).save(any(Appointment.class));
    }

    @Test
    void shouldUpdateAppointment() {
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(null, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(null, userPatient,true);
        final Appointment appointment = new Appointment(1l,patient,doctor,1000L, LocalDateTime.now());
        given(appointmentService.save(appointment)).willReturn(appointment);
        final Appointment expected = appointmentServiceImpl.update(appointment);
        assertThat(expected).isNotNull();
        verify(appointmentService).save(any(Appointment.class));
    }

    @Test
    void shouldReturnSelectAll() {
        final User userDoctor1 = new User(1l,"Tom","Kowalsky","dsadzx1xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final User userDoctor2 = new User(1l,"Tom","Kowalsky","dsadzx2xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final User userDoctor3 = new User(1l,"Tom","Kowalsky","dsadzx3xczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor1 = new Doctor(null, userDoctor1,"Cardiology");
        final Doctor doctor2 = new Doctor(null, userDoctor2,"Cardiology");
        final Doctor doctor3 = new Doctor(null, userDoctor3,"Cardiology");
        final User userPatient1 = new User(2l,"Tom","Kowalsky","dsad12ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final User userPatient2 = new User(2l,"Tom","Kowalsky","dsad32ssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final User userPatient3 = new User(2l,"Tom","Kowalsky","2122asdadsa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient1 = new Patient(null, userPatient1,true);
        final Patient patient2 = new Patient(null, userPatient2,true);
        final Patient patient3 = new Patient(null, userPatient3,true);
        List<Appointment> appointments = new ArrayList();
        appointments.add(new Appointment(1l,patient1,doctor1,1000L, LocalDateTime.now()));
        appointments.add(new Appointment(1l,patient2,doctor2,1000L, LocalDateTime.now()));
        appointments.add(new Appointment(1l,patient3,doctor3,1000L, LocalDateTime.now()));
        given(appointmentService.findAll()).willReturn(appointments);
        List<Appointment> expected = appointmentServiceImpl.selectAll();
        assertEquals(expected, appointments);
    }

    @Test
    void shouldFindAppointmentById(){
        final Long id = 1L;
        final User userDoctor = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(null, userDoctor,"Cardiology");
        final User userPatient = new User(2l,"Tom","Kowalsky","dsadssssa@osom.com","1I@wsdas","ROLE_PATIENT",true);
        final Patient patient = new Patient(null, userPatient,true);
        final Appointment appointment = new Appointment(1l,patient,doctor,1000L, LocalDateTime.now());
        given(appointmentService.findById(id)).willReturn(Optional.of(appointment));
        final Optional<Appointment> expected  = appointmentServiceImpl.selectAppointmentById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldBeDelete() {
        final Long appointmentId=1L;
        appointmentServiceImpl.deleteById(appointmentId);
        appointmentServiceImpl.deleteById(appointmentId);
        verify(appointmentService, times(2)).deleteById(appointmentId);
    }

}
