package dev.marvin.savingsmanagement;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractTestContainerTest {

    @SuppressWarnings("resource")
    @Container
    protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("password");

    @DynamicPropertySource
    private static void dynamicProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.username", ()-> postgreSQLContainer.getUsername());
        dynamicPropertyRegistry.add("spring.datasource.password", ()-> postgreSQLContainer.getPassword());
    }
}
