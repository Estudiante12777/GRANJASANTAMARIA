package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contenedor_producto")
public class ContenedorProducto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenedor_producto", nullable = false)
    private Long idContenedorProducto;

    @NotNull
    @Column(name = "nombre_contenedor_producto", nullable = false)
    private String nombreContenedorProducto;

    @NotNull
    @Column(name = "estado_contenedor_producto", nullable = false)
    private boolean estadoContenedorProducto;

}
