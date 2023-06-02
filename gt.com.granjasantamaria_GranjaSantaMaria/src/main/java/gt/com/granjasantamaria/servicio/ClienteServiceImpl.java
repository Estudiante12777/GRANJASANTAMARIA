package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gerso
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public List<Cliente> listadoClientes() {
        return clienteDao.findByEstadoClienteIsTrue();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        cliente.setEstadoCliente(true);
        clienteDao.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    public void darBajaCliente(Cliente cliente) {
        Cliente clienteExistente = clienteDao.findById(cliente.getIdCliente()).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setEstadoCliente(false);
            clienteDao.save(clienteExistente);
        }
    }

}
