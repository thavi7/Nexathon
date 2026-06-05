# 🏆 Hackathon Management System

A full-featured **RESTful backend** for managing hackathon events — built with Spring Boot, Spring Data JPA, and PostgreSQL. Supports user registration, team formation, event management, project submissions, and leaderboards.

> 🔐 Spring Security integration is planned for the next phase.

---

## 📌 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture Overview](#architecture-overview)
- [Entity Relationships](#entity-relationships)
- [API Endpoints](#api-endpoints)
- [Key Concepts Used](#key-concepts-used)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Roadmap](#roadmap)

---

## ✨ Features

- Create and manage hackathon **Events** (online/offline, date range, location)
- Register **Users** with role-based metadata (Participant, Organizer, etc.)
- Form **Teams** and add/remove members dynamically
- **Register** teams for events with status tracking
- Submit **Projects** per team per event
- View **Leaderboards** ranked by submission scores
- Partial user updates via `PATCH`
- Cascading deletes with orphan removal for clean data integrity

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 4.x |
| ORM | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Mapping | ModelMapper |
| Validation | Jakarta Bean Validation |
| Schema Tool | JPA Buddy |
| Build Tool | Maven |
| Language | Java 17+ |

---

## 🧱 Architecture Overview

```
Client
  │
  ▼
Controller Layer   ← REST endpoints, request/response DTOs
  │
  ▼
Service Layer      ← Business logic, validation, mapping
  │
  ▼
Repository Layer   ← Spring Data JPA + custom JPQL queries
  │
  ▼
PostgreSQL DB
```

DTOs are used to decouple the API contract from internal entities. ModelMapper handles entity ↔ DTO conversions.

---

## 🗂️ Entity Relationships

```
User ──────────────────── ManyToMany ──────────────────── Team
                                                            │
                                                     OneToMany
                                                            │
Event ──── OneToMany ──── Registration ◄──── ManyToOne ───┘
  │
  └── OneToMany ──── Submission ◄──── ManyToOne ──── Team
```

**Summary:**

- A `User` can be a member of multiple `Team`s
- A `Team` can register for multiple `Event`s via `Registration`
- A `Team` can submit one `Submission` per `Event`
- An `Event` has many `Registration`s and `Submission`s
- `Event` → `Registration` uses `CascadeType.ALL` + `orphanRemoval = true`

---

## 🌐 API Endpoints

### 👤 Users — `/user`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/user` | Get all users |
| POST | `/user` | Create a new user |
| PUT | `/user/{id}` | Full update of a user |
| PATCH | `/user/{id}` | Partial update of a user |
| DELETE | `/user/{id}` | Delete a user |
| GET | `/user/{id}/team` | Get all teams of a user |

---

### 👥 Teams — `/team`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/team` | Get all teams |
| GET | `/team/{id}` | Get team by ID |
| POST | `/team` | Create a new team |
| DELETE | `/team/{id}` | Delete a team |
| GET | `/team/{id}/user` | Get all members of a team |
| POST | `/team/{teamId}/user/{userId}` | Add a user to a team |
| DELETE | `/team/{teamId}/user/{userId}` | Remove a user from a team |
| GET | `/team/{id}/reg` | Get all registrations of a team |
| GET | `/team/{id}/sub` | Get all submissions of a team |

---

### 🎯 Events — `/event`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/event` | Get all events |
| GET | `/event/{id}` | Get event by ID |
| POST | `/event` | Create a new event |
| PUT | `/event/{id}` | Update an event |
| DELETE | `/event/{id}` | Delete an event |
| GET | `/event/{id}/team` | Get all teams registered for an event |
| GET | `/event/{id}/sub` | Get all submissions for an event |
| GET | `/event/{id}/leaderboard` | Get ranked leaderboard for an event |

---

### 📋 Registrations — `/reg`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/reg` | Get all registrations |
| GET | `/reg/{id}` | Get registration by ID |
| POST | `/reg` | Register a team for an event |
| DELETE | `/reg/{id}` | Delete a registration |

---

### 📦 Submissions — `/sub`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/sub` | Get all submissions |
| POST | `/sub` | Create a submission (team + event) |
| DELETE | `/sub/team/{teamId}/event/{eventId}` | Delete a team's submission for an event |

---

## 🔑 Key Concepts Used

**JPA & Hibernate**
- `@OneToMany`, `@ManyToOne`, `@ManyToMany` — full relational mapping
- `CascadeType.ALL` on `Event → Registration` so deleting an event removes all its registrations
- `orphanRemoval = true` on collections to auto-delete detached children
- `FetchType.LAZY` to avoid loading unnecessary data

**N+1 Query Prevention**
- Custom JPQL queries with `JOIN FETCH` to load associations in a single query instead of N separate ones

**ModelMapper**
- Separates internal entity structure from API-facing DTOs
- Configured for nested mapping (e.g., `Team → TeamDTO` includes member list)

**JPA Buddy**
- Used for schema generation, entity inspection, and JPQL query assistance during development

**Validation**
- `@Valid` on request bodies with Jakarta constraints (`@Size`, `@NonNull`, etc.)
- Custom unique constraint: `(name, password)` on `User`

**Auditing**
- `@CreationTimestamp` on `User.createdAt` — auto-set on insert, never updated

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- PostgreSQL 14+
- Maven 3.8+

### Configuration

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hackathon_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### Run

```bash
# Clone the repository
git clone https://github.com/your-username/hackathon-management-system.git
cd hackathon-management-system

# Build and run
mvn spring-boot:run
```

The server starts on `http://localhost:8080`.

---

## 📁 Project Structure

```
src/
└── main/
    └── java/com/example/practice/
        ├── controller/
        │   ├── EventController.java
        │   ├── UserController.java
        │   ├── TeamController.java
        │   ├── RegistrationController.java
        │   └── SubmissionController.java
        ├── service/
        │   ├── EventService.java
        │   ├── UserService.java
        │   ├── TeamService.java
        │   ├── RegService.java
        │   └── SubmissionService.java
        ├── entity/
        │   ├── Event.java
        │   ├── User.java
        │   ├── Team.java
        │   ├── Registration.java
        │   ├── Submission.java
        │   └── type/
        │       ├── EventMode.java
        │       ├── RoleType.java
        │       └── StatusType.java
        ├── dto/
        │   ├── AddEventDTO.java
        │   ├── EventDTO.java
        │   ├── AddUserDTO.java
        │   ├── UserDTO.java
        │   ├── AddTeamDTO.java
        │   ├── TeamDTO.java
        │   ├── TeamEventDTO.java
        │   ├── AddRegistrationDTO.java
        │   ├── RegistrationDTO.java
        │   ├── AddSubmissionDTO.java
        │   ├── SubmissionDTO.java
        │   └── LeaderboardDTO.java
        └── repository/
            ├── EventRepository.java
            ├── UserRepository.java
            ├── TeamRepository.java
            ├── RegistrationRepository.java
            └── SubmissionRepository.java
```

---

## 🎬 Making Process

This project was built live and documented as a full walkthrough playlist on YouTube!

> 📺 **Watch the full building process here:**
> ### 👉 [Hackathon Management System — Full Build Playlist](https://youtube.com/playlist?list=PLiZNjjYgF6SdXB4UAx753-cnfQmvrNeC9&si=61j8FqBBidBiVjwE)

The playlist covers everything from project setup to JPA relationships, JPQL queries, DTO mapping, and more — great if you want to follow along or understand the design decisions behind the code. 🚀

---

## 🗺️ Roadmap

- [x] User CRUD with partial update (PATCH)
- [x] Team management with member add/remove
- [x] Event creation and management
- [x] Team registration for events
- [x] Submission handling per team per event
- [x] Leaderboard by event
- [x] N+1 prevention with JPQL JOIN FETCH
- [x] DTO layer with ModelMapper
- [ ] **Spring Security** — JWT-based authentication & authorization
- [ ] Role-based access control (Organizer vs Participant)
- [ ] Pagination & sorting on list endpoints
- [ ] File upload for submissions (project zip / GitHub link)
- [ ] Email notifications on registration & submission
- [ ] Swagger / OpenAPI documentation
- [ ] Docker + Docker Compose setup
- [ ] Unit & integration tests

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).
