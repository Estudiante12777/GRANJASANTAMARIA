package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.hibernate.annotations.JoinFormula;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "venta_producto")
public class VentaProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_producto")
    private Long idVentaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario_producto")
    private InventarioProducto inventarioProducto;

    @NotNull
    @Column(name = "fecha_venta_producto")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVentaProducto;

    @NotNull
    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;

    @NotNull
    @Column(name = "precio_por_unidad")
    private Double precioPorUnidad;

    @NotNull
    @Column(name = "total_precio_producto")
    private Double totalPrecioProducto;

    @NotNull
    @Column(name = "descuento_producto")
    private Double descuentoProducto;

    @NotNull
    @Column(name = "estado_venta_producto", nullable = false)
    private boolean estadoVentaProducto;

}
