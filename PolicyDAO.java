package dao;

import model.Policy;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PolicyDAO {
    public int addPolicy(Policy policy) {
        if (policy.getCustomerId() <= 0) {
            throw new IllegalArgumentException("customerId must be a valid persisted id (>0)");
        }
    
        String query = "INSERT INTO policy (customer_id, policy_type, issue_date, expiry_date, premium_amount, status) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
    
            stmt.setInt(1, policy.getCustomerId());
            stmt.setString(2, policy.getPolicyType());
            stmt.setDate(3, policy.getIssueDate());
            stmt.setDate(4, policy.getExpiryDate());
            stmt.setDouble(5, policy.getPremiumAmount());
            stmt.setString(6, policy.getStatus());
    
            int affected = stmt.executeUpdate();
            if (affected == 0) throw new SQLException("Creating policy failed, no rows affected.");
    
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int newPolicyId = rs.getInt(1);
                    System.out.println(" Policy inserted with ID: " + newPolicyId);
                    return newPolicyId;
                }
                throw new SQLException("Creating policy failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<Policy> getAllPolicies() {
        List<Policy> list = new ArrayList<>();
        String query = "SELECT * FROM policy";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Policy(
                    rs.getInt("id"),  
                    rs.getInt("customer_id"),
                    rs.getString("policy_type"),
                    rs.getDate("issue_date"),
                    rs.getDate("expiry_date"),
                    rs.getDouble("premium_amount"),
                    rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public Policy getPolicyById(int policyId) {
        String query = "SELECT * FROM policy WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, policyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Policy(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getString("policy_type"),
                        rs.getDate("issue_date"),
                        rs.getDate("expiry_date"),
                        rs.getDouble("premium_amount"),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void updatePolicyStatus(int policyId, String newStatus) {
        String query = "UPDATE policy SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newStatus);
            stmt.setInt(2, policyId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" Policy status updated successfully.");
            } else {
                System.out.println(" No policy found with ID: " + policyId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePolicy(int policyId) {
        String query = "DELETE FROM policy WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, policyId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println(" Policy deleted successfully.");
            } else {
                System.out.println(" No policy found with ID: " + policyId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
