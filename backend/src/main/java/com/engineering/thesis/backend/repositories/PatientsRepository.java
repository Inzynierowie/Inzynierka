package com.engineering.thesis.backend.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.engineering.thesis.backend.entity.Patients;

import java.util.List;

public interface PatientsRepository extends CrudRepository<Patients, Long> {


    @Query(
            value = "SELECT * FROM Patients",
            nativeQuery = true)
    public List<Patients> getByDescriptionLike(String search);
}