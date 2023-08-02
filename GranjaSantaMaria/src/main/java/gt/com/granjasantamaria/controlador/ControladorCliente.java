package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorCliente {

    private final DepartamentoService departamentoService;

    private final MunicipioService municipioService;

    private final ClienteService clienteService;

    @Autowired
    public ControladorCliente(DepartamentoService departamentoService, MunicipioService municipioService, ClienteService clienteService) {
        this.departamentoService = departamentoService;
        this.municipioService = municipioService;
        this.clienteService = clienteService;
    }

    @GetMapping("/modulo-persona/cliente/lista")
    public String listadoClientes(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<Cliente> clientePage = clienteService.obtenerClientePaginado(pageRequest);
        model.addAttribute("clientePage", clientePage);
        var clientes = clientePage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("clientes", clientes);
        return "pages/modulo-persona/cliente/cliente";
    }

    @GetMapping("/modulo-persona/cliente/municipios/{idDepartamento}")
    @ResponseBody
    public List<Municipio> obtenerMunicipiosPorDepartamento(@PathVariable("idDepartamento") Long idDepartamento) {
        return municipioService.obtenerMunicipiosPorDepartamento(idDepartamento);
    }

    @GetMapping("/modulo-persona/cliente/agregar")
    public String agregarCliente(Cliente cliente, Model model) {
        List<Municipio> listadoMunicipios = municipioService.listadoMunicipios();
        model.addAttribute("listadoMunicipios", listadoMunicipios);
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        return "pages/modulo-persona/cliente/modificar-cliente";
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
        return "pages/modulo-persona/cliente/modificar-cliente";
    }

    @GetMapping("/modulo-persona/cliente/baja")
    public String darBajaCliente(Cliente cliente) {
        clienteService.darBajaCliente(cliente);
        return "redirect:/modulo-persona/cliente/lista";
    }

}
