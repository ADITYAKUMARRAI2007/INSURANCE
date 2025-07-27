package dao;

import model.Renewal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RenewalDAO {
    private final Connection connection;

    public RenewalDAO(Connection connection) {
        this.connection = connection;
    }
    public void addRenewal(Renewal renewal) throws SQLException {
        String sql = "INSERT INTO renewal (renewal_id, policy_id, renewal_date, renewal_amount, due_date, grace_period, penalty_amount) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, renewal.getRenewalId());
            stmt.setLong(2, renewal.getPolicyId());
            stmt.setDate(3, renewal.getRenewalDate());
            stmt.setDouble(4, renewal.getRenewalAmount());
            stmt.setDate(5, renewal.getDueDate());
            stmt.setInt(6, renewal.getGracePeriod());
            stmt.setDouble(7, renewal.getPenaltyAmount());
            stmt.executeUpdate();
        }
    }
    public Renewal getRenewalById(long renewalId) throws SQLException {
        String sql = "SELECT * FROM renewal WHERE renewal_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, renewalId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToRenewal(rs);
                }
            }
        }
        return null;
    }
    public List<Renewal> getAllRenewals() throws SQLException {
        List<Renewal> renewals = new ArrayList<>();
        String sql = "SELECT * FROM renewal";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                renewals.add(mapRowToRenewal(rs));
            }
        }
        return renewals;
    }
    public void updateRenewal(Renewal renewal) throws SQLException {
        String sql = "UPDATE renewal SET policy_id = ?, renewal_date = ?, renewal_amount = ?, due_date = ?, " +
                     "grace_period = ?, penalty_amount = ? WHERE renewal_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, renewal.getPolicyId());
            stmt.setDate(2, renewal.getRenewalDate());
            stmt.setDouble(3, renewal.getRenewalAmount());
            stmt.setDate(4, renewal.getDueDate());
            stmt.setInt(5, renewal.getGracePeriod());
            stmt.setDouble(6, renewal.getPenaltyAmount());
            stmt.setLong(7, renewal.getRenewalId());
            stmt.executeUpdate();
        }
    }
    public void deleteRenewal(long renewalId) throws SQLException {
        String sql = "DELETE FROM renewal WHERE renewal_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, renewalId);
            stmt.executeUpdate();
        }
    }
    private Renewal mapRowToRenewal(ResultSet rs) throws SQLException {
        return new Renewal(
            rs.getLong("renewal_id"),
            rs.getLong("policy_id"),
            rs.getDate("renewal_date"),
            rs.getDouble("renewal_amount"),
            rs.getDate("due_date"),
            rs.getInt("grace_period"),
            rs.getDouble("penalty_amount")
        );
    }
}
