package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "raza_ganado")
public class RazaGanado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRazaGanado;

    @NotNull
    @Column(name = "nombre_raza_ganado", nullable = false)
    private String nombreRazaGanado;

    @NotNull
    @Column(name = "estado_raza_ganado", nullable = false)
    private boolean estadoRazaGando;

}
