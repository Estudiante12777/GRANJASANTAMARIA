package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
