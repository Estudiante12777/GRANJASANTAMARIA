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
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "Municipio")
public class Municipio implements Serializable {
    
    private static final long serialVersionUID = 1L; 
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMunicipio; 
    
    @NotEmpty
    private String nombreMunicipio; 
    
    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento; 
    
    @NotNull
    private boolean estadoMunicipio; 
    
}
