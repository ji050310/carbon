@echo off
echo ========================================
echo    Carbon Project - Start All Services
echo ========================================

echo Building all modules...
call mvn clean package -DskipTests
if %ERRORLEVEL% NEQ 0 (
    echo "Build failed!"
    exit /b 1
)

echo.
echo Starting Carbon Auth Service (Port 9001)...
start "Carbon Auth" cmd /k "cd carbon-auth && java -Xms256m -Xmx512m -XX:+UseG1GC -jar target\carbon-auth-1.0-SNAPSHOT.jar --spring.profiles.active=test --springfox.documentation.enabled=false"

echo Waiting 10 seconds...
timeout /t 10 /nobreak >nul

echo.
echo Starting Carbon System Service (Port 9002)...
start "Carbon System" cmd /k "cd carbon-system && java -Xms256m -Xmx512m -XX:+UseG1GC -jar target\carbon-system-1.0-SNAPSHOT.jar --spring.profiles.active=test"

echo Waiting 10 seconds...
timeout /t 10 /nobreak >nul

echo.
echo Starting Carbon Assets Service (Port 9003)...
start "Carbon Assets" cmd /k "cd carbon-assets && java -Xms512m -Xmx1g -XX:+UseG1GC -jar target\carbon-assets-1.0-SNAPSHOT.jar --spring.profiles.active=test --springfox.documentation.enabled=false"

echo.
echo All services starting...
echo Check individual windows for startup status.
echo.
echo Services:
echo - Carbon Auth: http://localhost:9001/authCenter
echo - Carbon System: http://localhost:9002/system  
echo - Carbon Assets: http://localhost:9003/assets
echo.
pause
