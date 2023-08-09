USE granja_santa_maria;

/*
    MODULO GANADO
*/
SELECT *
FROM raza_ganado;
SELECT *
FROM tipo_ganado;
SELECT *
FROM ganado_macho;
SELECT *
FROM ganado_hembra;

/*
    MODULO PRODUCCION LACTEOS
*/
SELECT *
FROM produccion_diaria_leche;

SELECT g_h.nombre_ganado_hembra,
       p_d_l.produccion_maniana_leche,
       p_d_l.produccion_tarde_leche,
       p_d_l.total_produccion_leche,
       p_d_l.fecha_produccion_leche
FROM produccion_diaria_leche AS p_d_l
         INNER JOIN ganado_hembra AS g_h ON p_d_l.id_ganado_hembra = g_h.id_ganado_hembra;

/*
    MODULO UBICACIO
*/

SELECT *
FROM pais;
SELECT *
FROM departamento;
SELECT *
FROM municipio;

SELECT *
FROM ganado_hembra;

SELECT NOW() AS FECHA_ACTUAL;

SELECT nombre_ganado_hembra, TIMESTAMPDIFF(MONTH, fecha_nacimiento, CURDATE()) AS meses_transcurridos
FROM ganado_hembra
WHERE id_ganado_hembra = 9;
SELECT nombre_ganado_hembra, TIMESTAMPDIFF(MONTH, fecha_nacimiento, CURDATE()) AS meses_transcurridos
FROM ganado_hembra
WHERE id_ganado_hembra = 10;
SELECT nombre_ganado_hembra, TIMESTAMPDIFF(MONTH, fecha_nacimiento, CURDATE()) AS meses_transcurridos
FROM ganado_hembra
WHERE id_ganado_hembra = 11;

SELECT nombre_ganado_hembra, fecha_nacimiento
FROM ganado_hembra
WHERE id_ganado_hembra = 9;

UPDATE ganado_hembra
SET fecha_nacimiento = '2023-05-08'
WHERE id_ganado_hembra = 9;

