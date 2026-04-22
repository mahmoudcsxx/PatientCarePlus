package entity;

import java.util.Date;
import java.util.List;
import enums.Role;

public class HospitalAdmin extends User {

    private int adminId;
    private String department;
    private String adminLevel;

    public HospitalAdmin() { super(); this.role = Role.ADMIN; }

    public HospitalAdmin(int userId, String name, int age, String phone, String email,
                         String password, Date createdAt, Date lastLogin,
                         int adminId, String department, String adminLevel) {
        super(userId, name, age, phone, email, password, Role.ADMIN, createdAt, lastLogin);
        this.adminId = adminId;
        this.department = department;
        this.adminLevel = adminLevel;
    }

    /** */
    public boolean registerPatient(Patient patient) {
        return patient != null
                && patient.getFullName() != null
                && !patient.getFullName().trim().isEmpty()
                && patient.getDateOfBirth() != null;
    }

    /** */
    public void manageUsers() {
        // represents opening the user management screen
    }

    /** */
    public void processInsuranceClaim(InsuranceClaim claim) {
        if (claim != null) {
            claim.setSubmittedBy(this.name);
        }
    }

    /** */
    public String generateReports(List<Patient> patients) {
        int total = patients == null ? 0 : patients.size();
        return "Total registered patients: " + total;
    }

    public List<Patient> viewAllRecords(List<Patient> all) {
        return all;
    }

    public int getAdminId() { return adminId; }
    public void setAdminId(int adminId) { this.adminId = adminId; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getAdminLevel() { return adminLevel; }
    public void setAdminLevel(String adminLevel) { this.adminLevel = adminLevel; }
}
