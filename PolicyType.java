package model;

public class PolicyType {
    private int policyTypeId;
    private String policyName;
    private String description;
    private double writtenPremium;
    public PolicyType(int policyTypeId, String policyName, String description,double writtenPremium) {
        this.policyTypeId = policyTypeId;
        this.policyName = policyName;
        this.description = description;
        this.writtenPremium = writtenPremium;
    }

    public int getPolicyTypeId() {
        return policyTypeId;
    }

    public void setPolicyTypeId(int policyTypeId) {
        this.policyTypeId = policyTypeId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public double getWrittenPremium() {
        return writtenPremium;
    }
    
    public void setWrittenPremium(double writtenPremium) {
        this.writtenPremium = writtenPremium;
    }
}
