# Online Movie Ticket Booking System - Backend API

A robust RESTful API built with Java and Spring Boot for an online movie ticket booking system. The application handles user authentication, movie and showtime management, real-time seat availability checking, booking processing, and payments.

---

## 🛠️ Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x (Spring Web, Spring Security, Spring Data JPA)
* **Database:** MySQL
* **Authentication:** JSON Web Token (JWT)
* **Build Tool:** Maven

---

## ⚙️ Database Configuration & Setup

1. Create a MySQL database for the project in your local MySQL environment:
   ```sql
   CREATE DATABASE movie_booking_db;

## ⚙️ Configuration (`application.properties`)

## ⚙️ application.properties

server.port=8081<br>
spring.datasource.url=jdbc:mysql://localhost:3306/movie_booking_db?useSSL=false&serverTimezone=UTC<br>
spring.datasource.username=YOUR_MYSQL_USERNAME<br>
spring.datasource.password=YOUR_MYSQL_PASSWORD<br>
<br>
spring.jpa.hibernate.ddl-auto=update<br>
spring.jpa.show-sql=true

 ## ⚙️ How to Run the Backend Application
Open your terminal or IDE terminal in the project root folder.

Build and run the project using Maven:
mvn spring-boot:run
The server will start running on port 8081:

## 🔑 Test Credentials (Default Users)

| Role | Email | Password |
| :--- | :--- | :--- |
| **Admin** | `admindasun@gmail.com` | `admin123` |
| **User** | `nirosh@gmail.com` | `pass123` |

 ## ⚙️ Authentication Flow
Send a POST request to /api/auth/login with your credentials.

The server will return a JWT token in the response.

For protected endpoints, pass the JWT token in the Authorization header:
Authorization: Bearer <YOUR_JWT_TOKEN>

 ## ⚙️ API Endpoints Overview
 
## 🚀 API Endpoints Summary

### 🔐 Auth Controller (`/api/auth`)
* `POST` `/api/auth/register` - Register a new user
* `POST` `/api/auth/login` - Authenticate user & return JWT token

### 🎬 Movie Controller (`/api/movies`)
* `GET` `/api/movies` - Get all movies
* `GET` `/api/movies/{id}` - Get movie details by ID
* `POST` `/api/movies` - Add a new movie (Admin)
* `PUT` `/api/movies/{id}` - Update movie details (Admin)
* `DELETE` `/api/movies/{id}` - Delete a movie (Admin)

### 🎭 Theatre Controller (`/api/theatres`)
* `GET` `/api/theatres` - Get all theatres
* `GET` `/api/theatres/{id}` - Get theatre details by ID
* `POST` `/api/theatres` - Add a new theatre (Admin)
* `PUT` `/api/theatres/{id}` - Update theatre details (Admin)
* `DELETE` `/api/theatres/{id}` - Delete a theatre (Admin)

### 📅 Show Controller (`/api/shows`)
* `GET` `/api/shows` - Get all scheduled shows
* `GET` `/api/shows/movie/{movieId}` - Get active shows for a specific movie
* `POST` `/api/shows` - Schedule a new show (Admin)
* `PUT` `/api/shows/{id}` - Update show details (Admin)
* `DELETE` `/api/shows/{id}` - Delete/Cancel a show (Admin)

### 🎟️ Booking Controller (`/api/bookings`)
* `POST` `/api/bookings` - Create a new seat booking (Customer)
* `GET` `/api/bookings/user/{userId}` - Get booking history for a specific user
* `GET` `/api/bookings/show/{showId}/booked-seats` - Get already booked seats for a show

### 💳 Payment Controller (`/api/payments`)
* `POST` `/api/payments` - Process payment for a booking
* `GET` `/api/payments/booking/{bookingId}` - Retrieve payment status for a booking

## 📐 Entity Relationship Diagram (ERD)

<div align="center">
  <img src="assets/erd-diagram.png" alt="Movie Booking System ERD" width="85%" style="background-color: white; padding: 10px; border-radius: 8px;" />
</div>
