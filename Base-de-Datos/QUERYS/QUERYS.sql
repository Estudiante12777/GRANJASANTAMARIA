USE granja_santa_maria;

SELECT * FROM ganado_macho;
SELECT * FROM ganado_hembra;

SELECT DATEDIFF(CURDATE(), '2022-12-01') AS dias_transcurridos;
SELECT * FROM ganado_hembra;
SELECT * FROM produccion_diaria_leche;

SELECT fecha_produccion_leche, nombre_ganado_hembra, produccion_maniana_leche, produccion_tarde_leche, total_produccion_leche
FROM ganado_hembra
INNER JOIN produccion_diaria_leche
ON ganado_hembra.id_ganado_hembra = produccion_diaria_leche.id_ganado_hembra;
