This Spring Boot application uses both MVC and REST controllers. Thymeleaf are used for the Admin and Doctor dashboards, while REST APIs serve all other modules. The application intercats with two databases-MYSQL (for patient, doctor, appointment, and admin data) and MongDB (for prescriptions). All controllers route requests through a common service layer, which in turn delegates to the appropriate repositories. MYSQL uses JPA entities while MongoDB uses document models.

1. User accesses AdminDashboard or Appointment pages.
2. The action is routed to the appropriate Thymeleaf or REST controller.
3. The controller calls the service layer.
4. The service layer uses MYSQL or MongoDB repository.
5. The repository access databases.
6. The database responds uses JPA for MYSQL or Document for MongoDB.
7. Models are passed from the controller to Thymeleaf templates
