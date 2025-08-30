# Forum API

Simple REST API built with Spring Boot, using Docker for the database and Swagger for API documentation. It includes features as:
- Management of Users
- Management of Posts
- Management of Notifications by events
- Threads of comments
---

## Features
- Spring Boot REST API
- Dockerized database
- Swagger UI documentation

---

## Requirements
- Java 17+
- Maven or Gradle
- Docker & Docker Compose

---

## Setup

### 1. Clone the repositories
```bash
git clone https://github.com/JesusVale/forum-api-entities
cd forum-api-entities
mvn clean install

git clone https://github.com/JesusVale/forum-api
cd forum-api
```

### 2. Start Database
```bash
docker compose up
```