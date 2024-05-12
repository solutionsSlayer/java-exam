# Pull the latest PostgreSQL image
docker pull postgres:latest

# Stop and remove existing container if it exists
docker stop postgres-test 2>$null
docker rm postgres-test 2>$null

# Run a new PostgreSQL container with proper credentials
docker run -d --name postgres-test `
    -e POSTGRES_USER=admin `
    -e POSTGRES_PASSWORD=adminpassword `
    -e POSTGRES_DB=myapp_db `
    -p 5432:5432 postgres:latest

# Wait for PostgreSQL to be ready
Write-Host "Waiting for PostgreSQL to start..."
Start-Sleep -Seconds 10

# Verify the container is running
docker ps -f name=postgres-test

# Test the connection
docker exec postgres-test psql -U admin -d myapp_db -c "SELECT 1"