package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
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

    @NotNull
    @Column(name = "nombre_ganado")
    private String nombreGanado;

    @NotNull
    @Column(name = "peso", nullable = false)
    private double peso;

    @NotNull
    @Column(name = "fecha_ingreso_granja")
    private Date fechaIngresoGranja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ganado")
    private TipoGanado tipoGanado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria_ganado")
    private CategoriaGanado categoriaGanado;

    @NotNull
    @Column(name = "estado_ganado", nullable = false)
    private boolean estadoGanado;

}
