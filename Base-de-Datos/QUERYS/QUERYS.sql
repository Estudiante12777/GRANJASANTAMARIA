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
