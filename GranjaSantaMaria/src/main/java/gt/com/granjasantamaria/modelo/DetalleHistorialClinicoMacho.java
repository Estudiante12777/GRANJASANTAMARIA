package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "detalle_historial_clinico_macho")
public class DetalleHistorialClinicoMacho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_historial_clinico_macho", nullable = false)
    private Long idDetalleHistorialClinicoMacho;

    @NotNull
    @Column(name = "id_historial_clinico_macho", nullable = false)
    private Long idHistorialClinicoMacho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historial_clinico_macho", insertable = false, updatable = false)
    private HistorialClinicoMacho historialClinicoMacho;

    @NotNull
    @Column(name = "fecha_registro_historial_clinico", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistroHistorialClinicoMacho;

    @NotNull
    @Column(name = "descripcion_historial_clinico", nullable = false)
    private String descripcionHistorialClinico;

    @NotNull
    @Column(name = "estado_detalle_historial_clinico_macho", nullable = false)
    private boolean estadoDetalleHistorialClinicoMacho;

}
