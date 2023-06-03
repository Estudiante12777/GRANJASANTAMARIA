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
@Table(name = "detalle_historial_clinico_hembra")
public class DetalleHistorialClinicoHembra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleHistorialClinicoHembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historial_clinico_hembra")
    private HistorialClinicoHembra historialClinicoHembra;

    @NotNull
    @Column(name = "novilla", nullable = false)
    private boolean novilla;

    @NotNull
    @Column(name = "novilla_preniada", nullable = false)
    private boolean novillaPreniada;

    @NotNull
    @Column(name = "vaca_primer_parto", nullable = false)
    private boolean vacaPrimerParto;

    @NotNull
    @Column(name = "produccion_leche_maniana", nullable = false)
    private Double produccionLecheManiana;

    @NotNull
    @Column(name = "produccion_leche_tarde", nullable = false)
    private Double produccionLecheTarde;

    @NotNull
    @Column(name = "observaciones_adicionales", nullable = false)
    private String observacionesAdicionales;

    @NotNull
    @Column(name = "fecha_registro_historial_clinico", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistroHistorialClinico;

    @NotNull
    @Column(name = "descripcion_historial_clinico", nullable = false)
    private String descripcionHistorialClinico;

    @NotNull
    @Column(name = "estado_detalle_historial_clinico_hembra", nullable = false)
    private boolean estadoDetalleHistorialClinicoHembra;

}
