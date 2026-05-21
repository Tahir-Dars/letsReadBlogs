# LetsReadBlogs

## 1. Introduction

LetsReadBlogs is a blog platform for creating, organizing, and managing posts with authentication, categories, tags, and draft support. It solves the common need for a simple content publishing system where readers can browse public posts while authenticated users can create and manage their own content.

## 2. Project Structure

The repository is organized as a standard Spring Boot application:

- `src/main/java/com/letsreadhere/blog/`
  - `config/` - application and security configuration
  - `controller/` - REST endpoints for authentication, posts, categories, and tags
  - `domain/` - request/response DTOs and persistence models
  - `mapper/` - MapStruct mappers for converting between entities and DTOs
  - `repository/` - Spring Data JPA repositories
  - `security/` - JWT and user-details security components
  - `service/` - business logic and application services
- `src/main/resources/` - runtime configuration such as database, JPA, and JWT settings
- `src/test/` - automated tests
- `docker-compose.yml` - local containerized database and admin tooling
- `Dockerfile` - application container image definition

## 3. Tech Stack Used

### Frontend

The frontend is a **React + TypeScript + Vite** single-page application based on the existing implementation by **Devitro**. It serves as the user-facing layer of the platform, consuming the backend APIs for browsing posts, signing in, and managing blog content.

- **Live Demo:** https://letsreadblogshere.vercel.app/
- **Credit:** The original implementation was built from Devtiro's tutorial content and has been extended and customized further for this project.

Frontend role in the system:

- Provides the blog UI for readers and authenticated users
- Connects to the backend REST API for posts, categories, tags, and authentication
- Handles client-side routing, form input, and rich text editing

### Backend

- **Java 25**
- **Spring Boot 4.0.5**
- **Spring Web MVC** for REST APIs
- **Spring Security** with JWT-based authentication
- **Spring Data JPA** for persistence
- **Hibernate / JPA** for ORM and schema management
- **MapStruct 1.6.3** for DTO/entity mapping
- **Lombok 1.18.42** for boilerplate reduction
- **JJWT 0.13.0** for token generation and validation
- **PostgreSQL** as the primary runtime database

The backend is integrated with the frontend through REST endpoints under `/api/v1/*`.

### Database

The database is containerized with Docker for easy local setup and repeatable environments:

- **Docker Image:** `postgres:latest`
- **Docker Compose:** starts PostgreSQL on port `5432` and includes **Adminer** for database inspection and administration on port `8888`

### Admin Panel

Adminer is included as a lightweight database admin panel. It makes it easier to inspect tables, verify data, and manage the PostgreSQL database during development.

## 4. Dependencies Used

### Backend dependencies

- `spring-boot-starter-webmvc` - REST API and web layer
- `spring-boot-starter-security` - authentication and access control
- `spring-boot-starter-data-jpa` - repository and persistence support
- `spring-boot-starter-validation` - request validation
- `postgresql` - PostgreSQL JDBC driver
- `h2` - lightweight test database support
- `spring-boot-h2console` - H2 console for development and testing
- `mapstruct` - compile-time object mapping
- `lombok` - reduces boilerplate code
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` - JWT token support
- Test dependencies for JPA, validation, web MVC, and security testing

### Frontend dependencies

- `react` / `react-dom` - core UI framework
- `typescript` - type-safe frontend development
- `vite` - development server and production build tool
- `react-router` - client-side routing
- `@nextui-org/react` - UI component library
- `tailwindcss` and `postcss` - utility-first styling and CSS processing
- `axios` - API client for backend requests
- `@tiptap/*` - rich text editor support for blog content
- `dompurify` - sanitization for user-generated HTML content
- `lucide-react` - icon set
- `eslint` - linting and code quality checks

## 5. Features

- User authentication with JWT login
- Public browsing of blog posts, categories, and tags
- Create new blog posts
- Edit existing blog posts
- Delete blog posts
- Draft post support for authenticated users
- Filter posts by category or tag
- Create and delete categories
- Create and delete tags
- Validation for incoming request payloads
- Database-backed persistence with PostgreSQL
- Containerized local development environment with Docker Compose
- Adminer access for database management

