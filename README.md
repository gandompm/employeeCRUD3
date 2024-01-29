# EmployeeCRUD3

**EmployeeCRUD3** is a Spring Boot application that demonstrates essential CRUD (Create, Read, Update, Delete) operations for managing employee data and has following technologies.




https://github.com/gandompm/employeeCRUD3/assets/17143396/5396f29b-f6c4-4575-b6c4-ad9e5b33fddc



https://github.com/gandompm/employeeCRUD3/assets/17143396/77e82cb1-0250-4586-babb-071059b79563




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
- **Testing** 
  - **Unit Testing:** Performed unit testing for both the MVC and REST API layers.
  - **Instrumented Testing:** involved different layers. 

  

