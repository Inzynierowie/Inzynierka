package com.engineering.thesis.backend.ServiceIntegrationTests;

import com.engineering.thesis.backend.exception.CreateObjException;
import com.engineering.thesis.backend.model.Doctor;
import com.engineering.thesis.backend.model.User;
import com.engineering.thesis.backend.repository.DoctorRepository;
import com.engineering.thesis.backend.serviceImpl.DoctorServiceImpl;
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
    private DoctorRepository doctorService;

    @InjectMocks
    private DoctorServiceImpl doctorServiceImpl;

    @Test
    void shouldSavedDoctorSuccessFully() {
        final User user = new User(1l,"Tom","Kowalsky","dsadzxxczsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(null, user,"Cardiology");
        given(doctorServiceImpl.selectByUserId(doctor.getId()))
                .willReturn(Optional.empty());
        given(doctorService.save(doctor)).willAnswer(invocation -> invocation.getArgument(0));
        Doctor savedUser = doctorService.save(doctor);
        assertThat(savedUser).isNotNull();
        verify(doctorService).save(any(Doctor.class));
    }

    @Test
    void shouldThrowExceptionWhenSaveDoctorWithExistingID() {
        final User user = new User(1l,"Tom","Kowalsky","dsaccdsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1L, user,"Cardiology");
        given(doctorService.findById(doctor.getId())).willReturn(Optional.of(doctor));
        assertThrows(CreateObjException.class,() -> {
            doctorServiceImpl.create(doctor);
        });
        verify(doctorService, never()).save(any(Doctor.class));
    }

    @Test
    void shouldUpdateDoctor() {
        final User user = new User(1l,"Tom","Kowalsky","dsaxxxdsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1L, user,"Cardiology");
        given(doctorService.save(doctor)).willReturn(doctor);
        final Doctor expected = doctorServiceImpl.update(doctor);
        assertThat(expected).isNotNull();
        verify(doctorService).save(any(Doctor.class));
    }

    @Test
    void shouldReturnSelectAll() {
        final User user1 = new User(1l,"Tom","Kowalsky","dsadsadsadsa@osom.com","1I@wssssdddas","ROLE_DOCTOR",true);
        final User user2 = new User(2l,"Tom","Kowalsky","dsaqqqdsadsa@osom.com","1I@wsaaadas","ROLE_DOCTOR",true);
        final User user3= new User(3l,"Tom","Kowalsky","wqeeeeeqeq@osom.com","1I@wsdasss","ROLE_DOCTOR",true);
        List<Doctor> doctors = new ArrayList();
        doctors.add(new Doctor(1L, user1,"Cardiology"));
        doctors.add(new Doctor(2L, user2,"Cardiology"));
        doctors.add(new Doctor(3L, user3,"Cardiology"));
        given(doctorService.findAll()).willReturn(doctors);
        List<Doctor> expected = doctorServiceImpl.selectAll();
        assertEquals(expected, doctors);
    }

    @Test
    void shouldFindDoctorById(){
        final Long id = 1L;
        final User user = new User(1l,"Tom","Kowalsky","dsw4w4adsa@osom.com","1I@wsdas","ROLE_DOCTOR",true);
        final Doctor doctor = new Doctor(1L, user,"Cardiology");
        given(doctorService.findById(id)).willReturn(Optional.of(doctor));
        final Optional<Doctor> expected  = doctorServiceImpl.selectByUserId(id);
        assertThat(expected).isNotNull();
    }
}
