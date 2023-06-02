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
@Table(name = "dieta_ternera_ternero")
public class DietaTerneraTernero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDietaTerneraTernero;

    @NotNull
    @Column(name = "descripcion_dieta")
    private String descripcionDieta;
    
    

}
