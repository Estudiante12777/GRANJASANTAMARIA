package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "venta_producto")
public class VentaProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_producto", nullable = false)
    private Long idVentaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario_producto")
    private InventarioProducto inventarioProducto;

    @NotNull
    @Column(name = "fecha_venta_producto", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVentaProducto;

    @NotNull
    @Column(name = "cantidad_producto", nullable = false)
    private Integer cantidadProducto;

    @NotNull
    @Column(name = "precio_por_unidad", nullable = false)
    private BigDecimal precioPorUnidad;

    @NotNull
    @Column(name = "total_precio_producto", nullable = false)
    private BigDecimal totalPrecioProducto;

    @NotNull
    @Column(name = "descuento_producto", nullable = false)
    private BigDecimal descuentoProducto;

    @NotNull
    @Column(name = "estado_venta_producto", nullable = false)
    private boolean estadoVentaProducto;

}
