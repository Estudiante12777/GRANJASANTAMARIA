package gt.com.granjasantamaria.servicio;

import gt.com.granjasantamaria.modelo.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {
    List<Cliente> listadoClientes();

    Page<Cliente> obtenerClientePaginado(Pageable pageable);

    void guardarCliente(Cliente cliente);

    Cliente encontrarCliente(Cliente cliente);

    void darBajaCliente(Cliente cliente);
}
