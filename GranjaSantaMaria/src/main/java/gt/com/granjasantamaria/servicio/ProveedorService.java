package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Proveedor;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface ProveedorService {

    public List<Proveedor> obtenerListadoProveedores();

    public void guardarProveedor(Proveedor proveedor);

    public void eliminarProveedor(Proveedor proveedor);

    public Proveedor encontrarProveedor(Proveedor proveedor);

    public void darBajaProveedor(Proveedor proveedor);

}
