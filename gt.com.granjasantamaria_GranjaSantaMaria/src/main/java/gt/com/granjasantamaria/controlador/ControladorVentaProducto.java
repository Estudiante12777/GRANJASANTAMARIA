package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorVentaProducto {

    @Autowired
    private VentaProductoService ventaProductoService;

    @Autowired
    private DetalleVentaProductoService detalleVentaProductoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DetalleProductoService detalleProductoService;

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @GetMapping("/modulo-venta/venta-producto/")
    public String ventaProductos(VentaProducto ventaProducto, DetalleVentaProducto detalleVentaProducto, DetalleProducto detalleProducto, Model model) {
        List<Cliente> listadoClientes = clienteService.listadoClientes();
        model.addAttribute("listadoClientes", listadoClientes);
        List<DetalleProducto> listadoDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listadoDetalleProductos", listadoDetalleProductos);
        List<DetalleVentaProducto> listaDetalleVentaProductos = detalleVentaProductoService.obtenerListadoDetalleVentaProductos();
        model.addAttribute("listaDetalleVentaProductos", listaDetalleVentaProductos);
        model.addAttribute("detalleVentaProducto", new DetalleVentaProducto()); // Agregado
        return "pages/modulo-venta/venta-producto/venta-producto";
    }

    @PostMapping("/modulo-venta/venta-producto/realizar-venta")
    public String realizarVentaProducto(@ModelAttribute VentaProducto ventaProducto, @ModelAttribute DetalleVentaProducto detalleVentaProducto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        ventaProductoService.guardarVentaProducto(ventaProducto);
        detalleVentaProducto.setVentaProducto(ventaProducto);
        detalleVentaProductoService.guardarDetalleVentaProducto(detalleVentaProducto);
        DetalleProducto detalleProductoVendido = detalleVentaProducto.getDetalleProducto();
        InventarioProducto inventarioProducto = inventarioProductoService.obtenerInventarioProductoPorProducto(detalleProductoVendido);
        int cantidadVendida = detalleVentaProducto.getCantidadProducto();
        int cantidadActualizada = inventarioProducto.getCantidadFinalProducto() - cantidadVendida;
        inventarioProducto.setCantidadFinalProducto(cantidadActualizada);
        inventarioProductoService.guardarInventarioProducto(inventarioProducto);
        return "redirect:/modulo-venta/venta-producto/";
    }

}
