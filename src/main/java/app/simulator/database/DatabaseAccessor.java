package app.simulator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseAccessor {
    public static Connection databaseLink;

    public static void terminate() {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        final String DB_NAME="simulator";
        final String DB_USER="root";
        final String DB_PASSWORD="root";
        final String DB_URL="jdbc:mysql://localhost:3306/"+DB_NAME;

        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            //System.out.println("Connected to database: " + DB_NAME);
        }  catch (ClassNotFoundException | SQLException e) {
            System.out.println("Driver not found: ");
            e.printStackTrace();
        }

        return databaseLink;
    }
}
