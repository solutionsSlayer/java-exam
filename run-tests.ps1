# Create logs directory if it doesn't exist
New-Item -ItemType Directory -Force -Path "logs"

# Get current timestamp for log file name
$timestamp = Get-Date -Format "yyyy-MM-dd_HH-mm-ss"
$logFile = "logs/test-run_$timestamp.log"

# Start logging
"=== Test Execution Started at $(Get-Date) ===" | Tee-Object -FilePath $logFile

# Initialize databases and log output
"=== Database Initialization ===" | Tee-Object -Append -FilePath $logFile

# Execute and log database initialization with verbose output
Write-Output "Starting database initialization..." | Tee-Object -Append -FilePath $logFile
$dbOutput = & {
    Write-Output "Dropping existing databases..."
    ./init-databases.ps1 2>&1
    Write-Output "Verifying databases..."
    docker exec postgres-test psql -U admin -d postgres -c "\l" 2>&1
} | ForEach-Object {
    $_ -replace '\x1B\[[0-9;]*[mGKH]|\x1B\[\?[0-9;]*[hl]' -replace '\[[\d;]+m'
}
$dbOutput | Tee-Object -Append -FilePath $logFile

# Execute tests and log output
"=== Test Execution ===" | Tee-Object -Append -FilePath $logFile
$testOutput = ./mvnw test 2>&1 | ForEach-Object {
    $_ -replace '\x1B\[[0-9;]*[mGKH]|\x1B\[\?[0-9;]*[hl]' -replace '\[[\d;]+m'
}
$testOutput | Tee-Object -Append -FilePath $logFile

"=== Test Execution Completed at $(Get-Date) ===" | Tee-Object -Append -FilePath $logFile