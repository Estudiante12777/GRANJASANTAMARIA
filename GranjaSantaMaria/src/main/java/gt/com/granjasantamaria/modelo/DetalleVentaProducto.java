package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_venta_producto")
public class DetalleVentaProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta_producto")
    private Long idDetalleVentaProducto;

    @OneToOne
    @JoinColumn(name = "id_venta_producto")
    private VentaProducto ventaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_producto")
    private DetalleProducto detalleProducto;

    @NotNull
    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidadProducto;

    @NotNull
    @Column(name = "precio_por_unidad", nullable = false)
    @DecimalMin(value = "0.0", message = "El precio por unidad debe ser mayor o igual a cero")
    private Double precioPorUnidad;

    @NotNull
    @Column(name = "total_precio_producto", nullable = false)
    @DecimalMin(value = "0.0", message = "El total del precio del producto debe ser mayor o igual a cero")
    private Double totalPrecioProducto;

    @NotNull
    @Column(name = "descuento_producto", nullable = false)
    @DecimalMin(value = "0.0", message = "El descuento del producto debe ser mayor o igual a cero")
    private Double descuentoProducto;

    @NotNull
    @Column(name = "estado_detalle_venta_producto", nullable = false)
    private boolean estadoDetalleVentaProducto;
    
}
