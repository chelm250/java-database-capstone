## MySQL Database Design
### Table: Admins
  - id: INT, Primary Key, Auto Increment
  - name: STRING, Not Null
  - last_name: STRING, Not Null
  - role: STRING, Not NUll
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
## MongoDB Collection Design
### Collection: prescriptions
```json
{
  "_id": "PrescriptionId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorName": "Arturo Ramirez",
  "doctorNotes": "Take 1 tablet every 6 hours.",
  "refillCount": 2,
  "pharmacy": {
    "name": "Walgreens SF",
    "location": "Market Street"
  }
}
```
### Collection: feedback
```json
{
  "_id": "ObjectId('564abc123456')"
  "patientId": 50,
  "appointmentId": 51,
  "doctorId": 10,
  "rating": 5,
  "comments": "Dr. Ramirez very attentive and explained everything clear",
  "tags": ["professional", "clear explanation"],
  "resolved": true,
  "response": {
    "responder": "Clinic Admin",
    "message": "Thank you for your feedback!",
    "respondedAt": "ISODate(2025-08-20T10:00:00Z)"
  }
}
```
### Collection: Logs
```json
{
  "_id": "LogId('64def789abc')",
  "patientId": 102,
  "eventType": "check-in,  // or message",
  "timestamp": "2025-08-19T15:42:00Z",
  "location": {
    "clinicId": 5,
    "name": "Eindhoven Medical Center"
  },
  "messageDetails": {
    "doctorId": 204,
    "doctorName": "Dr. Elise van Dijk",
    "content": "Hi doctor, I’m experiencing chest tightness since yesterday.",
    "responseStatus": "pending,  // or responded",
  },
  "deviceInfo": {
    "platform": "mobile",
    "ipAddress": "192.168.1.45"
  }
}
```
### Collection: Messages
```json
{
  "_id": "MessageId('64fabc123456')",
  "senderId": 102,
  "receiverId": 204,
  "senderRole": "patient, // or doctor",
  "receiverRole": "patient, // or doctor",
  "timestamp": "2025-08-19T16:45:00Z",
  "content": "Hi Dr. Elise, I have a question about my prescription.",
  "isRead": false,
  "attachments": [
    {
      "type": "image",
      "url": "https://capstone.com/images/prescription.jpg"
    }
  ],
  "conversationId": "conv_102_204"
}
```


