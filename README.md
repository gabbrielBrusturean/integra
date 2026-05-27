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