CREATE DATABASE granja_santa_maria;

USE granja_santa_maria; 

SELECT *
FROM alimentacion_becerro
JOIN produccion_diaria_leche ON alimentacion_becerro.id_produccion_diaria_leche = produccion_diaria_leche.id_produccion_diaria_leche
WHERE produccion_diaria_leche.id_produccion_diaria_leche = 1;


/** GANADO **/
CREATE TABLE raza_ganado(
	id_raza_ganado INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_raza_ganado VARCHAR(50) NOT NULL UNIQUE, 
    estado_raza_ganado TINYINT NOT NULL
); 
CREATE TABLE tipo_ganado(
	id_tipo_ganado INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_tipo_ganado VARCHAR(50) NOT NULL UNIQUE, 
    estado_tipo_ganado TINYINT NOT NULL
); 
CREATE TABLE ganado_macho(
    id_ganado_macho INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_ganado_macho VARCHAR(50) NOT NULL UNIQUE,
    id_tipo_ganado INT NOT NULL,
    id_raza_ganado INT NOT NULL,
    estado_ganado_macho TINYINT NOT NULL,
    CONSTRAINT fk_ganado_macho_tipo_ganado FOREIGN KEY (id_tipo_ganado) REFERENCES tipo_ganado (id_tipo_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ganado_macho_raza_ganado FOREIGN KEY (id_raza_ganado) REFERENCES raza_ganado (id_raza_ganado) ON DELETE CASCADE ON UPDATE CASCADE
); 
CREATE TABLE historial_clinico_macho (
    id_historial_clinico_macho INT PRIMARY KEY AUTO_INCREMENT,
    id_ganado_macho INT NOT NULL,
    fecha_ingreso_granja DATE NOT NULL,
    procedencia_ganado_macho VARCHAR(150) NOT NULL,
    condiciones_fisicas_recibido VARCHAR(150) NOT NULL,
    estado_historial_clinico_macho TINYINT NOT NULL,
    CONSTRAINT fk_historial_clinico_macho_ganado_macho FOREIGN KEY (id_ganado_macho) REFERENCES ganado_macho (id_ganado_macho) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE detalle_historial_clinico_macho (
    id_detalle_historial_clinico INT PRIMARY KEY AUTO_INCREMENT,
    id_historial_clinico_macho INT NOT NULL,
    fecha_registro_historial_clinico DATE NOT NULL,
    descripcion_historial_clinico TEXT NOT NULL,
    estado_detalle_historial_clinico TINYINT NOT NULL,
    CONSTRAINT fk_detalle_historial_clinico_macho_historial_clinico_macho FOREIGN KEY (id_historial_clinico_macho) REFERENCES historial_clinico_macho (id_historial_clinico_macho) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE ganado_hembra(
	id_ganado_hembra INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_ganado_hembra VARCHAR(50) NOT NULL UNIQUE,
    id_tipo_ganado INT NOT NULL,
    id_raza_ganado INT NOT NULL,
    estado_ganado_hembra TINYINT NOT NULL,
    CONSTRAINT fk_ganado_hembra_macho_tipo_ganado FOREIGN KEY (id_tipo_ganado) REFERENCES tipo_ganado (id_tipo_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ganado_hembra_raza_ganado FOREIGN KEY (id_raza_ganado) REFERENCES raza_ganado (id_raza_ganado) ON DELETE CASCADE ON UPDATE CASCADE
); 
CREATE TABLE historial_clinico_hembra (
    id_historial_clinico_hembra INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra INT NOT NULL,
    fecha_ingreso_granja DATE NOT NULL,
    procedencia_ganado_hembra VARCHAR(150) NOT NULL,
    condiciones_fisicas_recibida VARCHAR(150) NOT NULL,
    estado_historial_clinico_hembra TINYINT NOT NULL,
    CONSTRAINT fk_historial_clinico_hembra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE detalle_historial_clinico_hembra (
    id_detalle_historial_clinico_hembra INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_historial_clinico_hembra INT NOT NULL,
	novilla TINYINT NOT NULL,
    novilla_preniada TINYINT NOT NULL,
    vaca_primer_parto TINYINT NOT NULL,
    produccion_leche_maniana DOUBLE NOT NULL,
    produccion_leche_tarde DOUBLE NOT NULL,
    observaciones_adicionales VARCHAR(255),
    fecha_registro_historial_clinico DATE NOT NULL,
    descripcion_historial_clinico VARCHAR(255) NOT NULL,
    estado_detalle_historial_clinico_hembra TINYINT NOT NULL,
    CONSTRAINT fk_detalle_historial_clinico_hembra_historial_clinico_hembra FOREIGN KEY (id_historial_clinico_hembra) REFERENCES historial_clinico_hembra (id_historial_clinico_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE prenies_ganado_hembra (
    id_prenies_ganado_hembra INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra INT NOT NULL,
    fecha_concepcion DATE NOT NULL,
    toro_utilizado VARCHAR(50) NOT NULL,
    promedio_gestacion INT NOT NULL, 
    fecha_esperada_parto DATE NOT NULL,
    estado_prenies_ganado_hembra TINYINT NOT NULL,
    CONSTRAINT fk_preñes_ganado_hembra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE dieta_terneros_terneras(
	id_levante_terneros_terneras INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    descripcion_dieta TEXT NOT NULL
); 
/* TABLAS PARA PRODUCCION DE LACTEOS */
CREATE TABLE produccion_diaria_leche(
	id_produccion_diaria_leche INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    id_ganado_hembra INT NOT NULL,
    fecha_produccion_leche DATE NOT NUll, 
    produccion_maniana_leche DOUBLE NOT NULL, 
    produccion_tarde_leche DOUBLE NOT NULL, 
    total_produccion_leche DOUBLE NOT NULL, 
    estado_produccion_diaria_leche TINYINT NOT NULL,
    CONSTRAINT fk_produccion_diaria_leche_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE alimentacion_becerro(
    id_alimentacion_becerro INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    id_ganado_macho INT NOT NULL,
    fecha_alimentacion_becerro DATE NOT NULL,
    cantidad_maniana_alimentacion DOUBLE NOT NULL,
    cantidad_tarde_alimentacion DOUBLE NOT NULL,
    id_produccion_diaria_leche INT NOT NULL,
    estado_alimentacion_becerro TINYINT NOT NULL,
    CONSTRAINT fk_alimentacion_becerro_ganado_macho FOREIGN KEY (id_ganado_macho) REFERENCES ganado_macho (id_ganado_macho) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_alimentacion_becerro_produccion_diaria_leche FOREIGN KEY (id_produccion_diaria_leche) REFERENCES produccion_diaria_leche (id_produccion_diaria_leche) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE alimentacion_becerra(
    id_alimentacion_becerra INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    id_ganado_hembra INT NOT NULL,
    fecha_alimentacion_becerra DATE NOT NULL,
    cantidad_maniana_alimentacion DOUBLE NOT NULL,
    cantidad_tarde_alimentacion DOUBLE NOT NULL,
    id_produccion_diaria_leche INT NOT NULL,
    estado_alimentacion_becerra TINYINT NOT NULL,
    CONSTRAINT fk_alimentacion_becerra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_alimentacion_becerra_produccion_diaria_leche FOREIGN KEY (id_produccion_diaria_leche) REFERENCES produccion_diaria_leche (id_produccion_diaria_leche) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE dieta_ternera_ternero(
    id_dieta_ternera_ternero INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descripcion_dieta TEXT NOT NULL
);
