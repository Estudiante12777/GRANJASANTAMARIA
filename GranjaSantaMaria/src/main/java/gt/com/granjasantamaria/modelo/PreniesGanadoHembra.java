package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "prenies_ganado_hembra")
public class PreniesGanadoHembra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPreniesGanadoHembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado_hembra")
    private GanadoHembra ganadoHembra;

    @NotNull
    @Column(name = "fecha_concepcion", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaConcepcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado_macho")
    private GanadoMacho ganadoMacho;

    @NotNull
    @Column(name = "promedio_gestacion", nullable = false)
    private Integer promedioGestacion;

    @NotNull
    @Column(name = "fecha_esperada_parto", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEsperadaParto;

    @NotNull
    @Column(name = "estado_prenies_ganado_hembra")
    private boolean estadoPreniesGanadoHembra;

}
