package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DEFAULT_DB_URL = "jdbc:derby://localhost:1527/pcplusdb;create=true";
    private static final String DERBY_CLIENT_DRIVER = "org.apache.derby.client.ClientAutoloadedDriver";
    private static final ClassNotFoundException DRIVER_LOAD_ERROR;

    static {
        ClassNotFoundException loadError = null;
        try {
            Class.forName(DERBY_CLIENT_DRIVER);
        } catch (ClassNotFoundException e) {
            loadError = e;
        }
        DRIVER_LOAD_ERROR = loadError;
    }

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        if (DRIVER_LOAD_ERROR != null) {
            SQLException sqlException = new SQLException(
                    "Derby ClientDriver not found. Make sure libs/derbyclient.jar and libs/derbyshared.jar are in the project libraries.",
                    DRIVER_LOAD_ERROR
            );
            throw sqlException;
        }

        return DriverManager.getConnection(getDatabaseUrl());
    }

    public static boolean testConnection() {
        try (Connection connection = getConnection()) {
            return connection.isValid(2);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void initializeDatabase() throws SQLException {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(
                    "CREATE TABLE DIAGNOSIS ("
                    + "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                    + "DIAGNOSIS_TEXT VARCHAR(255) NOT NULL, "
                    + "SYMPTOMS VARCHAR(1000), "
                    + "TREATMENT VARCHAR(1000), "
                    + "CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                    + ")"
            );
        } catch (SQLException ex) {
            if (!"X0Y32".equals(ex.getSQLState())) {
                throw ex;
            }
        }
    }

    private static String getDatabaseUrl() {
        return System.getProperty("patientcareplus.db.url", DEFAULT_DB_URL);
    }
}
