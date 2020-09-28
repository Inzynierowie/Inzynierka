package com.engineering.thesis.backend.serviceImpl;

import com.engineering.thesis.backend.model.Appointment;
import com.engineering.thesis.backend.repository.AppointmentRepository;
import com.engineering.thesis.backend.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public void create(Appointment appointment){
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> selectAll(){
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment selectAppointmentById(Long id){
        Appointment appointment = appointmentRepository.findById(id).get();
        return appointment;
    }
}