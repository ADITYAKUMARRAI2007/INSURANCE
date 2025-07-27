package model;

import java.sql.Date;

public class Policy {
    private int policyId;
    private int customerId;
    private String policyType;
    private Date startDate;
    private Date endDate;
    private double premiumAmount;
    private String status;

    public Policy(int policyId, int customerId, String policyType, Date startDate, Date endDate, double premiumAmount, String status) {
        this.policyId = policyId;
        this.customerId = customerId;
        this.policyType = policyType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.premiumAmount = premiumAmount;
        this.status = status;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getIssueDate() {
        return this.startDate;
    }
    public Date getExpiryDate() {
        return this.endDate;
    }
}
