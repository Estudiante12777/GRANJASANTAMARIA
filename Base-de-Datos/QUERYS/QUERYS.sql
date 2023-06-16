USE granja_santa_maria;

SELECT * FROM produccion_diaria_leche;

SELECT g_h.nombre_ganado_hembra, p_d_l.produccion_maniana_leche, p_d_l.produccion_tarde_leche, p_d_l.total_produccion_leche , p_d_l.fecha_produccion_leche
FROM produccion_diaria_leche AS p_d_l
INNER JOIN ganado_hembra AS g_h
ON p_d_l.id_ganado_hembra = g_h.id_ganado_hembra;

SELECT * FROM dieta_ternera_ternero;
