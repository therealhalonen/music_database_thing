package database;

import java.sql.*;

public class Database {
    // Environment variable set in IDE options
    private static final String JDBC_URL = System.getenv("JDBC_DATABASE_URL");

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }
}

