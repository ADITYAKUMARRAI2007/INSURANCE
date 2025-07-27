package model;

import java.sql.Date;

public class Renewal {
    private long renewalId;
    private long policyId;
    private Date renewalDate;
    private double renewalAmount;
    private Date dueDate;
    private int gracePeriod;
    private double penaltyAmount;

    public Renewal(long renewalId, long policyId, Date renewalDate, double renewalAmount,
                   Date dueDate, int gracePeriod, double penaltyAmount) {
        this.renewalId = renewalId;
        this.policyId = policyId;
        this.renewalDate = renewalDate;
        this.renewalAmount = renewalAmount;
        this.dueDate = dueDate;
        this.gracePeriod = gracePeriod;
        this.penaltyAmount = penaltyAmount;
    }

    public long getRenewalId() {
        return renewalId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public double getRenewalAmount() {
        return renewalAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setRenewalId(long renewalId) {
        this.renewalId = renewalId;
    }

    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public void setRenewalAmount(double renewalAmount) {
        this.renewalAmount = renewalAmount;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }
}
