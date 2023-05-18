CREATE DATABASE granja_santa_maria;

USE granja_santa_maria; 

/*
	CREACION DE TABLAS 
*/

/**GANADO**/
CREATE TABLE categoria_ganado(
	id_categoria_ganado INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_categoria_ganado VARCHAR(100) NOT NULL UNIQUE, 
    estado_categoria_ganado TINYINT NOT NULL
); 

CREATE TABLE tipo_ganado(
	id_tipo_ganado INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_tipo_ganado VARCHAR(50) NOT NULL UNIQUE, 
    estado_tipo_ganado TINYINT NOT NULL
); 

CREATE TABLE ganado (
    id_ganado INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_ganado VARCHAR(100) NOT NULL UNIQUE,
    peso DOUBLE NOT NULL,
    fecha_ingreso_granja DATE NOT NULL,
    id_tipo_ganado INT NOT NULL,
    id_categoria_ganado INT NOT NULL,
    estado_ganado TINYINT NOT NULL,
    CONSTRAINT fk_ganado_tipo_ganado FOREIGN KEY (id_tipo_ganado) REFERENCES tipo_ganado (id_tipo_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ganado_categoria_ganado FOREIGN KEY (id_categoria_ganado) REFERENCES categoria_ganado (id_categoria_ganado) ON DELETE CASCADE ON UPDATE CASCADE
); 

/*
	OTROS SCRIPTS
*/
SELECT * FROM categoria_ganado;
