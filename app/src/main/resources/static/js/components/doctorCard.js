
// Create an export function to which will create a doctor card element dynamically based on the doctor data passed to it
// This function will be imported and used in other scripts to display doctor information
export function createDoctorCard(doctor) { // Use export to make it available outside this file, so it can be called from HTML file
  const card = document.createElement('div'); // Create the main container, a div element for HTML
  card.className = 'doctor-card'; // Add a class to style with CSS

  // Create a variable to get the user role from local storage
  // This will determine what action are available on the card depending on role
  const userRole = localStorage.getItem('userRole');

  // Create a div element to hold doctor information
  const infoDiv = document.createElement('div');
  doctorInfo.className = 'doctor-info'; // class styling in CSS

  // Create and populate elements for doctor details
  const name = document.createElement('h3'); // Doctor's name as a heading
  name.textContent = doctor.name; // Set the text content to the doctor's name from the passed object
  infoDiv.appendChild(name); // This makes the name appear on the infoDiv variable created above

  // Create a paragraph for specialization and set its text content
  const specialization = document.createElement('p');
  specialization.textContent = `Specialization: ${doctor.specialization}`;
  infoDiv.appendChild(specialization);

  // Doctor's email
  const email = document.createElement('p');
  email.textContent = `Email: ${doctor.email}`;
  infoDiv.appendChild(email);

  // Available times
  const times = document.createElement('p');
  times.textContent = `Available Times: ${doctor.availableTimes.join(', ')}`; // Join array into a string to display all available times, example format: "9:00 AM, 10:00 AM, 11:00 AM"
  infoDiv.appendChild(times);

  // Create a div to hold the action buttons (like delete or book now), the buttons are create below based on role
  const actionsDiv = document.createElement('div');
  actionsDiv.className = 'card-actions'; // class styling
  
  // === ADMIN ROLE ACTIONS ===

  // If the user role is admin, will show the buttons created below
  if (userRole === 'admin') {
    // Remove Doctor Button
    const removeBtn = document.createElement('button'); // Create a button element
    removeBtn.textContent = 'Delete'; // Set the button text
    removeBtn.className = 'deleteDoctorBtn'; // class styling CSS
    // Create an an action (event listener) for the button when clicked
    removeBtn.addEventListener('click', async () => { // Make it async since we will call an API
      // Confirm if the admin really wants to delete
      const confirmed = confirm(`Are you sure you want to delete Dr. ${doctor.name}?`); // Variable to confirm deletion
      if (!confirmed) return; // If not true, exit the function

      // If confirmed, proceed to delete
      // Get the admin token from local storage to authorize the delete request
      try {
        const token = localStorage.getItem('token');
        if (!token) { // If no token, alert and redirect to login
          alert('Session expired. Please log in again.');
          windows.location.href = "/";
          return;
        } 

        // Call API to delete
        const response = await deleteDoctor(doctor.id, token); // This will call the deleteDoctor function imported from doctorServices.js, passing the doctor ID and token

        // If successful, remove the card from the UI and alert success
        if (response.success) {
          alert('Doctor deleted successfully.');
          card.remove();
        } else { // If not successful, alert the error message
          alert(`Failed to delete doctor: ${response.message}`);
        }
      } catch (error) { // Catch any errors during the process and log them
        console.error('Error deleting doctor:', error);
        alert('An error occurred while deleting the doctor. Please try again later.');
      }  
    });

    // Add button to actionDiv variable created above
    actionsDiv.appendChild(removeBtn);

  }

  // Patient not logged-in
   else if (role === "patient") {
    const bookNow = document.createElement("button");
    bookNow.textContent = "Book Now";
    bookNow.addEventListener("click", () => {
      alert("Patient needs to login first.");
    });

    // Add button to actions container
    actionsDiv.appendChild(bookNow);
  }
  
  // Logged-in patient
  else if (role === "loggedPatient") {
    const bookNow = document.createElement("button");
    bookNow.textContent = "Book Now";
    bookNow.addEventListener("click", async (e) => { // The e represents info about the click event
      const token = localStorage.getItem("token");
      const patientData = await getPatientData(token);
      showBookingOverlay(e, doctor, patientData); // This gives the info to the showBookingOverlay function imported from loggedPatient.js
    });

    // Add button to actions container
    actionsDiv.appendChild(bookNow);
  }

  // Add info and actions to the card
  card.appendChild(infoDiv);
  card.appendChild(actionsDiv);

  return card;
}




/*
Import the overlay function for booking appointments from loggedPatient.js

  Import the deleteDoctor API function to remove doctors (admin role) from docotrServices.js

  Import function to fetch patient details (used during booking) from patientServices.js

  Function to create and return a DOM element for a single doctor card
    Create the main container for the doctor card
    Retrieve the current user role from localStorage
    Create a div to hold doctor information
    Create and set the doctor’s name
    Create and set the doctor's specialization
    Create and set the doctor's email
    Create and list available appointment times
    Append all info elements to the doctor info container
    Create a container for card action buttons
    === ADMIN ROLE ACTIONS ===
      Create a delete button
      Add click handler for delete button
     Get the admin token from localStorage
        Call API to delete the doctor
        Show result and remove card if successful
      Add delete button to actions container
   
    === PATIENT (NOT LOGGED-IN) ROLE ACTIONS ===
      Create a book now button
      Alert patient to log in before booking
      Add button to actions container
  
    === LOGGED-IN PATIENT ROLE ACTIONS === 
      Create a book now button
      Handle booking logic for logged-in patient   
        Redirect if token not available
        Fetch patient data with token
        Show booking overlay UI with doctor and patient info
      Add button to actions container
   
  Append doctor info and action buttons to the car
  Return the complete doctor card element
*/
