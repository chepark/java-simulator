package app.simulator.dao;

import app.simulator.database.DatabaseAccessor;
import app.simulator.entity.Record;
import app.simulator.models.ServicePoint;

import java.sql.*;
import java.util.*;

public class RecordDao {
    public List<Record> getAllRecords() {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "SELECT id, service_point, customers FROM event";
        List<Record> events = new ArrayList<>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // turn into java readable format
            while (rs.next()) {
                int id = rs.getInt(1);
                String service_point = rs.getString(2);
                String customers = rs.getString(3);

                Record record = new Record(id, service_point, customers);
                events.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    public void saveRecord(Record r) {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "INSERT INTO event (service_point, customers) VALUES (?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, r.getServicePoint());
            if (r.getCustomers().isEmpty()) {
                // Handle empty string case or set to NULL if needed
                ps.setNull(2, Types.VARCHAR); // Set to NULL
            } else {
                ps.setString(2, r.getCustomers());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
