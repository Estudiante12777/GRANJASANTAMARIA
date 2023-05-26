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
@Table(name = "proveedor")
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @NotNull
    @Column(name = "nombre_proveedor", nullable = false)
    private String nombreProveedor;

    @NotNull
    @Column(name = "apellido_proveedor", nullable = false)
    private String apellidoProveedor;

    @NotNull
    @Column(name = "telefono_proveedor", nullable = false)
    private String telefonoProveedor;

    @NotNull
    @Column(name = "direccion_proveedor", nullable = false)
    private String direccionProveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", nullable = false)
    private Municipio municipio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    @NotNull
    @JoinColumn(name = "estado_proveedor", nullable = false)
    private boolean estadoProveedor;

}
