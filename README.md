# SentinelTrace

### Software Supply-Chain Risk Intelligence Framework

SentinelTrace is a cybersecurity framework designed to analyse software dependencies, identify known vulnerabilities, model dependency relationships, and generate explainable risk assessments.

The framework combines vulnerability intelligence with dependency-graph analysis to help developers understand and prioritise software supply-chain risks.

## Problem Statement

Modern software applications depend on open-source packages and third-party components. Vulnerabilities in these components can affect applications through both direct and transitive dependencies.

Severity-based vulnerability ranking alone may not adequately represent a component's position within a dependency network or its potential influence on an application.

SentinelTrace investigates whether dependency-graph analysis and vulnerability intelligence can improve risk prioritisation compared with conventional severity-only ranking.

## Objectives

- Ingest software dependency information and supported SBOM formats.
- Identify known vulnerabilities using external vulnerability intelligence.
- Represent dependency relationships as a graph.
- Develop an explainable risk-scoring mechanism.
- Visualise dependencies, vulnerabilities, and risk assessments.
- Evaluate the proposed approach against severity-based baselines.
- Produce reproducible experimental results.

## Technology Stack

| Component | Technology |
|---|---|
| Language | Java |
| Backend | Spring Boot |
| API | Spring Web |
| Database | PostgreSQL |
| Persistence | Spring Data JPA, Hibernate |
| Vulnerability intelligence | OSV API |
| Frontend | React |
| Testing | JUnit, Mockito |
| Deployment | Docker |

Python may be introduced for research experiments when it provides a clear advantage.

## Architecture

The planned framework consists of six primary modules:

1. Project and dependency ingestion
2. Vulnerability intelligence
3. Dependency graph analysis
4. Explainable risk scoring
5. Security dashboard
6. Experimental evaluation and reporting

Detailed architecture documentation will be maintained in `docs/architecture/`.

## Research Question

**Can dependency-graph analysis and vulnerability intelligence improve software supply-chain risk prioritisation compared with vulnerability-severity-only ranking?**

The proposed approach will be evaluated using documented datasets, defined baselines, and reproducible experiments.

## Project Status

**Current stage:** Repository initialization and development planning.

The implementation will progress incrementally, beginning with the Spring Boot foundation and project management APIs.

## Scope and Limitations

SentinelTrace initially focuses on known vulnerabilities and dependency-related risk indicators.

A high risk score does not establish that a component is malicious or exploitable. Results depend on the quality of dependency metadata, vulnerability intelligence, and the assumptions used by the scoring method.

The framework will document its methodology, experimental limitations, and evaluation results.

## Documentation

- Architecture: `docs/architecture/`
- Research methodology: `docs/research/`
- Development roadmap: `docs/development/roadmap.md`
- Experimental documentation: `research/`

## License

A project license will be selected before the first public software release.