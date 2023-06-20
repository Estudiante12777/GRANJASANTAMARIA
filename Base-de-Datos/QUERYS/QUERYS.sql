USE granja_santa_maria;

SELECT * FROM inventario_producto;

SELECT * FROM venta_producto;

DELETE FROM venta_producto WHERE id_venta_producto = 1;
DELETE FROM venta_producto WHERE id_venta_producto = 2;
DELETE FROM venta_producto WHERE id_venta_producto = 3;
ALTER TABLE venta_producto AUTO_INCREMENT = 1;

SELECT * FROM inventario_producto;
