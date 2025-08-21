package com.project.back_end.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
    
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Street cannot be blank")
    @Size(max = 50, message = "Street cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 .,'-]+$", message = "Street can only contain letters, numbers, spaces, and . , ' -")
    private String street;

    @Column(nullable = false)
    @NotBlank(message = "House number cannot be blank")
    @Size(max = 10, message = "House number cannot exceed 6 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\\\-\\\\/ ]+$", message = "House number can contain letters, numbers, spaces, hyphens, and slashes")
    private String number;

    @Column(nullable = true)
    @Size(max = 10, message = "Interior number cannot exceed 6 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\\\-\\\\/ ]+$", message = "House number can contain letters, numbers, spaces, hyphens, and slashes")
    private String interiorNumber;

    @Column(nullable = false)
    @NotBlank(message = "Postal code cannot be blank")
    @Size(max = 50, message = "Postal code cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]+$", message = "Postal code can contain letters, numbers, spaces, and hyphens")
    private String postalCode;

    @Column(nullable = false)
    @NotBlank(message = "Region cannot exceed 50 characters")
    @Size(max = 50, message = "Region cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 .,'-]+$", message = "Street can only contain letters, numbers, spaces, and . , ' -")
    private String region;

    @Column(nullable = false)
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 .,'-]+$", message = "Street can only contain letters, numbers, spaces, and . , ' -")
    private String country;

    // Default constructor
    public Address() {}

    // Parametrized constructor
    public Address(String street, String number, String interiorNumber, String postalCode, String region, String country) {
        this.street = street;
        this.number = number;
        this.interiorNumber = interiorNumber;
        this.postalCode = postalCode;
        this.region = region;
        this.country = country;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getInteriorNumber() {
        return interiorNumber;
    }
    public void setInteriorNumber(String interiorNumber) {
        this.interiorNumber = interiorNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country; 
    }

    // toString override
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", interiorNumber='" + interiorNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                '}';
    }


}
