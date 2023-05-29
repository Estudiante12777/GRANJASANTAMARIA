package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la gestión de VentaProducto y DetalleVentaProducto.
 */
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

    @GetMapping("/modulo-venta/venta-producto/")
    public String ventaProductos(VentaProducto ventaProducto, DetalleVentaProducto detalleVentaProducto, DetalleProducto detalleProducto, Model model) {
        // Obtener la lista de clientes y productos desde el servicio y agregarlos al modelo
        List<Cliente> listadoClientes = clienteService.listadoClientes();
        model.addAttribute("listadoClientes", listadoClientes);
        List<DetalleProducto> listadoDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listadoDetalleProductos", listadoDetalleProductos);
        List<DetalleVentaProducto> listaDetalleVentaProductos = detalleVentaProductoService.obtenerListadoDetalleVentaProductos();
        model.addAttribute("listaDetalleVentaProductos", listaDetalleVentaProductos);
        return "pages/modulo-venta/venta-producto/venta-producto";
    }

    @PostMapping("/modulo-venta/venta-producto/realizar-venta")
    public String realizarVentaProducto(@ModelAttribute VentaProducto ventaProducto, @ModelAttribute DetalleVentaProducto detalleVentaProducto) {
        // Guardar la venta en la tabla venta_producto
        ventaProductoService.guardarVentaProducto(ventaProducto);

        // Establecer la venta en el detalle de venta
        detalleVentaProducto.setVentaProducto(ventaProducto);

        // Guardar el detalle de venta en la tabla detalle_venta_producto
        detalleVentaProductoService.guardarDetalleVentaProducto(detalleVentaProducto);

        return "redirect:/modulo-venta/venta-producto/";
    }

}
