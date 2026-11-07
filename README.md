
# Expense Tracker REST API

A backend application built using Java and Spring Boot to manage personal expenses through RESTful APIs. The application supports expense creation, retrieval, updating, and deletion, with PostgreSQL for persistent data storage.

## Overview

The Expense Tracker REST API provides a structured backend solution for managing personal financial records.

It follows a layered architecture that separates API handling, business logic, and database operations, making the application easier to maintain and extend.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Backend application framework |
| Spring Data JPA | Database interaction |
| Hibernate | ORM implementation |
| PostgreSQL | Relational database |
| Maven | Dependency management and build |
| Jakarta Bean Validation | Request validation |
| IntelliJ IDEA | Development environment |
| Postman | API testing |

## Features

- Create new expense records.
- Retrieve all expenses.
- Retrieve an individual expense by ID.
- Update existing expense records.
- Delete expense records.
- Validate incoming request data.
- Handle exceptions for missing expense records.
- Return appropriate HTTP status codes.
- Persist expense data using PostgreSQL.

## Project Architecture

The application follows a layered architecture.

```text
Expense-Tracker/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── expensetracker/
│   │   │           ├── controller/
│   │   │           │   └── ExpenseController.java
│   │   │           │
│   │   │           ├── service/
│   │   │           │   └── ExpenseService.java
│   │   │           │
│   │   │           ├── repository/
│   │   │           │   └── ExpenseRepository.java
│   │   │           │
│   │   │           ├── entity/
│   │   │           │   └── Expense.java
│   │   │           │
│   │   │           └── ExpenseTrackerApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
└── README.md
```

### Architecture Components

**1. Controller Layer**
- Handles incoming HTTP requests.
- Maps REST endpoints to application operations.
- Returns appropriate HTTP responses using ResponseEntity.

**2. Service Layer**
- Contains business logic.
- Coordinates application operations.
- Handles expense-related processing.

**3. Repository Layer**
- Uses Spring Data JPA.
- Extends JpaRepository for database operations.
- Communicates with PostgreSQL.

**4. Entity Layer**
- Defines the Expense database entity.
- Maps Java objects to database records using JPA annotations.

## Database Design

### Expense Entity

| Field | Data Type | Description |
|---|---|---|
| id | Long | Unique expense identifier |
| title | String | Expense title |
| amount | Double | Expense amount |
| category | String | Expense category |
| expenseDate | LocalDate | Date of expense |

The Expense entity is mapped to a PostgreSQL database table using Hibernate and JPA.

## REST API Endpoints

Base URL:

```text
http://localhost:8080/api/expenses
```

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/api/expenses` | Create an expense |
| GET | `/api/expenses` | Retrieve all expenses |
| GET | `/api/expenses/{id}` | Retrieve expense by ID |
| PUT | `/api/expenses/{id}` | Update an expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |

### 1. Create Expense

**POST** `/api/expenses`

Request Body:

```json
{
  "title": "Groceries",
  "amount": 1500.0,
  "category": "Food",
  "expenseDate": "2026-11-07"
}
```

Expected response: `201 Created`

### 2. Retrieve All Expenses

**GET** `/api/expenses`

Retrieves all stored expense records.

Expected response: `200 OK`

### 3. Retrieve Expense by ID

**GET** `/api/expenses/{id}`

Retrieves a specific expense using its unique identifier.

Expected response: `200 OK`

If the expense does not exist, the application handles the missing record through exception handling.

### 4. Update Expense

**PUT** `/api/expenses/{id}`

Updates an existing expense record.

Expected response: `200 OK`

### 5. Delete Expense

**DELETE** `/api/expenses/{id}`

Deletes an expense record using its ID.

Expected response: `204 No Content`

## Input Validation

The application uses Jakarta Bean Validation to validate incoming expense data.

Validation rules include:

- Expense title must not be blank.
- Expense category must not be blank.
- Expense amount must be greater than zero.

Invalid requests return an appropriate `400 Bad Request` response with validation error details.

## Exception Handling

The application handles scenarios where requested expense records cannot be found.

Example:

```text
Expense not found with id: 199
```

Appropriate HTTP response statuses are used to communicate the result of API operations.

## Getting Started

### Prerequisites

Make sure you have installed:

- Java JDK
- Maven
- PostgreSQL
- IntelliJ IDEA or another Java IDE
- Postman (optional, for API testing)

### 1. Clone the Repository

```bash
git clone https://github.com/Manoj051/Expense-Tracker.git
```

```bash
cd Expense-Tracker
```

### 2. Configure PostgreSQL

Create a database:

```sql
CREATE DATABASE expense_tracker;
```

### 3. Configure Application Properties

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker

spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Replace the database username and password with your PostgreSQL credentials.

Do not commit real database credentials to GitHub.

### 4. Build the Application

```bash
mvn clean install
```

### 5. Run the Application

```bash
mvn spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## Learning Outcomes

Through this project, I gained practical experience in:

- Developing RESTful APIs using Spring Boot.
- Understanding layered backend architecture.
- Implementing CRUD operations.
- Working with Spring Data JPA and Hibernate.
- Integrating PostgreSQL with a Spring Boot application.
- Implementing request validation.
- Handling exceptions and HTTP response codes.
- Testing backend endpoints using API testing tools.

## Future Enhancements

Planned improvements:

- User registration and authentication using Spring Security and JWT.
- User-specific expense management.
- Category-wise expense summaries.
- Monthly and yearly expense reports.
- Pagination and sorting.
- Unit and integration testing using JUnit and Mockito.
- Docker containerization.
- API documentation using Swagger / OpenAPI.

## Author

**Manoj MB**

Computer Science Engineering Graduate

- GitHub: [Manoj051](https://github.com/Manoj051)
- LinkedIn: [Manoj MB](https://www.linkedin.com/in/manoj-m-b-422691212/)

---

If you find this project useful, feel free to explore the repository and share your feedback!

