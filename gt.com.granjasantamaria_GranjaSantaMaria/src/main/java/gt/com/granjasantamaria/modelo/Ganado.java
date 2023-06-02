package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "nombre_ganado", nullable = false)
    private String nombreGanado;

    @NotNull
    @Column(name = "peso_ganado", nullable = false)
    @DecimalMin(value = "0.0", message = "El peso del ganado debe ser mayor o igual a cero")
    private double pesoGanado;

    @NotNull
    @Column(name = "fecha_ingreso_ganado", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngresoGanado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ganado")
    private TipoGanado tipoGanado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raza_ganado")
    private RazaGanado razaGanado;

    @NotNull
    @Column(name = "estado_ganado", nullable = false)
    private boolean estadoGanado;

}
