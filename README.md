# 📚 Online Book Store

A full-stack online book store built with **Java (Spring Boot)**, **HTML/CSS (Thymeleaf)**, and **PostgreSQL**.

## Features

- Browse & search books by title, author, or genre
- Book detail pages with stock status
- User registration & login (Spring Security, BCrypt password hashing)
- Shopping cart (add / update quantity / remove)
- Checkout with shipping address → order placement
- Order history for customers
- Admin panel: add / edit / delete books, view all orders, update order status
- Auto-seeded sample books + a default admin account on first run

## Tech Stack

| Layer      | Technology                          |
|------------|--------------------------------------|
| Backend    | Java 17, Spring Boot 3, Spring MVC   |
| Security   | Spring Security (form login, roles)  |
| Data       | Spring Data JPA (Hibernate)          |
| Database   | PostgreSQL                           |
| Frontend   | Thymeleaf, HTML5, CSS3               |
| Build      | Maven                                |

## Project Structure

```
online-bookstore/
├── src/main/java/com/bookstore/app/
│   ├── model/          # JPA entities: Book, User, CartItem, Order, OrderItem
│   ├── repository/     # Spring Data JPA repositories
│   ├── service/        # Business logic
│   ├── controller/     # MVC controllers
│   ├── config/         # Spring Security config, auth helpers, data seeding
│   └── OnlineBookstoreApplication.java
├── src/main/resources/
│   ├── templates/       # Thymeleaf HTML pages
│   ├── static/css/      # Stylesheet
│   ├── application.properties
│   ├── data.sql          # Sample book data (auto-loaded)
│   └── db/create_database.sql
└── pom.xml
```

## Setup Instructions

### 1. Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 13+ running locally (or update the URL in `application.properties` to point elsewhere)

### 2. Create the database
```bash
psql -U postgres -f src/main/resources/db/create_database.sql
```
Or simply run in `psql`:
```sql
CREATE DATABASE bookstore_db;
```

### 3. Configure credentials
Edit `src/main/resources/application.properties` if your PostgreSQL username/password differ from the defaults:
```properties
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 4. Run the application
```bash
mvn spring-boot:run
```
The app starts on **http://localhost:8080** and redirects to `/books`.

Tables are auto-created by Hibernate (`ddl-auto=update`) and sample books are inserted automatically from `data.sql`.

### 5. Log in

| Role   | Email                | Password  |
|--------|-----------------------|-----------|
| Admin  | admin@bookstore.com   | admin123  |
| User   | Register your own via `/register` |

> ⚠️ Change or remove the default admin account (`DataInitializer.java`) before deploying anywhere public.

## Pushing to GitHub

```bash
cd online-bookstore
git init
git add .
git commit -m "Initial commit: Online Book Store (Spring Boot + Thymeleaf + PostgreSQL)"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo>.git
git push -u origin main
```

## Possible Extensions
- Pagination for the book catalog
- Product reviews & ratings
- Payment gateway integration (Razorpay/Stripe)
- Email order confirmations
- REST API layer for a separate frontend (React/Angular)
