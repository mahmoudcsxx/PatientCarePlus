package entity;

import java.util.Date;
import java.util.List;
import enums.Role;

public class Doctor extends User {

    private String specialization;
    private String licenseNumber;
    private String department;

    public Doctor() { super(); this.role = Role.DOCTOR; }

    public Doctor(int userId, String name, int age, String phone, String email,
                  String password, Date createdAt, Date lastLogin,
                  String specialization, String licenseNumber, String department) {
        super(userId, name, age, phone, email, password, Role.DOCTOR, createdAt, lastLogin);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.department = department;
    }

    /** */
    public MedicalRecord viewMedicalRecord(MedicalRecord record) {
        return record; // controller supplies the record
    }

    /** */
    public void addDiagnosis(MedicalRecord record, String diagnosis) {
        if (record != null && diagnosis != null && !diagnosis.trim().isEmpty()) {
            record.addDiagnosis(diagnosis);
        }
    }

    /** */
    public void updateTreatment(MedicalRecord record, String plan) {
        if (record != null && plan != null && !plan.trim().isEmpty()) {
            record.updateTreatment(plan);
        }
    }

    /** */
    public List<MonitoringData> viewMonitoringData(List<MonitoringData> data) {
        return data; // controller loads from DB
    }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
