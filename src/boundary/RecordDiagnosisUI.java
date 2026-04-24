package boundary;

import db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class RecordDiagnosisUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecordDiagnosisUI.class.getName());

    public RecordDiagnosisUI() {
        this(null);
    }

    public RecordDiagnosisUI(String selectedPatientId) {
        initComponents();
        setupRecordDiagnosisUI(selectedPatientId);
    }

    private void setupRecordDiagnosisUI(String selectedPatientId) {
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(saveButton);
        patientCombo.addActionListener(e -> loadMedicalHistory());
        loadPatients(selectedPatientId);
        clearDiagnosisFields();
        loadMedicalHistory();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        patientLabel = new javax.swing.JLabel();
        patientCombo = new javax.swing.JComboBox<>();
        historyLabel = new javax.swing.JLabel();
        historyScrollPane = new javax.swing.JScrollPane();
        historyArea = new javax.swing.JTextArea();
        diagnosisLabel = new javax.swing.JLabel();
        diagnosisField = new javax.swing.JTextField();
        symptomsLabel = new javax.swing.JLabel();
        symptomsScrollPane = new javax.swing.JScrollPane();
        symptomsArea = new javax.swing.JTextArea();
        treatmentLabel = new javax.swing.JLabel();
        treatmentScrollPane = new javax.swing.JScrollPane();
        treatmentArea = new javax.swing.JTextArea();
        saveButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Record Diagnosis");
        setMinimumSize(new java.awt.Dimension(760, 680));
        setPreferredSize(new java.awt.Dimension(760, 680));
        setResizable(false);
        getContentPane().setLayout(null);

        headerPanel.setBackground(new java.awt.Color(102, 153, 255));
        headerPanel.setLayout(null);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Record Diagnosis");
        headerPanel.add(titleLabel);
        titleLabel.setBounds(28, 18, 300, 35);

        getContentPane().add(headerPanel);
        headerPanel.setBounds(0, 0, 760, 75);

        patientLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        patientLabel.setText("Patient:");
        getContentPane().add(patientLabel);
        patientLabel.setBounds(35, 100, 120, 25);
        getContentPane().add(patientCombo);
        patientCombo.setBounds(165, 98, 520, 30);

        historyLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        historyLabel.setForeground(new java.awt.Color(45, 91, 160));
        historyLabel.setText("Medical history (read-only)");
        getContentPane().add(historyLabel);
        historyLabel.setBounds(35, 150, 250, 24);

        historyArea.setEditable(false);
        historyArea.setColumns(20);
        historyArea.setLineWrap(true);
        historyArea.setRows(5);
        historyArea.setWrapStyleWord(true);
        historyScrollPane.setViewportView(historyArea);

        getContentPane().add(historyScrollPane);
        historyScrollPane.setBounds(35, 180, 650, 160);

        diagnosisLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        diagnosisLabel.setText("Diagnosis:");
        getContentPane().add(diagnosisLabel);
        diagnosisLabel.setBounds(35, 365, 120, 25);
        getContentPane().add(diagnosisField);
        diagnosisField.setBounds(165, 362, 520, 30);

        symptomsLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        symptomsLabel.setText("Symptoms:");
        getContentPane().add(symptomsLabel);
        symptomsLabel.setBounds(35, 410, 120, 25);

        symptomsArea.setColumns(20);
        symptomsArea.setLineWrap(true);
        symptomsArea.setRows(5);
        symptomsArea.setWrapStyleWord(true);
        symptomsScrollPane.setViewportView(symptomsArea);

        getContentPane().add(symptomsScrollPane);
        symptomsScrollPane.setBounds(165, 407, 520, 65);

        treatmentLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        treatmentLabel.setText("Treatment plan:");
        getContentPane().add(treatmentLabel);
        treatmentLabel.setBounds(35, 495, 120, 25);

        treatmentArea.setColumns(20);
        treatmentArea.setLineWrap(true);
        treatmentArea.setRows(5);
        treatmentArea.setWrapStyleWord(true);
        treatmentScrollPane.setViewportView(treatmentArea);

        getContentPane().add(treatmentScrollPane);
        treatmentScrollPane.setBounds(165, 492, 520, 75);

        saveButton.setBackground(new java.awt.Color(102, 153, 255));
        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save Diagnosis");
        saveButton.addActionListener(this::saveButtonActionPerformed);
        getContentPane().add(saveButton);
        saveButton.setBounds(300, 595, 150, 34);

        clearButton.setText("Clear");
        clearButton.addActionListener(this::clearButtonActionPerformed);
        getContentPane().add(clearButton);
        clearButton.setBounds(465, 595, 95, 34);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        getContentPane().add(cancelButton);
        cancelButton.setBounds(575, 595, 110, 34);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveDiagnosis();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearDiagnosisFields();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void loadPatients(String selectedPatientId) {
        patientCombo.removeAllItems();

        try {
            DiagnosisDAO.initializeDiagnosisTable();
            String sql = """
                    SELECT PATIENT_ID FROM PATIENT
                    UNION
                    SELECT PATIENT_ID FROM PATIENT_INSURANCE
                    ORDER BY PATIENT_ID
                    """;

            try (Connection con = DatabaseManager.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    patientCombo.addItem(rs.getString("PATIENT_ID"));
                }
            } catch (SQLException e) {
                if (!"42X05".equals(e.getSQLState())) {
                    throw e;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not load patients from Derby.");
        }

        if (patientCombo.getItemCount() == 0) {
            patientCombo.addItem("P001");
            patientCombo.addItem("P002");
            patientCombo.addItem("P003");
        }

        if (selectedPatientId != null && !selectedPatientId.isBlank()) {
            patientCombo.setSelectedItem(selectedPatientId);
        }
    }

    private void saveDiagnosis() {
        Object selectedPatient = patientCombo.getSelectedItem();
        if (selectedPatient == null) {
            JOptionPane.showMessageDialog(this, "Please select a patient.");
            return;
        }

        String diagnosis = diagnosisField.getText().trim();
        String symptoms = symptomsArea.getText().trim();
        String treatment = treatmentArea.getText().trim();

        if (diagnosis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the diagnosis.");
            diagnosisField.requestFocus();
            return;
        }

        try {
            DiagnosisDAO.saveDiagnosis(selectedPatient.toString(), diagnosis, symptoms, treatment);
            JOptionPane.showMessageDialog(this, "Diagnosis saved successfully.");
            clearDiagnosisFields();
            loadMedicalHistory();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Could not save diagnosis to Derby.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadMedicalHistory() {
        Object selectedPatient = patientCombo.getSelectedItem();
        if (selectedPatient == null) {
            historyArea.setText("Select a patient to view diagnosis history.");
            return;
        }

        try {
            historyArea.setText(DiagnosisDAO.getDiagnosisHistory(selectedPatient.toString()));
            historyArea.setCaretPosition(0);
        } catch (SQLException e) {
            e.printStackTrace();
            historyArea.setText("Could not load medical history from Derby.");
        }
    }

    private void clearDiagnosisFields() {
        diagnosisField.setText("");
        symptomsArea.setText("");
        treatmentArea.setText("");
        diagnosisField.requestFocus();
    }

    private static class DiagnosisDAO {

        private static void initializeDiagnosisTable() throws SQLException {
            try (Connection con = DatabaseManager.getConnection();
                 Statement statement = con.createStatement()) {

                try {
                    statement.executeUpdate(
                            "CREATE TABLE DIAGNOSIS ("
                            + "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                            + "PATIENT_ID VARCHAR(50), "
                            + "DIAGNOSIS_TEXT VARCHAR(255) NOT NULL, "
                            + "SYMPTOMS VARCHAR(1000), "
                            + "TREATMENT VARCHAR(1000), "
                            + "CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                            + ")"
                    );
                } catch (SQLException e) {
                    if (!"X0Y32".equals(e.getSQLState())) {
                        throw e;
                    }
                }

                ensureColumn(statement, "PATIENT_ID VARCHAR(50)");
                ensureColumn(statement, "CREATED_AT TIMESTAMP");
            }
        }

        private static void ensureColumn(Statement statement, String columnDefinition) throws SQLException {
            try {
                statement.executeUpdate("ALTER TABLE DIAGNOSIS ADD COLUMN " + columnDefinition);
            } catch (SQLException e) {
                String sqlState = e.getSQLState();
                if (!"X0Y32".equals(sqlState) && !"42X14".equals(sqlState)) {
                    throw e;
                }
            }
        }

        private static void saveDiagnosis(String patientId, String diagnosis, String symptoms, String treatment) throws SQLException {
            initializeDiagnosisTable();

            String sql = """
                    INSERT INTO DIAGNOSIS (PATIENT_ID, DIAGNOSIS_TEXT, SYMPTOMS, TREATMENT, CREATED_AT)
                    VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)
                    """;

            try (Connection con = DatabaseManager.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, patientId);
                pst.setString(2, diagnosis);
                setNullableString(pst, 3, symptoms);
                setNullableString(pst, 4, treatment);
                pst.executeUpdate();
            }
        }

        private static String getDiagnosisHistory(String patientId) throws SQLException {
            initializeDiagnosisTable();

            String sql = """
                    SELECT DIAGNOSIS_TEXT, SYMPTOMS, TREATMENT, CREATED_AT
                    FROM DIAGNOSIS
                    WHERE PATIENT_ID = ?
                    ORDER BY ID DESC
                    """;
            StringBuilder history = new StringBuilder();

            try (Connection con = DatabaseManager.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql)) {

                pst.setString(1, patientId);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        java.sql.Timestamp createdAt = rs.getTimestamp("CREATED_AT");

                        history.append("Diagnosis: ")
                                .append(rs.getString("DIAGNOSIS_TEXT"))
                                .append("\nSymptoms: ")
                                .append(defaultText(rs.getString("SYMPTOMS")))
                                .append("\nTreatment: ")
                                .append(defaultText(rs.getString("TREATMENT")))
                                .append("\nDate: ")
                                .append(createdAt == null ? "Unknown" : createdAt)
                                .append("\n-----------------------------\n");
                    }
                }
            }

            return history.length() == 0 ? "No diagnosis history for this patient yet." : history.toString();
        }

        private static void setNullableString(PreparedStatement pst, int index, String value) throws SQLException {
            if (value == null || value.trim().isEmpty()) {
                pst.setString(index, null);
            } else {
                pst.setString(index, value.trim());
            }
        }

        private static String defaultText(String value) {
            return value == null ? "" : value;
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RecordDiagnosisUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField diagnosisField;
    private javax.swing.JLabel diagnosisLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextArea historyArea;
    private javax.swing.JLabel historyLabel;
    private javax.swing.JScrollPane historyScrollPane;
    private javax.swing.JComboBox<String> patientCombo;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextArea symptomsArea;
    private javax.swing.JLabel symptomsLabel;
    private javax.swing.JScrollPane symptomsScrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextArea treatmentArea;
    private javax.swing.JLabel treatmentLabel;
    private javax.swing.JScrollPane treatmentScrollPane;
    // End of variables declaration//GEN-END:variables
}
