@echo off
REM Parametri di connessione al database
set PGHOST=localhost
set PGPORT=5432
set PGUSER=postgres
set PGPASSWORD=postgres
set DATABASE=dbcm

REM Verifica se il database esiste già
psql -h %PGHOST% -p %PGPORT% -U %PGUSER% -lqt | findstr /R /C:"^ %DATABASE% " >nul
if %errorlevel% equ 0 (
    echo Il database %DATABASE% esiste già.
) else (
    REM Crea il database
    psql -h %PGHOST% -p %PGPORT% -U %PGUSER% -c "CREATE DATABASE %DATABASE%;" > create_database.log 2>&1
    if %errorlevel% neq 0 (
        type create_database.log
        exit /b %errorlevel%
    )
    echo Database %DATABASE% creato con successo.
)

psql -h localhost -p 5432 -U postgres -lqt | findstr /R /C:"^ dbcm " >nul