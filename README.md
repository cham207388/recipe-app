# Recipe Project

Building a Recipe Application Using Microservice Architecture
## Services I use
1. Netflix Eureka discovery service for service registration and discovery
2. API Gateway: Spring Cloud Gateway 
3. Spring Cloud Config Server for centralized configuration
4. Recipe-main-service
   This is the main service. Recipe has ingredients, category, name, notes, etc
5. Recipe-notes-service
   Recipe notes are managed by the recipe-notes-service.

## Tools and libraries
1. Maven for dependency management. I leverage a parent pom for shared dependencies
2. Swagger for documentation and API testing
3. Lombok for cleaner code: generates getters, setters, toString, and equalsandhashcode through annotation
4. Git for continuous integration 
6. PostgreSQL
7. jdl-studio for ERD
8. Junit-jupiter and mockito for unit testing

## Stretch goals
1. Use jenkins
2. AWS

