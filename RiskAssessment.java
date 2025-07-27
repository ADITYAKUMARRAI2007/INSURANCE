package model;

public class RiskAssessment {
    private int riskId;
    private int customerId;
    private String riskLevel;
    private String riskFactors;

    public RiskAssessment(int riskId, int customerId, String riskLevel, String riskFactors) {
        this.riskId = riskId;
        this.customerId = customerId;
        this.riskLevel = riskLevel;
        this.riskFactors = riskFactors;
    }

    public int getRiskId() {
        return riskId;
    }

    public void setRiskId(int riskId) {
        this.riskId = riskId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getRiskFactors() {
        return riskFactors;
    }

    public void setRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
    }
}
