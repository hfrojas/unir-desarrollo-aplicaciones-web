USE gestion_ciudades;

-- Insertar ciudades principales de Colombia si no existen
INSERT INTO ciudad (id, nombre)
SELECT 1, 'Bogotá'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 1);

INSERT INTO ciudad (id, nombre)
SELECT 2, 'Medellín'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 2);

INSERT INTO ciudad (id, nombre)
SELECT 3, 'Cali'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 3);

INSERT INTO ciudad (id, nombre)
SELECT 4, 'Barranquilla'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 4);

INSERT INTO ciudad (id, nombre)
SELECT 5, 'Cartagena'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 5);

INSERT INTO ciudad (id, nombre)
SELECT 6, 'Bucaramanga'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 6);

INSERT INTO ciudad (id, nombre)
SELECT 7, 'Pereira'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 7);

INSERT INTO ciudad (id, nombre)
SELECT 8, 'Santa Marta'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 8);

INSERT INTO ciudad (id, nombre)
SELECT 9, 'Manizales'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 9);

INSERT INTO ciudad (id, nombre)
SELECT 10, 'Cúcuta'
WHERE NOT EXISTS (SELECT 1 FROM ciudad WHERE id = 10);