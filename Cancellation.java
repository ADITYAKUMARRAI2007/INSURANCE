package model;

import java.sql.Date;

public class Cancellation {
    private int cancellationId;
    private int policyId;
    private Date cancellationDate;
    private String reason;
    public Cancellation(int cancellationId, int policyId, Date cancellationDate, String reason) {
        this.cancellationId = cancellationId;
        this.policyId = policyId;
        this.cancellationDate = cancellationDate;
        this.reason = reason;
    }
    public int getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(int cancellationId) {
        this.cancellationId = cancellationId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Cancellation{" +
                "cancellationId=" + cancellationId +
                ", policyId=" + policyId +
                ", cancellationDate=" + cancellationDate +
                ", reason='" + reason + '\'' +
                '}';
    }
}
