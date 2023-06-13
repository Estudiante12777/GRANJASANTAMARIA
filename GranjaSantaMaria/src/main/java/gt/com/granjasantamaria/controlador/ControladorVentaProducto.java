package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorVentaProducto {

    @Autowired
    private VentaProductoService ventaProductoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DetalleProductoService detalleProductoService;

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @GetMapping("/modulo-venta/venta-producto/lista")
    public String listadoVentaProducto(Model model) {
        var listadoVentasProducto = ventaProductoService.obtenerListadoVentaProductos();
        model.addAttribute("listadoVentasProducto", listadoVentasProducto);
        return "/pages/modulo-venta/venta-producto/venta-producto";
    }

    @GetMapping("/modulo-venta/venta-producto/agregar")
    public String agregarVentaProducto(VentaProducto ventaProducto, Model model) {
        List<Cliente> listadoClientes = clienteService.listadoClientes();
        model.addAttribute("listadoClientes", listadoClientes);
        List<InventarioProducto> listadoInventarioProductos = inventarioProductoService.obtenerListadoInventarioProductos();
        model.addAttribute("listadoInventarioProductos", listadoInventarioProductos);
        return "/pages/modulo-venta/venta-producto/modificar-venta-producto";
    }

    @PostMapping("/modulo-venta/venta-producto/guardar")
    public String guardarVentaProducto(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) VentaProducto ventaProducto, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            ventaProductoService.guardarVentaProducto(ventaProducto);
            return "redirect:/modulo-venta/venta-producto/lista";
        }
    }

    @GetMapping("/modulo-venta/venta-producto/editar/{idVentaProducto}")
    public String editarVentaProducto(VentaProducto ventaProducto, Model model) {
        List<Cliente> listadoClientes = clienteService.listadoClientes();
        model.addAttribute("listadoClientes", listadoClientes);
        List<InventarioProducto> listadoInventarioProductos = inventarioProductoService.obtenerListadoInventarioProductos();
        model.addAttribute("listadoInventarioProductos", listadoInventarioProductos);
        ventaProducto = ventaProductoService.encontrarVentaProducto(ventaProducto);
        model.addAttribute("ventaProducto", ventaProducto);
        return "/pages/modulo-venta/venta-producto/modificar-venta-producto";
    }

    @GetMapping("/modulo-venta/venta-producto/eliminar")
    public String eliminarVentaProducto(VentaProducto ventaProducto) {
        ventaProductoService.eliminarVentaProducto(ventaProducto);
        return "redirect:/modulo-venta/venta-producto/lista";
    }

    @GetMapping("/modulo-venta/venta-producto/baja")
    public String darBajaVentaProducto(VentaProducto ventaProducto) {
        ventaProductoService.darBajaVentaProducto(ventaProducto);
        return "redirect:/modulo-venta/venta-producto/lista";
    }

}
