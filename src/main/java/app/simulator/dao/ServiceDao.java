package app.simulator.dao;

import app.simulator.database.DatabaseAccessor;
import app.simulator.entity.Service;
import java.sql.*;
import java.util.*;

public class ServiceDao {

    public List<Service> getAllServices() {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "SELECT service_name, total_customers, starting_time, ending_time, avg_service_time FROM service";
        List<Service> services = new ArrayList<Service>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                String service_name = rs.getString(1);
                int total_customer = rs.getInt(2);
                double starting_time = rs.getDouble(3);
                double ending_time = rs.getDouble(4);
                double avg_service_time = rs.getDouble(5);

                Service emp = new Service(service_name, total_customer, starting_time, ending_time, avg_service_time);
                services.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }


    public Service getService(int id) {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "SELECT service_name, total_customers, starting_time, ending_time, avg_service_time FROM service WHERE id=?";

        String service_name = null;
         int total_customer = 0;
         double starting_time = 0;
         double ending_time = 0;
         double avg_service_time = 0;
        int count = 0;

        try {
            Statement s = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count++;
                service_name = rs.getString(1);
                total_customer = rs.getInt(2);
                starting_time = rs.getDouble(3);
                ending_time = rs.getDouble(4);
                avg_service_time = rs.getDouble(5);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count==1) {
            return new Service(service_name, total_customer, starting_time, ending_time, avg_service_time);
        }
        else {
            return null;
        }
    }

    public void putService(Service emp) {
        Connection conn = DatabaseAccessor.getConnection();
        String sql = "INSERT INTO service (service_name, total_customers, starting_time, ending_time, avg_service_time) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getServiceName());
            ps.setInt(2, emp.getTotalCustomers());
            ps.setDouble(3, emp.getStartingTime());
            ps.setDouble(4, emp.getEndingTime());
            ps.setDouble(5, emp.getAverageServiceTime());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
