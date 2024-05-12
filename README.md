# PostgreSQL Docker Setup and Database Initialization

This guide provides instructions for setting up a PostgreSQL Docker container and initializing databases for testing purposes using PowerShell scripts.

## Prerequisites

- Docker installed on your machine
- PowerShell available for executing scripts

## Scripts Overview

1. **setup-container.ps1**: This script pulls the latest PostgreSQL Docker image and runs a new container named `postgres-test`. It sets up the environment with a default user and password.

2. **init-databases.ps1**: This script initializes a set of databases within the running PostgreSQL container. It drops any existing databases with the same names and creates new ones.

## Execution Order

1. **Run the Container Setup Script**

   First, execute the `setup-container.ps1` script to set up the PostgreSQL Docker container. This step ensures that the container is running and ready to accept database operations.

   ```powershell
   .\setup-container.ps1
   ```

   This script will:
   - Pull the latest PostgreSQL image
   - Start a new container with the specified user credentials
   - Confirm that the container is running

2. **Run the Database Initialization Script**

   After the container is up and running, execute the `init-databases.ps1` script to initialize the databases.

   ```powershell
   .\init-databases.ps1
   ```

   This script will:
   - Drop existing databases if they exist
   - Create new databases as specified in the script
   - Provide a summary of operations performed
   - List all current databases in the container

## Purpose

These scripts are designed for testing purposes, allowing you to quickly set up a PostgreSQL environment and initialize databases. This setup is ideal for development and testing scenarios where you need a clean database state.

By following this process, you ensure that your PostgreSQL environment is consistently set up, making it easier to test database-related functionality in your applications.

## Notes

- Ensure Docker is running before executing these scripts.
- Modify the database names and credentials in the scripts as needed for your specific testing requirements.