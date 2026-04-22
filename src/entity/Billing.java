package entity;

import java.util.Date;
import enums.BillingStatus;

public class Billing {

    private int invoiceId;
    private String patientId;
    private Integer recordId;           // nullable - links to MedicalRecord
    private double totalAmount;
    private double insuranceCoverage;
    private double patientResponsibility;
    private String serviceDescription;
    private Date serviceDate;
    private Date billingDate;
    private BillingStatus paymentStatus;

    public Billing() {}

    public Billing(int invoiceId, String patientId, Integer recordId,
                   double totalAmount, double insuranceCoverage,
                   double patientResponsibility, String serviceDescription,
                   Date serviceDate, Date billingDate, BillingStatus paymentStatus) {
        this.invoiceId = invoiceId;
        this.patientId = patientId;
        this.recordId = recordId;
        this.totalAmount = totalAmount;
        this.insuranceCoverage = insuranceCoverage;
        this.patientResponsibility = patientResponsibility;
        this.serviceDescription = serviceDescription;
        this.serviceDate = serviceDate;
        this.billingDate = billingDate;
        this.paymentStatus = paymentStatus;
    }

    /** */
    public double calculateInsuranceCoverage(double coverageRate) {
        if (coverageRate < 0) coverageRate = 0;
        if (coverageRate > 1) coverageRate = 1;
        this.insuranceCoverage = Math.round(totalAmount * coverageRate * 100.0) / 100.0;
        this.patientResponsibility = Math.round((totalAmount - insuranceCoverage) * 100.0) / 100.0;
        return insuranceCoverage;
    }

    /** */
    public void updatePaymentStatus(BillingStatus status) {
        this.paymentStatus = status;
        this.billingDate = new Date();
    }

    /** */
    public String getBillingSummary() {
        return String.format(
                "Invoice #%d%nPatient: %s%nTotal: %.2f%nCoverage: %.2f%nPatient pays: %.2f%nStatus: %s",
                invoiceId, patientId, totalAmount, insuranceCoverage, patientResponsibility, paymentStatus);
    }

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public Integer getRecordId() { return recordId; }
    public void setRecordId(Integer recordId) { this.recordId = recordId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public double getInsuranceCoverage() { return insuranceCoverage; }
    public void setInsuranceCoverage(double insuranceCoverage) { this.insuranceCoverage = insuranceCoverage; }
    public double getPatientResponsibility() { return patientResponsibility; }
    public void setPatientResponsibility(double patientResponsibility) { this.patientResponsibility = patientResponsibility; }
    public String getServiceDescription() { return serviceDescription; }
    public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }
    public Date getServiceDate() { return serviceDate; }
    public void setServiceDate(Date serviceDate) { this.serviceDate = serviceDate; }
    public Date getBillingDate() { return billingDate; }
    public void setBillingDate(Date billingDate) { this.billingDate = billingDate; }
    public BillingStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(BillingStatus paymentStatus) { this.paymentStatus = paymentStatus; }
}
