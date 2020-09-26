package com.engineering.thesis.backend.repository;

import com.engineering.thesis.backend.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends CrudRepository<Patient,Long> {
}
