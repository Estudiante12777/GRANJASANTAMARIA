package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Cliente;
import java.util.List;

/**
 *
 * @author gerso
 */
public interface ClienteService {

    public List<Cliente> listadoClientes();

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

    public Cliente encontrarCliente(Cliente cliente);

    public void darBajaCliente(Cliente cliente);

}
