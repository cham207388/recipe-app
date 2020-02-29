# Recipe Project

Building a Recipe Application Using Microservice Architecture
## Services I use
1. Netflix Eureka discovery service for service registration and discovery
2. Zuul API Gateway service for one point entry 
3. Spring Cloud Config Server for centralized configuration
4. Recipe-main-service
5. Recipe-notes-service

## Tools
1. Maven for dependency management. I leverage a parent pom for shared dependencies
2. Swagger for documentation and API testing
3. Lombok for cleaner code: generates getters, setters, toString, and equalsandhashcode through annotation
4. Git for continuous integration
5. Thymeleaf
6. h2 in-memory database

## Stretch goals
1. Use jenkins
2. AWS

### 
I am currently using thymeleaf for UI and h2 in-memory database for development purpose.
Once the application is fully functional, I want to switch to Angular 9 and Postgres SQL
for UI presentation and data persisting respectively.

