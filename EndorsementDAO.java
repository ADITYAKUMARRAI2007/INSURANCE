package dao;

import model.Endorsement;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndorsementDAO {

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS endorsement (" +
                "endorsement_id BIGINT PRIMARY KEY," +
                "policy_id BIGINT," +
                "field_change VARCHAR(100)," +
                "change_type VARCHAR(100)," +
                "old_value DECIMAL(10,2)," +
                "new_value DECIMAL(10,2)," +
                "description TEXT," +
                "effective_date DATE," +
                "FOREIGN KEY (policy_id) REFERENCES policy(policy_id))";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEndorsement(Endorsement endorsement) {
        String sql = "INSERT INTO endorsement (endorsement_id, policy_id, field_change, change_type, old_value, new_value, description, effective_date) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, endorsement.getEndorsementId());
            pst.setLong(2, endorsement.getPolicyId());
            pst.setString(3, endorsement.getFieldChange());
            pst.setString(4, endorsement.getChangeType());
            pst.setDouble(5, endorsement.getOldValue());
            pst.setDouble(6, endorsement.getNewValue());
            pst.setString(7, endorsement.getDescription());
            pst.setDate(8, endorsement.getEffectiveDate());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Endorsement> getAllEndorsements() {
        List<Endorsement> list = new ArrayList<>();
        String sql = "SELECT * FROM endorsement";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Endorsement(
                        rs.getLong("endorsement_id"),
                        rs.getLong("policy_id"),
                        rs.getString("field_change"),
                        rs.getString("change_type"),
                        rs.getDouble("old_value"),
                        rs.getDouble("new_value"),
                        rs.getString("description"),
                        rs.getDate("effective_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
