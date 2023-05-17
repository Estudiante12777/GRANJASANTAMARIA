package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @NotNull
    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @NotNull
    @Column(name = "apellido_cliente", nullable = false)
    private String apellidoCliente;

    @NotNull
    @Column(name = "direccion_cliente", nullable = false)
    private String direccionCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio")
    private Municipio municipio;

    @NotNull
    @Column(name = "estado_cliente", nullable = false)
    private boolean estadoCliente;

}
