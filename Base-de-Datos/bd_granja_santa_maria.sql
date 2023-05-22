CREATE DATABASE granja_santa_maria;

USE granja_santa_maria; 

/* CREACION DE TABLAS */

/** USUARIOS **/
CREATE TABLE rol(
	id_rol INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre VARCHAR(50) NOT NULL UNIQUE, 
    id_usuario INT NOT NULL, 
    CONSTRAINT fk_rol_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
); 
CREATE TABLE usuario(
	id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    username VARCHAR(50) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL
); 

/* TABLAS PARA PERSONAS */
CREATE TABLE cliente(
	id_cliente INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_cliente VARCHAR(50) NOT NULL, 
    apellido_cliente VARCHAR(50) NOT NULL, 
    telefono VARCHAR(15) NOT NULL,
    direccion_cliente VARCHAR(100) NOT NULL,
    id_municipio INT NOT NULL, 
    id_departamento INT NOT NULL,
    estado_cliente TINYINT NOT NULL,
    CONSTRAINT fk_cliente_municipio FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_cliente_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE
);

/* TABLAS PARA UBICACIONES */
CREATE TABLE pais(
	id_pais INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_pais VARCHAR(50) NOT NULL UNIQUE, 
    estado_pais TINYINT NOT NULL
); 
CREATE TABLE departamento(
	id_departamento INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_departamento VARCHAR(50) NOT NULL UNIQUE, 
    id_pais INT NOT NULL,
    estado_departamento TINYINT NOT NULL,
    CONSTRAINT fk_departamento_pais FOREIGN KEY (id_pais) REFERENCES pais (id_pais) ON DELETE CASCADE ON UPDATE CASCADE
); 
CREATE TABLE municipio(
	id_municipio INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_municipio VARCHAR(50) NOT NULL,
    id_departamento INT NOT NULL, 
    id_pais INT NOT NULL,
    estado_municipio TINYINT NOT NULL,
    CONSTRAINT fk_municipio_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_municipio_pais FOREIGN KEY (id_pais) REFERENCES pais (id_pais) ON DELETE CASCADE ON UPDATE CASCADE
); 

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

/* TABLAS PARA PRODUCCION DE LACTEOS */
CREATE TABLE produccion_diaria_leche(
	id_produccion_diaria_leche INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    fecha_produccion_leche DATE NOT NUll, 
    produccion_maniana_leche DOUBLE NOT NULL, 
    produccion_tarde_leche DOUBLE NOT NULL, 
    id_ganado INT NOT NULL,
    estado_produccion_diaria_leche TINYINT NOT NULL,
    CONSTRAINT fk_produccion_diaria_leche_ganado FOREIGN KEY (id_ganado) REFERENCES ganado (id_ganado) ON DELETE CASCADE ON UPDATE CASCADE
);

/*
	OTROS SCRIPTS
*/
SELECT * FROM categoria_ganado;
