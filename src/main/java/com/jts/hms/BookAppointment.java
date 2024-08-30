package com.jts.hms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookAppointment {

    private Connection connection;
    private  Patient patient;
    private Doctor doctor;

    Scanner scanner = new Scanner(System.in);

    public BookAppointment(Connection connection, Patient patient, Doctor doctor){
        this.connection = connection;
        this.patient = patient;
        this.doctor = doctor;
    }

    public void bookAppointment() throws SQLException {

        System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();

        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();

        System.out.print("Enter appointment date: yyyy-mm-dd");
        String appointmentDate = scanner.next();

        if(!patient.getPatientById(patientId)){
            System.out.println("Please provide valid patient id.");
            return;
        }

        if(!doctor.getDoctorById(doctorId)){
            System.out.println("Please provide valid doctor id.");
            return;
        }

        if(checkAvailability(connection, doctorId, appointmentDate)){
            System.out.println("Doctor not available");
        }

        String query = "insert into appointments(patient_id, doctor_id, appointment_date) values(?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement((query))){
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            preparedStatement.setString(3, appointmentDate);

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Appointment booked successfully.");
            } else {
                System.out.println("Appointment not booked successfully.");
            }
        }
    }

    public boolean checkAvailability(Connection connection, int doctorId, String appointmentDate) throws SQLException{
        String query = "select count(1) from appointments WHERE doctor_id = ? AND appointment_date = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt(1) == 0;
                }
            }
        }
        return false;
    }

}
