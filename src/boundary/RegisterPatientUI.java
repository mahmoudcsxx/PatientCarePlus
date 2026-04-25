package boundary;

import controller.PatientCareController;
import entity.Patient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;


public class RegisterPatientUI extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegisterPatientUI.class.getName());
    private final PatientCareController controller = new PatientCareController();

   
    public RegisterPatientUI() {
        initComponents();
        setupRegisterPatientUI();
    }

    
    private void setupRegisterPatientUI() {
        setTitle("Register New Patient");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonsubmit);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelregisterpage = new javax.swing.JPanel();
        registerpatientpanel = new javax.swing.JPanel();
        registernewpatient = new javax.swing.JLabel();
        labelfullname = new javax.swing.JLabel();
        labeldateofbirth = new javax.swing.JLabel();
        labelphonenumber = new javax.swing.JLabel();
        labelemailaddress = new javax.swing.JLabel();
        labeladdress = new javax.swing.JLabel();
        labelbloodtype = new javax.swing.JLabel();
        labelpolicynumber = new javax.swing.JLabel();
        labelinsuranceprovider = new javax.swing.JLabel();
        txtfullname = new javax.swing.JTextField();
        txtphonenumber = new javax.swing.JTextField();
        txtemailaddress = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtgender = new javax.swing.JTextField();
        txtpolicynumber = new javax.swing.JTextField();
        txtinsuranceprovider = new javax.swing.JTextField();
        buttonsubmit = new javax.swing.JButton();
        buttoncancel = new javax.swing.JButton();
        listbloodtype = new javax.swing.JComboBox();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Register New Patient");

        panelregisterpage.setBackground(new java.awt.Color(255, 255, 255));

        registerpatientpanel.setBackground(new java.awt.Color(102, 153, 255));

        registernewpatient.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        registernewpatient.setForeground(new java.awt.Color(255, 255, 255));
        registernewpatient.setText("Register New Patient");

        javax.swing.GroupLayout registerpatientpanelLayout = new javax.swing.GroupLayout(registerpatientpanel);
        registerpatientpanel.setLayout(registerpatientpanelLayout);
        registerpatientpanelLayout.setHorizontalGroup(
            registerpatientpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerpatientpanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(registernewpatient)
                .addContainerGap(394, Short.MAX_VALUE))
        );
        registerpatientpanelLayout.setVerticalGroup(
            registerpatientpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerpatientpanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(registernewpatient)
                .addGap(18, 18, 18))
        );

        labelfullname.setForeground(new java.awt.Color(0, 0, 0));
        labelfullname.setText("Full Name");

        labeldateofbirth.setForeground(new java.awt.Color(0, 0, 0));
        labeldateofbirth.setText("Date of Birth");

        labelphonenumber.setForeground(new java.awt.Color(0, 0, 0));
        labelphonenumber.setText("Phone Number");

        labelemailaddress.setForeground(new java.awt.Color(0, 0, 0));
        labelemailaddress.setText("Email Address");

        labeladdress.setForeground(new java.awt.Color(0, 0, 0));
        labeladdress.setText("Address");

        labelbloodtype.setForeground(new java.awt.Color(0, 0, 0));
        labelbloodtype.setText("Blood Type");

        labelpolicynumber.setForeground(new java.awt.Color(0, 0, 0));
        labelpolicynumber.setText("Policy Number");

        labelinsuranceprovider.setForeground(new java.awt.Color(0, 0, 0));
        labelinsuranceprovider.setText("Insurance Provider");

        txtfullname.setBackground(new java.awt.Color(255, 255, 255));
        txtfullname.setForeground(new java.awt.Color(0, 0, 0));

        txtphonenumber.setBackground(new java.awt.Color(255, 255, 255));
        txtphonenumber.setForeground(new java.awt.Color(0, 0, 0));

        txtemailaddress.setBackground(new java.awt.Color(255, 255, 255));
        txtemailaddress.setForeground(new java.awt.Color(0, 0, 0));

        txtaddress.setBackground(new java.awt.Color(255, 255, 255));
        txtaddress.setForeground(new java.awt.Color(0, 0, 0));

        txtgender.setBackground(new java.awt.Color(255, 255, 255));
        txtgender.setForeground(new java.awt.Color(0, 0, 0));

        txtpolicynumber.setBackground(new java.awt.Color(255, 255, 255));
        txtpolicynumber.setForeground(new java.awt.Color(0, 0, 0));

        txtinsuranceprovider.setBackground(new java.awt.Color(255, 255, 255));
        txtinsuranceprovider.setForeground(new java.awt.Color(0, 0, 0));

        buttonsubmit.setBackground(new java.awt.Color(102, 153, 255));
        buttonsubmit.setForeground(new java.awt.Color(255, 255, 255));
        buttonsubmit.setText("Submit");
        buttonsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonsubmitActionPerformed(evt);
            }
        });

        buttoncancel.setBackground(new java.awt.Color(204, 204, 204));
        buttoncancel.setForeground(new java.awt.Color(0, 0, 0));
        buttoncancel.setText("Cancel");
        buttoncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoncancelActionPerformed(evt);
            }
        });

        listbloodtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" }));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Gender");

        javax.swing.GroupLayout panelregisterpageLayout = new javax.swing.GroupLayout(panelregisterpage);
        panelregisterpage.setLayout(panelregisterpageLayout);
        panelregisterpageLayout.setHorizontalGroup(
            panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registerpatientpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelregisterpageLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator6)
                    .addComponent(jSeparator7)
                    .addComponent(jSeparator8)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelregisterpageLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttoncancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonsubmit))
                    .addGroup(panelregisterpageLayout.createSequentialGroup()
                        .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelfullname)
                            .addComponent(labeldateofbirth)
                            .addComponent(labelphonenumber)
                            .addComponent(labelemailaddress)
                            .addComponent(labeladdress)
                            .addComponent(labelbloodtype)
                            .addComponent(jLabel1)
                            .addComponent(labelpolicynumber)
                            .addComponent(labelinsuranceprovider))
                        .addGap(32, 32, 32)
                        .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfullname)
                            .addComponent(jFormattedTextField1)
                            .addComponent(txtphonenumber)
                            .addComponent(txtemailaddress)
                            .addComponent(txtaddress)
                            .addComponent(listbloodtype, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtgender)
                            .addComponent(txtpolicynumber)
                            .addComponent(txtinsuranceprovider))))
                .addGap(28, 28, 28))
        );
        panelregisterpageLayout.setVerticalGroup(
            panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelregisterpageLayout.createSequentialGroup()
                .addComponent(registerpatientpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelfullname)
                    .addComponent(txtfullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeldateofbirth)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelphonenumber)
                    .addComponent(txtphonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelemailaddress)
                    .addComponent(txtemailaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labeladdress)
                    .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelbloodtype)
                    .addComponent(listbloodtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtgender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelpolicynumber)
                    .addComponent(txtpolicynumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelinsuranceprovider)
                    .addComponent(txtinsuranceprovider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(panelregisterpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttoncancel)
                    .addComponent(buttonsubmit))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelregisterpage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelregisterpage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonsubmitActionPerformed
        registerPatient();
    }//GEN-LAST:event_buttonsubmitActionPerformed

    private void buttoncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoncancelActionPerformed
        dispose();
    }//GEN-LAST:event_buttoncancelActionPerformed

   
    private void registerPatient() {
        String fullName = txtfullname.getText().trim();
        String dateOfBirthText = jFormattedTextField1.getText().trim();
        String phoneNumber = txtphonenumber.getText().trim();
        String email = txtemailaddress.getText().trim();
        String address = txtaddress.getText().trim();
        String bloodType = String.valueOf(listbloodtype.getSelectedItem()).trim();
        String gender = txtgender.getText().trim();
        String policyNumber = txtpolicynumber.getText().trim();
        String insuranceProvider = txtinsuranceprovider.getText().trim();

        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's full name.");
            txtfullname.requestFocus();
            return;
        }

        if (dateOfBirthText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's date of birth.");
            jFormattedTextField1.requestFocus();
            return;
        }

        try {
            Patient patient = controller.registerPatient(
                    fullName,
                    parseDateOfBirth(dateOfBirthText),
                    phoneNumber,
                    address,
                    email,
                    gender,
                    bloodType
            );
            controller.updatePatientInsuranceInfo(patient.getPatientId(), policyNumber, insuranceProvider);

            JOptionPane.showMessageDialog(this,
                    "Patient registered successfully.\nPatient ID: " + patient.getPatientId());
            clearForm();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid date. Please use a format like 20-12-2000 or 20/12/2000.",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE);
            jFormattedTextField1.requestFocus();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Could not save the patient to Derby.");
        }
    }

    private java.util.Date parseDateOfBirth(String value) throws ParseException {
        String normalized = value.replace('/', '-');
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        format.setLenient(false);
        return format.parse(normalized);
    }

 
    private void clearForm() {
        txtfullname.setText("");
        jFormattedTextField1.setText("");
        txtphonenumber.setText("");
        txtemailaddress.setText("");
        txtaddress.setText("");
        txtgender.setText("");
        txtpolicynumber.setText("");
        txtinsuranceprovider.setText("");
        listbloodtype.setSelectedIndex(0);
        txtfullname.requestFocus();
    }

   
    public static void main(String args[]) {
       
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        //</editor-fold>

        
        java.awt.EventQueue.invokeLater(() -> new RegisterPatientUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttoncancel;
    private javax.swing.JButton buttonsubmit;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel labeladdress;
    private javax.swing.JLabel labelbloodtype;
    private javax.swing.JLabel labeldateofbirth;
    private javax.swing.JLabel labelemailaddress;
    private javax.swing.JLabel labelfullname;
    private javax.swing.JLabel labelinsuranceprovider;
    private javax.swing.JLabel labelphonenumber;
    private javax.swing.JLabel labelpolicynumber;
    private javax.swing.JComboBox listbloodtype;
    private javax.swing.JPanel panelregisterpage;
    private javax.swing.JLabel registernewpatient;
    private javax.swing.JPanel registerpatientpanel;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtemailaddress;
    private javax.swing.JTextField txtfullname;
    private javax.swing.JTextField txtgender;
    private javax.swing.JTextField txtinsuranceprovider;
    private javax.swing.JTextField txtphonenumber;
    private javax.swing.JTextField txtpolicynumber;
    // End of variables declaration//GEN-END:variables
}


