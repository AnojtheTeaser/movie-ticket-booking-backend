# Online Movie Ticket Booking System - Backend API

A robust RESTful API built with Java and Spring Boot for an online movie ticket booking platform. The system supports JWT-based authentication, role-based access control (ADMIN and CUSTOMER), real-time seat availability checking, show scheduling, and payment processing.

---

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.x (Spring Web, Spring Security, Spring Data JPA)
* **Database:** MySQL
* **Authentication:** JSON Web Token (JWT)
* **Build Tool:** Maven

---

## ⚙️ Database Configuration & Setup

1. Create a MySQL database for the project:
   ```sql
   CREATE DATABASE movie_booking_db;
Configure your local MySQL database username and password in src/main/resources/application.properties:

Properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/movie_booking_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🚀 How to Run the Backend ApplicationOpen your terminal or IDE terminal in the project root folder.Build and run the project using Maven:Bashmvn spring-boot:run
The server will start running on port 8081:http://localhost:8081
🔑 Test Credentials (Default Users)For evaluation and testing, you can log in using the following credentials:RoleEmailPasswordAccess LevelAdminadmindasun@gmail.comadmin123Full Access (Add/Update/Delete Movies, Theatres, Shows)Customernirosh@gmail.compass123Customer Access (View Shows, Book Seats, Make Payments)🛡️ Authentication FlowSend a POST request to /api/auth/login with your credentials.The server will return a JWT token in the response.For protected endpoints, pass the JWT token in the Authorization header:HTTPAuthorization: Bearer <YOUR_JWT_TOKEN>
📡 API Endpoints OverviewAuth Controller (/api/auth)POST /api/auth/register - Register a new userPOST /api/auth/login - Authenticate user & return JWT tokenMovie Controller (/api/movies)GET /api/movies - Get all moviesGET /api/movies/{id} - Get movie details by IDPOST /api/movies - Add a new movie (Admin)PUT /api/movies/{id} - Update movie details (Admin)DELETE /api/movies/{id} - Delete a movie (Admin)Theatre Controller (/api/theatres)GET /api/theatres - Get all theatresGET /api/theatres/{id} - Get theatre details by IDPOST /api/theatres - Add a new theatre (Admin)PUT /api/theatres/{id} - Update theatre details (Admin)DELETE /api/theatres/{id} - Delete a theatre (Admin)Show Controller (/api/shows)GET /api/shows - Get all scheduled showsGET /api/shows/movie/{movieId} - Get active shows for a specific moviePOST /api/shows - Schedule a new show (Admin)PUT /api/shows/{id} - Update show details (Admin)DELETE /api/shows/{id} - Delete/Cancel a show (Admin)Booking Controller (/api/bookings)POST /api/bookings - Create a new seat booking (Customer)GET /api/bookings/user/{userId} - Get booking history for a specific userGET /api/bookings/show/{showId}/booked-seats - Get already booked seats for a showPayment Controller (/api/payments)POST /api/payments - Process payment for a bookingGET /api/payments/booking/{bookingId} - Retrieve payment status for a booking
