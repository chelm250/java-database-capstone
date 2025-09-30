package com.project.back_end.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back_end.models.Appointment;
import com.project.back_end.repo.AppointmentRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;

@Service
public class AppointmentService {

    // Repositories and Services that will be used in methods below
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private Service service;


    // Methods for booking, updating, canceling, and retrieving appointments would go here.
    public int bookAppointment(Appointment appointment) {
        try {
            appointmentRepository.save(appointment);
            System.out.println("Appointment booked successfully: " + appointment);
            return 1; // Success
        } catch (Exception e) {
            System.err.println("Error booking appointment: " + e.getMessage());
            return 0; // Failure
        }
        
    }

    public ResponseEntity<Map<String, String>> updateAppointment(Appointment appointment) {
        Map<String, String> response = new HashMap<>();

        // Step 1: Check if appointment exists
        Optional<Appointment> existing = appointmentRepository.findById(appointment.getId());

        if (existing.isEmpty()) {
            response.put("message", "Appointment not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Step 2: Validate the appointment
        if (appointment.getAppointmentTime() == null || appointment.getAppointmentTime().isBefore(LocalDateTime.now())) {
            response.put("message", "Invalid appointment data.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Step 3: Save the updated appointment
        try {
            appointmentRepository.save(appointment);
            response.put("message", "Appointment updated successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error updating appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public ResponseEntity<Map<String, String>> cancelAppointment(Long id, String token) {
        Map<String, String> response = new HashMap<>();

        // Extract patient ID from token
        Long patientId = tokenService.getUserIdFromToken(token);
        if (patientId == null) {
            response.put("message", "Invalid token.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Step 1: Check if appointment exists
        Optional<Appointment> existing = appointmentRepository.findById(id);

        if (existing.isEmpty()) {
            response.put("message", "Appointment not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        Appointment appointment = existing.get();

        // Step 2: Validate patient ID
        if (!appointment.getPatient().getId().equals(patientId)) {
            response.put("message", "Unauthorized to cancel this appointment.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Step 3: Delete the appointment
        try {
            appointmentRepository.deleteById(id);
            response.put("message", "Appointment canceled successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error canceling appointment: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    public Map<String, Object> getAppointment(String name, LocalDate date, String token) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Step 1: Extract doctor ID from token
            Long doctorId = tokenService.getUserIdFromToken(token);

            // Step 2: Define time range for the given date
            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.atTime(LocalTime.MAX);

            // Step 3: Fetch appointments for doctor and date
            List<Appointment> appointments = appointmentRepository
                .findByDoctorIdAndAppointmentTimeBetween(doctorId, start, end);

            // Step 4: Filter by patient name if provided
            if (name != null && !name.trim().isEmpty()) {
                appointments = appointments.stream()
                    .filter(a -> a.getPatient().getName().contains(name))
                    .collect(Collectors.toList());
            }

            // Step 5: Return results
            response.put("appointments", appointments);
            response.put("message", "Appointments retrieved successfully");
        } catch (Exception e) {
            response.put("appointments", Collections.emptyList());
            response.put("message", "Error retrieving appointments: " + e.getMessage());
        }

        return response;
    }





// 1. **Add @Service Annotation**:
//    - To indicate that this class is a service layer class for handling business logic.
//    - The `@Service` annotation should be added before the class declaration to mark it as a Spring service component.
//    - Instruction: Add `@Service` above the class definition.

// 2. **Constructor Injection for Dependencies**:
//    - The `AppointmentService` class requires several dependencies like `AppointmentRepository`, `Service`, `TokenService`, `PatientRepository`, and `DoctorRepository`.
//    - These dependencies should be injected through the constructor.
//    - Instruction: Ensure constructor injection is used for proper dependency management in Spring.

// 3. **Add @Transactional Annotation for Methods that Modify Database**:
//    - The methods that modify or update the database should be annotated with `@Transactional` to ensure atomicity and consistency of the operations.
//    - Instruction: Add the `@Transactional` annotation above methods that interact with the database, especially those modifying data.

// 4. **Book Appointment Method**:
//    - Responsible for saving the new appointment to the database.
//    - If the save operation fails, it returns `0`; otherwise, it returns `1`.
//    - Instruction: Ensure that the method handles any exceptions and returns an appropriate result code.

// 5. **Update Appointment Method**:
//    - This method is used to update an existing appointment based on its ID.
//    - It validates whether the patient ID matches, checks if the appointment is available for updating, and ensures that the doctor is available at the specified time.
//    - If the update is successful, it saves the appointment; otherwise, it returns an appropriate error message.
//    - Instruction: Ensure proper validation and error handling is included for appointment updates.

// 6. **Cancel Appointment Method**:
//    - This method cancels an appointment by deleting it from the database.
//    - It ensures the patient who owns the appointment is trying to cancel it and handles possible errors.
//    - Instruction: Make sure that the method checks for the patient ID match before deleting the appointment.

// 7. **Get Appointments Method**:
//    - This method retrieves a list of appointments for a specific doctor on a particular day, optionally filtered by the patient's name.
//    - It uses `@Transactional` to ensure that database operations are consistent and handled in a single transaction.
//    - Instruction: Ensure the correct use of transaction boundaries, especially when querying the database for appointments.

// 8. **Change Status Method**:
//    - This method updates the status of an appointment by changing its value in the database.
//    - It should be annotated with `@Transactional` to ensure the operation is executed in a single transaction.
//    - Instruction: Add `@Transactional` before this method to ensure atomicity when updating appointment status.


}
