# Backlog - ClusteredData Warehouse for FX Deals

## Epic 1: Data Ingestion & Validation
**Goal:** Accept FX deals data and validate incoming rows before saving to DB.

### User Story 1.1: Accept FX deal data
- **As a** system
- **I want** to accept FX deals data via requests
- **So that** the deals can be persisted in the database

**Tasks:**
- [ ] Define the request format:
    - Deal Unique Id
    - From Currency ISO Code
    - To Currency ISO Code
    - Deal timestamp
    - Deal Amount in ordering currency
- [ ] Implement endpoint or input method to receive deals
- [ ] Parse incoming request rows

### User Story 1.2: Validate row structure
- **As a** system
- **I want** to validate each incoming row
- **So that** only correctly structured data is saved

**Tasks:**
- [ ] Validate required fields exist
- [ ] Validate field types (e.g., timestamp format, numeric amount)
- [ ] Handle validation errors gracefully
- [ ] Log validation errors for monitoring

### User Story 1.3: Prevent duplicate imports
- **As a** system
- **I want** to avoid importing the same deal twice
- **So that** the database only contains unique deals

**Tasks:**
- [ ] Check for Deal Unique Id in DB before insert
- [ ] Skip duplicate rows and log the event
- [ ] Ensure no rollback, persist successfully imported rows

---

## Epic 2: Database & Persistence
**Goal:** Persist FX deals in a reliable and structured way.

### User Story 2.1: Select and configure database
- **As a** developer
- **I want** to use a database (Postgres, MySQL, or MongoDB)
- **So that** FX deals can be stored and queried efficiently

**Tasks:**
- [ ] Choose database technology
- [ ] Create database schema or collection structure
- [ ] Implement repository layer for CRUD operations
- [ ] Write unit tests for database layer

---

## Epic 3: Deployment & Docker
**Goal:** Ensure the application can be deployed and run easily.

### User Story 3.1: Containerize application
- **As a** devops engineer
- **I want** a Docker Compose setup
- **So that** the application can be deployed easily

**Tasks:**
- [ ] Write Dockerfile for the application
- [ ] Create Docker Compose for app + DB


## Epic 4: Error Handling & Logging
**Goal:** Make the application robust and maintainable.

### User Story 4.1: Proper error handling
- **As a** system
- **I want** to handle exceptions properly
- **So that** the application doesn’t crash unexpectedly

**Tasks:**
- [ ] Create Costume exceptions
- [ ] Create advice controller
- [ ] Return meaningful error responses
- [ ] Log all exceptions

### User Story 4.2: Logging
- **As a** developer
- **I want** proper logging
- **So that** we can track application behavior and issues

**Tasks:**
- [ ] Configure logging library (SLF4J)
- [ ] Log validation, duplicate checks, DB inserts, and errors

---

## Epic 5: Testing & Quality
**Goal:** Ensure code quality and reliability through testing.

### User Story 5.1: Unit testing
- **As a** developer
- **I want** proper unit tests with coverage
- **So that** I can ensure correctness of functionality

**Tasks:**
- [ ] Write unit tests for:
    - Row validation
    - Duplicate check
    - DB insert operations
- [ ] Configure code coverage reporting using JACOCO
- [ ] Ensure high coverage of critical logic

---

## Epic 6: Documentation & Delivery
**Goal:** Provide clear documentation and submit project properly.

### User Story 6.1: Project documentation
- **As a** reviewer
- **I want** clear documentation
- **So that** the project can be easily understood and deployed

**Tasks:**
- [ ] Create README.md with:
    - Project description
    - Setup instructions
    - How to run Docker Compose
    - Sample input data usage
- [ ] Document API endpoints, validations, and error handling

### User Story 6.2: Deliver project via GitHub
- **As a** team lead
- **I want** the project delivered via GitHub
- **So that** it can be reviewed and tested

**Tasks:**
- [ ] Initialize Git repository
- [ ] Push source code to GitHub
- [ ] Ensure repository includes all deliverables:
    - Source code
    - Docker files
    - Makefile
    - Sample input
    - README.md
