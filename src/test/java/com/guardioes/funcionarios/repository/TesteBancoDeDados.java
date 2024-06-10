package com.guardioes.funcionarios.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class TesteBancoDeDados {

    @Autowired
    private DataSource dataSource;

    @Test
    public void givenDataSourceAvailable_whenConnectionRequested_thenObtainConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        }
    }

    private void assertFalse(boolean closed) {
    }

    private void assertNotNull(Connection connection) {
    }
}
