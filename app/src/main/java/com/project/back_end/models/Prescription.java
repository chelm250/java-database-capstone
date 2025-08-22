package com.project.back_end.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Document(collection = "prescriptions")
public class Prescription {

  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  private Long id;

  @Size(min = 3, max = 100, message = "Patient name must be between 3 and 100 characters")
  @NotNull(message = "Patient name cannot be null")
  private String patientName;

  @Size(min = 3, max = 100, message = "Patient name must be between 3 and 100 characters")
  @NotNull(message = "Patient name cannot be null")
  private String doctortName;

  @NotNull(message = "Appointment id cannot be null")
  private Long appointmentId;

  @NotBlank(message = "Medication cannot be blank")
  @Size(min = 3, max = 100, message = "Medication must be between 3 and 100 characters")
  private String medication;

  @NotBlank(message = "Dosage cannot be blank")
  @Size(min = 3, max = 20, message = "Dosage must be between 3 and 100 characters")
  private String dosage;

  @NotNull(message = "Refill count cannot be null")
  @Size(min = 1, max = 10, message = "Refill count must be between 1 and 10")
  private Integer refillCount;

  @Size(max = 200, message = "Doctor notes cannot exceed 200 characters")
  private String doctorNotes;

  @NotBlank(message = "Pharmacy name cannot be blank")
  @Size(max = 100, message = "Pharmacy name cannot exceed 100 characters")
  private String pharmacy;

  // Default constructor
  public Prescription() {}

  // Parameterized constructor
  public Prescription(String patientName, Long appointmentId, String medication, String dosage, Integer refillCount, String doctorNotes) {
    this.patientName = patientName;
    this.appointmentId = appointmentId;
    this.medication = medication;
    this.dosage = dosage;
    this.refillCount = refillCount;
    this.doctorNotes = doctorNotes;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getPatientName() {
    return patientName;
  }
  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }
  public Long getAppointmentId() {
    return appointmentId;
  }
  public void setAppointmentId(Long appointmentId) {
    this.appointmentId = appointmentId;
  }
  public String getMedication() {
    return medication;
  }
  public void setMedication(String medication) {
    this.medication = medication;
  }
  public String getDosage() {
    return dosage;
  } 
  public void setDosage(String dosage) {
    this.dosage = dosage;
  }
  public Integer getRefillCount() {
    return refillCount;
  }
  public void setRefillCount(Integer refillCount) {
    this.refillCount = refillCount;
  }
  public String getDoctorNotes() {
    return doctorNotes;
  }
  public void setDoctorNotes(String doctorNotes) {
    this.doctorNotes = doctorNotes;
  }

  // toString method for debugging
  @Override
  public String toString() {
    return "Prescription{" +
            "id=" + id +
            ", patientName='" + patientName + '\'' +
            ", appointmentId='" + appointmentId + '\'' +
            ", medication='" + medication + '\'' +
            ", dosage='" + dosage + '\'' +
            ", refillCount=" + refillCount +
            ", doctorNotes='" + doctorNotes + '\'' +
            '}';
  }


  // @Document annotation:
//    - Marks the class as a MongoDB document (a collection in MongoDB).
//    - The collection name is specified as "prescriptions" to map this class to the "prescriptions" collection in MongoDB.

// 1. 'id' field:
//    - Type: private String
//    - Description:
//      - Represents the unique identifier for each prescription.
//      - The @Id annotation marks it as the primary key in the MongoDB collection.
//      - The id is of type String, which is commonly used for MongoDB's ObjectId as it stores IDs as strings in the database.

// 2. 'patientName' field:
//    - Type: private String
//    - Description:
//      - Represents the name of the patient receiving the prescription.
//      - The @NotNull annotation ensures that the patient name is required.
//      - The @Size(min = 3, max = 100) annotation ensures that the name length is between 3 and 100 characters, ensuring a reasonable name length.

// 3. 'appointmentId' field:
//    - Type: private Long
//    - Description:
//      - Represents the ID of the associated appointment where the prescription was given.
//      - The @NotNull annotation ensures that the appointment ID is required for the prescription.

// 4. 'medication' field:
//    - Type: private String
//    - Description:
//      - Represents the medication prescribed to the patient.
//      - The @NotNull annotation ensures that the medication name is required.
//      - The @Size(min = 3, max = 100) annotation ensures that the medication name is between 3 and 100 characters, which ensures meaningful medication names.

// 5. 'dosage' field:
//    - Type: private String
//    - Description:
//      - Represents the dosage information for the prescribed medication.
//      - The @NotNull annotation ensures that the dosage information is provided.

// 6. 'doctorNotes' field:
//    - Type: private String
//    - Description:
//      - Represents any additional notes or instructions from the doctor regarding the prescription.
//      - The @Size(max = 200) annotation ensures that the doctor's notes do not exceed 200 characters, providing a reasonable limit for additional notes.

// 7. Constructors:
//    - The class includes a no-argument constructor (default constructor) and a parameterized constructor that initializes the fields: patientName, medication, dosage, doctorNotes, and appointmentId.

// 8. Getters and Setters:
//    - Standard getter and setter methods are provided for all fields: id, patientName, medication, dosage, doctorNotes, and appointmentId.
//    - These methods allow access and modification of the fields of the Prescription class.


}
