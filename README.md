#  AI Gateway Platform | Multi-Model LLM Orchestration (Spring AI)

A production-grade AI platform showcasing **multi-model orchestration, intelligent routing, RAG pipelines, and reactive streaming** using Spring AI.

Designed with a **platform engineering mindset** to abstract LLM complexity and enable scalable, resilient, and cost-optimized AI systems.

---

##  Key Capabilities

- 🔌 **Pluggable AI Gateway (Spring Boot Starter)**
  - Drop-in library for any microservice
  - Encapsulates orchestration, routing, fallback, and RAG

- 🤖 **Multi-Model Orchestration**
  - OpenAI (cloud) + Ollama (local)
  - Dynamic routing based on prompt type & cost

- 🔁 **Automatic Fallback**
  - Seamless failover (OpenAI → Ollama)
  - High availability by design

- 🌊 **Reactive Streaming (WebFlux)**
  - Token-level streaming responses
  - Non-blocking architecture

- 📚 **RAG Pipeline (Vector Store Ready)**
  - pgvector integration
  - Generic retrieval pipeline

- 🔐 **Secure Access Layer**
  - API key-based authentication
  - Extendable to JWT/OAuth

---

## 🏗️ Architecture
Client
↓
AI Gateway App (Security Layer)
↓
AI Gateway Plugin (Reusable Starter)
↓
Orchestration Engine
├── Prompt Classifier
├── Intelligent Router
├── RAG Pipeline
├── Fallback Handler
↓
Model Providers
├── OpenAI
└── Ollama


---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.x
- Spring AI
- Spring WebFlux
- PostgreSQL + pgvector
- OpenAI API
- Ollama (local LLM)
- Docker Compose

---

## 🚀 Getting Started

```bash
# Start dependencies
docker-compose up -d

# Run Ollama
ollama run llama3.2:1b

# Start application
mvn spring-boot:run

curl -N "http://localhost:8080/ai/chat?prompt=hello" \
  -H "x-api-key: my-api-key"

💡 What This Demonstrates
AI platform engineering (not just API integration)
Multi-model LLM abstraction & orchestration
Reactive, scalable system design
Cost-aware AI routing
Production-ready architecture patterns
