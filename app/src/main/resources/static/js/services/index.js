import { openModal } from '../components/modals.js'; // Import the modal utility functions to handle showing/hiding modals. Modals are simple popups for login forms or messages.
import { API_BASE_URL } from '../config.js'; // Import the base API URL form the config file. The BASE_API_URL is a variable that hold the root URL created in config.js
import { selectRole } from '../render.js'; // Import the selectRole function from render.js to handle role selection and redirection after login

const ADMIN_API = API_BASE_URL + '/admin'; // Define a variable with the full URL for the admin login endpoint by appending /admin to the base URL
const DOCTOR_API = API_BASE_URL + '/doctor/login'; // Define a variable with the full URL for the doctor login endpoint by appending /doctor/login to the base URL

// Create a window.onload function to give an action to the buttons created in index.html
// window.onload ensures the DOM is fully loaded before we try to access elements. This means the code inside will run only after the page is ready.
window.onload = function() {
  const adminLoginBtn = document.getElementById('adminLoginBtn'); // Create a variable to select the admin login button using its Id in index.html
  if (adminLoginBtn) { // If the button exists on the page then add an event listener(action) to it
    adminLoginBtn.addEventListener('click', () => openModal('adminLogin')) // If the button is clicked, call the openModal function with 'adminLogin' to show the admin login modal
  };

  // Repeat the same process for the doctor login button
  const doctorLoginBtn = document.getElementById('doctorLoginBtn');
  if (doctorLoginBtn) {
    doctorLoginBtn.addEventListener('click', () => openModal('doctorLogin'))
  }
};

// Create the function to handle admin login when the admin clicks the login button in the admin login modal, the function is launched from index.html
window.adminLoginHandler = async function() {
  const username = document.getElementById('adminUsername').value; // Gets the entered username from the input fields in the admin login modal in the index.html file. Make sure the Id matches
  const password = document.getElementById('adminPassword').value; // Gets the entered password from the input fields in the admin login modal in the index.html file. Make sure the Id matches

  // Create a variable to hold the admin credentials, this will be used to send to the backend API for verification
  const admin = { username, password };

  // Use try catch to handle any errors that may ocurr during the fetch request
  try {
    const response = await fetch(ADMIN_API, { // Use fetch() to send a POST request to the ADMIN_API endpoint. This variable was created at the top of this file. The endpoint is where the java file is listening for the request
      method: 'POST', // Set the HTTP method to POST since we are sending data to the server, this is done in the backend using @PostMapping
      headers: { 'Content-Type': 'application/json' }, // Set the request headers to indicate we are sending JSON data. The header key is 'Content-Type' and the value is 'application/json'. This will be handled in the backend using @RequestBody
      body: JSON.stringify(admin) // Converts the admin variable created above to a JSON string and includes it in the request body. This will be handled in the backend using @RequestBody
    });

    if (response.ok) { // If the response is succesful (status code 200-299), then:
      const data = await response.json(); // This parses the JSON response from the server to get the token sent from the backend. The token contains the admin's session information like id and role. This token is created in the backend using JWT library and returned in the response body
      localStorage.setItem('token', data.token); // Store the token in the localStorage of the browser so it can be used later to authenticate requests to protect endpoints. The token is stored with the key 'token'
      selectRole('admin'); // This runs the function selectRole created in render.js to handle what happens when an admin logs in. This function is imported from render.js
    } else {
      alert('Invalid admin credentials. Please try again.'); // If the login fails (invalid credentials or server error), show an alert with an error message
    }
  } catch (error) {
    alert('An error occurred during admin login. Please try again later.') // If there is a network error or other issues, show a generic error message
  }
};

// Create the function for doctor in this case we use email instead of username
window.doctorLoginHandler = async function() {
  const email = document.getElementById('doctorEmail').value;
  const password = document.getElementById('doctorPassword').value;

  const doctor = { email, password };

  try {
    const response = await fetch(DOCTOR_API, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(doctor)
    });

    if (response.ok) {
      const data = await response.json();
      localStorage.setItem('token', data.token);
      selectRole('doctor');
    } else {
      alert('Invalid doctor credentials. Please try again.');
    }
  } catch (error) {
    console.error('Error during doctor login:', error);
    alert('An error occurred during doctor login. Please try again later.')
  }
};

/*
  Import the openModal function to handle showing login popups/modals
  Import the base API URL from the config file
  Define constants for the admin and doctor login API endpoints using the base URL

  Use the window.onload event to ensure DOM elements are available after page load
  Inside this function:
    - Select the "adminLogin" and "doctorLogin" buttons using getElementById
    - If the admin login button exists:
        - Add a click event listener that calls openModal('adminLogin') to show the admin login modal
    - If the doctor login button exists:
        - Add a click event listener that calls openModal('doctorLogin') to show the doctor login modal


  Define a function named adminLoginHandler on the global window object
  This function will be triggered when the admin submits their login credentials

  Step 1: Get the entered username and password from the input fields
  Step 2: Create an admin object with these credentials

  Step 3: Use fetch() to send a POST request to the ADMIN_API endpoint
    - Set method to POST
    - Add headers with 'Content-Type: application/json'
    - Convert the admin object to JSON and send in the body

  Step 4: If the response is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('admin') to proceed with admin-specific behavior

  Step 5: If login fails or credentials are invalid:
    - Show an alert with an error message

  Step 6: Wrap everything in a try-catch to handle network or server errors
    - Show a generic error message if something goes wrong


  Define a function named doctorLoginHandler on the global window object
  This function will be triggered when a doctor submits their login credentials

  Step 1: Get the entered email and password from the input fields
  Step 2: Create a doctor object with these credentials

  Step 3: Use fetch() to send a POST request to the DOCTOR_API endpoint
    - Include headers and request body similar to admin login

  Step 4: If login is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('doctor') to proceed with doctor-specific behavior

  Step 5: If login fails:
    - Show an alert for invalid credentials

  Step 6: Wrap in a try-catch block to handle errors gracefully
    - Log the error to the console
    - Show a generic error message
*/
