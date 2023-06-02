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
@Table(name = "descripcion_producto")
public class DescripcionProducto implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescripcionProducto; 
    
    @NotNull
    @Column(name = "nombre_descripcion_producto", nullable = false)
    private String nombreDescripcionProducto; 
    
    @NotNull
    @Column(name = "estado_descripcion_producto", nullable = false)
    private boolean estadoDescripcionProducto; 
    
}
