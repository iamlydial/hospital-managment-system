package com.jts.hms;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class HospitalManagement {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        try(Connection conn = DatabaseService.getConnection()){
            Patient patient = new Patient(conn);
            Doctor doctor = new Doctor(conn);
            BookAppointment bookAppointment = new BookAppointment(conn, patient, doctor);


            while (true){
                System.out.println("=== Hospital Management System ===");
                System.out.println("1. Add Patient");
                System.out.println("2. Add Doctor");
                System.out.println("3. View Patients");
                System.out.println("4. View Doctors");
                System.out.println("5. Book Appointment");
                System.out.println("6. Exit");

                System.out.println("Enter your choice: ");

                int ch = sc.nextInt();

                switch (ch){
                    case 1:{
                        // add patients
                        patient.addPatient();
                        break;
                    }
                    case 2:{
                        doctor.addDoctor();
                        break;
                    }
                    case 3:{
                        // View Patients
                        patient.viewPatients();
                        break;
                    }
                    case 4:{
                        // View Doctors
                        doctor.viewDoctors();
                        break;
                    }
                    case 5:{
                        // Book Appointment
                        bookAppointment.bookAppointment();
                        break;
                    }
                    case 6:{
                        // close scanner
                        sc.close();
                        break;
                    }
                    default:
                        System.out.println("Please enter valid choice.");
                        break;
                }
            }
        }
    }
}
