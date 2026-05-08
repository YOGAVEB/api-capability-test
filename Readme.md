# API Capability Test

## Overview

This project demonstrates a small event-driven microservice architecture using:

- Java Spring Boot
- RabbitMQ
- PostgreSQL
- Redis
- Docker Compose

The system contains two separate services:

- Service A
- Service B

Communication between services is handled only through RabbitMQ.

---

# Architecture

Client  
→ Service A  
→ RabbitMQ  
→ Service B  
→ PostgreSQL / Redis

---

# Services

## Service A

Responsibilities:

- Exposes REST API endpoints
- Sends messages to RabbitMQ
- Receives reply messages from RabbitMQ

Service A does NOT connect directly to:

- PostgreSQL
- Redis

---

## Service B

Responsibilities:

- Consumes RabbitMQ messages
- Handles authentication
- Handles business logic
- Connects to PostgreSQL
- Connects to Redis
- Sends reply messages back through RabbitMQ

---

# Infrastructure

The project uses Docker Compose for:

- RabbitMQ
- Redis
- PostgreSQL

---

# Technologies

- Java 17
- Spring Boot
- Spring AMQP
- Spring Data JPA
- Spring Data Redis
- PostgreSQL
- RabbitMQ
- Docker Compose

---

# How To Run

## 1. Start Infrastructure

```bash 
docker compose up -d
```

## 2. Run Service A
```bash
Open project:

service.a

Run:

Application.java

Service A runs on:

http://localhost:8080
```
## 3. Run Service B
```bash
Open project:

service.b

Run:

Application.java

Service B runs on:

http://localhost:8081
```

##  RabbitMQ Dashboard
```bash
URL:

http://localhost:15672

Credentials:

guest / guest
```

##  PostgreSQL
```bash
Database:

appdb

Username: admin

Password:admin
```
##  Redis
````bash
Redis is used for:

token storage
session validation

Token data format: token -> username
````
##  Queue Names
```bash
  -login.queue
  -login.reply.queue
  -profile.queue
  -rofile.reply.queu
```
##  Authentication Flow
```bash
Login Request

Endpoint:

POST /auth/login

Request:

{
"username": "admin",
"password": "123"
}

Success Response:

{
"success": true,
"message": "Login success",
"token": "generated-token"
}

Failed Response:

{
"success": false,
"message": "Invalid credentials",
"token": null
}
```

##  Authenticated Business Flow
```bash
Get Profile

Endpoint:

GET /profile

Headers:

Authorization: TOKEN

Success Response:

{
  "success": true,
  "username": "admin",
  "message": "Profile success"
}

Failed Response:

{
  "success": false,
  "username": null,
  "message": "Invalid token"
}
```
## Database
```bash
PostgreSQL is used only by Service B.

Entity:

UserEntity

Table:

users 

```
## Event-Driven Communication
```bash
All communication between Service A and Service B uses RabbitMQ.

No direct HTTP communication exists between services.

The implementation uses a request-reply messaging pattern
```

# Notes
```bash
-Service A does not connect to Redis or PostgreSQL
-Service B owns authentication and database access
-Redis stores temporary login sessions and tokens
-RabbitMQ handles all service-to-service communication
```