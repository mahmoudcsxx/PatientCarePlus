package entity;

import java.util.Date;
import enums.ClaimStatus;

public class InsuranceClaim {

    private String claimId;
    private String billingId;
    private String patientId;
    private String submittedBy;
    private ClaimStatus status;
    private Date submittedAt;
    private Date processedAt;

    public InsuranceClaim() {}

    public InsuranceClaim(String claimId, String billingId, String patientId,
                          String submittedBy, ClaimStatus status,
                          Date submittedAt, Date processedAt) {
        this.claimId = claimId;
        this.billingId = billingId;
        this.patientId = patientId;
        this.submittedBy = submittedBy;
        this.status = status;
        this.submittedAt = submittedAt;
        this.processedAt = processedAt;
    }

    /** */
    public void submitClaim() {
        this.status = ClaimStatus.SUBMITTED;
        this.submittedAt = new Date();
    }

    /** */
    public void updateStatus(ClaimStatus newStatus) {
        this.status = newStatus;
        if (newStatus == ClaimStatus.APPROVED || newStatus == ClaimStatus.REJECTED) {
            this.processedAt = new Date();
        }
    }

    /** */
    public String getClaimDetails() {
        return "Claim " + claimId + " (Billing " + billingId + ") — " + status;
    }

    public String getClaimId() { return claimId; }
    public void setClaimId(String claimId) { this.claimId = claimId; }
    public String getBillingId() { return billingId; }
    public void setBillingId(String billingId) { this.billingId = billingId; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getSubmittedBy() { return submittedBy; }
    public void setSubmittedBy(String submittedBy) { this.submittedBy = submittedBy; }
    public ClaimStatus getStatus() { return status; }
    public void setStatus(ClaimStatus status) { this.status = status; }
    public Date getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Date submittedAt) { this.submittedAt = submittedAt; }
    public Date getProcessedAt() { return processedAt; }
    public void setProcessedAt(Date processedAt) { this.processedAt = processedAt; }
}
