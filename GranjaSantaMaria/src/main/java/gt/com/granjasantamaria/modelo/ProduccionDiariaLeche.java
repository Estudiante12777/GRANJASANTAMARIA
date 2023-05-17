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
@Table(name = "produccion_diaria_leche")
public class ProduccionDiariaLeche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduccionDiariaLeche;

    @NotNull
    @Column(name = "fecha_produccion_leche", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaProduccionLeche;

    @NotNull
    @Column(name = "produccion_maniana_leche", nullable = false)
    @DecimalMin(value = "0.0", message = "La producción de la maniana debe ser mayor o igual a cero")
    private Double produccionManianaLeche;

    @NotNull
    @Column(name = "produccion_tarde_leche", nullable = false)
    @DecimalMin(value = "0.0", message = "La producción de la tarde debe ser mayor o igual a cero")
    private Double produccionTardeLeche;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado")
    private Ganado ganado;

    @NotNull
    @Column(name = "estado_produccion_diaria_leche", nullable = false)
    private boolean estadoProduccionDiariaLeche;

}
