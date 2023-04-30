package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.CategoriaGanado;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface CategoriaGanadoService {
    
    public List<CategoriaGanado> listaCategoriasGanado(); 
    
    public void guardarCategoriaGanado(CategoriaGanado categoriaGanado);
    
    public void eliminarCategoriaGanado(CategoriaGanado categoriaGanado);
    
    public CategoriaGanado encontrarCategoriaGando(CategoriaGanado categoriaGanado);
    
    public void darBajaCategoriaGanado(CategoriaGanado categoriaGanado); 
    
}
