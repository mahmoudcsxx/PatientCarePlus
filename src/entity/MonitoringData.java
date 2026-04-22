package entity;

import java.util.Date;

public class MonitoringData {

    private int dataId;
    private String patientId;
    private Date timestamp;
    private String bloodPressure;
    private int heartRate;
    private double temperature;
    private int respiratoryRate;
    private double oxygenSaturation;
    private String medicationGiven;
    private String medicationDosage;
    private String recordedBy;
    private String notes;

    public MonitoringData() {}

    public MonitoringData(int dataId, String patientId, Date timestamp,
                          String bloodPressure, int heartRate, double temperature,
                          int respiratoryRate, double oxygenSaturation,
                          String medicationGiven, String medicationDosage,
                          String recordedBy, String notes) {
        this.dataId = dataId;
        this.patientId = patientId;
        this.timestamp = timestamp;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.respiratoryRate = respiratoryRate;
        this.oxygenSaturation = oxygenSaturation;
        this.medicationGiven = medicationGiven;
        this.medicationDosage = medicationDosage;
        this.recordedBy = recordedBy;
        this.notes = notes;
    }

    /** */
    public void recordVitals(double temperature, int heartRate, String bloodPressure) {
        this.temperature = temperature;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        if (this.timestamp == null) this.timestamp = new Date();
    }

    /** */
    public void addMedication(String medication, String dosage) {
        this.medicationGiven = medication;
        this.medicationDosage = dosage;
    }

    /** */
    public void addNotes(String noteLine) {
        if (noteLine == null) return;
        this.notes = (notes == null || notes.trim().isEmpty())
                ? noteLine : notes + "\n" + noteLine;
    }

    /** */
    public String getVitalsSummary() {
        return String.format("T=%.1f°C | HR=%d | BP=%s | SpO2=%.1f%%",
                temperature, heartRate, bloodPressure, oxygenSaturation);
    }

    /** Domain method: quick abnormal-vitals check (useful for warning messages). */
    public boolean hasAbnormalVitals() {
        return temperature > 38.0 || temperature < 35.5
                || heartRate > 120 || heartRate < 50
                || oxygenSaturation < 92.0;
    }

    public int getDataId() { return dataId; }
    public void setDataId(int dataId) { this.dataId = dataId; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public int getRespiratoryRate() { return respiratoryRate; }
    public void setRespiratoryRate(int respiratoryRate) { this.respiratoryRate = respiratoryRate; }
    public double getOxygenSaturation() { return oxygenSaturation; }
    public void setOxygenSaturation(double oxygenSaturation) { this.oxygenSaturation = oxygenSaturation; }
    public String getMedicationGiven() { return medicationGiven; }
    public void setMedicationGiven(String medicationGiven) { this.medicationGiven = medicationGiven; }
    public String getMedicationDosage() { return medicationDosage; }
    public void setMedicationDosage(String medicationDosage) { this.medicationDosage = medicationDosage; }
    public String getRecordedBy() { return recordedBy; }
    public void setRecordedBy(String recordedBy) { this.recordedBy = recordedBy; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
