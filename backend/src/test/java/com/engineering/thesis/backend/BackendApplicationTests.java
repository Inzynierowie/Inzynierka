package com.engineering.thesis.backend;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SqlGroup({
		//First delete is required due to preload of data.sql (we don't want to perform work on it)
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:delete_data_after_test.sql"),
		@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:test_data.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD ,scripts = "classpath:delete_data_after_test.sql")
})

@SpringBootTest
class BackendApplicationTests {
	@Test
	public void initialTest(){

	}
}


	
