package com.project.back_end.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class ClinicLocation {
    
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Location name cannot be blank")
    @Size(max = 50, message = "Location name cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Location name only can contain alphanumeric characters")
    private String name;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "Address cannot be null")
    private Address address;

    // Default constructor
    public ClinicLocation() {}

    // Parametrized constructor
    public ClinicLocation(String name, Address address) {
        this.name = name;
        this.address = address;
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
    public void setName(String name){
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    // toString override
    @Override
    public String toString() {
        return "ClinicLocation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';    
    }

}
