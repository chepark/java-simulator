package app.simulator.dao;

import app.simulator.database.DatabaseAccessor;
import app.simulator.models.Record;

import java.sql.*;
import java.util.*;

/***
 * Data Access Object for the event table in the database.
 */
public class RecordDao {

    /***
     * Get all records from the event table in database.
     * @return
     */
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

    /***
     * Save a record to the database
     * @param r record that includes service point name and customer ids
     */
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

    /***
     * Remove all records from the database
     */
    public void removeAllRecords() {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "DELETE FROM event";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
