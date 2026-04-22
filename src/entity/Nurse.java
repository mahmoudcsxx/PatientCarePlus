package entity;

import java.util.Date;
import java.util.List;
import enums.Role;

public class Nurse extends User {

    private String ward;
    private String shift;
    private String licenseNumber;

    public Nurse() { super(); this.role = Role.NURSE; }

    public Nurse(int userId, String name, int age, String phone, String email,
                 String password, Date createdAt, Date lastLogin,
                 String ward, String shift, String licenseNumber) {
        super(userId, name, age, phone, email, password, Role.NURSE, createdAt, lastLogin);
        this.ward = ward;
        this.shift = shift;
        this.licenseNumber = licenseNumber;
    }

    /** */
    public void recordMonitoringData(MonitoringData data) {
        if (data != null) {
            data.setRecordedBy(this.name);
        }
    }

    /** */
    public void administerMedication(MonitoringData data, String medication, String dosage) {
        if (data != null) {
            data.addMedication(medication, dosage);
        }
    }

    /** */
    public List<Patient> viewPatientList(List<Patient> patients) {
        return patients;
    }

    public Patient viewPatientDetails(Patient patient) {
        return patient;
    }

    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
}
