package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "alimentacion_becerro")
public class AlimentacionBecerro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimentacion_becerro", nullable = false)
    private Long idAlimentacionBecerro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_becerro")
    private Becerro becerro;

    @NotNull
    @Column(name = "fecha_alimentacion_becerro", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlimentacionBecerro;

    @NotNull
    @Column(name = "cantidad_maniana_alimentacion", nullable = false)
    @DecimalMin(value = "0.0", message = "La alimentacion de la maniana debe ser mayor o igual a cero")
    private BigDecimal cantidadManianaAlimentacion;

    @NotNull
    @Column(name = "cantidad_tarde_alimentacion", nullable = false)
    @DecimalMin(value = "0.0", message = "La alimentacion de la maniana debe ser mayor o igual a cero")
    private BigDecimal cantidadTardeAlimentacion;

    @NotNull
    @Column(name = "total_alimentacion_becerro", nullable = false)
    @DecimalMin(value = "0.0", message = "El total de alimentacion debe ser mayor o igual a cero")
    private BigDecimal totalAlimentacionBecerro;

    @NotNull
    @Column(name = "id_produccion_diaria_leche", nullable = false)
    private Long idProduccionDiariaLeche;

    @NotNull
    @Column(name = "estado_alimentacion_becerro", nullable = false)
    private boolean estadoAlimentacionBecerro;

}
