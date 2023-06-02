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
@Table(name = "historial_clinico_hembra")
public class HistorialClinicoHembra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorialClinicoHembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado_hembra")
    private GanadoHembra ganadoHembra;

    @NotNull
    @Column(name = "fecha_ingreso_granja", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngresoGranja;

    @NotNull
    @Column(name = "procedencia_ganado_hembra", nullable = false)
    private String procedenciaGanadoHembra;

    @NotNull
    @Column(name = "condiciones_fisicas_recibida", nullable = false)
    private String condicionesFisicasRecibida;

    @NotNull
    @Column(name = "estado_historial_clinico_hembra", nullable = false)
    private boolean estadoHistorialClinicoHembra;

}
