package entity;

import java.util.Date;

public class LabRequest {

    private int requestId;
    private String patientId;
    private String testType;
    private Date requestedDate;
    private Date resultDate;
    private String results;
    private String referenceRange;
    private String status;        // PENDING / READY / CANCELLED
    private String orderedBy;
    private String performedBy;

    public LabRequest() {}

    public LabRequest(int requestId, String patientId, String testType,
                      Date requestedDate, Date resultDate, String results,
                      String referenceRange, String status,
                      String orderedBy, String performedBy) {
        this.requestId = requestId;
        this.patientId = patientId;
        this.testType = testType;
        this.requestedDate = requestedDate;
        this.resultDate = resultDate;
        this.results = results;
        this.referenceRange = referenceRange;
        this.status = status;
        this.orderedBy = orderedBy;
        this.performedBy = performedBy;
    }

    /** */
    public void submitRequest(String testType, String orderedBy) {
        this.testType = testType;
        this.orderedBy = orderedBy;
        this.status = "PENDING";
        this.requestedDate = new Date();
    }

    /** */
    public void updateResults(String results, Date resultDate) {
        this.results = results;
        this.resultDate = resultDate == null ? new Date() : resultDate;
        this.status = "READY";
    }

    /** */
    public void cancelRequest() {
        this.status = "CANCELLED";
    }

    /** */
    public boolean isResultReady() {
        return "READY".equalsIgnoreCase(status);
    }

    public int getRequestId() { return requestId; }
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getTestType() { return testType; }
    public void setTestType(String testType) { this.testType = testType; }
    public Date getRequestedDate() { return requestedDate; }
    public void setRequestedDate(Date requestedDate) { this.requestedDate = requestedDate; }
    public Date getResultDate() { return resultDate; }
    public void setResultDate(Date resultDate) { this.resultDate = resultDate; }
    public String getResults() { return results; }
    public void setResults(String results) { this.results = results; }
    public String getReferenceRange() { return referenceRange; }
    public void setReferenceRange(String referenceRange) { this.referenceRange = referenceRange; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getOrderedBy() { return orderedBy; }
    public void setOrderedBy(String orderedBy) { this.orderedBy = orderedBy; }
    public String getPerformedBy() { return performedBy; }
    public void setPerformedBy(String performedBy) { this.performedBy = performedBy; }
}
