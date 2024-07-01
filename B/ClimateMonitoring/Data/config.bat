@echo off
REM Save the current directory
set CURRENT_DIR=%cd%

REM Change directory to the directory of the batch script
cd /d %~dp0

REM Set the relative path to the JAR file
set JAR_PATH=..\lib\codice-fiscale-java-master.jar

REM Check if the JAR file exists
if not exist "%JAR_PATH%" (
    echo Error: %JAR_PATH% does not exist.
    pause
    exit /b 1
)

REM Esegui il comando usando il percorso relativo per installare il JAR nel repository locale di Maven
echo Installing %JAR_PATH% to local Maven repository
mvn install:install-file -Dfile="%JAR_PATH%" -DgroupId=climatemonitoring -DartifactId=codice-fiscale-java-master -Dversion=1.0.0 -Dpackaging=jar

REM Check if the Maven command was successful
if errorlevel 1 (
    echo Error: Maven install command failed.
    pause
    exit /b 1
)

REM Return to the original directory
cd /d %CURRENT_DIR%
