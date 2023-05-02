package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "ganado")
public class Ganado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGanado;

    @NotEmpty
    private String nombreGanado;

    @NotNull
    private double peso;

    @NotNull
    private Date fechaIngresoGranja;

    @ManyToOne
    @JoinColumn(name = "id_tipo_ganado")
    private TipoGanado tipoGanado;

    @ManyToOne
    @JoinColumn(name = "id_categoria_ganado")
    private CategoriaGanado categoriaGanado;

    @NotNull
    private boolean estadoGanado;

}
