package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.ProveedorDao;
import gt.com.granjasantamaria.modelo.Proveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gerso
 */
@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorDao proveedorDao;

    @Override
    public List<Proveedor> obtenerListadoProveedores() {
        return proveedorDao.findByEstadoProveedorIsTrue();
    }

    @Override
    public void guardarProveedor(Proveedor proveedor) {
        proveedor.setEstadoProveedor(true);
        proveedorDao.save(proveedor);
    }

    @Override
    public void eliminarProveedor(Proveedor proveedor) {
        proveedorDao.delete(proveedor);
    }

    @Override
    public Proveedor encontrarProveedor(Proveedor proveedor) {
        return proveedorDao.findById(proveedor.getIdProveedor()).orElse(null);
    }

    @Override
    public void darBajaProveedor(Proveedor proveedor) {
        Proveedor proveedorExistente = proveedorDao.findById(proveedor.getIdProveedor()).orElse(null);
        if (proveedorExistente != null) {
            proveedorExistente.setEstadoProveedor(false);
            proveedorDao.save(proveedorExistente);
        }
    }

}
