package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "inventario_producto")
public class InventarioProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario_producto", nullable = false)
    private Long idInventarioProducto;

    @ManyToOne
    @JoinColumn(name = "id_detalle_producto")
    private DetalleProducto detalleProducto;

    @NotNull
    @Column(name = "fecha_inventario_producto", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInventarioProducto;

    @NotNull
    @Column(name = "cantidad_ingresada_producto", nullable = false)
    private Integer cantidadIngresadaProducto;

    @NotNull
    @Column(name = "cantidad_salida_producto", nullable = false)
    private Integer cantidadSalidaProducto;

    @NotNull
    @Column(name = "cantidad_final_producto", nullable = false)
    private Integer cantidadFinalProducto;

    @NotNull
    @Column(name = "cantidad_vendida_hasta_hoy", nullable = false)
    private Integer cantidadVendidaHastaHoy;

    @NotNull
    @Column(name = "estado_inventario_producto", nullable = false)
    private boolean estadoInventarioProducto;

}
