CREATE DATABASE prueba; 

USE prueba; 

CREATE TABLE productos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  descripcion VARCHAR(255)
);
CREATE TABLE variantes_presentacion (
  id INT AUTO_INCREMENT PRIMARY KEY,
  producto_id INT,
  presentacion_id INT,
  cantidad VARCHAR(50),
  FOREIGN KEY (producto_id) REFERENCES productos (id),
  FOREIGN KEY (presentacion_id) REFERENCES presentaciones (id)
);
CREATE TABLE presentaciones (
  id INT AUTO_INCREMENT PRIMARY KEY,
  presentacion VARCHAR(100)
);
CREATE TABLE ventas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fecha_venta DATE,
  producto_id INT,
  cantidad INT,
  precio DECIMAL(10, 2),
  cliente_id INT,
  FOREIGN KEY (producto_id) REFERENCES productos (id),
  FOREIGN KEY (cliente_id) REFERENCES clientes (id)
);
CREATE TABLE clientes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  direccion VARCHAR(255),
  telefono VARCHAR(20)
);

/*
	Reporte de ventas por producto
*/
SELECT p.nombre AS producto, SUM(v.cantidad) AS total_vendido, SUM(v.precio) AS total_ventas
FROM ventas v
JOIN productos p ON v.producto_id = p.id
GROUP BY p.nombre;

/*
	Reporte de ventas por cliente
*/
SELECT c.nombre AS cliente, COUNT(v.id) AS total_ventas, SUM(v.precio) AS total_monto
FROM ventas v
JOIN clientes c ON v.cliente_id = c.id
GROUP BY c.nombre;

SELECT
  c.nombre AS cliente,
  COUNT(v.id) AS total_ventas,
  SUM(v.precio) AS total_monto,
  MIN(v.fecha_venta) AS primera_venta,
  MAX(v.fecha_venta) AS ultima_venta,
  AVG(v.precio) AS promedio_precio
FROM ventas v
JOIN clientes c ON v.cliente_id = c.id
GROUP BY c.nombre;

SELECT c.nombre AS cliente, p.nombre AS producto, vp.cantidad, v.precio / vp.cantidad AS precio_unitario
FROM ventas v
JOIN clientes c ON v.cliente_id = c.id
JOIN variantes_presentacion vp ON v.producto_id = vp.producto_id
JOIN productos p ON vp.producto_id = p.id
WHERE v.presentacion_id = vp.id;



/*
	Reporte de ventas por fecha
*/
SELECT fecha_venta, COUNT(id) AS total_ventas, SUM(precio) AS total_monto
FROM ventas
GROUP BY fecha_venta;
