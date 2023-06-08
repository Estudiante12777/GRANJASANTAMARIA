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
@Table(name = "alimentacion_becerra")
public class AlimentacionBecerra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlimentacionBecerra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ganado_hembra")
    private GanadoHembra ganadoHembra;

    @NotNull
    @Column(name = "fecha_alimentacion_becerra", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlimentacionBecerra;

    @NotNull
    @Column(name = "cantidad_maniana_alimentacion", nullable = false)
    @DecimalMin(value = "0.0", message = "La alimentacion de la maniana debe ser mayor o igual a cero")
    private Double cantidadManianaAlimentacion;

    @NotNull
    @Column(name = "cantidad_tarde_alimentacion", nullable = false)
    @DecimalMin(value = "0.0", message = "La alimentacion de la maniana debe ser mayor o igual a cero")
    private Double cantidadTardeAlimentacion;

    @NotNull
    @Column(name = "total_alimentacion_becerra", nullable = false)
    @DecimalMin(value = "0.0", message = "El total de alimentacion debe ser mayor o igual a cero")
    private Double totalAlimentacionBecerra;

    @NotNull
    @Column(name = "id_produccion_diaria_leche", nullable = false)
    private Long idProduccionDiariaLeche;

    @NotNull
    @Column(name = "estado_alimentacion_becerra", nullable = false)
    private boolean estadoAlimentacionBecerra;

}