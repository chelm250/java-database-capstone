package com.project.back_end.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcedureInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProcedureInitializer.class);

    private final JdbcTemplate jdbc;

    public ProcedureInitializer(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void run(String... args) {
        try {
            log.info("Dropping procedure if it exists...");
            jdbc.execute("DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor");

            log.info("Creating procedure GetDailyAppointmentReportByDoctor...");
            jdbc.execute(
                "CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN report_date DATE) " +
                "BEGIN " +
                "SELECT d.name AS doctor_name, " +
                "       a.appointment_time, " +
                "       a.status, " +
                "       p.name AS patient_name, " +
                "       p.phone AS patient_phone " +
                "FROM appointment a " +
                "JOIN doctor d ON a.doctor_id = d.id " +
                "JOIN patient p ON a.patient_id = p.id " +
                "WHERE DATE(a.appointment_time) = report_date " +
                "ORDER BY d.name, a.appointment_time; " +
                "END"
            );

            log.info("Dropping procedure GetDoctorWithMostPatientsByMonth if it exists...");
            jdbc.execute("DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth");
    
            log.info("Creating procedure GetDoctorWithMostPatientsByMonth...");
            jdbc.execute(
                "CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(" +
                "    IN input_month INT, " +
                "    IN input_year INT" +
                ") " +
                "BEGIN " +
                "    SELECT doctor_id, COUNT(patient_id) AS patients_seen " +
                "    FROM appointment " +
                "    WHERE MONTH(appointment_time) = input_month " +
                "      AND YEAR(appointment_time) = input_year " +
                "    GROUP BY doctor_id " +
                "    ORDER BY patients_seen DESC " +
                "    LIMIT 1; " +
                "END"
            );

            log.info("Dropping procedure GetDoctorWithMostPatientsByYear if it exists...");
            jdbc.execute("DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear");
    
            // Create new procedure
            log.info("Creating procedure GetDoctorWithMostPatientsByYear...");
            jdbc.execute(
                "CREATE PROCEDURE GetDoctorWithMostPatientsByYear(" +
                "    IN input_year INT" +
                ") " +
                "BEGIN " +
                "    SELECT doctor_id, COUNT(patient_id) AS patients_seen " +
                "    FROM appointment " +
                "    WHERE YEAR(appointment_time) = input_year " +
                "    GROUP BY doctor_id " +
                "    ORDER BY patients_seen DESC " +
                "    LIMIT 1; " +
                "END"
            );

            log.info("Procedure created successfully.");
        } catch (Exception e) {
            log.error("Failed to create stored procedure: {}", e.getMessage(), e);
        }
    }
}
