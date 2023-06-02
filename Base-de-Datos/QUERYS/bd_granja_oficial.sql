CREATE DATABASE granja_santa_maria; 

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
/*TABLAS PARA VENTAS*/
CREATE TABLE producto(
	id_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_producto VARCHAR(50) NOT NULL ,
    descripcion_producto VARCHAR(75) NOT NULL, 
    estado_producto TINYINT NOT NULL
); 
CREATE TABLE medida_producto(
  id_medida_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nombre_medida_producto VARCHAR(50) NOT NULL, 
  estado_medida_producto TINYINT NOT NULL
);
CREATE TABLE contenedor_producto(
  id_contenedor_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nombre_contenedor_producto VARCHAR(50) NOT NULL, 
  estado_contenedor_producto TINYINT NOT NULL
);
CREATE TABLE descripcion_producto(
  id_descripcion_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nombre_descripcion_producto VARCHAR(50) NOT NULL, 
  estado_descripcion_producto TINYINT NOT NULL
);
CREATE TABLE detalle_producto(
  id_detalle_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_producto INT NOT NULL,
  id_medida_producto INT NOT NULL,
  id_contenedor_producto INT NOT NULL,
  id_descripcion_producto INT NOT NULL,
  estado_detalle_producto INT NOT NULL,
  CONSTRAINT fk_detalle_producto_producto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
  CONSTRAINT fk_detalle_producto_medida_producto FOREIGN KEY (id_medida_producto) REFERENCES medida_producto(id_medida_producto),
  CONSTRAINT fk_detalle_producto_contenedor_producto FOREIGN KEY (id_contenedor_producto) REFERENCES contenedor_producto(id_contenedor_producto),
  CONSTRAINT fk_detalle_producto_descripcion_producto FOREIGN KEY (id_descripcion_producto) REFERENCES descripcion_producto(id_descripcion_producto)
);
CREATE TABLE venta_producto (
    id_venta_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fecha_venta_producto DATE NOT NULL,
    id_cliente INT NOT NULL,
    id_inventario_producto INT NOT NULL,
    estado_venta_producto TINYINT NOT NULL,
    CONSTRAINT fk_venta_producto_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_venta_producto_inventario_producto FOREIGN KEY (id_inventario_producto) REFERENCES inventario_producto (id_inventario_producto) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE detalle_venta_producto (
    id_detalle_venta_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_venta_producto INT NOT NULL,
    id_detalle_producto INT NOT NULL,
    cantidad_producto INT NOT NULL,
    precio_por_unidad DOUBLE NOT NULL,
    total_precio_producto DOUBLE NOT NULL,
    descuento_producto DOUBLE NOT NULL,
    estado_detalle_venta_producto TINYINT NOT NULL,
    CONSTRAINT fk_detalle_venta_venta_producto FOREIGN KEY (id_venta_producto) REFERENCES venta_producto (id_venta_producto) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_detalle_venta_detalle_producto FOREIGN KEY (id_detalle_producto) REFERENCES detalle_producto (id_detalle_producto) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE inventario_producto (
  id_inventario_producto INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  id_detalle_producto INT NOT NULL,
  fecha_inventario_producto DATE NOT NULL,
  cantidad_ingresada_producto INT NOT NULL,
  cantidad_entrada_producto INT NOT NULL,
  cantidad_salida_producto INT NOT NULL,
  cantidad_final_producto INT NOT NULL,
  fecha_ingreso DATE NOT NULL,
  cantidad_vendida_hasta_hoy INT NOT NULL,
  estado_inventario_producto TINYINT NOT NULL,
  CONSTRAINT fk_inventario_detalle_producto FOREIGN KEY (id_detalle_producto) REFERENCES detalle_producto (id_detalle_producto) ON DELETE CASCADE ON UPDATE CASCADE
);
/* TABLAS PARA PERSONAS */
CREATE TABLE cliente(
	id_cliente INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_cliente VARCHAR(50) NOT NULL, 
    apellido_cliente VARCHAR(50) NOT NULL, 
    telefono_cliente VARCHAR(15) NOT NULL,
    direccion_cliente VARCHAR(100) NOT NULL,
    id_municipio INT NOT NULL, 
    id_departamento INT NOT NULL,
    estado_cliente TINYINT NOT NULL,
    CONSTRAINT fk_cliente_municipio FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_cliente_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE proveedor(
	id_proveedor INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    nombre_proveedor VARCHAR(50) NOT NULL, 
    apellido_proveedor VARCHAR(50) NOT NULL, 
    telefono_proveedor VARCHAR(15) NOT NULL, 
    direccion_proveedor VARCHAR(100) NOT NULL, 
    id_municipio INT NOT NULL, 
    id_departamento INT NOT NULL, 
    estado_proveedor TINYINT NOT NULL, 
    CONSTRAINT fk_proveedor_municipio FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_proveedor_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE
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