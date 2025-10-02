package com.project.back_end.services;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.back_end.repo.AdminRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



@Component 
public class TokenService {

    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Value("${jwt.secret}")
    private String secret;

    
    public TokenService(AdminRepository adminRepository,
                        DoctorRepository doctorRepository,
                        PatientRepository patientRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    
    public String generateToken(String identifier) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000L); // 7 days

        return Jwts.builder()
                .setSubject(identifier) // Set identifier as subject
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey())
                .compact();
    }

   
    public String extractIdentifier(String token) {
        try {
            JwtParser parser = Jwts.parser()
                .verifyWith(getSigningKey()) // Must be a SecretKey
                .build();

            Jws<Claims> jws = parser.parseSignedClaims(token);
            return jws.getPayload().getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    
    public boolean validateToken(String token, String role) {
        try {
            String identifier = extractIdentifier(token);
            if (identifier == null) {
                return false; // Invalid token
            }

            switch (role.toLowerCase()) {
                case "admin":
                    return adminRepository.findByUsername(identifier) != null;
                case "doctor":
                    return doctorRepository.findByEmail(identifier) != null;
                case "patient":
                    return patientRepository.findByEmail(identifier) != null;
                default:
                    return false; // Unknown role
            }
        } catch (Exception e) {
            return false; // Handle any errors gracefully
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Long extractPatientId (String token) {
        try {
            String email = extractIdentifier(token);
            if (email == null) {
                return null; // Invalid token
            }
            var patient = patientRepository.findByEmail(email);
            return (patient != null) ? patient.getId() : null;
        } catch (Exception e) {
            return null; // Handle any errors gracefully
        }
    }

    public String extractEmail(String token) {
        String identifier = extractIdentifier(token);
        return identifier;
    }
    
    public Long getUserIdFromToken(String token) {
        try {
            String identifier = extractIdentifier(token);
            if (identifier == null) {
                return null; // Invalid token
            }
            var patient = patientRepository.findByEmail(identifier);
            if (patient != null) {
                return patient.getId();
            }
            var doctor = doctorRepository.findByEmail(identifier);
            if (doctor != null) {
                return doctor.getId();
            }
            var admin = adminRepository.findByUsername(identifier);
            if (admin != null) {
                return admin.getId();
            }
            return null; // User not found
        } catch (Exception e) {
            return null; // Handle any errors gracefully
        }
    }






// 1. **@Component Annotation**
// The @Component annotation marks this class as a Spring component, meaning Spring will manage it as a bean within its application context.
// This allows the class to be injected into other Spring-managed components (like services or controllers) where it's needed.

// 2. **Constructor Injection for Dependencies**
// The constructor injects dependencies for `AdminRepository`, `DoctorRepository`, and `PatientRepository`,
// allowing the service to interact with the database and validate users based on their role (admin, doctor, or patient).
// Constructor injection ensures that the class is initialized with all required dependencies, promoting immutability and making the class testable.

// 3. **getSigningKey Method**
// This method retrieves the HMAC SHA key used to sign JWT tokens.
// It uses the `jwt.secret` value, which is provided from an external source (like application properties).
// The `Keys.hmacShaKeyFor()` method converts the secret key string into a valid `SecretKey` for signing and verification of JWTs.

// 4. **generateToken Method**
// This method generates a JWT token for a user based on their email.
// - The `subject` of the token is set to the user's email, which is used as an identifier.
// - The `issuedAt` is set to the current date and time.
// - The `expiration` is set to 7 days from the issue date, ensuring the token expires after one week.
// - The token is signed using the signing key generated by `getSigningKey()`, making it secure and tamper-proof.
// The method returns the JWT token as a string.

// 5. **extractEmail Method**
// This method extracts the user's email (subject) from the provided JWT token.
// - The token is first verified using the signing key to ensure it hasn’t been tampered with.
// - After verification, the token is parsed, and the subject (which represents the email) is extracted.
// This method allows the application to retrieve the user's identity (email) from the token for further use.

// 6. **validateToken Method**
// This method validates whether a provided JWT token is valid for a specific user role (admin, doctor, or patient).
// - It first extracts the email from the token using the `extractEmail()` method.
// - Depending on the role (`admin`, `doctor`, or `patient`), it checks the corresponding repository (AdminRepository, DoctorRepository, or PatientRepository)
//   to see if a user with the extracted email exists.
// - If a match is found for the specified user role, it returns true, indicating the token is valid.
// - If the role or user does not exist, it returns false, indicating the token is invalid.
// - The method gracefully handles any errors by returning false if the token is invalid or an exception occurs.
// This ensures secure access control based on the user's role and their existence in the system.
}
