function renderHeader() {
  const headerDiv = document.getElementById("header"); // Get the header div

  // If on the root page, clear session and render basic header
  if (window.location.pathname.endsWith("/")) {
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
  }

  // Get user role and token from localStorage
  const role = localStorage.getItem("userRole");
  const token = localStorage.getItem("token");

  // Validate session
  if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    alert("Session expired or invalid login. Please log in again.");
    window.location.href = "/"; // Redirect to homepage
    return;
  }

  // Start building the header content
  let headerContent = "";

  if (role === 'admin') {
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

  // This injects header content into the header div
  headerDiv.innerHTML = headerContent;
  attachHeaderButtonListeners();

  // Add event listeners to make buttons work with javascript
  function attachHeaderButtonListeners() {

    // Admin Buttons
    // Admin Home Button
    const adminBtn = document.getElementById("adminBtn");
    if (adminBtn) {
      adminBtn.addEventListener("click", () => window.location.href = 'adminDashboard.html');
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

    // Logout Button
    const logoutBtn = document.getElementById("logout");
    if (logoutBtn) {
      logoutBtn.addEventListener("click", () => logout());
    }
  }

  // Logout function to clear session and redirect to homepage
  function logout() {
    localStorage.removeItem("userRole");
    localStorage.removeItem("token");
    if (role === 'loggedPatient') {
      window.location.href = "/pages/patientDashboard.html"; // Redirect to patient dashboard
      return;
    } else {
      window.location.href = "/"; // Redirect to homepage
      return;
    }
  }

}  

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
   
