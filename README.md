# Hospital Management System

A simple Java-based application for managing a hospital's operations, including adding patients, adding doctors, viewing lists of patients and doctors, and booking appointments.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Environment Variables](#environment-variables)


## Introduction

The Hospital Management System is a console-based Java application designed to help hospitals manage their day-to-day operations. The system allows users to add and view patients, add and view doctors, and book appointments for patients with specific doctors.

## Features

- **Add Patients**: Add new patients to the system.
- **Add Doctors**: Add new doctors to the system.
- **View Patients**: View a list of all patients.
- **View Doctors**: View a list of all doctors.
- **Book Appointments**: Schedule appointments for patients with doctors.
- **Check Doctor Availability**: Before booking an appointment, the system checks if the doctor is available on the specified date.

## Technologies Used

- **Java**: The main programming language used for development.
- **MySQL**: Relational database used to store patient, doctor, and appointment information.
- **Maven**: Build automation tool used for managing dependencies.
- **Dotenv**: Library used for loading environment variables from a `.env` file.

## Prerequisites

- **Java 8 or higher**: Ensure that Java is installed on your machine.
- **MySQL**: Install MySQL and set up a database for the application.
- **Maven**: Install Maven for managing dependencies and building the project.

## Setup Instructions

1. **Clone the repository**:

    ```bash
    git clone https://github.com/yourusername/hospital-management-system.git
    cd hospital-management-system
    ```

2. **Create the MySQL database**:

    Open your MySQL client and run the following commands to create the database:

    ```sql
    CREATE DATABASE hospital_system;
    USE hospital_system;
    
    CREATE TABLE patients (
        id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        age VARCHAR(3) NOT NULL,
        gender VARCHAR(7) NOT NULL,
        PRIMARY KEY (id)
    );

    CREATE TABLE doctors (
        id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        specialty VARCHAR(100) NOT NULL,
        PRIMARY KEY (id)
    );

    CREATE TABLE appointments (
        id MEDIUMINT(8) NOT NULL AUTO_INCREMENT,
        patient_id MEDIUMINT(8) NOT NULL,
        doctor_id MEDIUMINT(8) NOT NULL,
        appointment_date DATE NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (patient_id) REFERENCES patients(id),
        FOREIGN KEY (doctor_id) REFERENCES doctors(id)
    );
    ```

3. **Configure environment variables**:

    Create a `.env` file in the root directory of your project with the following content:

    ```plaintext
    DB_URL=jdbc:mysql://localhost:3306/hospital_system
    DB_USER=root
    DB_PASSWORD=your_password
    ```

    Replace `your_password` with your actual MySQL root password.

4. **Build the project using Maven**:

    Run the following command to install dependencies and build the project:

    ```bash
    mvn clean install
    ```

5. **Run the application**:

    Execute the following command to start the application:

    ```bash
    mvn exec:java -Dexec.mainClass="com.jts.hms.HospitalManagement"
    ```

## Usage

After starting the application, you will be presented with a menu:

=== Hospital Management System ===

1. Add Patient
2. Add Doctor
3. View Patients
4. View Doctors
5. Book Appointment
6. Exit Enter your choice:


- **Add Patient**: Prompts you to enter patient details and saves them in the database.
- **Add Doctor**: Prompts you to enter doctor details and saves them in the database.
- **View Patients**: Displays a list of all patients currently stored in the database.
- **View Doctors**: Displays a list of all doctors currently stored in the database.
- **Book Appointment**: Allows you to schedule an appointment by entering the patient ID, doctor ID, and appointment date. Checks if the doctor is available before booking.
- **Exit**: Closes the application.

## Database Schema

The system uses a simple relational schema with three main tables:

- **patients**: Stores patient information.
- **doctors**: Stores doctor information.
- **appointments**: Stores appointments, linking patients with doctors and including appointment dates.

Refer to the setup instructions for the SQL commands used to create these tables.

## Environment Variables

The application uses a `.env` file to store sensitive information such as database credentials. This file should include:

```plaintext
DB_URL=jdbc:mysql://localhost:3306/hospital_system
DB_USER=root
DB_PASSWORD=your_password
