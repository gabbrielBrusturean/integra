# Event Sample Data Guide

## Overview

Sample event data is automatically seeded into the database when the backend starts in `dev` profile mode.
This provides realistic data for frontend development and testing.

## Database Configuration by Profile

### Development Profile (`dev`)

- **Database**: H2 (in-memory)
- **Configuration File**: `application-dev.yaml`
- **Features**:
    - In-memory database (no setup required)
    - H2 Console accessible at `http://localhost:8080/api/h2-console`
    - Auto-creates schema on startup (`create-drop`)
    - Sample data automatically seeded
    - Perfect for local development

### Production Profile (`prod`)

- **Database**: PostgreSQL
- **Configuration File**: `application-prod.yaml`
- **Features**:
    - External PostgreSQL database
    - Schema validation only (no auto-creation)
    - Requires `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD` environment variables
    - Connection pooling enabled (Hikari)

1. **Java Developer Conference 2026** - 2-day conference (500 participants)
2. **Frontend Masterclass - Angular Workshop** - 1-day hands-on workshop (50 participants)
3. **Cloud Architecture & DevOps Summit** - 2-day summit (300 participants)
4. **Webinar: Spring Security Best Practices** - 1.5-hour online webinar (1000 participants)
