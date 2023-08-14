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
@Table(name = "ganado_macho")
public class GanadoMacho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ganado_macho", nullable = false)
    private Long idGanadoMacho;

    @Column(name = "fotografia", nullable = false)
    private String fotografia;

    @NotNull
    @Column(name = "nombre_ganado_macho", nullable = false)
    private String nombreGanadoMacho;

    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ganado")
    private TipoGanado tipoGanado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raza_ganado")
    private RazaGanado razaGanado;

    @NotNull
    @Column(name = "estado_ganado_macho", nullable = false)
    private boolean estadoGanadoMacho;

}
