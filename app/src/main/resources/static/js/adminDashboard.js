import {openModal} from '../utils/modal.js'; // Import the modal utility functions to handle showing/hiding modals. Modals are simple popups for login forms or messages.
import {getDoctors, filterDoctors, saveDoctor} from '../services/doctorServices.js'; // Import the service functions to interact with the backend API for doctor data
import {createDoctorCard} from '../components/doctorCard.js'; // Import the component function to create doctor card HTML elements

window.onload = function() {

  const addDoctorBtn = document.getElementById('addDoctorBtn'); // Select the "Add Doctor" button by its ID
  if (addDoctorBtn) {
    addDoctorBtn.addEventListener('click', () => openModal('addDoctor')); // When clicked, open the "Add Doctor" modal
  }

  // Load and display all doctors when the page loads
  loadDoctorCards();

  // Attach event listeners to the search bar and filter dropdowns
  const searchBar = document.getElementById('searchBar').addEventListener('input', filterDoctorsOnChange);
  const timeFilter = document.getElementById('timeFilter').addEventListener('change', filterDoctorsOnChange);
  const specialtyFilter = document.getElementById('specialtyFilter').addEventListener('change', filterDoctorsOnChange);


}

async function loadDoctorCards() {

  try {
    const doctors = await getDoctors(); // Fetch all doctors from the service layer
    const contentDiv = document.getElementById('content'); // Select the content div where doctor cards will be displayed
    contentDiv.innerHTML = ''; // Clear any existing content
    
    if (doctors.length === 0) {
      contentDiv.innerHTML = '<p>No doctors available.</p>'; // Show message if no doctors found
      return;
    }

    doctors.forEach(doctor => {
      const doctorCard = createDoctorCard(doctor); // Create a card for each doctor
      contentDiv.appendChild(doctorCard); // Append the card to the content div
    });
    
  } catch (error) {
    console.error('Error loading doctors:', error);
  }

}

async function filterDoctorsOnChange() {
  const name = document.getElementById('searchBar').value.trim() || null; // Get the search bar value or null if empty
  const time = document.getElementById('timeFilter').value || null; // Get the selected time filter or null if none
  const specialty = document.getElementById('specialtyFilter').value || null; // Get the selected specialty filter or null if none

  try {
    const response = await filterDoctors(name, time, specialty); // Call the service to filter doctors based on inputs
    
    if (response.doctor.length == 0) {
      document.getElementById('content').innerHTML = '<p>No doctors found with the given filters.</p>'; // Show message if no doctors match the filter
      return;
    }

    const doctors = response.doctors; // Extract the doctors array from the response
    renderDoctorCards(doctors); // Render the filtered doctors
 
  } catch (error) {
    console.error('Error filtering doctors:', error);
    alert('An error occurred while filtering doctors. Please try again later.');
  }
}

async function renderDoctorCards(doctors) {
  const contentDiv = document.getElementById('content'); // Select the content div where doctor cards will be displayed
  contentDiv.innerHTML = ''; // Clear any existing content

  doctors.forEach(doctor => {
    const doctorCard = createDoctorCard(doctor); // Create a card for each doctor
    contentDiv.appendChild(doctorCard); // Append the card to the content div
  });
}


/*
  This script handles the admin dashboard functionality for managing doctors:
  - Loads all doctor cards
  - Filters doctors by name, time, or specialty
  - Adds a new doctor via modal form


  Attach a click listener to the "Add Doctor" button
  When clicked, it opens a modal form using openModal('addDoctor')


  When the DOM is fully loaded:
    - Call loadDoctorCards() to fetch and display all doctors


  Function: loadDoctorCards
  Purpose: Fetch all doctors and display them as cards

    Call getDoctors() from the service layer
    Clear the current content area
    For each doctor returned:
    - Create a doctor card using createDoctorCard()
    - Append it to the content div

    Handle any fetch errors by logging them


  Attach 'input' and 'change' event listeners to the search bar and filter dropdowns
  On any input change, call filterDoctorsOnChange()


  Function: filterDoctorsOnChange
  Purpose: Filter doctors based on name, available time, and specialty

    Read values from the search bar and filters
    Normalize empty values to null
    Call filterDoctors(name, time, specialty) from the service

    If doctors are found:
    - Render them using createDoctorCard()
    If no doctors match the filter:
    - Show a message: "No doctors found with the given filters."

    Catch and display any errors with an alert


  Function: renderDoctorCards
  Purpose: A helper function to render a list of doctors passed to it

    Clear the content area
    Loop through the doctors and append each card to the content area


  Function: adminAddDoctor
  Purpose: Collect form data and add a new doctor to the system

    Collect input values from the modal form
    - Includes name, email, phone, password, specialty, and available times

    Retrieve the authentication token from localStorage
    - If no token is found, show an alert and stop execution

    Build a doctor object with the form values

    Call saveDoctor(doctor, token) from the service

    If save is successful:
    - Show a success message
    - Close the modal and reload the page

    If saving fails, show an error message
*/
