import { getAllAppointments } from './services/index.js'; // Import the function to fetch appointments from the backend
import { createPatientRow } from './components/patientRow.js';

const tableBody = document.getElementById('appointmentsTableBody'); // Get the table body where patient rows will be added
let selectedDate = new Date().toISOString().split('T')[0]; // Initialize selectedDate with today's date in 'YYYY-MM-DD' format
const token = localStorage.getItem('token'); // Get the saved token from localStorage (used for authenticated API calls)
let patientName = null; // Initialize patientName to null (used for filtering by name)

document.getElementById('searchBar').addEventListener('input', function() {
  const input = this.value.trim();
  patientName = input !== '' ? input : "null"; // Use "null" string if input is empty to match backend expectation
  loadAppointments(); // Reload appointments with the updated filter
});

document.getElementById('todayBtn').addEventListener('click', function() {
  selectedDate = new Date().toISOString().split('T')[0]; // Set selectedDate to today's date
  document.getElementById('datePicker').value = selectedDate; // Update the date picker UI to match
  loadAppointments(); // Reload appointments for today
});

document.getElementById('datePicker').addEventListener('change', function() {
  selectedDate = this.value; // Update selectedDate with the new value
  loadAppointments(); // Reload appointments for the selected date
});

async function loadAppointments() {
  try {
    const appointments = await getAllAppointments(selectedDate, patientName, token); // Fetch appointments from the backend
    tableBody.innerHTML = ''; // Clear existing table rows

    if (appointments.length === 0) {
      tableBody.innerHTML = '<tr><td colspan="4">No Appointments found for today.</td></tr>'; // Show message if no appointments
      return;
    }

    appointments.forEach(appointment => {
      const patient = {
        id: appointment.patient.id,
        name: appointment.patient.name,
        phone: appointment.patient.phone,
        email: appointment.patient.email
      };
      const row = createPatientRow(appointment, patient); // Create a table row for the appointment
      tableBody.appendChild(row); // Append the row to the table body
    });

  } catch (error) {
    console.error('Error loading appointments:', error);
    tableBody.innerHTML = '<tr><td colspan="4">Error loading appointments. Try again later.</td></tr>'; // Show error message in table
  }
}

document.addEventListener('DOMContentLoaded', function() {
  // Assuming renderContent sets up the UI layout
  renderContent(); 
  loadAppointments(); // Load today's appointments on page load
});

/*
  Import getAllAppointments to fetch appointments from the backend
  Import createPatientRow to generate a table row for each patient appointment


  Get the table body where patient rows will be added
  Initialize selectedDate with today's date in 'YYYY-MM-DD' format
  Get the saved token from localStorage (used for authenticated API calls)
  Initialize patientName to null (used for filtering by name)


  Add an 'input' event listener to the search bar
  On each keystroke:
    - Trim and check the input value
    - If not empty, use it as the patientName for filtering
    - Else, reset patientName to "null" (as expected by backend)
    - Reload the appointments list with the updated filter


  Add a click listener to the "Today" button
  When clicked:
    - Set selectedDate to today's date
    - Update the date picker UI to match
    - Reload the appointments for today


  Add a change event listener to the date picker
  When the date changes:
    - Update selectedDate with the new value
    - Reload the appointments for that specific date


  Function: loadAppointments
  Purpose: Fetch and display appointments based on selected date and optional patient name

  Step 1: Call getAllAppointments with selectedDate, patientName, and token
  Step 2: Clear the table body content before rendering new rows

  Step 3: If no appointments are returned:
    - Display a message row: "No Appointments found for today."

  Step 4: If appointments exist:
    - Loop through each appointment and construct a 'patient' object with id, name, phone, and email
    - Call createPatientRow to generate a table row for the appointment
    - Append each row to the table body

  Step 5: Catch and handle any errors during fetch:
    - Show a message row: "Error loading appointments. Try again later."


  When the page is fully loaded (DOMContentLoaded):
    - Call renderContent() (assumes it sets up the UI layout)
    - Call loadAppointments() to display today's appointments by default
*/
