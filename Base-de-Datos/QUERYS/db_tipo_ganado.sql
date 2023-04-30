USE granja_santa_maria; 

SELECT * FROM tipo_ganado;

TRUNCATE TABLE tipo_ganado;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM ganado WHERE id_tipo_ganado IN (SELECT id_tipo_ganado FROM tipo_ganado);
DELETE FROM tipo_ganado;

ALTER TABLE tipo_ganado AUTO_INCREMENT = 1;
