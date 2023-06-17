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

    @NotNull
    @Column(name = "id_historial_clinico_hembra", nullable = false)
    private Long idHistorialClinicoHembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_historial_clinico_hembra", insertable = false, updatable = false)
    private HistorialClinicoHembra historialClinicoHembra;

    @NotNull
    @Column(name = "fecha_registro_detalle_historial_clinico", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRegistroDetalleHistorialClinico;

    @NotNull
    @Column(name = "descripcion_detalle_historial_clinico", nullable = false)
    private String descripcionDetalleHistorialClinico;

    @NotNull
    @Column(name = "estado_detalle_historial_clinico_hembra", nullable = false)
    private boolean estadoDetalleHistorialClinicoHembra;

}
