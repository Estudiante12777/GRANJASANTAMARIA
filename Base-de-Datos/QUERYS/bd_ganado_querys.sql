USE granja_santa_maria; 

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
CREATE TABLE ganado (
    id_ganado INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_ganado VARCHAR(50) NOT NULL UNIQUE,
    peso_ganado DOUBLE NOT NULL,
    fecha_ingreso_ganado DATE NOT NULL,
    id_tipo_ganado INT NOT NULL,
    id_raza_ganado INT NOT NULL,
    estado_ganado TINYINT NOT NULL,
    CONSTRAINT fk_ganado_tipo_ganado FOREIGN KEY (id_tipo_ganado) REFERENCES tipo_ganado (id_tipo_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ganado_raza_ganado FOREIGN KEY (id_raza_ganado) REFERENCES raza_ganado (id_raza_ganado) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE alimentacion_becerro(
	id_alimentacion_becerro INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    id_ganado INT NOT NULL,
    fecha_alimentacion_becerro DATE NOT NULL,
	cantidad_maniana_alimentacion DOUBLE NOT NULL,
	cantidad_tarde_alimentacion DOUBLE NOT NULL,
	id_produccion_diaria_leche INT NOT NULL,
	estado_alimentacion_becerro TINYINT NOT NULL,
    CONSTRAINT fk_alimentacion_becerro_ganado FOREIGN KEY (id_ganado) REFERENCES ganado (id_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_alimentacion_becerro_produccion_diaria_leche FOREIGN KEY (id_produccion_diaria_leche) REFERENCES produccion_diaria_leche (id_produccion_diaria_leche) ON DELETE CASCADE ON UPDATE CASCADE
);
