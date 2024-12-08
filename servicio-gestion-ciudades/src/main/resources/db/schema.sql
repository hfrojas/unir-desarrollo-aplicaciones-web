-- schema.sql
CREATE DATABASE IF NOT EXISTS gestion_ciudades DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
USE gestion_ciudades;

-- Asegurarnos de que estamos en el esquema correcto
CREATE SCHEMA IF NOT EXISTS gestion_ciudades;
USE gestion_ciudades;

-- Crear tabla ciudad
CREATE TABLE IF NOT EXISTS ciudad (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE -- Agregar restricción de unicidad
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
