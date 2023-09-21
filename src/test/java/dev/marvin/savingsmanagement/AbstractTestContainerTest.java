package dev.marvin.savingsmanagement;

import com.github.javafaker.Faker;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@Testcontainers
public abstract class AbstractTestContainerTest {

    @BeforeAll
    static void beforeAll() {
        // Create the Flyway instance and point it to the database
        Flyway flyway = Flyway.configure().dataSource(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword()).load();

        // Start the migration
        flyway.migrate();
    }

    @SuppressWarnings("resource")
    @Container
    protected static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("password");

    @DynamicPropertySource
    private static void registerDatasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl());
        dynamicPropertyRegistry.add("spring.datasource.username", () -> postgreSQLContainer.getUsername());
        dynamicPropertyRegistry.add("spring.datasource.password", () -> postgreSQLContainer.getPassword());
    }

    protected static Faker faker() {
        return new Faker();
    }

    protected static DataSource dataSource() {
        return DataSourceBuilder.create()
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword())
                .build();
    }

}
