package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "ganado_macho")
public class GanadoMacho implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGanadoMacho;

    @Column(name = "fotografia", nullable = false)
    private String fotografia;

    @NotNull
    @Column(name = "nombre_ganado_macho", nullable = false)
    private String nombreGanadoMacho;

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
