package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "detalle_producto")
public class DetalleProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_medida_producto")
    private MedidaProducto medidaProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_contenedor_producto")
    private ContenedorProducto contenedorProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_descripcion_producto")
    private DescripcionProducto descripcionProducto;

    @NotNull
    @Column(name = "estado_detalle_producto", nullable = false)
    private boolean estadoDetalleProducto;

    @Override
    public String toString() {
        return producto.getNombreProducto() + " - " + medidaProducto.getNombreMedidaProducto() + " - " + contenedorProducto.getNombreContenedorProducto() + " - " + descripcionProducto.getNombreDescripcionProducto();
    }

}
