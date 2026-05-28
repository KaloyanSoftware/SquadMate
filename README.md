# SquadMate

**SquadMate** is a full-stack web application for managing amateur football (soccer) teams. It provides a role-based platform where **coaches** can build rosters, schedule matches, and track squad availability, while **players** can view their team details, upcoming fixtures, and update their own availability.

Built as a portfolio project to demonstrate real-world Spring Boot development — including layered architecture, JPA entity relationships, Spring Security with method-level authorization, REST API design, and integration testing with a live PostgreSQL database.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture Overview](#architecture-overview)
- [Domain Model](#domain-model)
- [REST API Reference](#rest-api-reference)
- [Security & Authorization](#security--authorization)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Running Tests](#running-tests)
- [Design Decisions](#design-decisions)

---

## Features

### Coach Dashboard
- Register and log in as a coach
- Create a team and manage its roster
- Add and remove players
- Update player details (jersey number, position, starter status)
- Schedule matches against registered or unregistered opponent teams
- View all past and upcoming fixtures

### Player Dashboard
- Register and log in as a player
- View team details and squad roster
- View upcoming matches and their details
- Mark match availability (Yes / No / Maybe) with optional notes

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 24 |
| Framework | Spring Boot 3.5.3 |
| Build Tool | Gradle (Kotlin DSL) |
| Web / MVC | Spring Web, Thymeleaf |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL 17.2 (via Docker) |
| Security | Spring Security 6 |
| Frontend | Bootstrap 5.3.3, Bootstrap Icons |
| Testing | JUnit 5, Mockito, Spring Boot Test, Spring Security Test |
| Dev Tooling | Spring DevTools, Lombok |
| Containerization | Docker Compose |

---

## Architecture Overview

SquadMate follows a classic **layered architecture**:

```
┌─────────────────────────────────────────────────────┐
│                  Presentation Layer                  │
│   webApi/ (REST Controllers)  │  webControllers/    │
│        (JSON responses)       │  (Thymeleaf views)  │
├─────────────────────────────────────────────────────┤
│                   Service Layer                      │
│    Business logic, authorization checks, DTOs       │
├─────────────────────────────────────────────────────┤
│                  Repository Layer                    │
│         Spring Data JPA repositories                │
├─────────────────────────────────────────────────────┤
│                   Domain Layer                      │
│          JPA entities, enums, relationships         │
├─────────────────────────────────────────────────────┤
│                PostgreSQL Database                  │
└─────────────────────────────────────────────────────┘
```

**Key architectural choices:**
- REST controllers handle API consumers; Thymeleaf MVC controllers serve the rendered UI
- Method-level security (`@PreAuthorize`) enforces fine-grained authorization rules
- DTOs decouple the API contract from the domain model
- `JOINED` inheritance strategy for the `User` entity preserves type-safe polymorphism in a relational schema

---

## Domain Model

### Entity Hierarchy

```
User (abstract, JOINED inheritance)
├── Coach  (OneToOne → Team)
└── Player (ManyToOne → Team)

Team      (OneToMany → Player, OneToMany → TeamMatch)
Match     (OneToMany → TeamMatch, OneToMany → MatchAvailability)
TeamMatch (Match participation record with stats per team)
MatchAvailability (Player availability per match)
```

### Entities

#### `User` (base)
| Field | Type | Notes |
|---|---|---|
| id | Integer | Primary key |
| email | String | Login identifier |
| password | String | BCrypt-encoded |
| firstName, lastName | String | |
| birthDate | LocalDate | |
| profileImagePath | String | |

#### `Coach` extends `User`
| Field | Type | Notes |
|---|---|---|
| team | Team | OneToOne, nullable until team is created |

#### `Player` extends `User`
| Field | Type | Notes |
|---|---|---|
| jerseyNumber | int | |
| position | Position (enum) | See positions below |
| isStarter | boolean | |
| team | Team | ManyToOne, assigned by coach |
| matchAvailabilities | List\<MatchAvailability\> | |

#### `Team`
| Field | Type | Notes |
|---|---|---|
| id | Integer | |
| name | String | |
| coach | Coach | OneToOne inverse |
| players | List\<Player\> | OneToMany |
| matchParticipations | List\<TeamMatch\> | OneToMany |

#### `Match`
| Field | Type | Notes |
|---|---|---|
| id | Integer | |
| matchDate | LocalDateTime | |
| location | String | |
| playerAvailabilities | List\<MatchAvailability\> | |
| matchStats | List\<TeamMatch\> | |

#### `TeamMatch` (join entity for match statistics)
| Field | Type | Notes |
|---|---|---|
| team | Team | |
| match | Match | |
| teamName | String | Snapshot of team name at match time |
| goals, redCards, yellowCards | int | |
| corners, fouls, totalShots, shotsOnTarget | int | |
| ballPossession, totalPasses | int | |
| lineUpNotes | String | |

#### `MatchAvailability`
| Field | Type | Notes |
|---|---|---|
| status | MatchAvailabilityStatus | YES / NO / MAYBE |
| note | String | Optional player note |
| player | Player | |
| match | Match | |

### Enums

**`Position`** — 15 values covering all outfield roles:
`GOALKEEPER`, `CENTER_BACK`, `LEFT_BACK`, `RIGHT_BACK`, `LEFT_WING_BACK`, `RIGHT_WING_BACK`, `DEFENSIVE_MIDFIELDER`, `CENTRAL_MIDFIELDER`, `ATTACKING_MIDFIELDER`, `LEFT_MIDFIELDER`, `RIGHT_MIDFIELDER`, `LEFT_WINGER`, `RIGHT_WINGER`, `SECOND_STRIKER`, `STRIKER`

**`MatchAvailabilityStatus`**: `YES`, `NO`, `MAYBE`

---

## REST API Reference

All endpoints are prefixed with `/api`. Unauthorized requests return `401 Unauthorized`; forbidden requests return `403 Forbidden`.

### Teams

| Method | Endpoint | Auth Required | Description |
|---|---|---|---|
| `POST` | `/api/team` | Coach with no existing team | Create a new team |
| `PATCH` | `/api/team/players/{id}` | Coach | Add/update a player on the coach's team |
| `DELETE` | `/api/team/players/{playerId}` | Coach (own team players only) | Remove a player from the team |

**`POST /api/team` — Request Body:**
```json
{
  "teamName": "FC Riverside"
}
```
**Response:**
```json
{
  "id": 1,
  "name": "FC Riverside"
}
```

---

### Matches

| Method | Endpoint | Auth Required | Description |
|---|---|---|---|
| `POST` | `/api/match` | Coach | Schedule a new match |

**`POST /api/match` — Request Body:**
```json
{
  "matchDate": "2025-06-15T15:00:00",
  "location": "Home Stadium",
  "opponentTeam": "City FC"
}
```
**Response:**
```json
{
  "matchId": 42,
  "matchDate": "2025-06-15T15:00:00",
  "team1": "FC Riverside",
  "team2": "City FC"
}
```

> Matches support both registered (in-database) and unregistered opponent teams.

---

### Players

| Method | Endpoint | Auth Required | Description |
|---|---|---|---|
| `PATCH` | `/api/player/{id}` | Coach (own team players only) | Update player jersey, position, and starter status |

**`PATCH /api/player/{id}` — Request Body:**
```json
{
  "jerseyNumber": 10,
  "position": "CENTRAL_MIDFIELDER",
  "isStarter": true
}
```
**Response:**
```json
{
  "playerId": 5,
  "jerseyNumber": 10,
  "position": "CENTRAL_MIDFIELDER",
  "isStarter": true,
  "teamName": "FC Riverside"
}
```

---

## Security & Authorization

Security is enforced at two levels: **route-level** (SecurityConfig) and **method-level** (`@PreAuthorize` + `AuthorizationService`).

### Roles

| Role | Assigned To | Access |
|---|---|---|
| `ROLE_COACH` | Registered coaches | Full team, player, and match management |
| `ROLE_PLAYER` | Registered players | Read-only access to team and match info |

### Route Access

| Access Level | Routes |
|---|---|
| Public | `GET /`, `GET /register`, `GET /login`, `POST /register`, static assets |
| Authenticated | All other routes |

### Custom Authorization Rules (`AuthorizationService`)

| Rule | Logic |
|---|---|
| `isCoach()` | Principal must be an instance of `Coach` |
| `isCoachWithNoTeam()` | Coach authenticated, and coach has no team assigned yet |
| `canModifyPlayer(playerId)` | Coach authenticated, and the target player belongs to the coach's team |

### Authentication Flow

1. User submits credentials to `POST /login`
2. `CustomUserDetailsService` loads user by email, resolves role from entity type
3. On success: coach → `/coach/home`, player → `/player/home`
4. Passwords stored as BCrypt hashes

---

## Project Structure

```
src/
├── main/
│   ├── java/com/portfolio/squadmate/
│   │   ├── domain/                     # JPA entities and enums
│   │   │   ├── User.java
│   │   │   ├── Coach.java
│   │   │   ├── Player.java
│   │   │   ├── Team.java
│   │   │   ├── Match.java
│   │   │   ├── TeamMatch.java
│   │   │   ├── MatchAvailability.java
│   │   │   └── enums/
│   │   ├── presentation/
│   │   │   ├── webApi/                 # REST controllers + DTOs
│   │   │   │   ├── ApiTeamController.java
│   │   │   │   ├── ApiPlayerController.java
│   │   │   │   ├── ApiMatchController.java
│   │   │   │   └── dto/
│   │   │   └── webControllers/         # Thymeleaf MVC controllers
│   │   ├── repository/                 # Spring Data JPA repositories
│   │   ├── security/
│   │   │   ├── SecurityConfig.java
│   │   │   └── CustomUserDetailsService.java
│   │   ├── service/
│   │   │   ├── UserService.java
│   │   │   ├── TeamService.java
│   │   │   ├── PlayerService.java
│   │   │   ├── MatchService.java
│   │   │   ├── AuthorizationService.java
│   │   │   └── RoleManagementFactory.java
│   │   └── SquadMateApplication.java
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       ├── sql/data.sql                # Dev seed data
│       ├── static/                     # CSS, JS, images
│       └── templates/                  # Thymeleaf HTML templates
└── test/
    └── java/com/portfolio/squadmate/
        ├── webApi/
        │   ├── ApiTeamControllerTest.java
        │   └── ApiPlayerControllerUnitTest.java
        ├── service/
        │   └── PlayerServiceTest.java
        └── TestHelper.java
```

---

## Getting Started

### Prerequisites

- Java 24+
- Docker and Docker Compose
- Gradle (or use the included `./gradlew` wrapper)

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/SquadMate.git
cd SquadMate
```

### 2. Start the Database

```bash
docker-compose up -d
```

This starts two PostgreSQL 17.2 containers:
- **Main DB** on `localhost:5434` (used by the application)
- **Test DB** on `localhost:5435` (used by integration tests)

### 3. Run the Application

```bash
./gradlew bootRun
```

The application starts on `http://localhost:8080` using the `dev` profile, which:
- Auto-creates the database schema via Hibernate DDL
- Seeds the database with sample coaches, players, and a team from `sql/data.sql`

### 4. Access the App

Navigate to [http://localhost:8080](http://localhost:8080). You can register as a new Coach or Player, or use the dev seed data credentials.

---

## Running Tests

Ensure both Docker containers are running, then:

```bash
./gradlew test
```

### Test Coverage

| Test Class | Type | What It Tests |
|---|---|---|
| `ApiTeamControllerTest` | Integration | `POST /api/team` — creates a team as an authenticated coach, verifies response and DB state |
| `ApiPlayerControllerUnitTest` | Unit | Player controller with mocked service layer |
| `PlayerServiceTest` | Unit / Integration | `patchPlayer()` — selective field updates; `addPlayerToTeam()` — roster assignment |

**Test Infrastructure:**
- `TestHelper` Spring component provides factory methods (`createCoachUser()`, `createPlayer()`, `createTeam()`, etc.) and a `cleanup()` method to reset DB state between tests
- Tests use `@SpringBootTest` with `MockMvc` and `@WithUserDetails` for security context injection
- CSRF tokens are included in mutating requests to mirror production behavior

---

## Design Decisions

### JOINED Inheritance for `User`
The `Coach` and `Player` entities extend `User` using JPA's `JOINED` inheritance strategy. This means each type has its own table joined by primary key, preserving relational integrity and type-specific queries without the null-column overhead of `SINGLE_TABLE`.

### Method-Level Security over URL Pattern Rules
Rather than listing every protected route in `SecurityConfig`, the application uses `@PreAuthorize` annotations backed by `AuthorizationService`. This keeps authorization logic co-located with the business logic it protects, and allows context-sensitive checks (e.g., "is this player actually on my team?") that URL patterns cannot express.

### Factory Pattern for Role Creation
`RoleManagementFactory` centralizes the instantiation of `Coach` vs. `Player` during registration. This isolates role-specific construction from `UserService` and makes it easy to add new user types without modifying registration logic.

### DTO Layer for API Contracts
All REST endpoints use dedicated request and response DTOs, keeping the domain model decoupled from the API contract. This prevents accidental exposure of sensitive fields and makes API evolution easier.

### Matches Support Unregistered Opponents
When scheduling a match, the opponent team can be any string — it does not have to be a registered team in the database. This models the reality of amateur football leagues where not every club uses the same platform.

### Lazy Loading Throughout
All entity relationships use `FetchType.LAZY` to avoid N+1 query problems. Data is fetched only when explicitly accessed, giving the service layer full control over query behavior.

### Bidirectional Relationship Consistency
Setter methods (e.g., `player.setTeam(team)`) maintain both sides of bidirectional relationships in memory, preventing stale state within the same transaction.
