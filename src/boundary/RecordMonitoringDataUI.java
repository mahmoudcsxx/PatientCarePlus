package boundary;

import controller.PatientCareController;
import javax.swing.JOptionPane;

public class RecordMonitoringDataUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RecordMonitoringDataUI.class.getName());
    private final PatientCareController controller = new PatientCareController();

    public RecordMonitoringDataUI() {
        this(null);
    }

    public RecordMonitoringDataUI(String selectedPatientId) {
        initComponents();
        setupRecordMonitoringDataUI(selectedPatientId);
    }

    private void setupRecordMonitoringDataUI(String selectedPatientId) {
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(saveButton);
        patientCombo.addActionListener(e -> clearFields());
        loadPatients(selectedPatientId);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        patientLabel = new javax.swing.JLabel();
        patientCombo = new javax.swing.JComboBox<>();
        bloodPressureLabel = new javax.swing.JLabel();
        bloodPressureField = new javax.swing.JTextField();
        heartRateLabel = new javax.swing.JLabel();
        heartRateField = new javax.swing.JTextField();
        weightLabel = new javax.swing.JLabel();
        weightField = new javax.swing.JTextField();
        spo2Label = new javax.swing.JLabel();
        spo2Field = new javax.swing.JTextField();
        temperatureLabel = new javax.swing.JLabel();
        temperatureField = new javax.swing.JTextField();
        respiratoryRateLabel = new javax.swing.JLabel();
        respiratoryRateField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Record Monitoring Data");
        setMinimumSize(new java.awt.Dimension(636, 520));
        setPreferredSize(new java.awt.Dimension(636, 520));
        setResizable(false);
        getContentPane().setLayout(null);

        headerPanel.setBackground(new java.awt.Color(102, 153, 255));
        headerPanel.setLayout(null);

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Record Monitoring Data");
        headerPanel.add(titleLabel);
        titleLabel.setBounds(25, 20, 310, 32);

        getContentPane().add(headerPanel);
        headerPanel.setBounds(0, 0, 630, 75);

        patientLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        patientLabel.setText("Patient:");
        getContentPane().add(patientLabel);
        patientLabel.setBounds(45, 105, 130, 25);
        getContentPane().add(patientCombo);
        patientCombo.setBounds(200, 102, 360, 30);

        bloodPressureLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bloodPressureLabel.setText("Blood pressure:");
        getContentPane().add(bloodPressureLabel);
        bloodPressureLabel.setBounds(45, 150, 130, 25);
        getContentPane().add(bloodPressureField);
        bloodPressureField.setBounds(200, 147, 360, 30);

        heartRateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        heartRateLabel.setText("Heart rate:");
        getContentPane().add(heartRateLabel);
        heartRateLabel.setBounds(45, 195, 130, 25);
        getContentPane().add(heartRateField);
        heartRateField.setBounds(200, 192, 360, 30);

        weightLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        weightLabel.setText("Weight:");
        getContentPane().add(weightLabel);
        weightLabel.setBounds(45, 240, 130, 25);
        getContentPane().add(weightField);
        weightField.setBounds(200, 237, 360, 30);

        spo2Label.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spo2Label.setText("SpO2:");
        getContentPane().add(spo2Label);
        spo2Label.setBounds(45, 285, 130, 25);
        getContentPane().add(spo2Field);
        spo2Field.setBounds(200, 282, 360, 30);

        temperatureLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        temperatureLabel.setText("Temperature:");
        getContentPane().add(temperatureLabel);
        temperatureLabel.setBounds(45, 330, 130, 25);
        getContentPane().add(temperatureField);
        temperatureField.setBounds(200, 327, 360, 30);

        respiratoryRateLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        respiratoryRateLabel.setText("Respiratory rate:");
        getContentPane().add(respiratoryRateLabel);
        respiratoryRateLabel.setBounds(45, 375, 130, 25);
        getContentPane().add(respiratoryRateField);
        respiratoryRateField.setBounds(200, 372, 360, 30);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        getContentPane().add(cancelButton);
        cancelButton.setBounds(310, 430, 100, 32);

        saveButton.setBackground(new java.awt.Color(102, 153, 255));
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save Monitoring Data");
        saveButton.addActionListener(this::saveButtonActionPerformed);
        getContentPane().add(saveButton);
        saveButton.setBounds(420, 430, 170, 32);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveMonitoringData();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void loadPatients(String selectedPatientId) {
        patientCombo.removeAllItems();

        try {
            for (String patientId : controller.getPatientIds()) {
                patientCombo.addItem(patientId);
            }
        } catch (Exception e) {
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

        clearFields();
    }

    private void saveMonitoringData() {
        Object selectedItem = patientCombo.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(this, "Please select a patient.");
            return;
        }

        try {
            String patientId = selectedItem.toString();
            String bloodPressure = bloodPressureField.getText().trim();
            int heartRate = parseInt(heartRateField, "Heart rate");
            double weight = parseDouble(weightField, "Weight");
            int spo2 = parseInt(spo2Field, "SpO2");
            double temperature = parseDouble(temperatureField, "Temperature");
            int respiratoryRate = parseInt(respiratoryRateField, "Respiratory rate");

            if (bloodPressure.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter blood pressure.");
                bloodPressureField.requestFocus();
                return;
            }

            controller.saveMonitoringData(patientId, bloodPressure, heartRate, weight, spo2, temperature, respiratoryRate);
            JOptionPane.showMessageDialog(this, "Monitoring data saved successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Number", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Could not save monitoring data to Derby.\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private int parseInt(javax.swing.JTextField field, String label) {
        try {
            return Integer.parseInt(field.getText().trim());
        } catch (NumberFormatException e) {
            field.requestFocus();
            throw new NumberFormatException(label + " must be a whole number.");
        }
    }

    private double parseDouble(javax.swing.JTextField field, String label) {
        try {
            return Double.parseDouble(field.getText().trim());
        } catch (NumberFormatException e) {
            field.requestFocus();
            throw new NumberFormatException(label + " must be a number.");
        }
    }

    private void clearFields() {
        bloodPressureField.setText("");
        heartRateField.setText("");
        weightField.setText("");
        spo2Field.setText("");
        temperatureField.setText("");
        respiratoryRateField.setText("");
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
        java.awt.EventQueue.invokeLater(() -> new RecordMonitoringDataUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bloodPressureField;
    private javax.swing.JLabel bloodPressureLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JTextField heartRateField;
    private javax.swing.JLabel heartRateLabel;
    private javax.swing.JComboBox<String> patientCombo;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JTextField respiratoryRateField;
    private javax.swing.JLabel respiratoryRateLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField spo2Field;
    private javax.swing.JLabel spo2Label;
    private javax.swing.JTextField temperatureField;
    private javax.swing.JLabel temperatureLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField weightField;
    private javax.swing.JLabel weightLabel;
    // End of variables declaration//GEN-END:variables
}


