USE granja_santa_maria;

SELECT ab.id_alimentacion_becerra,
       ab.fecha_alimentacion_becerra,
       gh.nombre_ganado_hembra,
       ab.cantidad_maniana_alimentacion,
       ab.cantidad_tarde_alimentacion,
       rm.nombre_ganado_hembra AS nombre_madre,
       ab.total_alimentacion_becerra,
       ab.estado_alimentacion_becerra
FROM alimentacion_becerra ab
         INNER JOIN produccion_diaria_leche pdl ON ab.id_produccion_diaria_leche = pdl.id_produccion_diaria_leche
         INNER JOIN ganado_hembra gh ON ab.id_becerra = gh.id_ganado_hembra
         LEFT JOIN relacion_madre_becerra rmb ON gh.id_ganado_hembra = rmb.id_becerra
         LEFT JOIN ganado_hembra rm ON rmb.id_madre = rm.id_ganado_hembra;


SELECT
    gh.nombre_ganado_hembra AS nombreBecerra,
    a.fecha_alimentacion_becerra,
    a.cantidad_maniana_alimentacion,
    a.cantidad_tarde_alimentacion,
    a.total_alimentacion_becerra,
    m.nombre_ganado_hembra AS madreBecerra,
    a.id_alimentacion_becerra
FROM
    alimentacion_becerra a
INNER JOIN
    produccion_diaria_leche p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche
INNER JOIN
    ganado_hembra gh ON a.id_becerra = gh.id_ganado_hembra
LEFT JOIN
    relacion_madre_becerra rmb ON gh.id_ganado_hembra = rmb.id_becerra
LEFT JOIN
    ganado_hembra m ON rmb.id_madre = m.id_ganado_hembra
WHERE
    p.id_produccion_diaria_leche = 34
    AND a.estado_alimentacion_becerra = TRUE;


