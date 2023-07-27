package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.dao.*;
import gt.com.granjasantamaria.modelo.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteDao clienteDao;

    @Autowired
    public ClienteServiceImpl(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listadoClientes() {
        return clienteDao.findByEstadoClienteIsTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> obtenerClientePaginado(Pageable pageable) {
        return clienteDao.findAllByEstadoClienteIsTrue(pageable);
    }

    @Override
    @Transactional
    public void guardarCliente(Cliente cliente) {
        cliente.setEstadoCliente(true);
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrarCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    @Override
    @Transactional
    public void darBajaCliente(Cliente cliente) {
        Cliente clienteExistente = clienteDao.findById(cliente.getIdCliente()).orElse(null);
        if (clienteExistente != null) {
            clienteExistente.setEstadoCliente(false);
            clienteDao.save(clienteExistente);
        }
    }

}
