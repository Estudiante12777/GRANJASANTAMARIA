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
@Table(name = "prenies_ganado_hembra")
public class PreniesGanadoHembra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenies_ganado_hembra", nullable = false)
    private Long idPreniesGanadoHembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado_hembra")
    private GanadoHembra ganadoHembra;

    @NotNull
    @Column(name = "fecha_prenies", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPrenies;

    @NotNull
    @Column(name = "promedio_gestacion", nullable = false)
    private Integer promedioGestacion;

    @Column(name = "fecha_concepcion", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaConcepcion;

    @NotNull
    @Column(name = "estado_prenies_ganado_hembra", nullable = false)
    private boolean estadoPreniesGanadoHembra;

}
