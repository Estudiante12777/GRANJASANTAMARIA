USE granja_santa_maria;

SELECT * FROM prenies_ganado_hembra;

SELECT DATEDIFF(CURDATE(), '2022-12-16') AS dias_transcurridos;
SELECT * FROM prenies_ganado_hembra;

UPDATE prenies_ganado_hembra SET fecha_prenies = '2022-12-16' WHERE id_prenies_ganado_hembra = 1;
