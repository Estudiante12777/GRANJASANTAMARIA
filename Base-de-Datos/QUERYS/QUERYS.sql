USE granja_santa_maria;

SELECT * FROM alimentacion_becerra;

SELECT * FROM produccion_diaria_leche;

SELECT g_h.nombre_ganado_hembra AS NOMBRE_BECERRA
FROM alimentacion_becerra AS a_b
INNER JOIN relacion_madre_becerra AS r_m_b ON a_b.id_becerra = r_m_b.id_becerra
INNER JOIN ganado_hembra AS g_h ON r_m_b.id_becerra = g_h.id_ganado_hembra
WHERE a_b.id_alimentacion_becerra = 11;
