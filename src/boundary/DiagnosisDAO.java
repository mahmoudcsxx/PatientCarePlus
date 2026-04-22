package boundary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class DiagnosisDAO {

    public static boolean saveDiagnosis(String diagnosis, String symptoms, String treatment) {
        String sql = "INSERT INTO DIAGNOSIS (DIAGNOSIS_TEXT, SYMPTOMS, TREATMENT) VALUES (?, ?, ?)";

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/pcplusdb",
                "app",
                "app"
            );

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, diagnosis);
            pst.setString(2, symptoms);
            pst.setString(3, treatment);

            pst.executeUpdate();

            pst.close();
            con.close();

            JOptionPane.showMessageDialog(null, "Saved successfully!");
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            return false;
        }
    }
}