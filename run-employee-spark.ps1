
# Configure Hadoop environment
$env:HADOOP_HOME = "C:\hadoop"
$env:PATH = "$env:HADOOP_HOME\bin;$env:PATH"

# Verify winutils
if (-not (Test-Path "$env:HADOOP_HOME\bin\winutils.exe")) {
    Write-Error "winutils.exe not found"
    exit 1
}

# Locate assembly JAR
$jar = ".\target\scala-2.12\employee-spark-assembly-0.1.0.jar"

if (-not (Test-Path $jar)) {
    Write-Error "Assembly JAR not found. Run sbt assembly first."
    exit 1
}

Write-Host "Starting Employee Spark Application..."

# Submit application
spark-submit.cmd `
    --class com.training.EmployeeAnalysis `
    --master "local[*]" `
    $jar

# Check execution result
if ($LASTEXITCODE -ne 0) {
    Write-Error "Spark application failed"
    exit $LASTEXITCODE
}

Write-Host "Employee Spark Application completed successfully!"
