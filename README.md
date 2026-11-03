# Trading Bot Control App & CI/CD Platform

A enterprise-grade Cloud-Native platform designed to manage, configure, and monitor remote MetaTrader 5 Expert Advisors (`.ex5` trading bots) in real time. 

The architecture decouples the trading execution environment (MT5) from parameter management by utilizing a RESTful backend API, automated database migrations, Kubernetes orchestrations via Helm and ArgoCD, and automated Azure DevOps CI/CD pipelines.

---

## 🏗️ System Architecture & Workflow

```
 +-------------------------------------------------------------------------+
 |                                                                         |
 |    +-------------------+    REST API    +--------------------------+    |
 |    |   External Client |  ------------> |   Java Spring Boot API   |    |
 |    | (Admin UI / User) |  (POST / PUT)  |      (users-api)         |    |
 |    +-------------------+                +--------------------------+    |
 |                                                      |                  |
 |                                                  SQL | Read/Write       |
 |                                                      v                  |
 |  +-----------------------+              +--------------------------+    |
 |  | MT5 Trading Bot (.ex5) |  Polling     |    PostgreSQL Database   |    |
 |  |  (Remote Terminal)    | -----------> |   (user_account_settings)|    |
 |  +-----------------------+  (GET /ping) +--------------------------+    |
 |                                                                         |
 +-------------------------------------------------------------------------+
```

### 🔄 How It Works

1. **Parameter Management (API Layer):**
   - User account configurations, risk parameters, feature flags, and access validation data are securely stored in the PostgreSQL database.
   - External clients or administrative interfaces send HTTP requests (REST API) to update configuration records for a specific trading account.

2. **Remote Execution (Trading Bot Polling):**
   - The compiled MT5 bot (`SMC(4h-15m).ex5`) runs within the client's trading environment.
   - At configurable polling intervals, the bot executes HTTP GET requests back to the Control API using its unique Account ID and access password.
   - The API verifies account validity (including expiration date checks) and returns updated trading parameters in real time.

---

## 🛠️ Tech Stack & Key Components

- **Backend Service:** Java 21, Spring Boot (REST API, Spring Data JPA).
- **Database & Migrations:** PostgreSQL 15 + Flyway Migration Service (`migration-service`).
- **Trading Environment:** MetaTrader 5 (`.ex5` compiled Expert Advisor).
- **Containerization & Local Dev:** Docker, Docker Compose.
- **Orchestration & Helm:** Kubernetes (`k3d`/`minikube`), Helm Charts (`k8s/trading-bot`).
- **GitOps & Deployment:** ArgoCD (OCI-based Helm registry sync via Docker Hub).
- **CI/CD Pipelines:** Azure DevOps (Multi-stage YAML pipelines, dynamic versioning, automated Helm packaging).

---

## 📁 Repository Structure

```text
.
├── Bot/                        # Compiled MetaTrader 5 bot (.ex5)
│   └── SMC(4h-15m).ex5
├── CI/                         # Azure DevOps CI/CD pipeline assets and templates
│   ├── Main Bot.yml
│   ├── PipelineTemplates.zip
│   └── trading-bot-state.zip
├── k8s/                        # Kubernetes & Helm Manifests
│   ├── manual/                 # Plain K8s YAML manifests (Deployment, Secret, Service)
│   └── trading-bot/            # Helm chart templates & values.yaml
├── migration-service/          # Flyway SQL migration container definitions
│   ├── Dockerfile
│   └── sql/
├── src/                        # Spring Boot API Application Source Code
├── azure-pipelines.yml         # Main Azure DevOps pipeline
├── docker-compose.yaml         # Local environment orchestration
├── Dockerfile                  # Main Java API Dockerfile
└── pom.xml                     # Maven project configuration
```

---

## 🚀 CI/CD & GitOps Delivery Flow

The deployment lifecycle is fully automated through an **Azure DevOps Pipeline** and **ArgoCD**:

1. **Pre-Build (Semantic Versioning):** Fetches tags from Docker Hub API and dynamically calculates the next `APP_VERSION`.
2. **Build & Test:** Compiles Java sources and runs tests using Maven with JDK 21.
3. **Container Build & Push:**
   - Builds and tags the main application Docker image (`prozgamer/trading-bot`).
   - Builds and tags the Flyway migration job container (`prozgamer/migration-job`).
4. **Helm Chart Packaging:** Updates image tags dynamically inside `values.yaml`, packages the Helm chart, and pushes it as an OCI artifact to Docker Hub (`oci://registry-1.docker.io/prozgamer`).
5. **ArgoCD GitOps Sync:** Automatically updates the target revision inside the state repository (`trading-bot-state`), triggering ArgoCD to sync and deploy updated pods to the Kubernetes cluster.

---

## Video
[![Описание видео](https://img.youtube.com/vi/v9x1DK9F7ms/0.jpg)](https://youtu.be/v9x1DK9F7ms)
