$databases = @(
    "nowelle_dev",
    "nowelle_test",
    "nowelle_prod",
    "myapp_db"
)

$containerName = "postgres-test"
$PGPASSWORD = "adminpassword"
$user = "admin"

# Create an array to store operation results
$operationLog = @()

# Check if container is running
$containerStatus = docker ps -q -f name=$containerName
if (!$containerStatus) {
    Write-Error "Container $containerName is not running. Please start it first using setup-container.ps1"
    exit 1
}

# Wait for PostgreSQL to be ready
Write-Host "Waiting for PostgreSQL to be ready..."
$retries = 30
while ($retries -gt 0) {
    try {
        $result = docker exec $containerName pg_isready
        if ($LASTEXITCODE -eq 0) {
            break
        }
    }
    catch {
        Write-Host "Waiting for PostgreSQL to start... ($retries attempts remaining)"
    }
    Start-Sleep -Seconds 1
    $retries--
}

if ($retries -eq 0) {
    Write-Error "PostgreSQL failed to start in time"
    exit 1
}

foreach ($db in $databases) {
    Write-Host "Creating database: $db"
    
    try {
        # Drop database operation
        $dropResult = docker exec -it $containerName psql -U $user -d postgres -c "DROP DATABASE IF EXISTS $db;"
        $operationLog += "Dropped database (if existed): $db"
        
        # Create database operation
        $createResult = docker exec -it $containerName psql -U $user -d postgres -c "CREATE DATABASE $db;"
        $operationLog += "Created database: $db"
    }
    catch {
        Write-Error "Failed to create database $db"
    }
}

Write-Host "`nAll databases created successfully!`n"
Write-Host "Operation Summary:"
Write-Host "----------------"
foreach ($entry in $operationLog) {
    Write-Host "- $entry"
}

# Simplified output at the end - just confirm databases are ready
Write-Host "`nDatabases initialized and ready for API testing!"
Write-Host "------------------------------------------------"
Write-Host "Available databases:"
foreach ($db in $databases) {
    Write-Host "- $db"
}

# Skip the detailed PostgreSQL listing since it's not relevant for API testing

# Create the database if it doesn't exist
docker exec postgres-test psql -U admin -d postgres -c "
CREATE DATABASE myapp_db WITH OWNER = admin;
" 2>$null

# Verify the database was created
docker exec postgres-test psql -U admin -d postgres -c "\l"