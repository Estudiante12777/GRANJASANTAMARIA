package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "contenedor_producto")
public class ContenedorProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long idContenedorProducto;

    @NotNull
    @Column(name = "nombre_contenedor_producto", nullable = false)
    private String nombreContenedorProducto;

    @NotNull
    @Column(name = "estado_contenedor_producto", nullable = false)
    private boolean estadoContenedorProducto;

}
