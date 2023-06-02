package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorCliente {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/modulo-persona/cliente/lista")
    public String listadoClientes(Model model) {
        var clientes = clienteService.listadoClientes();
        model.addAttribute("clientes", clientes);
        return "/pages/modulo-persona/cliente/cliente";
    }

    @GetMapping("/modulo-persona/cliente/agregar")
    public String agregarCliente(Cliente cliente, Model model) {
        List<Municipio> listadoMunicipios = municipioService.listadoMunicipios();
        model.addAttribute("listadoMunicipios", listadoMunicipios);
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        return "/pages/modulo-persona/cliente/modificar-cliente";
    }

    @PostMapping("/modulo-persona/cliente/guardar")
    public String guardarCliente(@Valid Cliente cliente, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            clienteService.guardarCliente(cliente);
            return "redirect:/modulo-persona/cliente/lista";
        }
    }

    @GetMapping("/modulo-persona/cliente/editar/{idCliente}")
    public String editarCliente(Cliente cliente, Model model) {
        List<Municipio> listadoMunicipios = municipioService.listadoMunicipios();
        model.addAttribute("listadoMunicipios", listadoMunicipios);
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        cliente = clienteService.encontrarCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "/pages/modulo-persona/cliente/modificar-cliente";
    }

    @GetMapping("/modulo-persona/cliente/eliminar")
    public String eliminarCliente(Cliente cliente) {
        clienteService.eliminarCliente(cliente);
        return "redirect:/modulo-persona/cliente/lista";
    }

    @GetMapping("/modulo-persona/cliente/baja")
    public String darBajaCliente(Cliente cliente) {
        clienteService.darBajaCliente(cliente);
        return "redirect:/modulo-persona/cliente/lista";
    }

}
