package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

import javax.validation.constraints.NotNull;

import javax.persistence.Id;

import lombok.ToString;

/**
 * @author gerso
 */
@Data
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

    @NotNull
    @Column(name = "estado_ganado_hembra", nullable = false)
    private boolean estadoGanadoHembra;

    public String ToString() {
        return getNombreGanadoHembra();
    }

}
