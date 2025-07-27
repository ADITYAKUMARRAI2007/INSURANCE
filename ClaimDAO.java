package dao;

import model.Claim;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClaimDAO {

    public void addClaim(Claim claim) {
        String sql = "INSERT INTO claim (policy_id, incident_date, submission_date, approval_date, payout_date, " +
                "amount_requested, amount_approved, status, incident_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, claim.getPolicyId());
            stmt.setDate(2, Date.valueOf(claim.getIncidentDate()));
            stmt.setDate(3, Date.valueOf(claim.getSubmissionDate()));
            stmt.setDate(4, Date.valueOf(claim.getApprovalDate()));
            stmt.setDate(5, Date.valueOf(claim.getPayoutDate()));
            stmt.setDouble(6, claim.getAmountRequested());
            stmt.setDouble(7, claim.getAmountApproved());
            stmt.setString(8, claim.getStatus());
            stmt.setString(9, claim.getIncidentType());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Claim getClaimById(long id) {
        String sql = "SELECT * FROM claim WHERE claim_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractClaim(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claim";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                claims.add(extractClaim(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return claims;
    }

    public void updateClaimStatus(long id, String newStatus) {
        String sql = "UPDATE claim SET status = ? WHERE claim_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newStatus);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClaim(long id) {
        String sql = "DELETE FROM claim WHERE claim_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Claim extractClaim(ResultSet rs) throws SQLException {
        return new Claim(
                rs.getLong("claim_id"),
                rs.getLong("policy_id"),
                rs.getDate("incident_date").toLocalDate(),
                rs.getDate("submission_date").toLocalDate(),
                rs.getDate("approval_date").toLocalDate(),
                rs.getDate("payout_date").toLocalDate(),
                rs.getDouble("amount_requested"),
                rs.getDouble("amount_approved"),
                rs.getString("status"),
                rs.getString("incident_type")
        );
    }
}
