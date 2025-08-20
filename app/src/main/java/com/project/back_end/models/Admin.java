package com.project.back_end.models;

import java.lang.annotation.Inherited;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.tools.javac.jvm.Gen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
public class Admin {

    // Attributes of the Admin entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    @Pattern(regexp = "^[\\p{L}]+([\\p{L}\\s\\-]*[\\p{L}]+)?$", message = "Name must contain only letters, spaces, or hyphens")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Last Name cannot be blank")
    @Size(max = 50, message = "Last Name cannot exceed 50 characters")
    @Pattern(regexp = "^[\\p{L}]+([\\p{L}\\s\\-]*[\\p{L}]+)?$", message = "Last Name must contain only letters, spaces, or hyphens")
    private String lastName;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username cannot be blank")
    @Size(max = 10, message = "Username cannot exceed 10 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Must contain only letters and numbers")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    @NotBlank(message = "Address cannot be null")
    private Address address;

    @Column(nullable = false)
    @NotBlank(message = "Role cannot be blank")
    @Size(max = 20, message = "Role cannot exceed 20 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Role must contain only letters")
    private String role;

    // Default constructor
    public Admin() {}

    // Parameterized constructor
    public Admin(String name, String lastName, String username, String email, String password, Address address, String role) {
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    // toString method for easy representation of the Admin object
    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", role='" + role + '\'' +
                '}';
    }
    
// @Entity annotation:
//    - Marks the class as a JPA entity, which means it represents a table in the database.
//    - It is required for persistence frameworks like Hibernate to map the class to a database table.


// 1. 'id' field:
//    - Type: private Long
//    - Description: 
//      - Represents the unique identifier for the Admin entity.
//      - This field is auto-generated by the database using @GeneratedValue with strategy GenerationType.IDENTITY.
//      - It is the primary key of the entity, identified by @Id annotation.

// 2. 'username' field:
//    - Type: private String
//    - Description: 
//      - Represents the username of the admin.
//      - Used to log into the system.
//      - @NotNull validation ensures that this field cannot be null when creating or updating an Admin.

// 3. 'password' field:
//    - Type: private String
//    - Description: 
//      - Represents the password of the admin for authentication.
//      - The field is marked with @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) to prevent the password from being exposed in JSON responses.
//      - @NotNull validation ensures the password cannot be null when creating or updating an Admin.

// 4. Constructor(s):
//    - A no-argument constructor is implicitly provided, required by JPA for entity creation.
//    - A parameterized constructor can be added as needed.

// 5. Getters and Setters:
//    - Standard getter and setter methods are provided for accessing and modifying the fields.

}
