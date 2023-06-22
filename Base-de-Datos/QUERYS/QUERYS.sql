USE granja_santa_maria;

SELECT * FROM departamento;
SELECT * FROM municipio;

SELECT COUNT(*) AS total_municipios_suhitepequez
FROM municipio WHERE id_departamento = 20;

SELECT COUNT(*) AS total_municipios_retalhuleu
FROM municipio WHERE id_departamento = 15;
