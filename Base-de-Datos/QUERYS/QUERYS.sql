USE granja_santa_maria;

SELECT * FROM raza_ganado;
SELECT * FROM tipo_ganado;
SELECT * FROM ganado_macho;

DELETE FROM ganado_macho WHERE id_ganado_macho = 1;
ALTER TABLE ganado_macho AUTO_INCREMENT = 1;

