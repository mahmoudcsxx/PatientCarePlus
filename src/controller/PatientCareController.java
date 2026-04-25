package controller;

import db.DatabaseManager;
import entity.MedicalRecord;
import entity.MonitoringData;
import entity.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Central controller for patient-care database operations.
 *
 * The GUI screens can call this class instead of placing SQL directly inside
 * every JFrame. Some screens still have local SQL while we migrate gradually,
 * but this class is now the main reusable service layer.
 */
public class PatientCareController {

    private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public PatientCareController() {
    }

    public void initializeCoreTables() throws SQLException {
        try (Connection con = DatabaseManager.getConnection();
             Statement statement = con.createStatement()) {

            createPatientTable(statement);
            createPatientInsuranceTable(statement);
            createMedicalRecordTable(statement);
            createDiagnosisTable(statement);
            createLabResultTable(statement);
            createPatientMonitoringTable(statement);
        }
    }

    public Patient registerPatient(String fullName, Date dateOfBirth, String phone,
                                   String address, String email, String gender,
                                   String bloodType) throws SQLException {
        initializeCoreTables();

        String patientSql = """
                INSERT INTO PATIENT
                (PATIENT_ID, FULL_NAME, DATE_OF_BIRTH, GENDER, PHONE_NUMBER, EMAIL, ADDRESS, BLOOD_TYPE)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DatabaseManager.getConnection()) {
            con.setAutoCommit(false);

            try {
                String patientId = generateNextPatientId(con);

                try (PreparedStatement pst = con.prepareStatement(patientSql)) {
                    pst.setString(1, patientId);
                    pst.setString(2, fullName);
                    pst.setString(3, dateOfBirth == null ? null : DB_DATE_FORMAT.format(dateOfBirth));
                    setNullableString(pst, 4, gender);
                    setNullableString(pst, 5, phone);
                    setNullableString(pst, 6, email);
                    setNullableString(pst, 7, address);
                    setNullableString(pst, 8, bloodType);
                    pst.executeUpdate();
                }

                con.commit();

                Patient patient = new Patient();
                patient.setPatientId(patientId);
                patient.setFullName(fullName);
                patient.setDateOfBirth(dateOfBirth);
                patient.setPhone(phone);
                patient.setAddress(address);
                patient.setEmail(email);
                patient.setBloodType(bloodType);
                patient.setRegistrationDate(new Date());
                return patient;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public boolean updatePatientInsuranceInfo(String patientId, String policyNumber,
                                              String provider) throws SQLException {
        initializeCoreTables();

        String updateSql = """
                UPDATE PATIENT_INSURANCE
                SET POLICY_NUMBER = ?, PROVIDER = ?
                WHERE PATIENT_ID = ?
                """;
        String insertSql = """
                INSERT INTO PATIENT_INSURANCE (PATIENT_ID, POLICY_NUMBER, PROVIDER)
                VALUES (?, ?, ?)
                """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement updatePst = con.prepareStatement(updateSql)) {

            setNullableString(updatePst, 1, policyNumber);
            setNullableString(updatePst, 2, provider);
            updatePst.setString(3, patientId);

            if (updatePst.executeUpdate() > 0) {
                return true;
            }

            try (PreparedStatement insertPst = con.prepareStatement(insertSql)) {
                insertPst.setString(1, patientId);
                setNullableString(insertPst, 2, policyNumber);
                setNullableString(insertPst, 3, provider);
                return insertPst.executeUpdate() > 0;
            }
        }
    }

    public List<String> getPatientIds() throws SQLException {
        initializeCoreTables();

        String sql = """
                SELECT PATIENT_ID FROM PATIENT
                UNION
                SELECT PATIENT_ID FROM PATIENT_INSURANCE
                ORDER BY PATIENT_ID
                """;
        List<String> patientIds = new ArrayList<>();

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                patientIds.add(rs.getString("PATIENT_ID"));
            }
        }

        return patientIds;
    }

    public List<Patient> getPatients() throws SQLException {
        initializeCoreTables();

        String sql = """
                SELECT p.PATIENT_ID, p.FULL_NAME, p.DATE_OF_BIRTH, p.PHONE_NUMBER, p.EMAIL,
                       p.ADDRESS, p.BLOOD_TYPE, i.POLICY_NUMBER, i.PROVIDER
                FROM PATIENT p
                LEFT JOIN PATIENT_INSURANCE i ON p.PATIENT_ID = i.PATIENT_ID
                ORDER BY p.PATIENT_ID
                """;
        List<Patient> patients = new ArrayList<>();

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setPatientId(rs.getString("PATIENT_ID"));
                patient.setFullName(rs.getString("FULL_NAME"));
                patient.setPhone(rs.getString("PHONE_NUMBER"));
                patient.setEmail(rs.getString("EMAIL"));
                patient.setAddress(rs.getString("ADDRESS"));
                patient.setBloodType(rs.getString("BLOOD_TYPE"));
                patient.setInsurancePolicyNo(rs.getString("POLICY_NUMBER"));
                patient.setInsuranceProvider(rs.getString("PROVIDER"));
                patients.add(patient);
            }
        }

        return patients;
    }

    public boolean saveDiagnosis(String patientId, String diagnosis, String symptoms,
                                 String treatment) throws SQLException {
        initializeCoreTables();

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
            return pst.executeUpdate() > 0;
        }
    }

    public String getDiagnosisHistory(String patientId) throws SQLException {
        initializeCoreTables();

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
                    Timestamp createdAt = rs.getTimestamp("CREATED_AT");
                    history.append("Diagnosis: ")
                            .append(defaultText(rs.getString("DIAGNOSIS_TEXT")))
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

    public boolean saveMonitoringData(String patientId, String bloodPressure, int heartRate,
                                      double weight, int spo2, double temperature,
                                      int respiratoryRate) throws SQLException {
        initializeCoreTables();

        String updateSql = """
                UPDATE PATIENT_MONITORING
                SET BLOOD_PRESSURE = ?, HEART_RATE = ?, WEIGHT = ?, SPO2 = ?,
                    TEMPERATURE = ?, RESPIRATORY_RATE = ?, RECORDED_AT = CURRENT_TIMESTAMP
                WHERE PATIENT_ID = ?
                """;
        String insertSql = """
                INSERT INTO PATIENT_MONITORING
                (PATIENT_ID, BLOOD_PRESSURE, HEART_RATE, WEIGHT, SPO2, TEMPERATURE, RESPIRATORY_RATE)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement updatePst = con.prepareStatement(updateSql)) {

            updatePst.setString(1, bloodPressure);
            updatePst.setInt(2, heartRate);
            updatePst.setDouble(3, weight);
            updatePst.setInt(4, spo2);
            updatePst.setDouble(5, temperature);
            updatePst.setInt(6, respiratoryRate);
            updatePst.setString(7, patientId);

            if (updatePst.executeUpdate() > 0) {
                return true;
            }

            try (PreparedStatement insertPst = con.prepareStatement(insertSql)) {
                insertPst.setString(1, patientId);
                insertPst.setString(2, bloodPressure);
                insertPst.setInt(3, heartRate);
                insertPst.setDouble(4, weight);
                insertPst.setInt(5, spo2);
                insertPst.setDouble(6, temperature);
                insertPst.setInt(7, respiratoryRate);
                return insertPst.executeUpdate() > 0;
            }
        }
    }

    public MonitoringData getMonitoringData(String patientId) throws SQLException {
        initializeCoreTables();

        String sql = """
                SELECT BLOOD_PRESSURE, HEART_RATE, SPO2, TEMPERATURE, RESPIRATORY_RATE, RECORDED_AT
                FROM PATIENT_MONITORING
                WHERE PATIENT_ID = ?
                """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, patientId);

            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                MonitoringData data = new MonitoringData();
                data.setPatientId(patientId);
                data.setBloodPressure(rs.getString("BLOOD_PRESSURE"));
                data.setHeartRate(rs.getInt("HEART_RATE"));
                data.setOxygenSaturation(rs.getDouble("SPO2"));
                data.setTemperature(rs.getDouble("TEMPERATURE"));
                data.setRespiratoryRate(rs.getInt("RESPIRATORY_RATE"));
                Timestamp recordedAt = rs.getTimestamp("RECORDED_AT");
                if (recordedAt != null) {
                    data.setTimestamp(new Date(recordedAt.getTime()));
                }
                return data;
            }
        }
    }

    public MedicalRecord getMedicalRecord(String patientId) throws SQLException {
        initializeCoreTables();

        String sql = """
                SELECT DIAGNOSIS, MEDICATION, TREATMENT_PLAN, ALLERGIES, DOCTOR_NOTES, UPDATED_AT
                FROM MEDICAL_RECORD
                WHERE PATIENT_ID = ?
                """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, patientId);

            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                MedicalRecord record = new MedicalRecord();
                record.setPatientId(patientId);
                record.setDiagnosis(rs.getString("DIAGNOSIS"));
                record.setMedications(rs.getString("MEDICATION"));
                record.setTreatmentPlan(rs.getString("TREATMENT_PLAN"));
                record.setAllergies(rs.getString("ALLERGIES"));
                record.setNotes(rs.getString("DOCTOR_NOTES"));
                Timestamp updatedAt = rs.getTimestamp("UPDATED_AT");
                if (updatedAt != null) {
                    record.setRecordDate(new Date(updatedAt.getTime()));
                }
                return record;
            }
        }
    }

    public List<MedicalRecord> getMedicalRecords(String patientId) throws SQLException {
        List<MedicalRecord> records = new ArrayList<>();
        MedicalRecord record = getMedicalRecord(patientId);
        if (record != null) {
            records.add(record);
        }
        return records;
    }

    public boolean updateMedicalRecord(String patientId, String diagnosis, String treatmentPlan,
                                       String medication, String allergies, String doctorNotes)
            throws SQLException {
        initializeCoreTables();

        String updateSql = """
                UPDATE MEDICAL_RECORD
                SET DIAGNOSIS = ?, MEDICATION = ?, TREATMENT_PLAN = ?,
                    ALLERGIES = ?, DOCTOR_NOTES = ?, UPDATED_AT = CURRENT_TIMESTAMP
                WHERE PATIENT_ID = ?
                """;
        String insertSql = """
                INSERT INTO MEDICAL_RECORD
                (PATIENT_ID, DIAGNOSIS, MEDICATION, TREATMENT_PLAN, ALLERGIES, DOCTOR_NOTES)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement updatePst = con.prepareStatement(updateSql)) {

            setNullableString(updatePst, 1, diagnosis);
            setNullableString(updatePst, 2, medication);
            setNullableString(updatePst, 3, treatmentPlan);
            setNullableString(updatePst, 4, allergies);
            setNullableString(updatePst, 5, doctorNotes);
            updatePst.setString(6, patientId);

            if (updatePst.executeUpdate() > 0) {
                return true;
            }

            try (PreparedStatement insertPst = con.prepareStatement(insertSql)) {
                insertPst.setString(1, patientId);
                setNullableString(insertPst, 2, diagnosis);
                setNullableString(insertPst, 3, medication);
                setNullableString(insertPst, 4, treatmentPlan);
                setNullableString(insertPst, 5, allergies);
                setNullableString(insertPst, 6, doctorNotes);
                return insertPst.executeUpdate() > 0;
            }
        }
    }

    private void createPatientTable(Statement statement) throws SQLException {
        executeCreate(statement,
                "CREATE TABLE PATIENT ("
                + "PATIENT_ID VARCHAR(50) PRIMARY KEY, "
                + "FULL_NAME VARCHAR(150) NOT NULL, "
                + "DATE_OF_BIRTH VARCHAR(30), "
                + "GENDER VARCHAR(20), "
                + "PHONE_NUMBER VARCHAR(30), "
                + "EMAIL VARCHAR(120), "
                + "ADDRESS VARCHAR(255), "
                + "BLOOD_TYPE VARCHAR(10), "
                + "CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")");
    }

    private void createPatientInsuranceTable(Statement statement) throws SQLException {
        executeCreate(statement,
                "CREATE TABLE PATIENT_INSURANCE ("
                + "PATIENT_ID VARCHAR(50) PRIMARY KEY, "
                + "POLICY_NUMBER VARCHAR(100), "
                + "PROVIDER VARCHAR(100)"
                + ")");
    }

    private void createMedicalRecordTable(Statement statement) throws SQLException {
        executeCreate(statement,
                "CREATE TABLE MEDICAL_RECORD ("
                + "PATIENT_ID VARCHAR(50) PRIMARY KEY, "
                + "DIAGNOSIS VARCHAR(2000), "
                + "MEDICATION VARCHAR(2000), "
                + "TREATMENT_PLAN VARCHAR(2000), "
                + "ALLERGIES VARCHAR(2000), "
                + "DOCTOR_NOTES VARCHAR(2000), "
                + "UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")");
    }

    private void createDiagnosisTable(Statement statement) throws SQLException {
        executeCreate(statement,
                "CREATE TABLE DIAGNOSIS ("
                + "ID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, "
                + "PATIENT_ID VARCHAR(50), "
                + "DIAGNOSIS_TEXT VARCHAR(255) NOT NULL, "
                + "SYMPTOMS VARCHAR(1000), "
                + "TREATMENT VARCHAR(1000), "
                + "CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")");

        ensureColumn(statement, "DIAGNOSIS", "PATIENT_ID VARCHAR(50)");
        ensureColumn(statement, "DIAGNOSIS", "CREATED_AT TIMESTAMP");
    }

    private void createLabResultTable(Statement statement) throws SQLException {
        executeCreate(statement,
                "CREATE TABLE LAB_RESULT ("
                + "PATIENT_ID VARCHAR(50) PRIMARY KEY, "
                + "HBALC DOUBLE, "
                + "FASTING_GLUCOSE DOUBLE, "
                + "LDL_CHOLESTEROL DOUBLE, "
                + "CREATININE DOUBLE, "
                + "RESULT_STATUS VARCHAR(30), "
                + "UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")");
    }

    private void createPatientMonitoringTable(Statement statement) throws SQLException {
        executeCreate(statement,
                "CREATE TABLE PATIENT_MONITORING ("
                + "PATIENT_ID VARCHAR(50) PRIMARY KEY, "
                + "BLOOD_PRESSURE VARCHAR(30), "
                + "HEART_RATE INT, "
                + "WEIGHT DOUBLE, "
                + "SPO2 INT, "
                + "TEMPERATURE DOUBLE, "
                + "RESPIRATORY_RATE INT, "
                + "RECORDED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ")");
    }

    private void executeCreate(Statement statement, String sql) throws SQLException {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) {
                throw e;
            }
        }
    }

    private void ensureColumn(Statement statement, String tableName, String columnDefinition) throws SQLException {
        try {
            statement.executeUpdate("ALTER TABLE " + tableName + " ADD COLUMN " + columnDefinition);
        } catch (SQLException e) {
            String sqlState = e.getSQLState();
            if (!"X0Y32".equals(sqlState) && !"42X14".equals(sqlState)) {
                throw e;
            }
        }
    }

    private String generateNextPatientId(Connection con) throws SQLException {
        int nextNumber = 1;
        String sql = """
                SELECT PATIENT_ID FROM PATIENT
                UNION
                SELECT PATIENT_ID FROM PATIENT_INSURANCE
                """;

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String patientId = rs.getString("PATIENT_ID");
                if (patientId != null && patientId.matches("P\\d+")) {
                    int number = Integer.parseInt(patientId.substring(1));
                    nextNumber = Math.max(nextNumber, number + 1);
                }
            }
        }

        return String.format("P%03d", nextNumber);
    }

    private void setNullableString(PreparedStatement pst, int index, String value) throws SQLException {
        if (value == null || value.trim().isEmpty()) {
            pst.setString(index, null);
        } else {
            pst.setString(index, value.trim());
        }
    }

    private String defaultText(String value) {
        return value == null ? "" : value;
    }
}
