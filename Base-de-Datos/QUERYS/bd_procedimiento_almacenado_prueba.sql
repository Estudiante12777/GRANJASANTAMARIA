USE granja_santa_maria; 

DELIMITER //
CREATE PROCEDURE RealizarVenta(
     IN p_fecha_venta DATE,
     IN p_id_cliente INT,
     IN p_id_detalle_producto INT,
     IN p_cantidad_producto INT,
     IN p_precio_por_unidad DOUBLE,
     IN p_descuento_producto DOUBLE
)
BEGIN
    DECLARE v_id_venta INT;
    -- Insertar en la tabla venta_producto
    INSERT INTO venta_producto (fecha_venta_producto, id_cliente, id_inventario_producto, estado_venta_producto)
    VALUES (p_fecha_venta, p_id_cliente, p_id_detalle_producto, 1);
    -- Obtener el ID de la venta insertada
    SET v_id_venta = LAST_INSERT_ID();
    -- Insertar en la tabla detalle_venta_producto
    INSERT INTO detalle_venta_producto (id_venta_producto, id_detalle_producto, cantidad_producto, precio_por_unidad, total_precio_producto, descuento_producto, estado_detalle_venta_producto)
    VALUES (v_id_venta, p_id_detalle_producto, p_cantidad_producto, p_precio_por_unidad, p_cantidad_producto * p_precio_por_unidad, p_descuento_producto, 1);
    -- Actualizar la tabla inventario_producto
    UPDATE inventario_producto
    SET cantidad_salida_producto = cantidad_salida_producto + p_cantidad_producto,
        cantidad_final_producto = cantidad_final_producto - p_cantidad_producto,
        cantidad_vendida_hasta_hoy = cantidad_vendida_hasta_hoy + p_cantidad_producto
    WHERE id_inventario_producto = p_id_detalle_producto;
    -- Verificar si la cantidad final es menor o igual a 0 y actualizar el estado del inventario
    IF cantidad_final_producto <= 0 THEN
        UPDATE inventario_producto
        SET estado_inventario_producto = 0
        WHERE id_inventario_producto = p_id_detalle_producto;
    END IF;
END //
DELIMITER ;

CALL RealizarVenta('2023-05-29', 1, 1, 5, 10.0, 0.0);
