
package entity;

import java.util.Calendar;
import java.util.Date;

public class Patient {
    
   private String patientId;
    private String fullName;
    private Date dateOfBirth;
    private int age;
    private String phone;
    private String email;
    private String address;
    private String bloodType;
    private String emergencyContact;
    private String insurancePolicyNo;
    private String insuranceProvider;
    private Date registrationDate;
    
    public Patient() {}

    public Patient(String patientId, String fullName, Date dateOfBirth,String phone, String email, String address, String bloodType,String emergencyContact, String insurancePolicyNo,String insuranceProvider, Date registrationDate) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
        this.insurancePolicyNo = insurancePolicyNo;
        this.insuranceProvider = insuranceProvider;
        this.registrationDate = registrationDate;
        this.age = computeAge(); // ***************
    }
    
     /** update demographic fields in one call. */
      public void updateDemographics(String name, String phone, String address) {
        this.fullName = name;
        this.phone = phone;
        this.address = address;
    }
      
      /** update insurance data. */
    public void updateInsuranceInfo(String policyNo, String provider) {
        this.insurancePolicyNo = policyNo;
        this.insuranceProvider = provider;
    }
    
    
    /** single string summary of insurance data. */
    public String getInsuranceInfo() {
        if (insuranceProvider == null || insurancePolicyNo == null) {
            return "No insurance on file";
        }
        return insuranceProvider + " (#" + insurancePolicyNo + ")";
    }
    
      /** compute current age from DOB. */
    public int computeAge() {
        if (dateOfBirth == null) return 0;
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar now = Calendar.getInstance();
        int years = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) years--;
        this.age = Math.max(years, 0);
        return this.age;
    }
   
    
       public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; this.age = computeAge(); }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getInsurancePolicyNo() { return insurancePolicyNo; }
    public void setInsurancePolicyNo(String insurancePolicyNo) { this.insurancePolicyNo = insurancePolicyNo; }
    public String getInsuranceProvider() { return insuranceProvider; }
    public void setInsuranceProvider(String insuranceProvider) { this.insuranceProvider = insuranceProvider; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date registrationDate) { this.registrationDate = registrationDate; }

    @Override
    public String toString() {
        return patientId + " - " + fullName;
    }
}
    
    
    
    
}
