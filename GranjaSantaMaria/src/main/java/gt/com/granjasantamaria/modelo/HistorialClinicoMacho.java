package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "historial_clinico_macho")
public class HistorialClinicoMacho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorialClinicoMacho;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado_macho")
    private GanadoMacho ganadoMacho;

    @NotNull
    @Column(name = "fecha_ingreso_granja", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaIngresoGranja;

    @NotNull
    @Column(name = "procedencia_ganado_macho", nullable = false)
    private String procedenciaGanadoMacho;

    @NotNull
    @Column(name = "condiciones_fisicas_recibido", nullable = false)
    private String condicionesFisicasRecibido;

    @NotNull
    @Column(name = "estado_historial_clinico_macho", nullable = false)
    private boolean estadoHistorialClinicioMacho;

}
