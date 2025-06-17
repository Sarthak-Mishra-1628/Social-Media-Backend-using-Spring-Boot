# Social Media Backend using Spring Boot

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.1-green)
![GitHub last commit](https://img.shields.io/github/last-commit/Sarthak-Mishra-1628/Social-Media-Backend-using-Spring-Boot)
![GitHub stars](https://img.shields.io/github/stars/Sarthak-Mishra-1628/Social-Media-Backend-using-Spring-Boot?style=social)

## 📝 Description

This is a **backend API** for a Social Media platform built with **Spring Boot**. It handles features like user signup, authentication (JWT), post creation, likes, saves, and story expiry — with a focus on scalability and real-world backend practices.

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security + JWT
- MySQL
- Maven
- Postman (for testing)

## 🚀 Features

- ✅ User signup/login with JWT token-based authentication
- 📝 Create, delete, like, and save posts
- 🕒 Stories with 24-hour expiry logic
- 📰 Feed with advanced sorting/filtering
- 📊 Analytics endpoint for user insights
- 🔐 Role-based access control

## 🔐 Authentication

This app uses **JWT (JSON Web Tokens)** for stateless authentication.  
On login, a token is generated which must be included in headers for all secure endpoints