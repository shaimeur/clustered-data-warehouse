# ClusteredData Warehouse for FX Deals

A backend service built with **Java 17** and **Spring Boot 3.5.7** designed to **ingest, validate, and persist FX deals** data.  
This project ensures data integrity, prevents duplicates, and provides a robust containerized deployment environment.

---

## Features

- Accept FX deals via REST API
- Validate incoming data structure and types
- Prevent duplicate deal imports
- Persist validated deals into a relational database
- Modular architecture using Spring Boot layers (Controller, Service, Repository, Entity)
- Robust error handling with custom exceptions and Controller Advice
- Unit testing using JUnit 5 + Mockito + Jacoco
- Docker & Docker Compose setup for seamless deployment
- Configurable environment variables

---

## Architecture Overview
```bash
src
├── main
│ ├── java/com/example/deals
│ │ ├── controllers # REST endpoints
│ │ ├── configs # Configuration
│ │ ├── services # Business logic
│ │ ├── repositories # Database persistence layer
│ │ ├── entities # Deal entity definition
│ │ └── exceptions # Custom exceptions and handlers
│ └── resources
│ ├── application.yml # Environment variables configuration
│ └── logback.xml # Logging configuration
└── test
└── java/... # Unit tests
```


---

## Prerequisites

Make sure you have the following installed on your system:

- **Java 17**
- **Maven 3.9+**
- **Docker & Docker Compose**
- **Make** (for simplified commands)

---

## Environment Variables

Set the following environment variables before running the application:

| Variable Name | Description | Example |
|----------------|-------------|----------|
| `DB_URL` | JDBC connection URL | `jdbc:postgresql://db:5432/fx_deals` |
| `DB_USERNAME` | Database username | `postgres` |
| `DB_PASSWORD` | Database password | `postgres` |
| `SERVER_PORT` | Application port | `8080` |

You can define them in a `bash` or `zsh` file or export them manually:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/fx_deals
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=postgres
```

## Setup & Run
1. Build and run locally
```bash
make build
make run
```
2. Run using Docker Compose
```bash
make docker-up
# or manually:
docker-compose up --build
```
## This will start:
- The Spring Boot application
- The PostgreSQL database

## Error Handling
- All validation and system errors are handled by a Global Controller Advice.
- Logs are written via SLF4J and visible in console and file (if configured).

🧪 Testing

To run all unit tests:
```bash
make test
# or:
mvn test
```
### Generate coverage report with JaCoCo:

```bash
mvn clean verify
```

Reports will be available in: `target/site/jacoco/index.html` 

## 🧾 API Overview

All available endpoints are documented and accessible via **Swagger UI**.

Once the application is running, open the following URL in your browser:

 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**  
or (depending on your setup):  
 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

---