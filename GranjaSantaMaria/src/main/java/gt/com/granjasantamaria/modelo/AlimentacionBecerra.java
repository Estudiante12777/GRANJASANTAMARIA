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
@Table(name = "alimentacion_becerra")
public class AlimentacionBecerra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimentacion_becerra", nullable = false)
    private Long idAlimentacionBecerra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_relacion_madre_becerra")
    private Becerra becerra;

    @NotNull
    @Column(name = "fecha_alimentacion_becerra", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaAlimentacionBecerra;

    @NotNull
    @Column(name = "cantidad_maniana_alimentacion", nullable = false)
    @DecimalMin(value = "0.0", message = "La alimentacion de la maniana debe ser mayor o igual a cero")
    private BigDecimal cantidadManianaAlimentacion;

    @NotNull
    @Column(name = "cantidad_tarde_alimentacion", nullable = false)
    @DecimalMin(value = "0.0", message = "La alimentacion de la maniana debe ser mayor o igual a cero")
    private BigDecimal cantidadTardeAlimentacion;

    @NotNull
    @Column(name = "total_alimentacion_becerra", nullable = false)
    @DecimalMin(value = "0.0", message = "El total de alimentacion debe ser mayor o igual a cero")
    private BigDecimal totalAlimentacionBecerra;

    @NotNull
    @Column(name = "id_produccion_diaria_leche", nullable = false)
    private Long idProduccionDiariaLeche;

    @NotNull
    @Column(name = "estado_alimentacion_becerra", nullable = false)
    private boolean estadoAlimentacionBecerra;

    public AlimentacionBecerra() {
    }

    public AlimentacionBecerra(Long idAlimentacionBecerra, LocalDate fechaAlimentacionBecerra, BigDecimal cantidadManianaAlimentacion, BigDecimal cantidadTardeAlimentacion, BigDecimal totalAlimentacionBecerra, boolean estadoAlimentacionBecerra, Becerra becerra, Long idProduccionDiariaLeche) {
        this.idAlimentacionBecerra = idAlimentacionBecerra;
        this.fechaAlimentacionBecerra = fechaAlimentacionBecerra;
        this.cantidadManianaAlimentacion = cantidadManianaAlimentacion;
        this.cantidadTardeAlimentacion = cantidadTardeAlimentacion;
        this.totalAlimentacionBecerra = totalAlimentacionBecerra;
        this.estadoAlimentacionBecerra = estadoAlimentacionBecerra;
        this.becerra = becerra;
        this.idProduccionDiariaLeche = idProduccionDiariaLeche;
    }

    @Override
    public String toString() {
        return "IdAlimetacionBecerra: " + idAlimentacionBecerra + " -->";
    }

}
