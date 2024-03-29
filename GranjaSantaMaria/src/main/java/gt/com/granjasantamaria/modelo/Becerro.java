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
@Table(name = "relacion_madre_becerro")
public class Becerro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacion_madre_becerro", nullable = false)
    private Long idRelacionMadreBecerro;

    @ManyToOne()
    @JoinColumn(name = "id_madre")
    private GanadoHembra madre;

    @ManyToOne()
    @JoinColumn(name = "id_becerro")
    private GanadoMacho becerro;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull
    @Column(name = "estado_relacion_madre_becerro", nullable = false)
    private boolean estadoRelacionMadreBecerro;

}
