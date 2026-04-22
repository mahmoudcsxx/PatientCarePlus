package entity;

import java.util.Date;

public class MedicalRecord {

    private int recordId;
    private String patientId;
    private int doctorId;
    private Date recordDate;
    private String diagnosis;
    private String treatmentPlan;
    private String symptoms;
    private String medications;
    private String allergies;
    private String notes;
    private Date followUpDate;

    public MedicalRecord() {}

    public MedicalRecord(int recordId, String patientId, int doctorId, Date recordDate,
                         String diagnosis, String treatmentPlan, String symptoms,
                         String medications, String allergies, String notes, Date followUpDate) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.recordDate = recordDate;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.symptoms = symptoms;
        this.medications = medications;
        this.allergies = allergies;
        this.notes = notes;
        this.followUpDate = followUpDate;
    }

    /** */
    public void addDiagnosis(String diagnosis) {
        if (diagnosis == null || diagnosis.trim().isEmpty()) return;
        if (this.diagnosis == null || this.diagnosis.trim().isEmpty()) {
            this.diagnosis = diagnosis;
        } else {
            this.diagnosis = this.diagnosis + "\n- " + diagnosis;
        }
    }

    /** */
    public void updateTreatment(String plan) {
        this.treatmentPlan = plan;
    }

    /**  */
    public void addNotes(String noteLine) {
        if (noteLine == null) return;
        if (notes == null || notes.trim().isEmpty()) {
            notes = noteLine;
        } else {
            notes = notes + "\n" + noteLine;
        }
    }

    /** */
    public void updateMedications(String medications) {
        this.medications = medications;
    }

    /** */
    public String getSummary() {
        String d = (diagnosis == null) ? "(no diagnosis)" : diagnosis.split("\n")[0];
        return "Record #" + recordId + " — " + d;
    }

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    public Date getRecordDate() { return recordDate; }
    public void setRecordDate(Date recordDate) { this.recordDate = recordDate; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getTreatmentPlan() { return treatmentPlan; }
    public void setTreatmentPlan(String treatmentPlan) { this.treatmentPlan = treatmentPlan; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getMedications() { return medications; }
    public void setMedications(String medications) { this.medications = medications; }
    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Date getFollowUpDate() { return followUpDate; }
    public void setFollowUpDate(Date followUpDate) { this.followUpDate = followUpDate; }
}
