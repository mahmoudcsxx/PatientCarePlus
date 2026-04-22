
package entity;

import java.util.Date;
import java.util.List;
import enums.Role;

public class Doctor extends User{
    
    private String specialization;
    private String licenseNumber;
    private String department;
    
    public Doctor() {
    
        super();
        this.role = Role.DOCTOR;
    
    }
    public Doctor(int userId, String name, String phone, int age, String email, String password, Date createdAt, Date lastLogin, String specialization, String licenseNumber, String department) {
    
        super(userId, name, age, phone, email, password, Role.DOCTOR, createdAt, lastLogin);
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
        this.department = department;
    
    }
    
    /** Doctor reviews a patient's medical record. **/
    public MedicalRecord viewMedicalRecord(MedicalRecord record) {
    
        return record;
    
    }
    
    /** Append a diagnosis entry to an existing record. **/
    public void addDiagnosis(MedicalRecord record, String diagnosis) {
    
        if(record != null && diagnosis != null && !diagnosis.trim().isEmpty()) {
        
          record.addDiagnosis(diagnosis);
            
        }
    
    }
    
    /** Update the treatment plan on the provided record. **/
    public void updateTreatment(MedicalRecord record, String plan) {
    
          if(record != null && plan != null && !plan.trim().isEmpty()) {
          
              record.updateTreatment(plan);
          
          }
    }


/** Filter monitoring data list to last N entries for review **/
public List<MonitoringData> viewMonitorData(List<MonitoringData> data) {

      return data;

}


  public String getSpecialization() {return specialization;}
  public void setSpecialization(String specialization) {this.specialization = specialization;}
  public String getLicenseNumber() {return licenseNumber;}
  public String getDepartment() { return department; }
  public void setDepartment(String department) { this.department = department; }

}