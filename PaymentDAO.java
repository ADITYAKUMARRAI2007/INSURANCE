package dao;

import model.Payment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    public int addPayment(Payment payment) {
        final String sql =
                "INSERT INTO payment (policy_id, payment_date, amount, payment_method, transaction_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, payment.getPolicyId());                
            ps.setDate(2, payment.getPaymentDate());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getTransactionId());

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Inserting payment failed, no rows affected.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int newId = rs.getInt(1);
                    System.out.println(" Payment inserted with ID: " + newId);
                    return newId;
                }
            }
            throw new SQLException("Inserting payment failed, no ID obtained.");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public Payment getPaymentById(int paymentId) {
        final String sql = "SELECT * FROM payment WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Payment> getAllPayments() {
        final String sql = "SELECT * FROM payment";
        List<Payment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean updatePayment(Payment payment) {
        final String sql = "UPDATE payment SET policy_id = ?, payment_date = ?, amount = ?, " +
                           "payment_method = ?, transaction_id = ? WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, payment.getPolicyId());
            ps.setDate(2, payment.getPaymentDate());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setString(5, payment.getTransactionId());
            ps.setInt(6, payment.getPaymentId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deletePayment(int paymentId) {
        final String sql = "DELETE FROM payment WHERE payment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, paymentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private Payment map(ResultSet rs) throws SQLException {
        return new Payment(
                rs.getInt("payment_id"),
                rs.getInt("policy_id"),
                rs.getDate("payment_date"),
                rs.getDouble("amount"),
                rs.getString("payment_method"),
                rs.getString("transaction_id")
        );
        }
}
