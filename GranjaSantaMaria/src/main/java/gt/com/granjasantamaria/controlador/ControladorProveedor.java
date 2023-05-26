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
public class ControladorProveedor {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/modulo-persona/proveedor/lista")
    public String obtenerListadoProveedores(Model model) {
        var proveedores = proveedorService.obtenerListadoProveedores();
        model.addAttribute("proveedores", proveedores);
        return "/pages/modulo-persona/proveedor/proveedor";
    }

    @GetMapping("/modulo-persona/proveedor/agregar")
    public String agregarProveedor(Proveedor proveedor, Model model) {
        List<Municipio> listadoMunicipios = municipioService.listadoMunicipios();
        model.addAttribute("listadoMunicipios", listadoMunicipios);
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        return "/pages/modulo-persona/proveedor/modificar-proveedor";
    }

    @PostMapping("/modulo-persona/proveedor/guardar")
    public String guardarProveedor(@Valid Proveedor proveedor, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            proveedorService.guardarProveedor(proveedor);
            return "redirect:/modulo-persona/proveedor/lista";
        }
    }

    @GetMapping("/modulo-persona/proveedor/editar/{idProveedor}")
    public String editarProveedor(Proveedor proveedor, Model model) {
        List<Municipio> listadoMunicipios = municipioService.listadoMunicipios();
        model.addAttribute("listadoMunicipios", listadoMunicipios);
        List<Departamento> listadoDepartamentos = departamentoService.listadoDepartamento();
        model.addAttribute("listadoDepartamentos", listadoDepartamentos);
        proveedor = proveedorService.encontrarProveedor(proveedor);
        model.addAttribute("proveedor", proveedor);
        return "/pages/modulo-persona/proveedor/modificar-proveedor";
    }

    @GetMapping("/modulo-persona/proveedor/eliminar")
    public String eliminarProveedor(Proveedor proveedor) {
        proveedorService.eliminarProveedor(proveedor);
        return "redirect:/modulo-persona/proveedor/lista";
    }

    @GetMapping("/modulo-persona/proveedor/baja")
    public String darBajaProveedor(Proveedor proveedor) {
        proveedorService.darBajaProveedor(proveedor);
        return "redirect:/modulo-persona/proveedor/lista";
    }

}
