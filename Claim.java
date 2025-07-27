package model;

import java.time.LocalDate;

public class Claim {
    private long claimId;
    private long policyId;
    private LocalDate incidentDate;
    private LocalDate submissionDate;
    private LocalDate approvalDate;
    private LocalDate payoutDate;
    private double amountRequested;
    private double amountApproved;
    private String status;
    private String incidentType;

    public Claim() {}

    public Claim(long claimId, long policyId, LocalDate incidentDate, LocalDate submissionDate,
                 LocalDate approvalDate, LocalDate payoutDate, double amountRequested,
                 double amountApproved, String status, String incidentType) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.incidentDate = incidentDate;
        this.submissionDate = submissionDate;
        this.approvalDate = approvalDate;
        this.payoutDate = payoutDate;
        this.amountRequested = amountRequested;
        this.amountApproved = amountApproved;
        this.status = status;
        this.incidentType = incidentType;
    }
    public long getClaimId() { return claimId; }
    public void setClaimId(long claimId) { this.claimId = claimId; }

    public long getPolicyId() { return policyId; }
    public void setPolicyId(long policyId) { this.policyId = policyId; }

    public LocalDate getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDate incidentDate) { this.incidentDate = incidentDate; }

    public LocalDate getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(LocalDate submissionDate) { this.submissionDate = submissionDate; }

    public LocalDate getApprovalDate() { return approvalDate; }
    public void setApprovalDate(LocalDate approvalDate) { this.approvalDate = approvalDate; }

    public LocalDate getPayoutDate() { return payoutDate; }
    public void setPayoutDate(LocalDate payoutDate) { this.payoutDate = payoutDate; }

    public double getAmountRequested() { return amountRequested; }
    public void setAmountRequested(double amountRequested) { this.amountRequested = amountRequested; }

    public double getAmountApproved() { return amountApproved; }
    public void setAmountApproved(double amountApproved) { this.amountApproved = amountApproved; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getIncidentType() { return incidentType; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }
}
