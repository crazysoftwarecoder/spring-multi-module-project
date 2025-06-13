# Spring Multi-Module Project

[![Build Status](https://img.shields.io/github/actions/workflow/status/ashwanthfernando/spring-multi-module-project/maven.yml?branch=main&label=build)](https://github.com/ashwanthfernando/spring-multi-module-project/actions/workflows/maven.yml)

A Spring Boot multi-module project demonstrating a clean architecture with separate modules for:
- `common`: Shared entities and DTOs
- `repository`: Database access layer
- `application`: REST API and business logic

## Project Structure

```
spring-multi-module-project/
├── common/                 # Shared entities and DTOs
├── repository/            # Database access layer
│   └── src/main/resources/com/example/repository/db/migration/  # Database migrations
└── application/          # REST API and business logic
```

## Technology Stack

- Java 17
- Spring Boot 3.2.3
- PostgreSQL 16
- Flyway for database migrations
- Maven for build management
- TestContainers for integration testing

## Getting Started

1. Start the PostgreSQL database:
```bash
docker-compose up -d
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run -pl application
```

## API Endpoints

### Create User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "handle": "johndoe",
    "message": "Hello, world!"
  }'
```

### Get User by Handle
```bash
curl http://localhost:8080/api/users/johndoe
```

## Development

### Running Tests
```bash
# Run all tests
mvn verify

# Run tests for a specific module
mvn verify -pl repository
```

### Database Migrations
Database migrations are managed by Flyway and located in `repository/src/main/resources/com/example/repository/db/migration/`.

## License

This project is licensed under the MIT License - see the LICENSE file for details. 