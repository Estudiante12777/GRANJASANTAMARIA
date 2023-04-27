package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Entity
@Data
@Table(name = "tipo_ganado")
public class TipoGanado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long idTipoGanado;

    @NotEmpty
    private String nombre;

    //CONSTRUCTORES
    public TipoGanado() {
        super();
    }

    public TipoGanado(long idTipoGanado, String nombre) {
        this.idTipoGanado = idTipoGanado;
        this.nombre = nombre;
    }

    public TipoGanado(String nombre) {
        this.nombre = nombre;
    }

    public TipoGanado(long idTipoGanado) {
        this.idTipoGanado = idTipoGanado;
    }
}
