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
@Table(name = "diario_gasto_granja")
public class DiarioGastoGranja implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_diario_gasto_granja", nullable = false)
    private Long idDiarioGastoGranja;

    @NotNull
    @Column(name = "fecha_gasto", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaGasto;

    @NotNull
    @Column(name = "unidades_adquiridas", nullable = false)
    private Integer unidadesAdquiridas;

    @NotNull
    @Column(name = "detalle_inversion", nullable = false)
    private String detalleInversion;

    @NotNull
    @Column(name = "valor_unitario", nullable = false)
    private BigDecimal valorUnitario;

    @NotNull
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @NotNull
    @Column(name = "estado_diario_gasto_ganja", nullable = false)
    private boolean estadoDiarioGastoGranja;

}
