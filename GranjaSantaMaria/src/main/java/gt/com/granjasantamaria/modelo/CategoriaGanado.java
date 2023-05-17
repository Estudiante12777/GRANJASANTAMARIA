package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "categoria_ganado")
public class CategoriaGanado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaGanado;

    @NotNull
    @Column(name = "nombre_categoria_ganado", nullable = false)
    private String nombreCategoriaGanado;

    @NotNull
    @Column(name = "estado_categoria_ganado", nullable = false)
    private boolean estadoCategoriaGanado;

}
