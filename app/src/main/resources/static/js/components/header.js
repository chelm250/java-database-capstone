// Create the function to render the header on the HTML page
// This function will check user role and session status to determine what to show in the header
function renderHeader() {

  // Create the header div (for the HTML file)
  // Creates a const variable, with the name headerDiv
  const headerDiv = document.getElementById("header"); // Set the header div Id so it can be injected into HTML

  // Create a let variable to hold the header content. Use ""; to initialize it as an empty string
  let headerContent = "";

  // If you are on the root page, clear localStorage and render a simple header
  // (no user-specific elements, just logo and site title) on this case
  if (window.location.pathname.endsWith("/")) {
    localStorage.removeItem("userRole"); // Clear user role from localStorage
    localStorage.removeItem("token"); // Clear token from localStorage
  }

  // Create variables and get user role and token from localStorage
  // This will help determine what to show in the header depending on user role
  const role = localStorage.getItem("userRole"); // Get user role from localStorage
  const token = localStorage.getItem("token"); // Get token from localStorage

  // Create validation to check if user is logged in and has a valid token
  // If user has a role but no token, it means the session has expired or is invalid
  // In this case, log the user out and redirect to homepage
  if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
    localStorage.removeItem("userRole"); // Clear user role from localStorage
    localStorage.removeItem("token"); // Clear token from localStorage
    alert("Session expired or invalid login. Please log in again."); // Alert user
    window.location.href = "/"; // Redirect to homepage
    return;
  }

  // Create the header content based on user role
  // Here we build the header in HTML format and is stored in the headerContent variable
  // The headerContent variable then will be injected into the headerDiv variable (which is the header div in HTML)
  if (role === 'admin') { // Check if user role is admin
    // If user is admin, show admin-specific buttons
    // Use backticks `` to create a multi-line string in JavaScript
    // Use single quotes '' inside the backticks to avoid conflicts
    // Use an id for each button to attach event listeners later (defined actions in the attachHeaderButtonListeners function below)
    // Use class for grouping itmes for styling purposes on CSS
    // Add actions in case the the function attached to the button is not defined or not working
    headerContent = `
      <button id="adminBtn" class="menuBtn" onclick="window.location.href='adminDashboard.html'">Home</button>
      <button id="addDocBtn" class="menuBtn" onclick="openModal(\'addDoctor\')">Add Doctor</button>
      <a id="logout" href="#" onclick="logout()">Logout</a>
    `;
  }

  else if (role === 'doctor') {
    headerContent = `
      <button id="doctorBtn" class="menuBtn" onclick="window.location.href='adminDashboard.html'">Home</button>
      <a id="logout" href="#" onclick="logout()">Logout</a>
      `;
  }

  else if (role === 'patient') {
    headerContent = `
      <button id="loggedOutPatientBtn" class="menuBtn"window.location.href='patientDashboard.html'">Home</button>
      <button id="patientLoginBtn" class="menuBtn">Login</button>
      <button id="patientSignupBtn" class="menuBtn">Sign Up</button>
    `;
  }

  else if (role === 'loggedPatient') {
    headerContent = `
      <button id="patientBtn" class="menuBtn" onclick="window.location.href='loggedPatientDashboard.html'">Home</button>
      <button id="patientAppointmentBtn" class="menuBtn" onclick="window.location.href='patientAppointments.html'">Appointments</button>
      <a id="logout" href="#" onclick="logout()">Logout</a>
      `;
  }

  // Inject the headerContent variable into the headerDiv variable (which is the header div in HTML)
  // This will render the header on the HTML page
  headerDiv.innerHTML = headerContent; // Inject the header content into the header div
  attachHeaderButtonListeners(); // Run the function to attach actions to the buttons, this function is defined below
}

// Create a Logout function to clear localStorage and redirect to homepage
// This function will be called when the user clicks the logout button, defined in the attachHeaderButtonListeners function below
function logout() {
  // Clear user session data from localStorage
  localStorage.removeItem("userRole"); // Clear user role from localStorage
  localStorage.removeItem("token"); // Clear token from localStorage
  // Redirect to page depending on role
  if (role === 'loggedPatient') {
    window.location.href = "/pages/patientDashboard.html"; // Redirect to patient dashboard
    return;
  } else {
    window.location.href = "/"; // Redirect to homepage
    return;
  }
}

// Creat the function to give actions to the buttons in the header
// This function will add event listeners to the buttons in the header, so they can perform actions when clicked
// The buttons are created dynamically based on user role, so we need to attach the event listeners after the header is rendered
// Remember to use the correct Ids for the buttons, as defined in the renderHeader function above
function attachHeaderButtonListeners() {

  // Admin Buttons
  // Admin Home Button
  const adminBtn = document.getElementById("adminBtn"); // Create a const variable to get the admin home button by its Id
  if (adminBtn) { // If the admin button exists (user is admin), add an event listener to it
    adminBtn.addEventListener("click", () => window.location.href = 'adminDashboard.html'); // When clicked, redirect to admin dashboard
  }
  // Add Doctor Button (Admin Role)
  const addDocBtn = document.getElementById("addDocBtn");
  if (addDocBtn) {
    addDocBtn.addEventListener("click", () => openModal('addDoctor'));
  }

  // Doctor Buttons
  // Add Home Button (Doctor Role)
  const doctorBtn = document.getElementById("doctorBtn");
  if (doctorBtn) {
    addDocBtn.addEventListener("click", () => window.location.href = 'doctorDashboard.html');
  }

  // Patient Buttons
  // Logged Out Patient Home Button
  const loggedOutPatientBtn = document.getElementById("loggedOutPatientBtn");
  if (loggedOutPatientBtn) {
    loggedOutPatientBtn.addEventListener("click", () => window.location.href = 'patientDashboard.html');
  }
  // Patient Login Button
  const patientLoginBtn = document.getElementById("patientLoginBtn");
  if (patientLoginBtn) {
    patientLoginBtn.addEventListener("click", () => openModal('patientLogin'));
  }
  // Patient Signup Button
  const patientSignupBtn = document.getElementById("patientSignupBtn");
  if (patientSignupBtn) {
    patientSignupBtn.addEventListener("click", () => openModal('patientSignup'));
  }

  // Logged Patient Buttons
  // Logged Patient Home Button
  const patientBtn = document.getElementById("patientBtn");
  if (patientBtn) {
    patientBtn.addEventListener("click", () => window.location.href = 'loggedPatientDashboard.html');
  }
  // Logged Patient Appointments Button
  const patientAppointmentBtn = document.getElementById("patientAppointmentBtn");
  if (patientAppointmentBtn) {
    patientAppointmentBtn.addEventListener("click", () => window.location.href = 'patientAppointments.html');
  }

  // Create an action for the logout button which call the logout function defined above
  const logoutBtn = document.getElementById("logout");
  if (logoutBtn) {
    logoutBtn.addEventListener("click", () => logout()); // When clicked, call the logout function
  }
}

// Call the function to render the header when the HTML page loads
// Remember to call the js file in the HTML file, so this function can be executed
// This is called with the Id "header" defined in the const headerDiv variable above
renderHeader();


/*
  Step-by-Step Explanation of Header Section Rendering

  This code dynamically renders the header section of the page based on the user's role, session status, and available actions (such as login, logout, or role-switching).

  1. Define the `renderHeader` Function

     * The `renderHeader` function is responsible for rendering the entire header based on the user's session, role, and whether they are logged in.

  2. Select the Header Div

     * The `headerDiv` variable retrieves the HTML element with the ID `header`, where the header content will be inserted.
       ```javascript
       const headerDiv = document.getElementById("header");
       ```

  3. Check if the Current Page is the Root Page

     * The `window.location.pathname` is checked to see if the current page is the root (`/`). If true, the user's session data (role) is removed from `localStorage`, and the header is rendered without any user-specific elements (just the logo and site title).
       ```javascript
       if (window.location.pathname.endsWith("/")) {
         localStorage.removeItem("userRole");
         headerDiv.innerHTML = `
           <header class="header">
             <div class="logo-section">
               <img src="../assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
               <span class="logo-title">Hospital CMS</span>
             </div>
           </header>`;
         return;
       }
       ```

  4. Retrieve the User's Role and Token from LocalStorage

     * The `role` (user role like admin, patient, doctor) and `token` (authentication token) are retrieved from `localStorage` to determine the user's current session.
       ```javascript
       const role = localStorage.getItem("userRole");
       const token = localStorage.getItem("token");
       ```

  5. Initialize Header Content

     * The `headerContent` variable is initialized with basic header HTML (logo section), to which additional elements will be added based on the user's role.
       ```javascript
       let headerContent = `<header class="header">
         <div class="logo-section">
           <img src="../assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
           <span class="logo-title">Hospital CMS</span>
         </div>
         <nav>`;
       ```

  6. Handle Session Expiry or Invalid Login

     * If a user with a role like `loggedPatient`, `admin`, or `doctor` does not have a valid `token`, the session is considered expired or invalid. The user is logged out, and a message is shown.
       ```javascript
       if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
         localStorage.removeItem("userRole");
         alert("Session expired or invalid login. Please log in again.");
         window.location.href = "/";   or a specific login page
         return;
       }
       ```

  7. Add Role-Specific Header Content

     * Depending on the user's role, different actions or buttons are rendered in the header:
       - **Admin**: Can add a doctor and log out.
       - **Doctor**: Has a home button and log out.
       - **Patient**: Shows login and signup buttons.
       - **LoggedPatient**: Has home, appointments, and logout options.
       ```javascript
       else if (role === "admin") {
         headerContent += `
           <button id="addDocBtn" class="adminBtn" onclick="openModal('addDoctor')">Add Doctor</button>
           <a href="#" onclick="logout()">Logout</a>`;
       } else if (role === "doctor") {
         headerContent += `
           <button class="adminBtn"  onclick="selectRole('doctor')">Home</button>
           <a href="#" onclick="logout()">Logout</a>`;
       } else if (role === "patient") {
         headerContent += `
           <button id="patientLogin" class="adminBtn">Login</button>
           <button id="patientSignup" class="adminBtn">Sign Up</button>`;
       } else if (role === "loggedPatient") {
         headerContent += `
           <button id="home" class="adminBtn" onclick="window.location.href='/pages/loggedPatientDashboard.html'">Home</button>
           <button id="patientAppointments" class="adminBtn" onclick="window.location.href='/pages/patientAppointments.html'">Appointments</button>
           <a href="#" onclick="logoutPatient()">Logout</a>`;
       }
       ```



  9. Close the Header Section



  10. Render the Header Content

     * Insert the dynamically generated `headerContent` into the `headerDiv` element.
       ```javascript
       headerDiv.innerHTML = headerContent;
       ```

  11. Attach Event Listeners to Header Buttons

     * Call `attachHeaderButtonListeners` to add event listeners to any dynamically created buttons in the header (e.g., login, logout, home).
       ```javascript
       attachHeaderButtonListeners();
       ```


  ### Helper Functions

  13. **attachHeaderButtonListeners**: Adds event listeners to login buttons for "Doctor" and "Admin" roles. If clicked, it opens the respective login modal.

  14. **logout**: Removes user session data and redirects the user to the root page.

  15. **logoutPatient**: Removes the patient's session token and redirects to the patient dashboard.

  16. **Render the Header**: Finally, the `renderHeader()` function is called to initialize the header rendering process when the page loads.
*/
   
