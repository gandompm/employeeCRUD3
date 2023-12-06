# EmployeeCRUD3

**EmployeeCRUD3** is a powerful Spring Boot application that demonstrates essential CRUD (Create, Read, Update, Delete) operations for managing employee data.

It offers a seamless RESTful API, enabling smooth employee management with functionalities such as addition, removal, update, and retrieval of employee details.


## Key Technologies: 

- **Spring Boot 3**
- **Spring MVC**
- **Hibernate/JPA CRUD**
- **Spring Boot Security:** Manages user authentication. 
- **Aspect-Oriented Programming (AOP):** Logs before and after returning all functions in the Controller, Service, and DAO layers for monitoring. 
- **REST API Development:** Implements a robust API for seamless data communication.
- **Role-Based Access Control:** Defines different roles (EMPLOYEE, MANAGER, and ADMIN) with specific access permissions:
  - **EMPLOYEE Role:** Restricted access to detailed employee information and not beeing able to add a new employee.
  - **ADMIN Role:** Access to delete employee records.
  - **MANAGER Role:** It has unrestricted access.
- **Entity Mapping:** Implements entity mappings within the application:
  - **OneToOne Mapping:** Between Employee and Employee_Detail entities.
  - **OneToMany Mapping:** Between Employee and Task entities.

  

