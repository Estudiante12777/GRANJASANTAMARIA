package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
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

    @NotNull
    @Column(name = "fecha_venta_producto", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaVentaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario_producto")
    private InventarioProducto inventarioProducto;

    @NotNull
    @Column(name = "estado_venta_producto", nullable = false)
    private boolean estadoVentaProducto;

    @OneToMany(mappedBy = "ventaProducto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleVentaProducto> detallesVenta;

    // Resto del código de la clase
    public List<DetalleVentaProducto> getDetallesVenta() {
        return detallesVenta;
    }

}
