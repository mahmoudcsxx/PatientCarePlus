package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DEFAULT_DB_URL = "jdbc:derby://localhost:1527/pcplusdb;create=true"; 
   

    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DEFAULT_DB_URL
                
        );
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
