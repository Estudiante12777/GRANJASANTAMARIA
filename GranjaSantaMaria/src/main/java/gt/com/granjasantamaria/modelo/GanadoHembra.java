package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "ganado_hembra")
public class GanadoHembra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGanadoHembra;

    @Column(name = "fotografia", nullable = false)
    private String fotografia;

    @NotNull
    @Column(name = "nombre_ganado_hembra", nullable = false)
    private String nombreGanadoHembra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_ganado")
    private TipoGanado tipoGanado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_raza_ganado")
    private RazaGanado razaGanado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prenies_ganado_hembra", insertable = false, updatable = false)
    private PreniesGanadoHembra preniesGanadoHembra;

    @NotNull
    @Column(name = "estado_ganado_hembra", nullable = false)
    private boolean estadoGanadoHembra;

}
