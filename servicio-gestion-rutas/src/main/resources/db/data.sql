-- Insertar rutas entre las ciudades principales si no existen
INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 1, 'Ruta Bogotá a Medellín', 1, 2, 415
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 1);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 2, 'Ruta Bogotá a Cali', 1, 3, 465
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 2);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 3, 'Ruta Medellín a Cartagena', 2, 5, 640
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 3);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 4, 'Ruta Bogotá a Barranquilla', 1, 4, 970
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 4);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 5, 'Ruta Barranquilla a Santa Marta', 4, 8, 103
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 5);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 6, 'Ruta Cartagena a Santa Marta', 5, 8, 209
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 6);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 7, 'Ruta Bogotá a Bucaramanga', 1, 6, 400
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 7);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 8, 'Ruta Bucaramanga a Cúcuta', 6, 10, 200
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 8);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 9, 'Ruta Pereira a Manizales', 7, 9, 50
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 9);

INSERT INTO ruta (id, nombre, ciudad_id_origen, ciudad_id_destino, distancia)
SELECT 10, 'Ruta Cali a Pereira', 3, 7, 215
WHERE NOT EXISTS (SELECT 1 FROM ruta WHERE id = 10);
