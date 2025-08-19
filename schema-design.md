## MySQL Database Design
### Table: patients
  - id: INT, Primary Key, Auto Increment
  - name: STRING, Not Null
  - last_name: STRING, Not Null
  - date_of_birth: DATE, Not Null
  - gender: STRING, Not Null
### Table: doctors
  - id: INT, Primary Key, Auto Increment
  - name: STRING, Not Null
  - last_name: STRING, Not Null
  - date_of_birth: DATE, Not Null
  - gender: STRING, Not Null
### Table: appointments
  - id: INT, Primary Key, Auto Increment
  - patient_id: INT, Foreign Key -> patients(id)
  - doctor_id: INT, Foreign Key -> doctors(id)
  - appointment_time: DATETIME, Not Null
  - location: STRING, Not Null
  - status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)
### Table: clinic_locations
  - id: INT, Primary Key, Auto Increment
  - location_name: STRING, Not Null, Unique
  - street: STRING, Not Null
  - number: STRING, Not Null
  - interior_number: STRING, Not Null
  - postal_code: STRING, Not Null
  - region: STRING, Not Null
  - country: STRING, Not Null
### Table: payments
  - id: INT, Primary Key, Auto Increment
  - appointment_id: INT, Foreign Key -> appointments(id)
  - payment_time: DATETIME, Not Null
  - status: INT (0 = Not paid, 1 = Paid, 2 = Cancelled)
### Table: prescription
  - id: INT, Primary Key, Auto Increment
  - patient_id: INT, Foreign Key -> patients(id)
  - doctor_id: INT, Foreign Key -> doctors(id)
  - appointment_id: INT, Foreign Key -> appointments(id)
## MongoDB Collection Design
