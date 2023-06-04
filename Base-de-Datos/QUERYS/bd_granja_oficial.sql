























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
