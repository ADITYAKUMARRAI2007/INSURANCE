package model;

import java.sql.Date;

public class Endorsement {
    private long endorsementId;
    private long policyId;
    private String fieldChange;
    private String changeType;
    private double oldValue;
    private double newValue;
    private String description;
    private Date effectiveDate;

    public Endorsement(long endorsementId, long policyId, String fieldChange, String changeType,
                       double oldValue, double newValue, String description, Date effectiveDate) {
        this.endorsementId = endorsementId;
        this.policyId = policyId;
        this.fieldChange = fieldChange;
        this.changeType = changeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.description = description;
        this.effectiveDate = effectiveDate;
    }

    public long getEndorsementId() {
        return endorsementId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public String getFieldChange() {
        return fieldChange;
    }

    public String getChangeType() {
        return changeType;
    }

    public double getOldValue() {
        return oldValue;
    }

    public double getNewValue() {
        return newValue;
    }

    public String getDescription() {
        return description;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }
}
