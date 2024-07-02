CREATE EXTENSION IF NOT EXISTS dblink;
-- Verifica se il database 'dbcm' esiste già
SELECT EXISTS (
    SELECT 1 FROM pg_catalog.pg_database WHERE datname = 'dbcm'
) AS db_exists;

-- Creazione del database 'dbcm' se non esiste
CREATE DATABASE IF NOT EXISTS dbcm;