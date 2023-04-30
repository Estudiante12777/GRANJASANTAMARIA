package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.CategoriaGanadoDao;
import gt.com.granjasantamaria.modelo.CategoriaGanado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gerso
 */
@Service
public class CategoriaGanadoServiceImpl implements CategoriaGanadoService {
    
    @Autowired
    private CategoriaGanadoDao categoriaGanadoDao; 

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaGanado> listaCategoriasGanado() {
        return categoriaGanadoDao.findByEstadoCategoriaGanadoTrue(); 
    }

    @Override
    @Transactional
    public void guardarCategoriaGanado(CategoriaGanado categoriaGanado) {
        categoriaGanado.setEstadoCategoriaGanado(true);
        categoriaGanadoDao.save(categoriaGanado); 
    }

    @Override
    @Transactional
    public void eliminarCategoriaGanado(CategoriaGanado categoriaGanado) {
        categoriaGanadoDao.delete(categoriaGanado);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaGanado encontrarCategoriaGando(CategoriaGanado categoriaGanado) {
        return categoriaGanadoDao.findById(categoriaGanado.getIdCategoriaGanado()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaCategoriaGanado(CategoriaGanado categoriaGanado) {
        CategoriaGanado categoriaGanadoExistente = categoriaGanadoDao.findById(categoriaGanado.getIdCategoriaGanado()).orElse(null);
        if (categoriaGanadoExistente != null) {
            categoriaGanadoExistente.setEstadoCategoriaGanado(false);
            categoriaGanadoDao.save(categoriaGanadoExistente);
        }
    }
    
}
