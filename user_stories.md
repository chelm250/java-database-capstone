# User Story Template

## Admin User Stories
**Title:**
As an admin, I want to log into the portal with my username and password, so that I can manage the platform securely.

**Acceptance Criteria:**
1. Admin can enter valid credentials to access the portal.
2. Invalid credentials trigger an error message.
3. Successful login redirects to the admin dashboard.

**Priority:** High
**Story Points:** 2
**Notes:**
- Ensure encryption of credentials during transmission.

**Title:**
As an admin, I want to log out of the portal, so that I can protect system access when I'm not using it.

**Acceptance Criteria:**
1. Admin can click a logout button to end the session.
2. Session is terminated and user is redirected to the login page.
3. Access to admin features is blocked after logout.

**Priority:** High
**Story Points:** 1
**Notes:**
- Implement session timeout for added security.

**Title:**
As an admin, I want to delete a doctor's profile from the portal, so that outdated or incorrect entries are removed.

**Acceptance Criteria:**
1. Admin can fill out a form with doctor details.
2. Form validation ensures required fields are completed.
3. New doctor profile appears in the doctor list.

**Priority:** High
**Story Points:** 3
**Notes:**
- Include fields for specialization, contact info, and availability.

**Title:**
As an admin, I want to delete a doctor's profile from the portal, so that outdated or incorrect entries are removed.

**Acceptance Criteria:**
1. Admin can select a doctor profile and confirm deletion.
2. Deleted profile is removed from the database and UI.
3. System prompts for confirmation before deletion.

**Priority:** High
**Story Points:** 2
**Notes:**
- Consider soft delete for audit purposes.

**Title:**
As an admin, I want to run a stored procedure in MySQL CLI to get the number of appointments per month, so that I can track usage statistics.

**Acceptance Criteria:**
1. Admin can access MySQL CLI and execute the stored procedure.
2. Procedure returns monthly appointment counts.
3. Output is formatted for readability or export.

**Priority:** Medium
**Story Points:** 3
**Notes:**
- Procedure should be optimized for performance.




## Patient User Stories
**Title:**
As a patient, I want to view a list of doctors without logging in, so that I can explore options before registering.

**Acceptance Criteria:**
1. Patient can access a public doctor directory.
2. Doctor profiles show name, specialization, and availability.
3. No login prompt is required for viewing.

**Priority:** High
**Story Points:** 2
**Notes:**
- Consider caching for faster access.

**Title:**
As a patient, I want to sign up using my email and password, so that I can book appointments.

**Acceptance Criteria:**
1. Patient can register with a valid email and password.
2. System validates email format and password strength.
3. Successful registration redirects to login or dashboard.

**Priority:** High
**Story Points:** 3
**Notes:**
- Include email verification for security.

**Title:**
As a patient, I want to log into the portal, so that I can manage my bookings.

**Acceptance Criteria:**
1. Patient can enter credentials to access their dashboard.
2. Incorrect login shows error message.
3. Dashboard displays booking options and history.

**Priority:** High
**Story Points:** 2
**Notes:**
- Enable "Remember Me" option for convenience.

**Title:**
As a patient, I want to log out of the portal, so that I can secure my account.

**Acceptance Criteria:**
1. Patient can click logout to end session.
2. Session is terminated and redirected to homepage.
3. Access to personal data is blocked after logout.

**Priority:** High
**Story Points:** 1
**Notes:**
- Include auto-logout after inactivity.

**Title:**
As a patient, I want to log in and book an hour-long appointment, so that I can consult with a doctor.

**Acceptance Criteria:**
1. Patient can select a doctor and choose a time slot.
2. System confirms availability and duration.
3. Appointment is added to both patient and doctor calendars.

**Priority:** High
**Story Points:** 3
**Notes:**
- Include confirmation email or notification.





## Doctor User Stories
**Title:**
As a doctor, I want to log into the portal, so that I can manage my appointments.

**Acceptance Criteria:**
1. Doctor can enter valid credentials to access the portal.
2. Invalid login attempts show error messages.
3. Successful login redirects to the appointment dashboard.

**Priority:** High
**Story Points:** 2
**Notes:**
- Include multi-factor authentication for added security.

**Title:**
As a doctor, I want to log out of the portal, so that I can protect my data.

**Acceptance Criteria:**
1. Doctor can click logout to end session.
2. Session is terminated and redirected to login page.
3. No data is accessible after logout.

**Priority:** High
**Story Points:** 1
**Notes:**
- Implement auto-logout after inactivity.

**Title:**
As a doctor, I want to view my appointment calendar, so that I can stay organized.

**Acceptance Criteria:**
1. Calendar displays all upcoming appointments.
2. Each entry includes patient name, time, and duration.
3. Calendar supports daily, weekly, and monthly views.

**Priority:** High
**Story Points:** 3
**Notes:**
- Include filters for appointment types.

**Title:**
As a doctor, I want to mark my unavailability, so that patients only see available slots.

**Acceptance Criteria:**
1. Doctor can select time ranges to mark as unavailable.
2. Unavailable slots are hidden from patient booking view.
3. System prevents bookings during blocked times.

**Priority:** High
**Story Points:** 3
**Notes:**
- Allow recurring unavailability (e.g., weekends).

**Title:**
As a doctor, I want to view the patient details for upcoming appointments, so that I can be prepared.

**Acceptance Criteria:**
1. Doctor can access patient profiles linked to appointments.
2. Details include medical history, contact info, and notes.
3. Access is restricted to confirmed appointments only.

**Priority:** High
**Story Points:** 3
**Notes:**
- Ensure compliance with data privacy regulations.
