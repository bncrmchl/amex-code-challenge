# School Management System

## Description

This project is a Spring Boot-based RESTful API for managing student records in a school system. It provides endpoints for adding, retrieving, updating, and deleting student data. The application utilizes PostgreSQL for data storage and is designed to be run in Docker containers, ensuring easy setup and scalability.

## Prerequisites

Before you can run this project, you need to ensure that your system has the following software installed:

- **Java JDK 17**: The application is built and tested with OpenJDK 17.
- **Docker**: Docker is used to containerize the application and the PostgreSQL database.
- **Docker Compose**: Used to manage multi-container Docker applications.
- **Gradle**: Used for dependency management.

## Installation

Follow these steps to get your development environment set up:

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/school-management-system.git
   cd school-management-system
   ```
   
2. **Set up your environment variables**
   This project stands up its own database, but of course it is not safe to store database passwords in git. Instead, the application will look for environment variables containing the passwords for the admin user and api user. You can export the passwords with the following bash commands.
   ```bash
   export POSTGRES_DB=schooldb
   export POSTGRES_ADMIN_USER=admin_user
   export POSTGRES_ADMIN_PASSWORD=admin_password
   export POSTGRES_API_USER=api_user
   export POSTGRES_API_PASSWORD=api_password
   ```
   Alternatively, you can create a .env file in the project root that looks like this:
   ```
   POSTGRES_DB=schooldb
   POSTGRES_ADMIN_USER=admin_user
   POSTGRES_ADMIN_PASSWORD=admin_password
   POSTGRES_API_USER=api_user
   POSTGRES_API_PASSWORD=api_password
   ```
   The gitignore file is pre-configured to ignore the .env file, so these credentials will not be stored in version control.


2. **Build the project**
   Navigate to the project directory and build the project using Gradle:

   ```bash
   ./gradlew build
   ```
   
3. **Docker**
   This command will stand up both the database and API, and put them on the same docker network so they can communicate.
   ```bash
   docker compose up 
   ```
   This command builds the images if they don't exist and starts the containers as specified in the docker-compose.yml file.

## Running the Application
Once the Docker containers are running, you can access the application at http://localhost:8080.

### Available Endpoints
- POST /students: Add a new student
- GET /students/{id}: Retrieve a student by ID
- GET /students/search: Search for students by name or class
- PUT /students/{id}: Update a student's details
- DELETE /students/{id}: Delete a student by ID

You can use tools like Postman or curl to interact with the API.
