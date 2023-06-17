CREATE
DATABASE granja_santa_maria;

USE
granja_santa_maria;

/** TABLAS PARA USUARIOS **/
CREATE TABLE usuario
(
    id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username   VARCHAR(50)     NOT NULL UNIQUE,
    password   VARCHAR(255)    NOT NULL
);
CREATE TABLE rol
(
    id_rol     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre     VARCHAR(50)     NOT NULL UNIQUE,
    id_usuario INT             NOT NULL,
    CONSTRAINT fk_rol_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario)
);
/* TABLAS PARA UBICACIONES */
CREATE TABLE pais
(
    id_pais     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_pais VARCHAR(50)     NOT NULL UNIQUE,
    estado_pais TINYINT         NOT NULL
);
CREATE TABLE departamento
(
    id_departamento     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_departamento VARCHAR(50)     NOT NULL UNIQUE,
    id_pais             INT             NOT NULL,
    estado_departamento TINYINT         NOT NULL,
    CONSTRAINT fk_departamento_pais FOREIGN KEY (id_pais) REFERENCES pais (id_pais) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE municipio
(
    id_municipio     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_municipio VARCHAR(50)     NOT NULL,
    id_departamento  INT             NOT NULL,
    id_pais          INT             NOT NULL,
    estado_municipio TINYINT         NOT NULL,
    CONSTRAINT fk_municipio_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_municipio_pais FOREIGN KEY (id_pais) REFERENCES pais (id_pais) ON DELETE CASCADE ON UPDATE CASCADE
);
/* TABLAS PARA PERSONAS */
CREATE TABLE cliente
(
    id_cliente        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_cliente    VARCHAR(50)     NOT NULL,
    apellido_cliente  VARCHAR(50)     NOT NULL,
    telefono_cliente  VARCHAR(15)     NOT NULL,
    direccion_cliente VARCHAR(100)    NOT NULL,
    id_municipio      INT             NOT NULL,
    id_departamento   INT             NOT NULL,
    estado_cliente    TINYINT         NOT NULL,
    CONSTRAINT fk_cliente_municipio FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_cliente_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE proveedor
(
    id_proveedor        INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_proveedor    VARCHAR(50)     NOT NULL,
    apellido_proveedor  VARCHAR(50)     NOT NULL,
    telefono_proveedor  VARCHAR(15)     NOT NULL,
    direccion_proveedor VARCHAR(100)    NOT NULL,
    id_municipio        INT             NOT NULL,
    id_departamento     INT             NOT NULL,
    estado_proveedor    TINYINT         NOT NULL,
    CONSTRAINT fk_proveedor_municipio FOREIGN KEY (id_municipio) REFERENCES municipio (id_municipio) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_proveedor_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id_departamento) ON DELETE CASCADE ON UPDATE CASCADE
);
/*TABLAS PARA GANADO*/
CREATE TABLE raza_ganado
(
    id_raza_ganado     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_raza_ganado VARCHAR(50)     NOT NULL UNIQUE,
    estado_raza_ganado TINYINT         NOT NULL
);
CREATE TABLE tipo_ganado
(
    id_tipo_ganado     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_tipo_ganado VARCHAR(50)     NOT NULL UNIQUE,
    estado_tipo_ganado TINYINT         NOT NULL
);
CREATE TABLE ganado_macho
(
    id_ganado_macho     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fotografia          VARCHAR(100)    NOT NULL,
    nombre_ganado_macho VARCHAR(50)     NOT NULL UNIQUE,
    id_tipo_ganado      INT             NOT NULL,
    id_raza_ganado      INT             NOT NULL,
    estado_ganado_macho TINYINT         NOT NULL,
    CONSTRAINT fk_ganado_macho_tipo_ganado FOREIGN KEY (id_tipo_ganado) REFERENCES tipo_ganado (id_tipo_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ganado_macho_raza_ganado FOREIGN KEY (id_raza_ganado) REFERENCES raza_ganado (id_raza_ganado) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE historial_clinico_macho
(
    id_historial_clinico_macho     INT PRIMARY KEY AUTO_INCREMENT,
    id_ganado_macho                INT          NOT NULL,
    fecha_ingreso_granja           DATE         NOT NULL,
    procedencia_ganado_macho       VARCHAR(150) NOT NULL,
    condiciones_fisicas_recibido   VARCHAR(150) NOT NULL,
    estado_historial_clinico_macho TINYINT      NOT NULL,
    CONSTRAINT fk_historial_clinico_macho_ganado_macho FOREIGN KEY (id_ganado_macho) REFERENCES ganado_macho (id_ganado_macho) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE detalle_historial_clinico_macho
(
    id_detalle_historial_clinico_macho     INT PRIMARY KEY AUTO_INCREMENT,
    id_historial_clinico_macho             INT     NOT NULL,
    fecha_registro_historial_clinico       DATE    NOT NULL,
    descripcion_historial_clinico          TEXT    NOT NULL,
    estado_detalle_historial_clinico_macho TINYINT NOT NULL,
    CONSTRAINT fk_detalle_historial_clinico_macho_historial_clinico_macho FOREIGN KEY (id_historial_clinico_macho) REFERENCES historial_clinico_macho (id_historial_clinico_macho) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE ganado_hembra
(
    id_ganado_hembra     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fotografia           VARCHAR(100)    NOT NULL,
    nombre_ganado_hembra VARCHAR(50)     NOT NULL UNIQUE,
    id_tipo_ganado       INT             NOT NULL,
    id_raza_ganado       INT             NOT NULL,
    estado_ganado_hembra TINYINT         NOT NULL,
    CONSTRAINT fk_ganado_hembra_macho_tipo_ganado FOREIGN KEY (id_tipo_ganado) REFERENCES tipo_ganado (id_tipo_ganado) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_ganado_hembra_raza_ganado FOREIGN KEY (id_raza_ganado) REFERENCES raza_ganado (id_raza_ganado) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE historial_clinico_hembra
(
    id_historial_clinico_hembra          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra                     INT             NOT NULL,
    fecha_ingreso_granja                 DATE            NOT NULL,
    procedencia_ganado_hembra            VARCHAR(150)    NOT NULL,
    condiciones_fisicas_recibida         VARCHAR(150)    NOT NULL,
    descripcion_historial_clinico_hembra VARCHAR(255)    NOT NULL,
    estado_historial_clinico_hembra      TINYINT         NOT NULL,
    CONSTRAINT fk_historial_clinico_hembra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE detalle_historial_clinico_hembra
(
    id_detalle_historial_clinico_hembra     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_historial_clinico_hembra             INT             NOT NULL,
    fecha_registro_detalle_historial_clinico        DATE            NOT NULL,
    descripcion_detalle_historial_clinico           TEXT            NOT NULL,
    estado_detalle_historial_clinico_hembra TINYINT         NOT NULL,
    CONSTRAINT fk_detalle_historial_clinico_hembra_historial_clinico_hembra FOREIGN KEY (id_historial_clinico_hembra) REFERENCES historial_clinico_hembra (id_historial_clinico_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE prenies_ganado_hembra
(
    id_prenies_ganado_hembra     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra             INT             NOT NULL,
    fecha_prenies                DATE            NOT NULL,
    promedio_gestacion           INT             NOT NULL,
    estado_prenies_ganado_hembra TINYINT         NOT NULL,
    CONSTRAINT fk_preñes_ganado_hembra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);

/* TABLAS PARA PRODUCCION DE LACTEOS */
CREATE TABLE produccion_diaria_leche
(
    id_produccion_diaria_leche     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra               INT             NOT NULL,
    fecha_produccion_leche         DATE            NOT NUll,
    produccion_maniana_leche       DOUBLE          NOT NULL,
    produccion_tarde_leche         DOUBLE          NOT NULL,
    total_produccion_leche         DOUBLE          NOT NULL,
    estado_produccion_diaria_leche TINYINT         NOT NULL,
    CONSTRAINT fk_produccion_diaria_leche_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE alimentacion_becerro
(
    id_alimentacion_becerro       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_macho               INT             NOT NULL,
    fecha_alimentacion_becerro    DATE            NOT NULL,
    cantidad_maniana_alimentacion DOUBLE          NOT NULL,
    cantidad_tarde_alimentacion   DOUBLE          NOT NULL,
    total_alimentacion_becerro    DOUBLE          NOT NULL,
    id_produccion_diaria_leche    INT             NOT NULL,
    estado_alimentacion_becerro   TINYINT         NOT NULL,
    CONSTRAINT fk_alimentacion_becerro_ganado_macho FOREIGN KEY (id_ganado_macho) REFERENCES ganado_macho (id_ganado_macho) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_alimentacion_becerro_produccion_diaria_leche FOREIGN KEY (id_produccion_diaria_leche) REFERENCES produccion_diaria_leche (id_produccion_diaria_leche) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE alimentacion_becerra
(
    id_alimentacion_becerra       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra              INT             NOT NULL,
    fecha_alimentacion_becerra    DATE            NOT NULL,
    cantidad_maniana_alimentacion DOUBLE          NOT NULL,
    cantidad_tarde_alimentacion   DOUBLE          NOT NULL,
    total_alimentacion_becerra    DOUBLE          NOT NULL,
    id_produccion_diaria_leche    INT             NOT NULL,
    estado_alimentacion_becerra   TINYINT         NOT NULL,
    CONSTRAINT fk_alimentacion_becerra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_alimentacion_becerra_produccion_diaria_leche FOREIGN KEY (id_produccion_diaria_leche) REFERENCES produccion_diaria_leche (id_produccion_diaria_leche) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE dieta_ternera_ternero
(
    id_dieta_ternera_ternero     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    descripcion_dieta            TEXT            NOT NULL,
    estado_dieta_ternera_ternero TINYINT         NOT NULL
);
CREATE TABLE diario_gasto_granja
(
    id_diario_gasto_granja    INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    fecha_gasto               DATE            NOT NULL,
    unidades_adquiridas       INT             NOT NULL,
    detalle_inversion         VARCHAR(50)     NOT NULL,
    valor_unitario            DOUBLE          NOT NULL,
    valor_total               DOUBLE          NOT NULL,
    estado_diario_gasto_ganja TINYINT         NOT NULL
);
/*TABLAS PARA VENTAS*/
CREATE TABLE producto
(
    id_producto          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_producto      VARCHAR(50)     NOT NULL,
    descripcion_producto VARCHAR(75)     NOT NULL,
    estado_producto      TINYINT         NOT NULL
);
CREATE TABLE medida_producto
(
    id_medida_producto     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_medida_producto VARCHAR(50)     NOT NULL,
    estado_medida_producto TINYINT         NOT NULL
);
CREATE TABLE contenedor_producto
(
    id_contenedor_producto     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_contenedor_producto VARCHAR(50)     NOT NULL,
    estado_contenedor_producto TINYINT         NOT NULL
);
CREATE TABLE descripcion_producto
(
    id_descripcion_producto     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nombre_descripcion_producto VARCHAR(50)     NOT NULL,
    estado_descripcion_producto TINYINT         NOT NULL
);
CREATE TABLE detalle_producto
(
    id_detalle_producto     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_producto             INT             NOT NULL,
    id_medida_producto      INT             NOT NULL,
    id_contenedor_producto  INT             NOT NULL,
    id_descripcion_producto INT             NOT NULL,
    estado_detalle_producto INT             NOT NULL,
    CONSTRAINT fk_detalle_producto_producto FOREIGN KEY (id_producto) REFERENCES producto (id_producto),
    CONSTRAINT fk_detalle_producto_medida_producto FOREIGN KEY (id_medida_producto) REFERENCES medida_producto (id_medida_producto),
    CONSTRAINT fk_detalle_producto_contenedor_producto FOREIGN KEY (id_contenedor_producto) REFERENCES contenedor_producto (id_contenedor_producto),
    CONSTRAINT fk_detalle_producto_descripcion_producto FOREIGN KEY (id_descripcion_producto) REFERENCES descripcion_producto (id_descripcion_producto)
);
CREATE TABLE inventario_producto
(
    id_inventario_producto      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_detalle_producto         INT             NOT NULL,
    fecha_inventario_producto   DATE            NOT NULL,
    cantidad_ingresada_producto INT             NOT NULL,
    cantidad_entrada_producto   INT             NOT NULL,
    cantidad_salida_producto    INT             NOT NULL,
    cantidad_final_producto     INT             NOT NULL,
    fecha_ingreso               DATE            NOT NULL,
    cantidad_vendida_hasta_hoy  INT             NOT NULL,
    estado_inventario_producto  TINYINT         NOT NULL,
    CONSTRAINT fk_inventario_detalle_producto FOREIGN KEY (id_detalle_producto) REFERENCES detalle_producto (id_detalle_producto) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE venta_producto
(
    id_venta_producto      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_cliente             INT             NOT NULL,
    id_inventario_producto INT             NOT NULL,
    fecha_venta_producto   DATE            NOT NULL,
    cantidad_producto      INT             NOT NULL,
    precio_por_unidad      DOUBLE          NOT NULL,
    total_precio_producto  DOUBLE          NOT NULL,
    descuento_producto     DOUBLE          NOT NULL,
    estado_venta_producto  TINYINT         NOT NULL,
    CONSTRAINT fk_venta_producto_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_venta_producto_inventario_producto FOREIGN KEY (id_inventario_producto) REFERENCES inventario_producto (id_inventario_producto) ON DELETE CASCADE ON UPDATE CASCADE
);
