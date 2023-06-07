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
public class ControladorDetalleProducto {

    @Autowired
    private DetalleProductoService detalleProductoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MedidaProductoService medidaProductoService;

    @Autowired
    private ContenedorProductoService contenedorProductoService;

    @Autowired
    private DescripcionProductoService descripcionProductoService;

    @GetMapping("/modulo-producto/detalle-producto/lista")
    public String obtenerListadoDetalleProductos(Model model) {
        var detalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("detalleProductos", detalleProductos);
        return "/pages/modulo-producto/detalle-producto/detalle-producto";
    }

    @GetMapping("/modulo-producto/detalle-producto/agregar")
    public String agregarDetalleProducto(DetalleProducto detalleProducto, Model model) {
        List<Producto> listadoProductos = productoService.obtenerListadoProductos();
        model.addAttribute("listadoProductos", listadoProductos);
        List<MedidaProducto> listadoMedidaProductos = medidaProductoService.obtenerListadoMedidaProductos();
        model.addAttribute("listadoMedidaProductos", listadoMedidaProductos);
        List<ContenedorProducto> listadoContenedorProductos = contenedorProductoService.obtenerListadoContenedorProductos();
        model.addAttribute("listadoContenedorProductos", listadoContenedorProductos);
        List<DescripcionProducto> listadoDescripcionProductos = descripcionProductoService.obtenerListadoDescripcionProductos();
        model.addAttribute("listadoDescripcionProductos", listadoDescripcionProductos);
        return "/pages/modulo-producto/detalle-producto/modificar-detalle-producto";
    }

    @PostMapping("/modulo-producto/detalle-producto/guardar")
    public String guardarDetalleProducto(@Valid DetalleProducto detalleProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            detalleProductoService.guardarDetalleProducto(detalleProducto);
            return "redirect:/modulo-producto/detalle-producto/lista";
        }
    }

    @GetMapping("/modulo-producto/detalle-producto/editar/{idDetalleProducto}")
    public String editarDetalleProducto(DetalleProducto detalleProducto, Model model) {
        List<Producto> listadoProductos = productoService.obtenerListadoProductos();
        model.addAttribute("listadoProductos", listadoProductos);
        List<MedidaProducto> listadoMedidaProductos = medidaProductoService.obtenerListadoMedidaProductos();
        model.addAttribute("listadoMedidaProductos", listadoMedidaProductos);
        List<ContenedorProducto> listadoContenedorProductos = contenedorProductoService.obtenerListadoContenedorProductos();
        model.addAttribute("listadoContenedorProductos", listadoContenedorProductos);
        List<DescripcionProducto> listadoDescripcionProductos = descripcionProductoService.obtenerListadoDescripcionProductos();
        model.addAttribute("listadoDescripcionProductos", listadoDescripcionProductos);
        detalleProducto = detalleProductoService.encontrarDetalleProducto(detalleProducto);
        model.addAttribute("producto", detalleProducto);
        return "/pages/modulo-producto/detalle-producto/modificar-detalle-producto";
    }

    @GetMapping("/modulo-producto/detalle-producto/eliminar")
    public String eliminarDetalleProducto(DetalleProducto detalleProducto) {
        detalleProductoService.eliminarDetalleProducto(detalleProducto);
        return "redirect:/modulo-producto/detalle-producto/lista";
    }

    @GetMapping("/modulo-producto/detalle-producto/baja")
    public String darBajaDetalleProducto(DetalleProducto detalleProducto) {
        detalleProductoService.darBajaDetalleProducto(detalleProducto);
        return "redirect:/modulo-producto/detalle-producto/lista";
    }

}
