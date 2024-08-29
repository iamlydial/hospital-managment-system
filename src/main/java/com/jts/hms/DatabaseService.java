package com.jts.hms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseService {
    public static Connection conn;

    private static Connection createConn() {
        try {
            // Load the environment variables from the .env file
            Dotenv dotenv = Dotenv.load();
            String dbUrl = dotenv.get("DB_URL");
            String dbUser = dotenv.get("DB_USER");
            String dbPassword = dotenv.get("DB_PASSWORD");

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection using the environment variables
            assert dbUrl != null;
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            System.out.println("Database connection created successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Please include it in your library path.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create the database connection.");
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        if(conn == null){
            return createConn();
        }
        return conn;
    }


}
