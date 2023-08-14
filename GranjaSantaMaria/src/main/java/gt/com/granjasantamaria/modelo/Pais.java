package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pais")
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais", nullable = false)
    private Long idPais;

    @NotNull
    @Column(name = "nombre_pais", nullable = false)
    private String nombrePais;

    @NotNull
    @Column(name = "estado_pais", nullable = false)
    private boolean estadoPais;

}
