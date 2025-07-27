package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PolicyType;
import util.DBConnection;

public class PolicyTypeDAO {
    public int addPolicyType(PolicyType policyType) {
        String sql = "INSERT INTO policytype (type_name, description, written_premium) VALUES (?, ?, ?)";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
    
            pstmt.setString(1, policyType.getPolicyName());
            pstmt.setString(2, policyType.getDescription());
            pstmt.setDouble(3, policyType.getWrittenPremium());
    
            int affectedRows = pstmt.executeUpdate();
    
            if (affectedRows == 0) {
                throw new SQLException("Creating policy type failed, no rows affected.");
            }
    
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating policy type failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public PolicyType getPolicyTypeById(int policyTypeId) {
        String sql = "SELECT * FROM policytype WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, policyTypeId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new PolicyType(
                    rs.getInt("id"),
                    rs.getString("type_name"),
                    rs.getString("description"),
                    rs.getDouble("written_premium")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updatePolicyType(PolicyType policyType) {
        String sql = "UPDATE policytype SET type_name = ?, description = ?, written_premium = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, policyType.getPolicyName());
            pstmt.setString(2, policyType.getDescription());
            pstmt.setDouble(3, policyType.getWrittenPremium());
            pstmt.setInt(4, policyType.getPolicyTypeId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("PolicyType updated successfully.");
            } else {
                System.out.println(" No PolicyType found with ID: " + policyType.getPolicyTypeId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePolicyType(int policyTypeId) {
        String sql = "DELETE FROM policytype WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, policyTypeId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println(" PolicyType deleted successfully.");
            } else {
                System.out.println(" No PolicyType found with ID: " + policyTypeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
