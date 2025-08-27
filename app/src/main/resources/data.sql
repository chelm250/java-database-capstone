-- Disable FK checks temporarily so we can clear in any order
SET FOREIGN_KEY_CHECKS = 0;

-- Clear child tables first (they reference parent tables)
DELETE FROM appointment;
DELETE FROM clinic_location;
DELETE FROM doctor;
DELETE FROM patient;

-- Clear parent tables
DELETE FROM address;

-- Reset auto-increment counters
ALTER TABLE appointment AUTO_INCREMENT = 1;
ALTER TABLE clinic_location AUTO_INCREMENT = 1;
ALTER TABLE doctor AUTO_INCREMENT = 1;
ALTER TABLE patient AUTO_INCREMENT = 1;
ALTER TABLE address AUTO_INCREMENT = 1;

-- Re‑enable FK checks
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO address (street, number, interior_number, postal_code, city, country) VALUES
('Wellness Blvd', 123, NULL, '1011AB', 'Amsterdam', 'Netherlands'),
('Brainwave Ave', 456, 'A1', '3011AA', 'Rotterdam', 'Netherlands'),
('Joint St', 789, NULL, '3511AD', 'Utrecht', 'Netherlands'),
('Little Ones Rd', 321, 'B2', '5611AE', 'Eindhoven', 'Netherlands'),
('Smooth Way', 654, NULL, '2511AG', 'The Hague', 'Netherlands'),
('Sunrise Lane', 12, 'C3', '1012BC', 'Amsterdam', 'Netherlands'),
('Maple Street', 88, NULL, '3012CD', 'Rotterdam', 'Netherlands'),
('Oak Avenue', 45, 'D4', '3512DE', 'Utrecht', 'Netherlands'),
('Riverbank Blvd', 67, NULL, '5612EF', 'Eindhoven', 'Netherlands'),
('Tulip Way', 101, 'E5', '2512FG', 'The Hague', 'Netherlands'),
('Elm Street', 33, NULL, '1013GH', 'Amsterdam', 'Netherlands'),
('Cedar Road', 76, 'F6', '3013HI', 'Rotterdam', 'Netherlands'),
('Pine Lane', 59, NULL, '3513IJ', 'Utrecht', 'Netherlands'),
('Lavender Blvd', 90, 'G7', '5613JK', 'Eindhoven', 'Netherlands'),
('Rose Avenue', 22, NULL, '2513KL', 'The Hague', 'Netherlands'),
('Birch Street', 11, 'H8', '1014LM', 'Amsterdam', 'Netherlands'),
('Willow Way', 44, NULL, '3014MN', 'Rotterdam', 'Netherlands'),
('Daisy Road', 77, 'I9', '3514NO', 'Utrecht', 'Netherlands'),
('Lily Lane', 99, NULL, '5614OP', 'Eindhoven', 'Netherlands'),
('Orchid Blvd', 66, 'J10', '2514PQ', 'The Hague', 'Netherlands'),
('Peony Street', 100, NULL, '1015QR', 'Amsterdam', 'Netherlands'),
('Magnolia Ave', 25, 'K11', '3015RS', 'Rotterdam', 'Netherlands'),
('Iris Road', 38, NULL, '3515ST', 'Utrecht', 'Netherlands'),
('Clover Lane', 73, 'L12', '5615TU', 'Eindhoven', 'Netherlands'),
('Fern Blvd', 84, NULL, '2515UV', 'The Hague', 'Netherlands'),
('Chestnut Street', 19, 'M13', '1016VW', 'Amsterdam', 'Netherlands'),
('Spruce Avenue', 50, NULL, '3016WX', 'Rotterdam', 'Netherlands'),
('Hazel Road', 61, 'N14', '3516XY', 'Utrecht', 'Netherlands'),
('Bamboo Lane', 82, NULL, '5616YZ', 'Eindhoven', 'Netherlands'),
('Palm Blvd', 93, 'O15', '2516ZA', 'The Hague', 'Netherlands'),
('Seaside Street', 17, NULL, '1017AB', 'Amsterdam', 'Netherlands'),
('Harbor Ave', 29, 'P16', '3017BC', 'Rotterdam', 'Netherlands'),
('Mountain Road', 41, NULL, '3517CD', 'Utrecht', 'Netherlands'),
('Forest Lane', 53, 'Q17', '5617DE', 'Eindhoven', 'Netherlands'),
('Valley Blvd', 64, NULL, '2517EF', 'The Hague', 'Netherlands'),
('Hilltop Street', 75, 'R18', '1018FG', 'Amsterdam', 'Netherlands'),
('Meadow Ave', 86, NULL, '3018GH', 'Rotterdam', 'Netherlands'),
('Brook Road', 97, 'S19', '3518HI', 'Utrecht', 'Netherlands'),
('Lake Lane', 108, NULL, '5618IJ', 'Eindhoven', 'Netherlands'),
('Coral Street', 121, NULL, '1019LM', 'Amsterdam', 'Netherlands'),
('Amber Avenue', 132, 'U21', '3019MN', 'Rotterdam', 'Netherlands'),
('Crystal Road', 143, NULL, '3519NO', 'Utrecht', 'Netherlands'),
('Pearl Lane', 154, 'V22', '5619OP', 'Eindhoven', 'Netherlands'),
('Jade Boulevard', 165, NULL, '2519PQ', 'The Hague', 'Netherlands'),
('Sapphire Street', 176, 'W23', '1020QR', 'Amsterdam', 'Netherlands'),
('Topaz Avenue', 187, NULL, '3020RS', 'Rotterdam', 'Netherlands'),
('Opal Road', 198, 'X24', '3520ST', 'Utrecht', 'Netherlands'),
('Quartz Lane', 209, NULL, '5620TU', 'Eindhoven', 'Netherlands'),
('Emerald Boulevard', 220, 'Y25', '2520UV', 'The Hague', 'Netherlands'),
('Garden Blvd', 119, 'T20', '2518JK', 'The Hague', 'Netherlands');

INSERT INTO clinic_location (name, address_id) VALUES
('Central Heart Clinic', 36),
('NeuroCare Center', 37),
('OrthoMotion Clinic', 38),
('Pediatric Health Hub', 39),
('Skin & Derm Studio', 40);

INSERT INTO doctor (name, last_name, email, password, date_of_birth, gender, address_id, phone, specialty, clinic_location) VALUES
('Emily', 'Adams', 'dr.adams@example.com', 'pass12345', '1980-04-12', 'FEMALE', 1, '5551012020', 'Cardiologist', 1),
('Mark', 'Johnson', 'dr.johnson@example.com', 'secure4567', '1975-09-23', 'MALE', 2, '5552023030', 'Neurologist', 2),
('Sarah', 'Lee', 'dr.lee@example.com', 'leePass987', '1982-06-15', 'FEMALE', 3, '5553034040', 'Orthopedist', 3),
('Tom', 'Wilson', 'dr.wilson@example.com', 'w!ls0nPwd', '1978-11-30', 'MALE', 4, '5554045050', 'Pediatrician', 4),
('Alice', 'Brown', 'dr.brown@example.com', 'brownie123', '1985-01-08', 'FEMALE', 5, '5555056060', 'Dermatologist', 5),
('Taylor', 'Grant', 'dr.taylor@example.com', 'taylor321', '1983-03-19', 'MALE', 6, '5556067070', 'Cardiologist', 1),
('Sam', 'White', 'dr.white@example.com', 'whiteSecure1', '1979-07-25', 'MALE', 7, '5557078080', 'Neurologist', 2),
('Emma', 'Clark', 'dr.clark@example.com', 'clarkPass456', '1986-05-14', 'FEMALE', 8, '5558089090', 'Orthopedist', 3),
('Olivia', 'Davis', 'dr.davis@example.com', 'davis789', '1981-12-03', 'FEMALE', 9, '5559090101', 'Pediatrician', 4),
('Henry', 'Miller', 'dr.miller@example.com', 'millertime!', '1974-08-17', 'MALE', 10, '5550101111', 'Dermatologist', 5),
('Ella', 'Moore', 'dr.moore@example.com', 'ellapass33', '1987-02-28', 'FEMALE', 11, '5551112222', 'Cardiologist', 1),
('Leo', 'Martin', 'dr.martin@example.com', 'martinpass', '1980-10-10', 'MALE', 12, '5552223333', 'Neurologist', 2),
('Ivy', 'Jackson', 'dr.jackson@example.com', 'jackson11', '1984-06-06', 'FEMALE', 13, '5553334444', 'Orthopedist', 3),
('Owen', 'Thomas', 'dr.thomas@example.com', 'thomasPWD', '1976-09-01', 'MALE', 14, '5554445555', 'Pediatrician', 4),
('Ava', 'Hall', 'dr.hall@example.com', 'hallhall', '1982-12-22', 'FEMALE', 15, '5555556666', 'Dermatologist', 5),
('Mia', 'Green', 'dr.green@example.com', 'greenleaf', '1985-07-07', 'FEMALE', 16, '5556667777', 'Cardiologist', 1),
('Jack', 'Baker', 'dr.baker@example.com', 'bakeitup', '1977-03-03', 'MALE', 17, '5557778888', 'Neurologist', 2),
('Nora', 'Walker', 'dr.walker@example.com', 'walkpass12', '1983-11-11', 'FEMALE', 18, '5558889999', 'Orthopedist', 3),
('Liam', 'Young', 'dr.young@example.com', 'young123', '1980-05-05', 'MALE', 19, '5559990000', 'Pediatrician', 4),
('Zoe', 'King', 'dr.king@example.com', 'kingkong1', '1987-10-10', 'FEMALE', 20, '5550001111', 'Dermatologist', 5),
('Lily', 'Scott', 'dr.scott@example.com', 'scottish', '1981-01-01', 'FEMALE', 21, '5551112223', 'Cardiologist', 1),
('Lucas', 'Evans', 'dr.evans@example.com', 'evansEv1', '1979-04-04', 'MALE', 22, '5552223334', 'Neurologist', 2),
('Grace', 'Turner', 'dr.turner@example.com', 'turnerBurner', '1984-09-09', 'FEMALE', 23, '5553334445', 'Orthopedist', 3),
('Ethan', 'Hill', 'dr.hill@example.com', 'hillclimb', '1978-06-06', 'MALE', 24, '5554445556', 'Pediatrician', 4),
('Ruby', 'Ward', 'dr.ward@example.com', 'wardWard', '1987-10-10', 'FEMALE', 25, '5555556667', 'Dermatologist', 5);

INSERT INTO doctor_available_times (doctor_id, available_times) VALUES
(1, '10:00-11:00'), (1, '11:00-12:00'), (1, '14:00-15:00'), (1, '15:00-16:00'),
(2, '09:00-10:00'), (2, '11:00-12:00'), (2, '14:00-15:00'), (2, '16:00-17:00'),
(3, '09:00-10:00'), (3, '10:00-11:00'), (3, '15:00-16:00'), (3, '16:00-17:00'),
(4, '09:00-10:00'), (4, '10:00-11:00'), (4, '14:00-15:00'), (4, '15:00-16:00'),
(5, '09:00-10:00'), (5, '10:00-11:00'), (5, '11:00-12:00'), (5, '14:00-15:00'),
(6, '09:00-10:00'), (6, '10:00-11:00'), (6, '15:00-16:00'), (6, '16:00-17:00'),
(7, '10:00-11:00'), (7, '11:00-12:00'), (7, '14:00-15:00'), (7, '15:00-16:00'),
(8, '09:00-10:00'), (8, '11:00-12:00'), (8, '13:00-14:00'), (8, '14:00-15:00'),
(9, '10:00-11:00'), (9, '11:00-12:00'), (9, '14:00-15:00'), (9, '16:00-17:00'),
(10, '09:00-10:00'), (10, '12:00-13:00'), (10, '14:00-15:00'), (10, '15:00-16:00'),
(11, '10:00-11:00'), (11, '11:00-12:00'), (11, '13:00-14:00'), (11, '14:00-15:00'),
(12, '13:00-14:00'), (12, '14:00-15:00'), (12, '15:00-16:00'), (12, '16:00-17:00'),
(13, '09:00-10:00'), (13, '10:00-11:00'), (13, '14:00-15:00'), (13, '16:00-17:00'),
(14, '10:00-11:00'), (14, '11:00-12:00'), (14, '13:00-14:00'), (14, '14:00-15:00'),
(15, '09:00-10:00'), (15, '11:00-12:00'), (15, '14:00-15:00'), (15, '16:00-17:00'),
(16, '09:00-10:00'), (16, '10:00-11:00'), (16, '11:00-12:00'), (16, '12:00-13:00'),
(17, '09:00-10:00'), (17, '10:00-11:00'), (17, '11:00-12:00'), (17, '15:00-16:00'),
(18, '13:00-14:00'), (18, '14:00-15:00'), (18, '15:00-16:00'), (18, '16:00-17:00'),
(19, '10:00-11:00'), (19, '13:00-14:00'), (19, '14:00-15:00'), (19, '15:00-16:00'),
(20, '09:00-10:00'), (20, '10:00-11:00'), (20, '14:00-15:00'), (20, '15:00-16:00'),
(21, '10:00-11:00'), (21, '11:00-12:00'), (21, '14:00-15:00'), (21, '16:00-17:00'),
(22, '11:00-12:00'), (22, '13:00-14:00'), (22, '15:00-16:00'), (22, '16:00-17:00'),
(23, '12:00-13:00'), (23, '13:00-14:00'), (23, '14:00-15:00'), (23, '15:00-16:00'),
(24, '12:00-13:00'), (24, '13:00-14:00'), (24, '14:00-15:00'), (24, '15:00-16:00'),
(25, '09:00-10:00'), (25, '10:00-11:00'), (25, '14:00-15:00'), (25, '15:00-16:00');

INSERT INTO patient (name, last_name, email, password, date_of_birth, gender, address_id, phone) VALUES
('Noah', 'Reed', 'n.reed@example.com', 'reedNoah', '1994-03-13', 'MALE', 26, '5556669990'),
('Harper', 'Cole', 'h.cole@example.com', 'harperC', '1986-07-29', 'FEMALE', 27, '5557770001'),
('Logan', 'Price', 'l.price@example.com', 'loganP!', '1992-11-18', 'MALE', 28, '5558881112'),
('Aria', 'Bell', 'a.bell@example.com', 'ariaBell', '1989-04-04', 'FEMALE', 29, '5559992223'),
('Caleb', 'Brooks', 'c.brooks@example.com', 'brooksC', '1990-06-22', 'MALE', 30, '5550003334'),
('Scarlett', 'Hayes', 's.hayes@example.com', 'scarlettH', '1987-10-10', 'FEMALE', 31, '5551114445'),
('Nathan', 'Foster', 'n.foster@example.com', 'nateFoster', '1993-01-07', 'MALE', 32, '5552225556'),
('Victoria', 'Barnes', 'v.barnes@example.com', 'vickyBarnes', '1988-08-16', 'FEMALE', 33, '5553336667'),
('Isaac', 'Perry', 'i.perry@example.com', 'isaacP', '1991-05-01', 'MALE', 34, '5554447778'),
('Penelope', 'Russell', 'p.russell@example.com', 'penelopeR', '1986-12-12', 'FEMALE', 35, '5555558889');

INSERT INTO appointment (patient_id, doctor_id, appointment_time, clinic_id, status) VALUES
(1, 1,  '2025-09-01 09:00:00',  1, 0),
(2, 2,  '2025-09-01 10:30:00', 2, 0),
(3, 3,  '2025-09-01 14:00:00', 3, 1),
(4, 4,  '2025-09-02 09:00:00', 4, 0),
(5, 5,  '2025-09-02 10:30:00', 5, 0),

(1, 6,  '2025-09-02 14:00:00', 1, 1),
(2, 7,  '2025-09-03 09:00:00', 2, 0),
(3, 8,  '2025-09-03 10:30:00', 3, 0),
(4, 9,  '2025-09-03 14:00:00', 4, 1),
(5, 10, '2025-09-04 09:00:00', 5, 0),

(1, 11, '2025-09-04 10:30:00', 1, 0),
(2, 12, '2025-09-04 14:00:00', 2, 1),
(3, 13, '2025-09-05 09:00:00', 3, 0),
(4, 14, '2025-09-05 10:30:00', 4, 0),
(5, 15, '2025-09-05 14:00:00', 5, 1),

(1, 16, '2025-09-06 09:00:00', 1, 0),
(2, 17, '2025-09-06 10:30:00', 2, 0),
(3, 18, '2025-09-06 14:00:00', 3, 1),
(4, 19, '2025-09-07 09:00:00', 4, 0),
(5, 20, '2025-09-07 10:30:00', 5, 0),

(1, 21, '2025-09-07 14:00:00', 1, 1),
(2, 22, '2025-09-08 09:00:00', 2, 0),
(3, 23, '2025-09-08 10:30:00', 3, 0),
(4, 24, '2025-09-08 14:00:00', 4, 1),
(5, 25, '2025-09-09 09:00:00', 5, 0);


