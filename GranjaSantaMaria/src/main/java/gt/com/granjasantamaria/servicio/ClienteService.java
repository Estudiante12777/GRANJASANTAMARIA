package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author gerso
 */
public interface ClienteService {

    public List<Cliente> listadoClientes();

    Page<Cliente> obtenerClientePaginado(Pageable pageable);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

    public Cliente encontrarCliente(Cliente cliente);

    public void darBajaCliente(Cliente cliente);

}
