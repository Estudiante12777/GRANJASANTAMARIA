package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name = "inventario_producto")
public class InventarioProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "cantidad_entrada_producto", nullable = false)
    private Integer cantidadEntradaProducto;

    @NotNull
    @Column(name = "cantidad_salida_producto", nullable = false)
    private Integer cantidadSalidaProducto;

    @NotNull
    @Column(name = "cantidad_final_producto", nullable = false)
    private Integer cantidadFinalProducto;

    @NotNull
    @Column(name = "fecha_ingreso")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngreso;

    @NotNull
    @Column(name = "cantidad_vendida_hasta_hoy")
    private Integer cantidadVendidaHastaHoy;

    @NotNull
    @Column(name = "estado_inventario_producto", nullable = false)
    private boolean estadoInventarioProducto;

}
