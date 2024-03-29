package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @NotNull
    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;

    @NotNull
    @Column(name = "descripcion_producto", nullable = false)
    private String descripcionProducto;

    @NotNull
    @Column(name = "estado_producto", nullable = false)
    private boolean estadoProducto;

}
