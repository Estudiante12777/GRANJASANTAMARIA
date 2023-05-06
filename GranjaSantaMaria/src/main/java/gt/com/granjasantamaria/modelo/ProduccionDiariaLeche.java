package gt.com.granjasantamaria.modelo;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author gerso
 */
@Data
@Entity
@Table(name = "produccion_diaria_leche")
public class ProduccionDiariaLeche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduccionDiariaLeche;

    @NotNull
    private Date fechaProduccionLeche;

    @NotNull
    private Double produccionManianaLeche;

    @NotNull
    private Double produccionTardeLeche;

    @ManyToOne
    @JoinColumn(name = "id_ganado")
    private Ganado ganado;

    @NotNull
    private boolean estadoProduccionDiariaLeche;

}
