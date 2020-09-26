package com.engineering.thesis.backend;

import java.util.*;

import com.engineering.thesis.backend.model.Patient;
import com.engineering.thesis.backend.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SqlGroup({
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:insert_patients.sql"),
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:insert_doctors.sql"),
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:insert_pricelist.sql"),
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:insert_meddata.sql"),
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:insert_medicalfacility.sql"),
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:insert_appointments.sql"),
	//remove 'after_method' to save data to db after test
		//  This after_test.sql perform drop table cascade on every table

	//@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD ,scripts = "classpath:after_test.sql")
})

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;
	PatientService patientService;

	//DB Initialization tests
	@Test
	void CheckBasicDataFromAllTables() {
		String[] commands = {
				"select * from patients where id = 1",
				"select * from doctors where id = 1",
				"select * from medical_facility where id = 1",
				"select * from appointments where id = 1",
				"select * from patients_med_data where id = 1",
				"select * from price_list where id = 1"
		};

		for (String command : commands) {
			List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(command);
			System.out.println("*************************************************************");
			System.out.println(resultSet);
			System.out.println("*************************************************************");


		}



		// CRUD test

	}
}


	
