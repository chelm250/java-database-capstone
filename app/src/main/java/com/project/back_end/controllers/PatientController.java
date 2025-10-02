package com.project.back_end.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.back_end.DTO.Login;
import com.project.back_end.models.Patient;
import com.project.back_end.services.ClinicService;
import com.project.back_end.services.PatientService;
import com.project.back_end.services.TokenService;


@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ClinicService service;


    @GetMapping("/patient")
    public ResponseEntity<Map<String, Object>> getPatient(String token) {
        // Validate the token for patient role
        if (!tokenService.validateToken(token, "patient")) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));
        }
        // Fetch patient details
        return patientService.getPatientDetails(token);
    }

    @PostMapping("/patient/register")
    public ResponseEntity<Map<String, String>> createPatient(Patient patient) {
        // Check if patient already exists
        String email = patient.getEmail();
        if (patientService.checkPatientExists(email)) {
            return ResponseEntity.status(400).body(Map.of("message", "Patient already exists"));
        }
        // Create new patient
        int result = patientService.createPatient(patient);
        if (result == 1) {
            return ResponseEntity.ok(Map.of("message", "Patient created successfully"));
        } else {
            return ResponseEntity.status(500).body(Map.of("message", "Error creating patient"));
        }
    }

    @PostMapping("/patient/login")
    public ResponseEntity<Map<String, Object>> validateLogin(Login login) {
        try {
            if (service.validatePatientLogin(login) == null) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid email/username or password"));
            }
            return ResponseEntity.status(200).body(Map.of("message", "Login successful", "token", service.validatePatientLogin(login)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("message", "Error during login: " + e.getMessage()));
        }

    }

    @GetMapping("/patient/{id}/{token}")
    public ResponseEntity<Map<String, Object>> getPatientAppointment(Long id, String token) {
        // Validate the token for patient role
        if (!tokenService.validateToken(token, "patient")) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));
        }
        // Fetch patient appointments
        return patientService.getPatientAppointment(id, token);
    }

    @GetMapping("/patient/filter/{condition}/{name}/{token}")
    public ResponseEntity<Map<String, Object>> filterPatientAppointment(String condition, String name, String token) {
        // Validate the token for patient role
        if (!tokenService.validateToken(token, "patient")) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));
        }
        // Filter patient appointments based on condition and name
        return ResponseEntity.ok(service.filterPatient(token, condition, name));
    }

    













// 1. Set Up the Controller Class:
//    - Annotate the class with `@RestController` to define it as a REST API controller for patient-related operations.
//    - Use `@RequestMapping("/patient")` to prefix all endpoints with `/patient`, grouping all patient functionalities under a common route.


// 2. Autowire Dependencies:
//    - Inject `PatientService` to handle patient-specific logic such as creation, retrieval, and appointments.
//    - Inject the shared `Service` class for tasks like token validation and login authentication.


// 3. Define the `getPatient` Method:
//    - Handles HTTP GET requests to retrieve patient details using a token.
//    - Validates the token for the `"patient"` role using the shared service.
//    - If the token is valid, returns patient information; otherwise, returns an appropriate error message.


// 4. Define the `createPatient` Method:
//    - Handles HTTP POST requests for patient registration.
//    - Accepts a validated `Patient` object in the request body.
//    - First checks if the patient already exists using the shared service.
//    - If validation passes, attempts to create the patient and returns success or error messages based on the outcome.


// 5. Define the `login` Method:
//    - Handles HTTP POST requests for patient login.
//    - Accepts a `Login` DTO containing email/username and password.
//    - Delegates authentication to the `validatePatientLogin` method in the shared service.
//    - Returns a response with a token or an error message depending on login success.


// 6. Define the `getPatientAppointment` Method:
//    - Handles HTTP GET requests to fetch appointment details for a specific patient.
//    - Requires the patient ID, token, and user role as path variables.
//    - Validates the token using the shared service.
//    - If valid, retrieves the patient's appointment data from `PatientService`; otherwise, returns a validation error.


// 7. Define the `filterPatientAppointment` Method:
//    - Handles HTTP GET requests to filter a patient's appointments based on specific conditions.
//    - Accepts filtering parameters: `condition`, `name`, and a token.
//    - Token must be valid for a `"patient"` role.
//    - If valid, delegates filtering logic to the shared service and returns the filtered result.



}


