package controller;

import db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthController {

    public boolean login(String email, String password) throws SQLException {
        initializeAuthTable();

        String sql = "SELECT ID FROM APP_USERS WHERE LOWER(EMAIL) = LOWER(?) AND PASSWORD = ?";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    private void initializeAuthTable() throws SQLException {
        try (Connection con = DatabaseManager.getConnection();
             Statement statement = con.createStatement()) {

            try {
                statement.executeUpdate(
                        "CREATE TABLE APP_USERS ("
                        + "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                        + "NAME VARCHAR(100), "
                        + "EMAIL VARCHAR(100) NOT NULL UNIQUE, "
                        + "PASSWORD VARCHAR(100) NOT NULL, "
                        + "ROLE VARCHAR(20)"
                        + ")"
                );
            } catch (SQLException e) {
                if (!"X0Y32".equals(e.getSQLState())) {
                    throw e;
                }
            }

            insertDefaultUsers(con);
        }
    }

    private void insertDefaultUsers(Connection con) {
        insertDefaultUser(con, "Admin", "admin@test.com", "1234", "ADMIN");
        insertDefaultUser(con, "Doctor", "doctor@test.com", "1234", "DOCTOR");
        insertDefaultUser(con, "Nurse", "nurse@test.com", "1234", "NURSE");
        insertDefaultUser(con, "Receptionist", "reception@test.com", "1234", "RECEPTIONIST");
    }

    private void insertDefaultUser(Connection con, String name, String email, String password, String role) {
        String sql = "INSERT INTO APP_USERS (NAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.setString(4, role);
            pst.executeUpdate();
        } catch (SQLException e) {
            if (!"23505".equals(e.getSQLState())) {
                e.printStackTrace();
            }
        }
    }
}

