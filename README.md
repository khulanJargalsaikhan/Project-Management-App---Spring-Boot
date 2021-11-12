# Project-Management-App---Spring-Boot


First commit
-----------

Utilized:

- Thymeleaf, Spring Data JPA, and Entity Relationships(used @OneToMany @ManyToOne @ManyToMany annotations)
- Seeding DB using CommandLineRunner and SQL Files
- Included custom queries in spring data repositories  
- Included JS and CSS files

Second commit
-------------

- Used Environment Variables in Property Configuration file to hide the sensitive information
- Configured properties for PostgreSQL DB and connected the DB to the app

Third commit
-------------

- Setting up configuration for integration testing (@SpringBootTest)
- Utilized junit dependency  
- Integration tests for Controllers/Views

**Not committed works** 
- Built a Docker image using Dockerfile and run it in a container.
- Utilized Amazon RDS (relational database service) and have DB running on the cloud.
- Built the connection between the container and the DB remotely. (Used Env Variables)

Fourth commit
-----------
- Created EmployeeService and ProjectService classes in order to decouple Repositories from Controllers
- Worked with Spring Profiles by creating application-dev.properties and application-prod.properties
  (was able to control the app in different state based on profile that is set)
- Utilized AOP(@Aspect, @Pointcut, @Before, @After with JoinPoint, @Around with ProceedJoinPoint)
- Tested Best Practices for Logging (WARN, DEBUG, INFO)
- Spring Security: Configured JDBC Authorization/Authentication with Postgres Database
- Created User Registration and Password Encryption
- Customized White Label Error pages


5th commit
-----------
- Built CRUD REST endpoints for entities and HTTP verbs
- Set validation rules for a REST API
- Built custom clientside validation involving data repositories
- Added pagination in REST API
- Added Spring Data Rest