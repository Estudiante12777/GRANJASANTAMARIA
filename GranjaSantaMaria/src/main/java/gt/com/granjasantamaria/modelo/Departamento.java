package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartamento; 
    
    @NotEmpty
    private String nombreDepartamento; 
    
    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais; 
    
}
