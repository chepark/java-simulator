package app.simulator.dao;

import app.simulator.database.DatabaseAccessor;
import app.simulator.models.WaitingTime;
import java.sql.*;
import java.util.*;

/***
 * Data Access Object for the waiting_time table in the database
 */
public class WaitingTimeDao {
    /***
     * Get all services from the database
     * @return list of all the services
     */
    public List<WaitingTime> getAllWaitTime() {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "SELECT id, service_name, total_customers, starting_time, ending_time, avg_waiting_time FROM waiting_time";
        List<WaitingTime> services = new ArrayList<WaitingTime>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            // turn into java readable format
            while (rs.next()) {
                int id = rs.getInt(1);
                String service_name = rs.getString(2);
                int total_customer = rs.getInt(3);
                double starting_time = rs.getDouble(4);
                double ending_time = rs.getDouble(5);
                double avg_waiting_time = rs.getDouble(6);

                WaitingTime wt = new WaitingTime(id, service_name, total_customer, starting_time, ending_time, avg_waiting_time);
                services.add(wt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }

    /***
     * Get a service from the database based on the id
     * @param id
     * @return
     */
    public WaitingTime getWaitTime(int id) {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "SELECT service_name, total_customers, starting_time, ending_time, avg_waiting_time FROM waiting_time WHERE id=?";

        String service_name = null;
         int total_customer = 0;
         double starting_time = 0;
         double ending_time = 0;
        double avg_waiting_time = 0;
        int count = 0;

        try {
            Statement s = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            // turn into java readable format
            while (rs.next()) {
                count++;
                service_name = rs.getString(1);
                total_customer = rs.getInt(2);
                starting_time = rs.getDouble(3);
                ending_time = rs.getDouble(4);
                avg_waiting_time = rs.getDouble(5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count==1) {
            return new WaitingTime(id, service_name, total_customer, starting_time, ending_time, avg_waiting_time);
        }
        else {
            return null;
        }
    }

    /***
     * Save a service in the database
     * @param wt
     */
    public void putWaitTime(WaitingTime wt) {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "INSERT INTO waiting_time (service_name, total_customers, starting_time, ending_time, avg_waiting_time) VALUES (?, ?, ?, ?, ? )";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, wt.getServiceName());
            ps.setInt(2, wt.getTotalCustomers());
            ps.setDouble(3, wt.getStartingTime());
            ps.setDouble(4, wt.getEndingTime());
            ps.setDouble(5, wt.getAverageWaitingTime());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
