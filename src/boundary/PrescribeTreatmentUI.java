package boundary;

import db.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class PrescribeTreatmentUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PrescribeTreatmentUI.class.getName());

  
    public PrescribeTreatmentUI() {
        initComponents();
        setupPrescribeTreatmentUI();
    }

    private void setupPrescribeTreatmentUI() {
        patientCombo.removeAllItems();
        patientCombo.addItem("P001");
        patientCombo.addItem("P002");
        patientCombo.addItem("P003");
        refreshPrescriptions();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        closeBtn = new javax.swing.JButton();
        formPanel = new javax.swing.JPanel();
        patientLabel = new javax.swing.JLabel();
        medicationLabel = new javax.swing.JLabel();
        dosageLabel = new javax.swing.JLabel();
        followUpLabel = new javax.swing.JLabel();
        treatmentLabel = new javax.swing.JLabel();
        notesLabel = new javax.swing.JLabel();
        patientCombo = new javax.swing.JComboBox<>();
        medicationField = new javax.swing.JTextField();
        dosageField = new javax.swing.JTextField();
        followUpField = new javax.swing.JTextField();
        treatmentScrollPane = new javax.swing.JScrollPane();
        treatmentArea = new javax.swing.JTextArea();
        notesScrollPane = new javax.swing.JScrollPane();
        notesArea = new javax.swing.JTextArea();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        tableScrollPane = new javax.swing.JScrollPane();
        prescriptionsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Prescribe Treatment");
        setPreferredSize(new java.awt.Dimension(850, 650));
        setSize(new java.awt.Dimension(850, 650));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        headerPanel.setBackground(new java.awt.Color(102, 153, 255));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Prescribe Treatment");

        closeBtn.setBackground(new java.awt.Color(255, 51, 51));
        closeBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        closeBtn.setForeground(new java.awt.Color(255, 255, 255));
        closeBtn.setText("Close");
        closeBtn.addActionListener(this::closeBtnActionPerformed);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
                .addComponent(closeBtn)
                .addGap(24, 24, 24))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel)
                    .addComponent(closeBtn))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        formPanel.setBackground(new java.awt.Color(255, 255, 255));
        formPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Treatment Details"));

        patientLabel.setText("Patient:");

        medicationLabel.setText("Medication:");

        dosageLabel.setText("Dosage:");

        followUpLabel.setText("Follow-up Date:");

        treatmentLabel.setText("Treatment Plan:");

        notesLabel.setText("Notes:");

        patientCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "P001", "P002", "P003" }));

        treatmentArea.setColumns(20);
        treatmentArea.setRows(4);
        treatmentScrollPane.setViewportView(treatmentArea);

        notesArea.setColumns(20);
        notesArea.setRows(3);
        notesScrollPane.setViewportView(notesArea);

        saveBtn.setText("Save Prescription");
        saveBtn.addActionListener(this::saveBtnActionPerformed);

        clearBtn.setText("Clear");
        clearBtn.addActionListener(this::clearBtnActionPerformed);

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(this::refreshBtnActionPerformed);

        javax.swing.GroupLayout formPanelLayout = new javax.swing.GroupLayout(formPanel);
        formPanel.setLayout(formPanelLayout);
        formPanelLayout.setHorizontalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientLabel)
                    .addComponent(medicationLabel)
                    .addComponent(dosageLabel)
                    .addComponent(followUpLabel)
                    .addComponent(treatmentLabel)
                    .addComponent(notesLabel))
                .addGap(28, 28, 28)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patientCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medicationField)
                    .addComponent(dosageField)
                    .addComponent(followUpField)
                    .addComponent(treatmentScrollPane)
                    .addComponent(notesScrollPane)
                    .addGroup(formPanelLayout.createSequentialGroup()
                        .addComponent(saveBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(clearBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(refreshBtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        formPanelLayout.setVerticalGroup(
            formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(patientLabel)
                    .addComponent(patientCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(medicationLabel)
                    .addComponent(medicationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dosageLabel)
                    .addComponent(dosageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(followUpLabel)
                    .addComponent(followUpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(treatmentLabel)
                    .addComponent(treatmentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notesLabel)
                    .addComponent(notesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(formPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(clearBtn)
                    .addComponent(refreshBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        prescriptionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Patient", "Medication", "Dosage", "Follow-up", "Created At"
            }
        ));
        tableScrollPane.setViewportView(prescriptionsTable);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        savePrescription();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearForm();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        refreshPrescriptions();
    }//GEN-LAST:event_refreshBtnActionPerformed

    private void savePrescription() {
        String patientId = patientCombo.getSelectedItem().toString();
        String medication = medicationField.getText().trim();
        String dosage = dosageField.getText().trim();
        String followUpDate = followUpField.getText().trim();
        String treatmentPlan = treatmentArea.getText().trim();
        String notes = notesArea.getText().trim();

        if (medication.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the medication name.");
            medicationField.requestFocus();
            return;
        }

        if (savePrescriptionToDatabase(patientId, medication, dosage, treatmentPlan, followUpDate, notes)) {
            JOptionPane.showMessageDialog(this, "Prescription saved successfully.");
            clearForm();
            refreshPrescriptions();
        } else {
            JOptionPane.showMessageDialog(this, "Could not save prescription. Please check the Derby connection.");
        }
    }

    private boolean savePrescriptionToDatabase(String patientId, String medication, String dosage,
                                               String treatmentPlan, String followUpDate, String notes) {
        String sql = "INSERT INTO TREATMENT_PRESCRIPTION "
                + "(PATIENT_ID, MEDICATION, DOSAGE, TREATMENT_PLAN, FOLLOW_UP_DATE, NOTES) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            initializePrescriptionTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, patientId);
            pst.setString(2, medication);
            pst.setString(3, dosage);
            pst.setString(4, treatmentPlan);
            pst.setString(5, followUpDate);
            pst.setString(6, notes);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void refreshPrescriptions() {
        DefaultTableModel model = (DefaultTableModel) prescriptionsTable.getModel();
        model.setRowCount(0);

        try {
            initializePrescriptionTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String sql = "SELECT ID, PATIENT_ID, MEDICATION, DOSAGE, FOLLOW_UP_DATE, CREATED_AT "
                + "FROM TREATMENT_PRESCRIPTION ORDER BY ID DESC";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("ID"),
                    rs.getString("PATIENT_ID"),
                    rs.getString("MEDICATION"),
                    rs.getString("DOSAGE"),
                    rs.getString("FOLLOW_UP_DATE"),
                    rs.getTimestamp("CREATED_AT")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializePrescriptionTable() throws SQLException {
        try (Connection con = DatabaseManager.getConnection();
             Statement statement = con.createStatement()) {

            try {
                statement.executeUpdate(
                        "CREATE TABLE TREATMENT_PRESCRIPTION ("
                        + "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                        + "PATIENT_ID VARCHAR(50) NOT NULL, "
                        + "MEDICATION VARCHAR(255) NOT NULL, "
                        + "DOSAGE VARCHAR(100), "
                        + "TREATMENT_PLAN VARCHAR(1000), "
                        + "FOLLOW_UP_DATE VARCHAR(30), "
                        + "NOTES VARCHAR(1000), "
                        + "CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                        + ")"
                );
            } catch (SQLException e) {
                if (!"X0Y32".equals(e.getSQLState())) {
                    throw e;
                }
            }
        }
    }

    private void clearForm() {
        medicationField.setText("");
        dosageField.setText("");
        followUpField.setText("");
        treatmentArea.setText("");
        notesArea.setText("");
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

        java.awt.EventQueue.invokeLater(() -> new PrescribeTreatmentUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JTextField dosageField;
    private javax.swing.JLabel dosageLabel;
    private javax.swing.JTextField followUpField;
    private javax.swing.JLabel followUpLabel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField medicationField;
    private javax.swing.JLabel medicationLabel;
    private javax.swing.JTextArea notesArea;
    private javax.swing.JLabel notesLabel;
    private javax.swing.JScrollPane notesScrollPane;
    private javax.swing.JComboBox<String> patientCombo;
    private javax.swing.JLabel patientLabel;
    private javax.swing.JTable prescriptionsTable;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextArea treatmentArea;
    private javax.swing.JLabel treatmentLabel;
    private javax.swing.JScrollPane treatmentScrollPane;
    // End of variables declaration//GEN-END:variables
}


