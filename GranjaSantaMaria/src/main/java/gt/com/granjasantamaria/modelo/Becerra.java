package gt.com.granjasantamaria.modelo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "relacion_madre_becerra")
public class Becerra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacion_madre_becerra")
    private Long idRelacionMadreBecerra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_madre")
    private GanadoHembra madre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_becerra")
    private GanadoHembra becerra;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull
    @Column(name = "estado_relacion_madre_becerra", nullable = false)
    private boolean estadoRelacionMadreBecerra;

}
