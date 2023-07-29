USE granja_santa_maria;

SELECT * FROM raza_ganado;
SELECT * FROM tipo_ganado;



DELETE FROM raza_ganado WHERE id_raza_ganado = 2;

ALTER TABLE raza_ganado AUTO_INCREMENT = 1;

SELECT id_tipo_ganado, estado_tipo_ganado FROM tipo_ganado WHERE estado_tipo_ganado = 0;

UPDATE tipo_ganado SET estado_tipo_ganado = 1 WHERE id_tipo_ganado = 8;

