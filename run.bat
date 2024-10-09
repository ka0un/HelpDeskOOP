@echo off
setlocal enabledelayedexpansion

rem Set project directories
set "SRC_DIR=src"
set "WEB_CONTENT_DIR=WebContent"
set "LIB_DIR=lib"
set "BUILD_DIR=build"
set "CLASSES_DIR=%BUILD_DIR%\WEB-INF\classes"

rem Set Java and Tomcat variables
set "JAVA_HOME=C:\Path\To\Your\JDK"
set "TOMCAT_HOME=C:\Path\To\Your\Tomcat9"

rem Clean build directory
if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%BUILD_DIR%"
mkdir "%CLASSES_DIR%"
mkdir "%BUILD_DIR%\WEB-INF\lib"

rem Copy web resources
echo Copying web resources...
xcopy /s /y "%WEB_CONTENT_DIR%\*" "%BUILD_DIR%"

rem Copy library files
echo Copying library files...
xcopy /s /y "%LIB_DIR%\*" "%BUILD_DIR%\WEB-INF\lib\"

rem Compile Java sources
echo Compiling Java sources...
set "CLASSPATH=%CLASSES_DIR%;%BUILD_DIR%\WEB-INF\lib\*;%TOMCAT_HOME%\lib\*"

rem Create a list of all Java files
dir /s /b "%SRC_DIR%\*.java" > javafiles.txt

rem Compile all Java files
javac --release 16 -cp "%CLASSPATH%" -d "%CLASSES_DIR%" @javafiles.txt

if %errorlevel% neq 0 (
    echo Compilation failed. Please check the error messages above.
    exit /b %errorlevel%
)

rem Build Docker image
echo Building Docker image...
docker build -t helpdeskoop:latest .

rem Run Docker container
echo Running Docker container...
docker run -d -p 8080:8080 helpdeskoop:latest

echo Build and deployment completed successfully.
del javafiles.txt

for /f "tokens=*" %%i in ('docker ps --format "{{.Ports}}"') do (
    echo Checking ports: %%i
    echo %%i | find "0.0.0.0:8080->8080/tcp" >nul
    if %ERRORLEVEL%==0 (
        set "portFound=true"
        echo [32m Success: Port 8080 is up and running on 0.0.0.0:8080 [0m
    )
)

:: If the port wasn't found, print an error message
if "%portFound%"=="false" (
    color 0C
    echo [31m Something went wrong: Port 8080 is not running. [0m
)

echo.
pause
