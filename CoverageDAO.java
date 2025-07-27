package dao;

import model.Coverage;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoverageDAO {

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS coverage (" +
                "coverage_id INT PRIMARY KEY," +
                "policy_id INT," +
                "coverage_type VARCHAR(100)," +
                "coverage_amount DOUBLE," +
                "description VARCHAR(255)," +
                "FOREIGN KEY (policy_id) REFERENCES policy(policy_id))";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCoverage(Coverage coverage) {
        String sql = "INSERT INTO coverage (coverage_id, policy_id, coverage_type, coverage_amount, description) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, coverage.getCoverageId());
            pst.setInt(2, coverage.getPolicyId());
            pst.setString(3, coverage.getCoverageType());
            pst.setDouble(4, coverage.getCoverageAmount());
            pst.setString(5, coverage.getDescription());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Coverage> getAllCoverage() {
        List<Coverage> list = new ArrayList<>();
        String sql = "SELECT * FROM coverage";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Coverage(
                        rs.getInt("coverage_id"),
                        rs.getInt("policy_id"),
                        rs.getString("coverage_type"),
                        rs.getDouble("coverage_amount"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Coverage getCoverageById(int id) {
        String sql = "SELECT * FROM coverage WHERE coverage_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Coverage(
                            rs.getInt("coverage_id"),
                            rs.getInt("policy_id"),
                            rs.getString("coverage_type"),
                            rs.getDouble("coverage_amount"),
                            rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteCoverage(int id) {
        String sql = "DELETE FROM coverage WHERE coverage_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoverageAmount(int coverageId, double newAmount) {
        String sql = "UPDATE coverage SET coverage_amount = ? WHERE coverage_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setDouble(1, newAmount);
            pst.setInt(2, coverageId);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
