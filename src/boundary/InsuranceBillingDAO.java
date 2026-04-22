package boundary;

import db.DatabaseManager;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class InsuranceBillingDAO {

    public static boolean createInvoice(String patientId, double amount, double coverageRate, String description) {
        double insuranceCoverage = Math.round(amount * coverageRate * 100.0) / 100.0;
        double patientPays = Math.round((amount - insuranceCoverage) * 100.0) / 100.0;

        String sql = """
            INSERT INTO BILLING_INVOICE
            (PATIENT_ID, TOTAL_AMOUNT, INSURANCE_COVERAGE, PATIENT_RESPONSIBILITY, SERVICE_DESCRIPTION, STATUS)
            VALUES (?, ?, ?, ?, ?, 'PENDING')
        """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, patientId);
            pst.setDouble(2, amount);
            pst.setDouble(3, insuranceCoverage);
            pst.setDouble(4, patientPays);
            pst.setString(5, description);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void loadInvoices(DefaultTableModel model) {
        model.setRowCount(0);

        String sql = """
            SELECT INVOICE_ID, PATIENT_ID, TOTAL_AMOUNT, INSURANCE_COVERAGE,
                   PATIENT_RESPONSIBILITY, STATUS
            FROM BILLING_INVOICE
            ORDER BY INVOICE_ID DESC
        """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("INVOICE_ID"),
                    rs.getString("PATIENT_ID"),
                    rs.getDouble("TOTAL_AMOUNT"),
                    rs.getDouble("INSURANCE_COVERAGE"),
                    rs.getDouble("PATIENT_RESPONSIBILITY"),
                    rs.getString("STATUS")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean submitClaim(int invoiceId, String patientId) {
        String insertClaim = """
            INSERT INTO INSURANCE_CLAIM (BILLING_ID, PATIENT_ID, SUBMITTED_BY, STATUS)
            VALUES (?, ?, 'System', 'SUBMITTED')
        """;

        String updateInvoice = """
            UPDATE BILLING_INVOICE
            SET STATUS = 'SUBMITTED'
            WHERE INVOICE_ID = ?
        """;

        try (Connection con = DatabaseManager.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement claimPst = con.prepareStatement(insertClaim);
                 PreparedStatement invoicePst = con.prepareStatement(updateInvoice)) {

                claimPst.setInt(1, invoiceId);
                claimPst.setString(2, patientId);
                claimPst.executeUpdate();

                invoicePst.setInt(1, invoiceId);
                invoicePst.executeUpdate();

                con.commit();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateInsurance(String patientId, String policyNumber, String provider) {
        String sql = """
            UPDATE PATIENT_INSURANCE
            SET POLICY_NUMBER = ?, PROVIDER = ?
            WHERE PATIENT_ID = ?
        """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, policyNumber);
            pst.setString(2, provider);
            pst.setString(3, patientId);

            int updated = pst.executeUpdate();

            if (updated == 0) {
                String insertSql = """
                    INSERT INTO PATIENT_INSURANCE (PATIENT_ID, POLICY_NUMBER, PROVIDER)
                    VALUES (?, ?, ?)
                """;

                try (PreparedStatement insertPst = con.prepareStatement(insertSql)) {
                    insertPst.setString(1, patientId);
                    insertPst.setString(2, policyNumber);
                    insertPst.setString(3, provider);
                    insertPst.executeUpdate();
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void loadClaims(DefaultTableModel model) {
        model.setRowCount(0);

        String sql = """
            SELECT CLAIM_ID, BILLING_ID, PATIENT_ID, SUBMITTED_BY, STATUS,
                   SUBMITTED_AT, PROCESSED_AT
            FROM INSURANCE_CLAIM
            ORDER BY CLAIM_ID DESC
        """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("CLAIM_ID"),
                    rs.getInt("BILLING_ID"),
                    rs.getString("PATIENT_ID"),
                    rs.getString("SUBMITTED_BY"),
                    rs.getString("STATUS"),
                    rs.getTimestamp("SUBMITTED_AT"),
                    rs.getTimestamp("PROCESSED_AT")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
