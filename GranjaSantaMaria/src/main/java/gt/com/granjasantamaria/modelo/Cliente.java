package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @NotNull
    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @NotNull
    @Column(name = "apellido_cliente", nullable = false)
    private String apellidoCliente;

    @NotNull
    @Column(name = "telefono_cliente", nullable = false)
    private String telefonoCliente;

    @NotNull
    @Column(name = "direccion_cliente", nullable = false)
    private String direccionCliente;

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
