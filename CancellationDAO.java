package dao;

import model.Cancellation;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CancellationDAO {

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS cancellation (" +
                "cancellation_id INT PRIMARY KEY," +
                "policy_id INT," +
                "cancellation_date DATE," +
                "reason VARCHAR(100)," +
                "FOREIGN KEY(policy_id) REFERENCES policy(policy_id))";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCancellation(Cancellation cancellation) {
        String query = "INSERT INTO cancellation (cancellation_id, policy_id, cancellation_date, reason) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, cancellation.getCancellationId());
            pst.setInt(2, cancellation.getPolicyId());
            pst.setDate(3, cancellation.getCancellationDate());
            pst.setString(4, cancellation.getReason());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cancellation> getAllCancellations() {
        List<Cancellation> list = new ArrayList<>();
        String query = "SELECT * FROM cancellation";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cancellation c = new Cancellation(
                        rs.getInt("cancellation_id"),
                        rs.getInt("policy_id"),
                        rs.getDate("cancellation_date"),
                        rs.getString("reason")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Cancellation getCancellationById(int id) {
        String query = "SELECT * FROM cancellation WHERE cancellation_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Cancellation(
                            rs.getInt("cancellation_id"),
                            rs.getInt("policy_id"),
                            rs.getDate("cancellation_date"),
                            rs.getString("reason")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteCancellation(int id) {
        String query = "DELETE FROM cancellation WHERE cancellation_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
