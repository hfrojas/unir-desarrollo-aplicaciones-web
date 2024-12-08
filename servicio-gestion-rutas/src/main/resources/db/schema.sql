-- schema.sql
CREATE DATABASE IF NOT EXISTS gestion_rutas DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
USE gestion_rutas;

-- Asegurarnos de que estamos en el esquema correcto
CREATE SCHEMA IF NOT EXISTS gestion_rutas;
USE gestion_rutas;

-- Crear tabla ruta
CREATE TABLE IF NOT EXISTS ruta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    ciudad_id_origen BIGINT NOT NULL,
    ciudad_id_destino BIGINT NOT NULL,
    distancia DOUBLE NOT NULL
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

