package model;

public class Coverage {
    private int coverageId;
    private int policyId;
    private String coverageType;
    private double coverageAmount;
    private String description;

    public Coverage() {}

    public Coverage(int coverageId, int policyId, String coverageType, double coverageAmount, String description) {
        this.coverageId = coverageId;
        this.policyId = policyId;
        this.coverageType = coverageType;
        this.coverageAmount = coverageAmount;
        this.description = description;
    }

    public int getCoverageId() {
        return coverageId;
    }

    public void setCoverageId(int coverageId) {
        this.coverageId = coverageId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Coverage{" +
                "coverageId=" + coverageId +
                ", policyId=" + policyId +
                ", coverageType='" + coverageType + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", description='" + description + '\'' +
                '}';
    }
}
