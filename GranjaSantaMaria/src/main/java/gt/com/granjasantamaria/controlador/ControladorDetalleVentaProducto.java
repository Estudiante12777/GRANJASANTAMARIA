package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.servicio.DetalleVentaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorDetalleVentaProducto {

    @Autowired
    private DetalleVentaProductoService detalleVentaProductoService;

    @GetMapping("/modulo-venta/detalle-venta-producto")
    public String obtenerListadoDetalleVentaProductos(Model model) {
        var detalleVentaProducto = detalleVentaProductoService.obtenerListadoDetalleVentaProductos();
        model.addAttribute("detalleVentaProducto", detalleVentaProducto);
        return "/pages/modulo-venta/detalle-venta-producto/detalle-venta-producto";
    }

}
