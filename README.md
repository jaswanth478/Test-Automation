# REST API Automation Framework

Lightweight API automation framework built with Java and TestNG for testing REST API endpoints.

## Features

- CRUD operations testing (Create, Read, Update, Delete)
- Parameterized tests for multiple payloads
- TestNG integration with parallel execution and test groups
- Utility classes for reusable API operations
- Custom test listeners for enhanced reporting
- Integration tests for end-to-end workflows
- Automated CI/CD with GitHub Actions
- Clean and maintainable test structure

## Prerequisites

- Java 11 or higher
- Maven 3.6+

## Setup

```bash
mvn clean install
```

## Running Tests

Run all tests:
```bash
mvn test
```

Run smoke tests only:
```bash
mvn test -Dgroups=smoke
```

Run specific test class:
```bash
mvn test -Dtest=CreateTests
```

## Test Structure

### Base Classes
- `BaseTest` - Base class with API configuration
- `Config` - Configuration constants

### Test Classes
- `CreateTests` - Tests for POST operations
- `ReadTests` - Tests for GET operations
- `UpdateTests` - Tests for PUT/PATCH operations
- `DeleteTests` - Tests for DELETE operations
- `ParameterizedTests` - Data-driven tests with multiple payloads
- `IntegrationTests` - End-to-end workflow tests

### Utilities
- `ApiUtils` - Reusable API operation methods
- `AssertionUtils` - Custom assertion utilities

### Listeners
- `TestListener` - Test execution listeners for reporting

## Test Groups

- `smoke` - Critical smoke tests
- `create`, `read`, `update`, `delete` - Operation-specific tests
- `parameterized` - Data-driven tests
- `integration` - End-to-end workflow tests

## CI/CD

Tests run automatically on every commit via GitHub Actions. Check the Actions tab for test results.

## API Under Test

This framework tests against JSONPlaceholder API (https://jsonplaceholder.typicode.com) - a fake REST API for testing.

