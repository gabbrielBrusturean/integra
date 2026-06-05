# Volunteering Platform

## Overview

The **Volunteering Platform** is an event management application designed to connect volunteers with organizations and opportunities. It allows users to discover, participate in, and manage volunteer events.

## Prerequisites

install before running the project:

- Node.js & npm
- Java
- PostgreSQL
- DBeaver

## Backend Setup & Run

1. Navigate to the backend project directory.

2. Run the following command:
```bash
   gradle bootRun
```

3. Note:
   - The backend will build successfully.
   - It will **not stay running** until the database connection is properly configured.


## Frontend Setup & Run

1. Navigate to the frontend project directory.

2. Install dependencies:
```bash
   npm install
```

3. Start the application:
```bash
   npm run start
```

## Frontend Deploy on Vercel

The Angular app is deployed as static files generated in `frontend/dist/frontend/browser`.

1. Push the repository to GitHub.

2. In Vercel, import the GitHub repository and set:
   - Root Directory: `frontend`
   - Framework Preset: `Angular`
   - Install Command: `npm ci`
   - Build Command: `npm run build`
   - Output Directory: `dist/frontend/browser`

3. Deploy the project.

The `frontend/vercel.json` file keeps these settings in the repo and adds a fallback to `index.html` so Angular Router routes work after refresh.

For local development, Angular uses `frontend/proxy.conf.json` to forward `/api` to `http://localhost:8080`. In production, the Spring Boot API must be deployed separately and exposed under the same `/api` path with a reverse proxy, or the frontend services must be changed to use the deployed backend URL.

## Backend Deploy on Render

The Spring Boot backend is deployed as a Docker web service.

1. In Render, create a New Web Service and connect the GitHub repository.

2. Configure the service:
   - Root Directory: `backend`
   - Runtime / Environment: `Docker`
   - Dockerfile Path: `./Dockerfile`
   - Docker Build Context Directory: `.`

3. Add environment variables:
   - `DATABASE_URL`
   - `DATABASE_USERNAME`
   - `DATABASE_PASSWORD`
   - `JWT_SECRET`
   - `SPRING_PROFILES_ACTIVE=prod`

Render provides `PORT` automatically. The backend uses `${PORT:8080}`, so it still runs locally on port `8080` when `PORT` is not set.

After deploy, the backend URL will look like:

```text
https://<service-name>.onrender.com
```

Test the health endpoint:

```text
https://<service-name>.onrender.com/api/health
```

After the backend is deployed, update the Vercel rewrite in `frontend/vercel.json` so `/api` points to Render before the Angular fallback:

```json
{
  "source": "/api/:path*",
  "destination": "https://<service-name>.onrender.com/api/:path*"
}
```

## PostgreSQL Setup

1. Open DBeaver and connect to your PostgreSQL server (ex: default `postgres` database)

2. Run the following SQL command:
```sql
   CREATE DATABASE integra;
```

3. make a new connection in dbeaver
   - make sure u added the correct username and pass
   - by this u should have a DB and coneected to it on dbeaver

4. Update your backend configuration:
   - create a .env file in backend folder
   - add your own username and password for DB connection
      - DB_USERNAME=username
      - DB_PASSWORD=password

5. rerun gradle bootRun
   - The application should now run successfully and remain up
   - u can check by going to http://localhost:8080/api/coffeemugs


## Notes

- Ensure PostgreSQL is running before starting the backend
- check database credentials if the backend fails to start
- check **Wiki section on Github** for naming Conventions and workflow
