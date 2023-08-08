CREATE DATABASE granja_santa_maria;

USE granja_santa_maria;

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
    fecha_nacimiento    DATE            NOT NULL,
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
    fecha_nacimiento     DATE            NOT NULL,
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
    id_detalle_historial_clinico_hembra      INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_historial_clinico_hembra              INT             NOT NULL,
    fecha_registro_detalle_historial_clinico DATE            NOT NULL,
    descripcion_detalle_historial_clinico    TEXT            NOT NULL,
    estado_detalle_historial_clinico_hembra  TINYINT         NOT NULL,
    CONSTRAINT fk_detalle_historial_clinico_hembra_historial_clinico_hembra FOREIGN KEY (id_historial_clinico_hembra) REFERENCES historial_clinico_hembra (id_historial_clinico_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE prenies_ganado_hembra
(
    id_prenies_ganado_hembra     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra             INT             NOT NULL,
    fecha_prenies                DATE            NOT NULL,
    promedio_gestacion           INT             NOT NULL,
    fecha_concepcion             DATE            NOT NULL,
    estado_prenies_ganado_hembra TINYINT         NOT NULL,
    CONSTRAINT fk_preñes_ganado_hembra_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
/* TABLAS PARA PRODUCCION DE LACTEOS */
CREATE TABLE produccion_diaria_leche
(
    id_produccion_diaria_leche     INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_hembra               INT             NOT NULL,
    fecha_produccion_leche         DATE            NOT NUll,
    produccion_maniana_leche       DECIMAL(10, 2)  NOT NULL,
    produccion_tarde_leche         DECIMAL(10, 2)  NOT NULL,
    total_produccion_leche         DECIMAL(10, 2)  NOT NULL,
    estado_produccion_diaria_leche TINYINT         NOT NULL,
    CONSTRAINT fk_produccion_diaria_leche_ganado_hembra FOREIGN KEY (id_ganado_hembra) REFERENCES ganado_hembra (id_ganado_hembra) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE alimentacion_becerro
(
    id_alimentacion_becerro       INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_ganado_macho               INT             NOT NULL,
    fecha_alimentacion_becerro    DATE            NOT NULL,
    cantidad_maniana_alimentacion DECIMAL(10, 2)  NOT NULL,
    cantidad_tarde_alimentacion   DECIMAL(10, 2)  NOT NULL,
    total_alimentacion_becerro    DECIMAL(10, 2)  NOT NULL,
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
    cantidad_maniana_alimentacion DECIMAL(10, 2)  NOT NULL,
    cantidad_tarde_alimentacion   DECIMAL(10, 2)  NOT NULL,
    total_alimentacion_becerra    DECIMAL(10, 2)  NOT NULL,
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
    valor_unitario            DECIMAL(10, 2)  NOT NULL,
    valor_total               DECIMAL(10, 2)  NOT NULL,
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
    cantidad_salida_producto    INT             NOT NULL,
    cantidad_final_producto     INT             NOT NULL,
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
    precio_por_unidad      DECIMAL(10, 2)  NOT NULL,
    total_precio_producto  DECIMAL(10, 2)  NOT NULL,
    descuento_producto     DECIMAL(10, 2)  NOT NULL,
    estado_venta_producto  TINYINT         NOT NULL,
    CONSTRAINT fk_venta_producto_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_venta_producto_inventario_producto FOREIGN KEY (id_inventario_producto) REFERENCES inventario_producto (id_inventario_producto) ON DELETE CASCADE ON UPDATE CASCADE
);

/*
    USUARIO PREDETERMINADO

    Usuario: Wilmer
    Contraseña: administrador-granja-santa-maria
    Rol: ROLE_ADMINISTRADOR
*/

INSERT INTO usuario (username, password) VALUES ('Wilmer', '$2a$10$MT26qZSAzg3u3TMEwhUy2eA2vp74mzdzOGPU2cm5QFYYZZF95k262');
INSERT INTO rol (nombre, id_usuario) VALUES ('ROLE_ADMINISTRADOR', 1);

/*
    DATOS PARA LAS UBICACIONES
    1. PAIS.
    2. DEPARTAMENTO.
    3. MUNICIPIO.
*/

INSERT INTO pais (nombre_pais, estado_pais) VALUES ('Guatemala', 1);

INSERT INTO departamento (nombre_departamento, id_pais, estado_departamento)
VALUES ('Alta Verapaz', 1, 1), ('Baja Verapaz', 1, 1), ('Chimaltenango', 1, 1), ('Chiquimula', 1, 1), ('El Progreso', 1, 1), ('Escuintla', 1, 1),
('Guatemala', 1, 1), ('Huehuetenango', 1, 1), ('Izabal', 1, 1), ('Jalapa', 1, 1), ('Jutiapa', 1, 1), ('Petén', 1, 1),
('Quetzaltenango', 1, 1), ('Quiché', 1, 1), ('Retalhuleu', 1, 1), ('Sacatepéquez', 1, 1), ('San Marcos', 1, 1), ('Santa Rosa', 1, 1),
('Sololá', 1, 1), ('Suchitepéquez', 1, 1), ('Totonicapán', 1, 1), ('Zacapa', 1, 1);

INSERT INTO municipio (nombre_municipio, id_departamento, id_pais, estado_municipio)
VALUES ('Cahabón', 1, 1, 1), ('Chahal', 1, 1, 1), ('Chisec', 1, 1, 1), ('Cobán', 1, 1, 1), ('Fray Bartolomé de las Casas', 1, 1, 1), ('Lanquín', 1, 1, 1),
('Panzós', 1, 1, 1), ('Raxruha', 1, 1, 1), ('San Cristóbal Verapaz', 1, 1, 1), ('San Juan Chamelco', 1, 1, 1), ('San Pedro Carchá', 1, 1, 1), ('Santa Catalina La Tinta', 1, 1, 1),
('Santa Cruz Verapaz', 1, 1, 1), ('Santa María Cahabón', 1, 1, 1), ('Senahú', 1, 1, 1), ('Tamahú', 1, 1, 1), ('Tucurú', 1, 1, 1), ('Cubulco', 2, 1, 1),
('Granados', 2, 1, 1), ('Purulhá', 2, 1, 1), ('Rabinal', 2, 1, 1), ('Salamá', 2, 1, 1), ('San Jerónimo', 2, 1, 1), ('San Miguel Chicaj', 2, 1, 1),
('Santa Cruz el Chol', 2, 1, 1), ('Acatenango', 3, 1, 1), ('Chimaltenango', 3, 1, 1), ('El Tejar', 3, 1, 1), ('Parramos', 3, 1, 1), ('Patzicía', 3, 1, 1),
('Patzún', 3, 1, 1), ('Pochuta', 3, 1, 1), ('San Andrés Itzapa', 3, 1, 1), ('San Juan Comalapa', 3, 1, 1), ('San José Poaquíl', 3, 1, 1), ('San Martín Jilotepeque', 3, 1, 1),
('San Pedro Yepocapa', 3, 1, 1), ('Santa Apolonia', 3, 1, 1), ('Santa Cruz Balanyá', 3, 1, 1), ('Tecpán Guatemala', 3, 1, 1), ('Zaragoza', 3, 1, 1), ('Camotán', 4, 1, 1),
('Chiquimula', 4, 1, 1), ('Concepción Las Minas', 4, 1, 1), ('Esquipulas', 4, 1, 1), ('Ipala', 4, 1, 1), ('Jocotán', 4, 1, 1), ('Olopa', 4, 1, 1),
('Quezaltepeque', 4, 1, 1), ('San Jacinto', 4, 1, 1), ('San José la Arada', 4, 1, 1), ('San Juan Ermita', 4, 1, 1), ('El Jícaro', 5, 1, 1), ('Guastatoya', 5, 1, 1),
('Morazán', 5, 1, 1), ('San Agustín Acasaguastlán', 5, 1, 1), ('San Antonio La Paz', 5, 1, 1), ('San Cristóbal Acasaguastlán', 5, 1, 1), ('Sanarate', 5, 1, 1), ('Sansare', 5, 1, 1),
('Escuintla', 6, 1, 1), ('Guanagazapa', 6, 1, 1), ('Iztapa', 6, 1, 1), ('La Democracia', 6, 1, 1), ('La Gomera', 6, 1, 1), ('Masagua', 6, 1, 1),
('Nueva Concepción', 6, 1, 1), ('Palín', 6, 1, 1), ('San José', 6, 1, 1), ('San Vicente Pacaya', 6, 1, 1), ('Santa Lucía Cotzumalguapa', 6, 1, 1), ('Sipacate', 6, 1, 1),
('Siquinalá', 6, 1, 1), ('Tiquisate', 6, 1, 1), ('Amatitlán', 7, 1, 1), ('Chinautla', 7, 1, 1), ('Chuarrancho', 7, 1, 1), ('Fraijanes', 7, 1, 1),
('Guatemala', 7, 1, 1), ('Mixco', 7, 1, 1), ('Palencia', 7, 1, 1), ('San José del Golfo', 7, 1, 1), ('San José Pinula', 7, 1, 1), ('San Juan Sacatepéquez', 7, 1, 1),
('San Miguel Petapa', 7, 1, 1), ('San Pedro Ayampuc', 7, 1, 1), ('San Pedro Sacatepéquez', 7, 1, 1), ('San Raymundo', 7, 1, 1), ('Santa Catarina Pinula', 7, 1, 1), ('Villa Canales', 7, 1, 1),
('Villa Nueva', 7, 1, 1), ('Aguacatán', 8, 1, 1), ('Chahal', 8, 1, 1), ('Chiantla', 8, 1, 1), ('Colotenango', 8, 1, 1), ('Concepción Huista', 8, 1, 1),
('Cuilco', 8, 1, 1), ('Fray Bartolomé de las Casas', 8, 1, 1), ('Huehuetenango', 8, 1, 1), ('Jacaltenango', 8, 1, 1), ('La Democracia', 8, 1, 1), ('La Libertad', 8, 1, 1),
('Malacatancito', 8, 1, 1), ('Nentón', 8, 1, 1), ('San Antonio Huista', 8, 1, 1), ('San Gaspar Ixchil', 8, 1, 1), ('San Ildefonso Ixtahuacán', 8, 1, 1), ('San Juan Atitán', 8, 1, 1),
('San Juan Ixcoy', 8, 1, 1), ('San Mateo Ixtatán', 8, 1, 1), ('San Miguel Acatán', 8, 1, 1), ('San Pedro Necta', 8, 1, 1), ('San Pedro Soloma', 8, 1, 1), ('San Rafael La Independencia', 8, 1, 1),
('San Rafael Petzal', 8, 1, 1), ('San Sebastián Coatán', 8, 1, 1), ('San Sebastián Huehuetenango', 8, 1, 1), ('Santa Ana Huista', 8, 1, 1), ('Santa Bárbara', 8, 1, 1), ('Santa Cruz Barillas', 8, 1, 1),
('Santa Eulalia', 8, 1, 1), ('Santiago Chimaltenango', 8, 1, 1), ('Tectitán', 8, 1, 1), ('Todos Santos Cuchumatán', 8, 1, 1), ('El Estor', 9, 1, 1), ('Livingston', 9, 1, 1),
('Los Amates', 9, 1, 1), ('Morales', 9, 1, 1), ('Puerto Barrios', 9, 1, 1), ('Jalapa', 10, 1, 1), ('Mataquescuintla', 10, 1, 1), ('Monjas', 10, 1, 1),
('San Carlos Alzatate', 10, 1, 1), ('San Luis Jilotepeque', 10, 1, 1), ('San Manuel Chaparrón', 10, 1, 1), ('San Pedro Pinula', 10, 1, 1), ('Agua Blanca', 11, 1, 1), ('Asunción Mita', 11, 1, 1),
('Atescatempa', 11, 1, 1), ('Comapa', 11, 1, 1), ('Conguaco', 11, 1, 1), ('El Adelanto', 11, 1, 1), ('El Progreso', 11, 1, 1), ('Jalpatagua', 11, 1, 1),
('Jerez', 11, 1, 1), ('Jutiapa', 11, 1, 1), ('Moyuta', 11, 1, 1), ('Pasaco', 11, 1, 1), ('Quesada', 11, 1, 1), ('San José Acatempa', 11, 1, 1),
('Santa Catarina Mita', 11, 1, 1), ('Yupiltepeque', 11, 1, 1), ('Zapotitlán', 11, 1, 1), ('Dolores', 12, 1, 1), ('Flores', 12, 1, 1), ('La Libertad', 12, 1, 1),
('Melchor de Mencos', 12, 1, 1), ('Poptún', 12, 1, 1), ('San Andrés', 12, 1, 1), ('San Benito', 12, 1, 1), ('San Francisco', 12, 1, 1), ('San José', 12, 1, 1),
('San Luis', 12, 1, 1), ('Santa Ana', 12, 1, 1), ('Sayaxché', 12, 1, 1), ('Las Cruces', 12, 1, 1), ('Almolonga', 13, 1, 1), ('Cabricán', 13, 1, 1),
('Cajolá', 13, 1, 1), ('Cantel', 13, 1, 1), ('Coatepeque', 13, 1, 1), ('Colomba Costa Cuca', 13, 1, 1), ('Concepción Chiquirichapa', 13, 1, 1), ('El Palmar', 13, 1, 1),
('Flores Costa Cuca', 13, 1, 1), ('Génova', 13, 1, 1), ('Huitán', 13, 1, 1), ('La Esperanza', 13, 1, 1), ('Olintepeque', 13, 1, 1), ('Palestina de Los Altos', 13, 1, 1),
('Quetzaltenango', 13, 1, 1), ('Salcajá', 13, 1, 1), ('San Carlos Sija', 13, 1, 1), ('San Francisco La Unión', 13, 1, 1), ('San Juan Ostuncalco', 13, 1, 1), ('San Martín Sacatepéquez', 13, 1, 1),
('San Mateo', 13, 1, 1), ('San Miguel Sigüilá', 13, 1, 1), ('Sibilia', 13, 1, 1), ('Zunil', 13, 1, 1), ('Canillá', 14, 1, 1), ('Chajul', 14, 1, 1),
('Chicamán', 14, 1, 1), ('Chiché', 14, 1, 1), ('Chichicastenango', 14, 1, 1), ('Chinique', 14, 1, 1), ('Cunén', 14, 1, 1), ('Ixcán', 14, 1, 1),
('Joyabaj', 14, 1, 1), ('Nebaj', 14, 1, 1), ('Pachalum', 14, 1, 1), ('Patzité', 14, 1, 1), ('Sacapulas', 14, 1, 1), ('San Andrés Sajcabajá', 14, 1, 1),
('San Antonio Ilotenango', 14, 1, 1), ('San Bartolomé Jocotenango', 14, 1, 1), ('San Juan Cotzal', 14, 1, 1), ('San Pedro Jocopilas', 14, 1, 1), ('Santa Cruz del Quiché', 14, 1, 1), ('Uspantán', 14, 1, 1),
('Zacualpa', 14, 1, 1), ('Champerico', 15, 1, 1), ('El Asintal', 15, 1, 1), ('Nuevo San Carlos', 15, 1, 1), ('Retalhuleu', 15, 1, 1), ('San Andrés Villa Seca', 15, 1, 1),
('San Felipe', 15, 1, 1), ('San Martín Zapotitlán', 15, 1, 1), ('San Sebastián', 15, 1, 1), ('Santa Cruz Muluá', 15, 1, 1), ('Alotenango', 16, 1, 1), ('Antigua Guatemala', 16, 1, 1),
('Ciudad Vieja', 16, 1, 1), ('Jocotenango', 16, 1, 1), ('Magdalena Milpas Altas', 16, 1, 1), ('Pastores', 16, 1, 1), ('San Antonio Aguas Calientes', 16, 1, 1), ('San Bartolomé Milpas Altas', 16, 1, 1),
('San Lucas Sacatepéquez', 16, 1, 1), ('San Miguel Dueñas', 16, 1, 1), ('Santa Catarina Barahona', 16, 1, 1), ('Santa Lucía Milpas Altas', 16, 1, 1), ('Santa María de Jesús', 16, 1, 1), ('Santiago Sacatepéquez', 16, 1, 1),
('Santo Domingo Xenacoj', 16, 1, 1), ('Sumpango', 16, 1, 1), ('Ayutla', 17, 1, 1), ('Catarina', 17, 1, 1), ('Comitancillo', 17, 1, 1), ('Concepción Tutuapa', 17, 1, 1),
('El Quetzal', 17, 1, 1), ('El Rodeo', 17, 1, 1), ('El Tumbador', 17, 1, 1), ('Esquipulas Palo Gordo', 17, 1, 1), ('Ixchiguán', 17, 1, 1), ('La Blanca', 17, 1, 1),
('La Reforma', 17, 1, 1), ('Malacatán', 17, 1, 1), ('Nuevo Progreso', 17, 1, 1), ('Ocós', 17, 1, 1), ('Pajapita', 17, 1, 1), ('Río Blanco', 17, 1, 1),
('San Antonio Sacatepéquez', 17, 1, 1), ('San Cristóbal Cucho', 17, 1, 1), ('San José Ojetenam', 17, 1, 1), ('San Lorenzo', 17, 1, 1), ('San Marcos', 17, 1, 1), ('San Miguel Ixtahuacán', 17, 1, 1),
('San Pablo', 17, 1, 1), ('San Pedro Sacatepéquez', 17, 1, 1), ('San Rafael Pie de la Guesta', 17, 1, 1), ('Sibinal', 17, 1, 1), ('Sipacapa', 17, 1, 1), ('Tacaná', 17, 1, 1),
('Tajumulco', 17, 1, 1), ('Tejutla', 17, 1, 1), ('Barberena', 18, 1, 1), ('Casillas', 18, 1, 1), ('Chiquimulilla', 18, 1, 1), ('Cuilapa', 18, 1, 1),
('Guazacapán', 18, 1, 1), ('Nueva Santa Rosa', 18, 1, 1), ('Oratorio', 18, 1, 1), ('Pueblo Nuevo Viñas', 18, 1, 1), ('San Juan Tecuaco', 18, 1, 1), ('San Rafael Las Flores', 18, 1, 1),
('Santa Cruz Naranjo', 18, 1, 1), ('Santa María Ixhuatán', 18, 1, 1), ('Santa Rosa de Lima', 18, 1, 1), ('Taxisco', 18, 1, 1), ('Concepción', 19, 1, 1), ('Nahualá', 19, 1, 1),
('Panajachel', 19, 1, 1), ('San Andrés Semetabaj', 19, 1, 1), ('San Antonio Palopó', 19, 1, 1), ('San José Chacayá', 19, 1, 1), ('San Juan La Laguna', 19, 1, 1), ('San Lucas Tolimán', 19, 1, 1),
('San Marcos La Laguna', 19, 1, 1), ('San Pablo La Laguna', 19, 1, 1), ('San Pedro La Laguna', 19, 1, 1), ('Santa Catarina Ixtahuacán', 19, 1, 1), ('Santa Catarina Palopó', 19, 1, 1), ('Santa Clara La Laguna', 19, 1, 1),
('Santa Cruz La Laguna', 19, 1, 1), ('Santa Lucía Utatlán', 19, 1, 1), ('Santa María Visitación', 19, 1, 1), ('Santiago Atitlán', 19, 1, 1), ('Sololá', 19, 1, 1), ('Chicacao', 20, 1, 1),
('Cuyotenango', 20, 1, 1), ('Mazatenango', 20, 1, 1), ('Patulul', 20, 1, 1), ('Pueblo Nuevo', 20, 1, 1), ('Río Bravo', 20, 1, 1), ('Samayac', 20, 1, 1),
('San Antonio Suchitepéquez', 20, 1, 1), ('San Bernardino', 20, 1, 1), ('San Francisco Zapotitlán', 20, 1, 1), ('San Gabriel', 20, 1, 1), ('San José El Ídolo', 20, 1, 1), ('San José La Máquina', 20, 1, 1),
('San Juan Bautista', 20, 1, 1), ('San Lorenzo', 20, 1, 1), ('San Miguel Panán', 20, 1, 1), ('San Pablo Jocopilas', 20, 1, 1), ('Santa Bárbara', 20, 1, 1), ('Santo Domingo Suchitepéquez', 20, 1, 1),
('Santo Tomás La Unión', 20, 1, 1), ('Zunilito', 20, 1, 1), ('Momostenango', 21, 1, 1), ('San Andrés Xecul', 21, 1, 1), ('San Bartolo', 21, 1, 1), ('San Cristóbal Totonicapán', 21, 1, 1),
('San Francisco El Alto', 21, 1, 1),  ('Santa Lucía La Reforma', 21, 1, 1), ('Santa María Chiquimula', 21, 1, 1), ('Totonicapán', 21, 1, 1), ('Cabañas', 22, 1, 1), ('Estanzuela', 22, 1, 1),
('Gualán', 22, 1, 1), ('Huité', 22, 1, 1), ('La Unión', 22, 1, 1), ('Río Hondo', 22, 1, 1), ('San Diego', 22, 1, 1), ('San Jorge', 22, 1, 1),
('Teculután', 22, 1, 1), ('Usumatlán', 22, 1, 1), ('Zacapa', 22, 1, 1);

