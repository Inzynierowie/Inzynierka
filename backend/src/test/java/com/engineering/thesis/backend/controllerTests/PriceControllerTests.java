package com.engineering.thesis.backend.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD ,scripts = "classpath:test_data.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD ,scripts = "classpath:delete_data_after_test.sql")
})

@SpringBootTest
public class PriceControllerTests {
    @Test
    void CreatePrice() throws Exception {

    }

    @Test
    void ShowAllPrice() throws Exception {

    }

    @Test
    void ShowPriceById() throws Exception{

    }

    @Test
    void DeletePrice() throws Exception {

    }
}
