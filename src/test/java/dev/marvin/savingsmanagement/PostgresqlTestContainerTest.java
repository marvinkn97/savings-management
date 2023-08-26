package dev.marvin.savingsmanagement;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostgresqlTestContainerTest extends AbstractTestContainerTest{

    @Test
    void canConnectToPostgresContainer() {
        assertThat(postgreSQLContainer.isCreated()).isTrue();
        assertThat(postgreSQLContainer.isRunning()).isTrue();
    }
}
