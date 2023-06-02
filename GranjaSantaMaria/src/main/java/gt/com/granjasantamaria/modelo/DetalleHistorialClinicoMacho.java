package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "detalle_historial_clinico_macho")
public class DetalleHistorialClinicoMacho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleHistorialClinicoMacho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historial_clinico_macho")
    private HistorialClinicoMacho historialClinicoMacho;

    @NotNull
    @Column(name = "fecha_registro_historial_clinico", nullable = false)
    private LocalDate fechaRegistroHistorialClinicioMacho;

    @NotNull
    @Column(name = "descripcion_historial_clinico", nullable = false)
    private String descripcionHistorialClinico;

    @NotNull
    @Column(name = "estado_detalle_historial_clinico", nullable = false)
    private boolean estadoDetalleHistorialClinicio;

}
