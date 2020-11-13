package com.engineering.thesis.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SqlGroup({
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD ,scripts = "classpath:delete_data_after_test.sql")
})

@SpringBootTest
class BackendApplicationTests {
	
	@Test
	public void initialTest(){
	}
}


	
