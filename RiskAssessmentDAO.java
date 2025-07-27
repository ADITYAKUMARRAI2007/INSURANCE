package dao;

import java.sql.*;
import java.util.*;
import model.RiskAssessment;
import util.DBConnection;

public class RiskAssessmentDAO {
    private Connection conn;

    public RiskAssessmentDAO() throws SQLException {
        this.conn = DBConnection.getConnection();
    }
    public void addRiskAssessment(RiskAssessment risk) throws SQLException {
        String query = "INSERT INTO riskassessment (customer_id, risk_level, notes) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, risk.getCustomerId());
            stmt.setString(2, risk.getRiskLevel());
            stmt.setString(3, risk.getRiskFactors());
            stmt.executeUpdate();
            System.out.println(" RiskAssessment inserted successfully.");
        }
    }
    public List<RiskAssessment> getAllRiskAssessments() throws SQLException {
        List<RiskAssessment> list = new ArrayList<>();
        String query = "SELECT * FROM riskassessment";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new RiskAssessment(
                    rs.getInt("id"),                
                    rs.getInt("customer_id"),
                    rs.getString("risk_level"),
                    rs.getString("notes")         
                ));
            }
        }
        return list;
    }
}
