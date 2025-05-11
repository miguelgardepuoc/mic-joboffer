# Antharos Job Offer Domain Service

## Overview

The Job Offer domain service is a core microservice within the Antharos HR platform ecosystem. It manages the company's candidates, departments and job offers. This service is built using Domain-Driven Design (DDD) principles and follows CQRS and hexagonal architecture pattern.

## Domain Model

The service is centered around the following core domain concepts:

- **JobOffer**: Job offers
- **Candidate**: External users that apply for a job offer

## Technology Stack

- **Framework**: Spring Boot 3.4
- **Build Tool**: Maven
- **Language**: Java 21
- **Architecture**: Hexagonal Architecture with CQRS and DDD
- **Database**: PostgreSQL
- **Event Bus**: Azure Service Bus

## Domain Events

The service publishes the following domain events:
- `CandidateApplied`

And consumes the following domain events:
- `CompleteNameExtractedFromCv`

## Getting Started

### Prerequisites

- JDK 21+
- Maven 3.9+
- PostgreSQL 14+
- Docker & Docker Compose

### Installation

```bash
# Clone the repository
git clone https://github.com/miguelgardepuoc/mic-joboffer.git
cd mic-joboffer

# Build the project
./mvnw clean install -U
```

### Running Locally

```bash
# Start all dependencies with Docker Compose
docker-compose up --build -d --remove-orphans
```

```bash
# Run the service
mvn spring-boot:run
```

The service will be available at `http://localhost:8081/job-offer`.
APIs documentation will be available at `http://localhost:8081/job-offer/swagger-ui/index.html`.