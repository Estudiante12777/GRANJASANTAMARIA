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
@Table(name = "medida_producto")
public class MedidaProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedidaProducto;

    @NotNull
    @Column(name = "nombre_medida_producto", nullable = false)
    private String nombreMedidaProducto;

    @NotNull
    @Column(name = "estado_medida_producto", nullable = false)
    private boolean estadoMedidaProducto;

}
