package com.jts.hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection conn;
    Scanner sc = new Scanner(System.in);
    public Doctor(Connection conn){
        this.conn = conn;
    }

    public void addDoctor() throws SQLException {
        System.out.print("Enter Doctor name: ");
        String name = sc.next();

        System.out.print("Enter Doctor department: ");
        String department = sc.next();


        String query = "insert into Doctors(name, department) values (?,?)";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, name);
            ps.setString(2, department);

            if(ps.execute()){
                System.out.println("Doctor details added successfully");
            } else {
                System.out.println("Failed to add doctor details");
            }

        }
    }

    public void viewDoctors() throws SQLException{
        String query = "select * from doctors";
        try (PreparedStatement ps = conn.prepareStatement(query)){
            try(ResultSet rs = ps.executeQuery()){
                System.out.println("Doctors details : ");

                while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String department = rs.getString("department");
                    System.out.println("Doctor id : "+ id);
                    System.out.println("Doctor name : "+ name);
                    System.out.println("Doctor department : "+ department);

                }
            }
        }
    }

    public boolean getDoctorById(int id) throws SQLException{
        String query = "select count(1) from doctors where id = ?";

        try (PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

}
