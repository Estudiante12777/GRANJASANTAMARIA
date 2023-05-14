package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface ProduccionDiariaLecheService {

    public List<ProduccionDiariaLeche> listaProduccionDiariaLeche();
    
    public List<ProduccionDiariaLeche> listaTotalProduccionDiariaLeche();

    public void guardarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    public void eliminarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    public ProduccionDiariaLeche encontrarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

    public void darBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche);

}
