

# SentinelTrace Product Development Roadmap

This roadmap tracks the development of SentinelTrace as a usable, secure, and deployable software product. Checked items indicate work reported as completed; unchecked items remain planned or in progress.

## Phase 0: Repository Initialization — Complete

- [x] Define the initial project specification.
- [x] Create the GitHub repository.
- [x] Initialize the local Git repository.
- [x] Establish the initial directory structure.
- [x] Add initial project documentation and configuration files.
- [x] Create the initial commit and push to GitHub.

## Phase 1: Backend Foundation — In Progress

- [x] Generate the Spring Boot application.
- [x] Configure PostgreSQL.
- [x] Establish the backend package structure.
- [x] Implement the project entity.
- [x] Create project CRUD APIs.
- [x] Add request validation and error handling.
- [x] Add the Actuator health endpoint.
- [x] Configure Flyway and add the initial database migrations.
- [x] Add `createdAt` and `updatedAt` timestamp handling.
- [x] Complete foundation testing and verification.
- [x] Align repository documentation with the product v1 plan.
- [x] Select and add a project license.

## Phase 2: Dependency Ingestion

- [ ] Define the dependency-ingestion API and input limits.
- [ ] Support CycloneDX JSON SBOM uploads.
- [ ] Support npm `package-lock.json` uploads.
- [ ] Parse package identifiers, versions, and dependency relationships.
- [ ] Validate malformed, unsupported, and incomplete inputs.
- [ ] Persist dependency records and relationships.
- [ ] Preserve source and parsing evidence for scan results.

## Phase 3: Vulnerability Intelligence

- [ ] Integrate the OSV API.
- [ ] Map ingested packages to the identifiers required for vulnerability queries.
- [ ] Match package versions against vulnerability affected-version information.
- [ ] Persist vulnerability information and matching evidence.
- [ ] Handle unavailable, invalid, or incomplete vulnerability responses.

## Phase 4: Dependency Graph Analysis

- [ ] Define graph nodes and edges.
- [ ] Build graph representations from ingested dependency relationships.
- [ ] Analyse direct and transitive dependencies.
- [ ] Trace paths from the application to vulnerable components.
- [ ] Include dependency paths in scan results.

## Phase 5: Explainable Risk Scoring

- [ ] Define the baseline risk calculation.
- [ ] Define dependency-related risk indicators.
- [ ] Implement the risk-scoring method.
- [ ] Record score inputs, contributing factors, and explanations.
- [ ] Return explainable risk assessments through scan results.

## Phase 6: Scan APIs and Security Dashboard

- [ ] Define scan request and response models.
- [ ] Implement scan APIs.
- [ ] Develop the React application.
- [ ] Integrate the dashboard with backend APIs.
- [ ] Visualise dependencies and vulnerabilities.
- [ ] Display dependency paths, risk assessments, and explanations.
- [ ] Provide useful loading, empty, and error states.

## Phase 7: Product Testing and Hardening

- [ ] Complete foundation testing and verification.
- [ ] Test supported dependency-file parsing and validation.
- [ ] Test vulnerability matching and affected-version handling.
- [ ] Test dependency graph construction and path tracing.
- [ ] Test risk-score calculations and explanations.
- [ ] Test scan API behavior and error handling.
- [ ] Test dashboard integration with the backend.
- [ ] Review input validation, upload limits, and secret handling.
- [ ] Verify database migration behavior on fresh and existing databases.

## Phase 8: Deployment and Product Release

- [ ] Add Docker support.
- [ ] Configure CI/CD.
- [ ] Configure HTTPS for deployment.
- [ ] Document setup and configuration instructions.
- [ ] Document API endpoints and product usage.
- [ ] Prepare deployment and operations instructions.
- [ ] Select and add a project license.
- [ ] Complete the v1 release checklist.
- [ ] Prepare the v1 release.
