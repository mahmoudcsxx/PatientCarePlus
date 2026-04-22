package controller;

import db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthController {

    public boolean login(String email, String password) {
        initializeAuthTable();

        String sql = "SELECT ID FROM APP_USERS WHERE LOWER(EMAIL) = LOWER(?) AND PASSWORD = ?";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void initializeAuthTable() {
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

            insertDefaultAdmin(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertDefaultAdmin(Connection con) {
        String sql = "INSERT INTO APP_USERS (NAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, "Admin");
            pst.setString(2, "admin@test.com");
            pst.setString(3, "1234");
            pst.setString(4, "ADMIN");
            pst.executeUpdate();
        } catch (SQLException e) {
            if (!"23505".equals(e.getSQLState())) {
                e.printStackTrace();
            }
        }
    }
}

