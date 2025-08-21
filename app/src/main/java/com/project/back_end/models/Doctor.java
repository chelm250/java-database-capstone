package com.project.back_end.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.back_end.enums.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Pattern(regexp = "^[\\p{L}]+([\\p{L}\\s\\-]*[\\p{L}]+)?$", message = "Last Name must contain only letters, spaces, or hyphens")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Last Name cannot be blank")
    @Size(max = 50, message = "Last Name cannot exceed 50 characters")
    @Pattern(regexp = "^[\\p{L}]+([\\p{L}\\s\\-]*[\\p{L}]+)?$", message = "Last Name must contain only letters, spaces, or hyphens")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Email cannot be blank")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    @Email(message = "Invalid Email format")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Date of birth cannot be blank")
    @Size(min = 10, max = 10, message = "Date of birth needs to be 9 characters")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$", message = "Date of birth must be in dd/mm/yyyy format")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @Column(nullable = false)
    @JoinColumn(name = "address", referencedColumnName = "id")
    @NotNull(message = "Address cannot be null")
    private Address address;

    @Column(nullable = false)
    @NotBlank(message = "Phone cannot be blank")
    @Size(min = 3, max = 10, message = "Phone must be between 3 and 10 characters")
    @Pattern(regexp = "^\\+?[0-9]{3,10}$", message = "Phone must be 3 to 10 digits, optionally starting with +")
    private String phone;

    @Column(nullable = false)
    @NotBlank(message = "Speciality cannot be blank")
    @Size(min = 3, max = 50, message = "Specialty must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z\\s\\-]+$", message = "Specialty can only contain letters, spaces, or hyphens")
    private String specialty;

    @ManyToOne(optional = false)
    @JoinColumn(name = "clinic_location", referencedColumnName = "id")
    @NotNull(message = "Clinic location cannot be null")
    private ClinicLocation clinicLocation;

    @ElementCollection
    private List<String> availableTimes;

    // Default constructor
    public Doctor() {}

    // Parameterized constructor
    public Doctor(String name, String lastName, String email, String password, LocalDate date) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public ClinicLocation getClinicLocation() {
        return clinicLocation;
    }
    public void setClinicLocation(ClinicLocation clinicLocation) {
        this.clinicLocation = clinicLocation;
    }
    public List<String> getAvailableTimes() {
        return availableTimes;
    }
    public void setAvailableTimes(List<String> availableTimes) {
        this.availableTimes = availableTimes;
    }

    // toString method
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", specialty='" + specialty + '\'' +
                ", clinicLocation=" + clinicLocation +
                ", availableTimes=" + availableTimes +
                '}';
    }


// @Entity annotation:
//    - Marks the class as a JPA entity, meaning it represents a table in the database.
//    - Required for persistence frameworks (e.g., Hibernate) to map the class to a database table.

// 1. 'id' field:
//    - Type: private Long
//    - Description:
//      - Represents the unique identifier for each doctor.
//      - The @Id annotation marks it as the primary key.
//      - The @GeneratedValue(strategy = GenerationType.IDENTITY) annotation auto-generates the ID value when a new record is inserted into the database.

// 2. 'name' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's name.
//      - The @NotNull annotation ensures that the doctor's name is required.
//      - The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters. 
//      - Provides validation for correct input and user experience.


// 3. 'specialty' field:
//    - Type: private String
//    - Description:
//      - Represents the medical specialty of the doctor.
//      - The @NotNull annotation ensures that a specialty must be provided.
//      - The @Size(min = 3, max = 50) annotation ensures that the specialty name is between 3 and 50 characters long.

// 4. 'email' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's email address.
//      - The @NotNull annotation ensures that an email address is required.
//      - The @Email annotation validates that the email address follows a valid email format (e.g., doctor@example.com).

// 5. 'password' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's password for login authentication.
//      - The @NotNull annotation ensures that a password must be provided.
//      - The @Size(min = 6) annotation ensures that the password must be at least 6 characters long.
//      - The @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) annotation ensures that the password is not serialized in the response (hidden from the frontend).

// 6. 'phone' field:
//    - Type: private String
//    - Description:
//      - Represents the doctor's phone number.
//      - The @NotNull annotation ensures that a phone number must be provided.
//      - The @Pattern(regexp = "^[0-9]{10}$") annotation validates that the phone number must be exactly 10 digits long.

// 7. 'availableTimes' field:
//    - Type: private List<String>
//    - Description:
//      - Represents the available times for the doctor in a list of time slots.
//      - Each time slot is represented as a string (e.g., "09:00-10:00", "10:00-11:00").
//      - The @ElementCollection annotation ensures that the list of time slots is stored as a separate collection in the database.

// 8. Getters and Setters:
//    - Standard getter and setter methods are provided for all fields: id, name, specialty, email, password, phone, and availableTimes.

}

