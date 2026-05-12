# Online Event & Hackathon Management System

A full-stack backend project built with Spring Boot and PostgreSQL for managing online/offline hackathons, coding events, and competitions.

---

# 🚀 Features

## 👤 User Management
- User Registration & Login
- JWT Authentication
- Role-Based Authorization
- Organizer & Participant Roles

## 🎯 Event Management
- Create/Edit/Delete Events
- Online & Offline Event Modes
- Event Registration
- Event Capacity Handling
- Event Status Management

## 👥 Team Management
- Create Teams
- Join Teams
- Team Leader System
- Team Size Validation

## 📂 Submission System
- Upload Project Submissions
- GitHub/Drive Link Submission
- Submission Deadlines
- Multiple File Handling

## 🏆 Leaderboard & Results
- Judge Scores
- Automatic Ranking
- Leaderboard Generation
- Winner Announcement

## 📜 Certificate Generation
- Participation Certificates
- Winner Certificates
- PDF Generation

## 📧 Email Notifications
- Registration Confirmation
- Event Reminders
- Submission Deadline Alerts
- Result Notifications

---

# 🛠️ Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

## Database
- PostgreSQL

## Authentication
- JWT Token Authentication

## Build Tool
- Maven

## Other Tools
- Lombok
- Docker
- Java Mail Sender

---

# 📁 Project Structure

```bash
src/main/java/com/example/hackathonmanagementsystem
│
├── config
├── controller
├── dto
├── entity
├── enums
├── exception
├── repository
├── security
├── service
│   └── impl
├── util
└── validator
```

---

# 🗄️ Main Entities

## User
- id
- name
- email
- password
- role

## Event
- id
- title
- description
- mode (ONLINE/OFFLINE)
- location
- startDate
- endDate

## Team
- id
- teamName
- maxMembers
- leader

## Submission
- id
- githubLink
- projectFile
- submittedAt

## Leaderboard
- id
- rank
- score

## Certificate
- id
- certificateUrl
- issuedAt

---

# 🔐 Authentication & Authorization

This project uses:
- JWT Authentication
- Spring Security
- Role-Based Access Control

## Roles
- ADMIN
- ORGANIZER
- PARTICIPANT
- JUDGE

---

# 🔄 API Modules

## Auth APIs
- Register User
- Login User
- Refresh Token

## Event APIs
- Create Event
- Update Event
- Delete Event
- Get All Events
- Get Event By Id

## Team APIs
- Create Team
- Join Team
- Remove Member
- Get Team Details

## Submission APIs
- Upload Submission
- Update Submission
- Get Submission

## Leaderboard APIs
- Generate Leaderboard
- Get Rankings

## Certificate APIs
- Generate Certificate
- Download Certificate

---

# ⚙️ Installation & Setup

## Clone Repository

```bash
git clone https://github.com/your-username/hackathon-management-system.git
```

## Navigate to Project

```bash
cd hackathon-management-system
```

## Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hackathon_db
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

# ▶️ Run The Project

## Using Maven

```bash
mvn spring-boot:run
```

## Using Docker

```bash
docker-compose up
```

---

# 📌 Future Enhancements

- Real-Time Chat
- Live Coding Rooms
- AI-Based Team Matching
- Payment Gateway Integration
- WebSocket Notifications
- Event Analytics Dashboard

---

# 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

---

# 📄 License

This project is licensed under the MIT License.

---

# 👨‍💻 Author

Developed using Spring Boot & PostgreSQL.
