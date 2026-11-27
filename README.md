# SentinelTrace

### Software Supply-Chain Risk Intelligence Platform

SentinelTrace is a cybersecurity product that helps developers understand software dependency risk. It ingests dependency files, identifies known vulnerabilities, maps dependency relationships, and presents explainable risk assessments.

The goal of v1 is to deliver an end-to-end tool that developers can use to scan a project’s dependencies and understand which components need attention and why.

## The Problem

Applications rely on open-source packages and third-party components. Vulnerabilities in these dependencies can affect an application through both direct and transitive dependencies.

A vulnerability severity score alone does not show how a vulnerable component relates to the application or how it is reached through the dependency tree.

SentinelTrace combines vulnerability intelligence, dependency-graph analysis, and explainable risk scoring to help developers prioritize dependency risks.

## v1 Capabilities

- Upload CycloneDX JSON SBOM files.
- Upload npm `package-lock.json` files.
- Parse package metadata and dependency relationships.
- Identify known vulnerabilities using the OSV API.
- Represent direct and transitive dependencies as a graph.
- Trace dependency paths to vulnerable components.
- Generate explainable risk assessments.
- Review scan results through APIs and a React dashboard.

## Planned Workflow

1. Upload a supported dependency file.
2. Parse its packages, versions, and dependency relationships.
3. Check package versions against OSV vulnerability data.
4. Build a dependency graph and identify paths to vulnerable components.
5. Calculate risk scores and record their contributing factors.
6. Present vulnerabilities, dependency paths, and explanations through the scan APIs and dashboard.

## Project Status

**Current stage: Spring Boot foundation and repository alignment.**

The backend currently includes:

- Project management CRUD APIs.
- Request validation and error handling.
- PostgreSQL persistence.
- Flyway-managed database migrations.
- `createdAt` and `updatedAt` timestamp handling.
- Spring Boot Actuator health monitoring.

The dependency-scanning workflow and dashboard are planned v1 functionality and are not yet represented as completed features.

## Technology Stack

| Component | Technology |
|---|---|
| Language | Java |
| Backend | Spring Boot |
| API | Spring Web |
| Database | PostgreSQL |
| Persistence | Spring Data JPA, Hibernate |
| Database migrations | Flyway |
| Health monitoring | Spring Boot Actuator |
| Vulnerability intelligence | OSV API |
| Frontend | React |
| Testing | JUnit, Mockito |
| Deployment | Docker |

## Repository Structure

```text
SentinelTrace/
├── backend/              # Spring Boot backend
├── docs/
│   └── development/      # Development roadmap
├── .github/              # Repository templates and workflows
├── README.md
├── CONTRIBUTING.md
└── SECURITY.md
```

## Development Setup

### Prerequisites

- Java 21
- PostgreSQL
- Git

### Run the backend

```bash
cd backend
./mvnw spring-boot:run
```

Configure the database connection using the environment variables expected by `application.properties`. Do not commit credentials or private configuration.

### Run tests

```bash
./mvnw test
```

Database schema changes are managed through Flyway migrations. Hibernate is configured to validate the schema rather than modify it automatically.

## Product Scope and Limitations

The initial version focuses on known vulnerabilities and dependency-related risk indicators.

A high risk score does not establish that a component is malicious or exploitable. Results depend on the quality of dependency metadata, the available vulnerability intelligence, and the assumptions used by the scoring method.

SentinelTrace is intended to support dependency-risk assessment and prioritization. It does not replace a developer’s security review or guarantee that an application is free of vulnerabilities.

## Documentation

- Development roadmap: `docs/development/Roadmap.md`

## Contributing

See [`CONTRIBUTING.md`](CONTRIBUTING.md) for development principles and commit-message conventions.

## Security

See [`SECURITY.md`](SECURITY.md) for security-reporting guidance. Never commit credentials, tokens, private keys, or confidential findings.

## License

A project license has not yet been selected. License details will be added once the licensing decision is made.

