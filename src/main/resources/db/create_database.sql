-- Run this once in psql (or pgAdmin) before starting the Spring Boot app.
-- Hibernate will auto-create the tables (ddl-auto=update) inside this database.

CREATE DATABASE bookstore_db;

-- Optional: create a dedicated user instead of using the default postgres user
-- CREATE USER bookstore_user WITH PASSWORD 'bookstore_pass';
-- GRANT ALL PRIVILEGES ON DATABASE bookstore_db TO bookstore_user;
