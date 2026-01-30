# Fitness Tracking Backend API

## 📌 Description
A Spring Boot backend application that provides secure REST APIs for user authentication,
activity tracking, and personalized fitness recommendations.

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Hibernate
- Maven

## 🔑 Features
- User registration & login with JWT authentication
- Role-based access control
- Activity tracking APIs
- Personalized recommendations
- DTO-based request/response handling
- Global exception handling

## 🧱 Architecture
Controller → Service → Repository → Database

## 🔐 Security
- JWT-based authentication
- Custom authentication filter
- Password encryption using BCrypt

## 📡 API Endpoints
| Method | Endpoint | Description |
|------|--------|-------------|
| POST | /api/auth/login | User login |
| POST | /api/auth/register | User registration |
| GET | /api/activities | Get user activities |

## 🗄 Database
- PostgreSQL
- Entity relationships using JPA

## 🚀 How to Run
1. Clone repository
2. Configure `application.properties`
3. Run using `mvn spring-boot:run`
