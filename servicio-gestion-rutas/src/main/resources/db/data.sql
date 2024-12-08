USE gestion_rutas;

-- Insertar rutas entre las ciudades principales si no existen
INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 1, 'Ruta Bogotá-Medellín', 1, 2
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 1);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 2, 'Ruta Bogotá-Cali', 1, 3
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 2);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 3, 'Ruta Medellín-Cartagena', 2, 5
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 3);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 4, 'Ruta Bogotá-Barranquilla', 1, 4
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 4);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 5, 'Ruta Barranquilla-Santa Marta', 4, 8
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 5);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 6, 'Ruta Cartagena-Santa Marta', 5, 8
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 6);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 7, 'Ruta Bogotá-Bucaramanga', 1, 6
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 7);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 8, 'Ruta Bucaramanga-Cúcuta', 6, 10
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 8);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 9, 'Ruta Pereira-Manizales', 7, 9
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 9);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino)
SELECT 10, 'Ruta Cali-Pereira', 3, 7
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 10);
