package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tipo_ganado")
public class TipoGanado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTipoGanado;

    @NotNull
    @Column(name = "nombre_tipo_ganado", nullable = false)
    private String nombreTipoGanado;

    @NotNull
    @Column(name = "estado_tipo_ganado")
    private boolean estadoTipoGanado;

}
