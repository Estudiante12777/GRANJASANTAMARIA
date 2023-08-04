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
public class ControladorDetalleProducto {

    private final ProductoService productoService;

    private final MedidaProductoService medidaProductoService;

    private final ContenedorProductoService contenedorProductoService;

    private final DescripcionProductoService descripcionProductoService;

    private final DetalleProductoService detalleProductoService;

    @Autowired
    public ControladorDetalleProducto(ProductoService productoService, MedidaProductoService medidaProductoService, ContenedorProductoService contenedorProductoService, DescripcionProductoService descripcionProductoService, DetalleProductoService detalleProductoService) {
        this.productoService = productoService;
        this.medidaProductoService = medidaProductoService;
        this.contenedorProductoService = contenedorProductoService;
        this.descripcionProductoService = descripcionProductoService;
        this.detalleProductoService = detalleProductoService;
    }

    @GetMapping("/modulo-producto/detalle-producto/lista")
    public String obtenerListadoDetalleProductos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<DetalleProducto> detalleProductoPage = detalleProductoService.obtenerListadoDetalleProductoPaginado(pageRequest);
        model.addAttribute("detalleProductoPage", detalleProductoPage);
        var detalleProductos = detalleProductoPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("detalleProductos", detalleProductos);
        return "pages/modulo-producto/detalle-producto/detalle-producto";
    }

    private void agregarListadosAlModelo(Model model) {
        List<Producto> listadoProductos = productoService.obtenerListadoProductos();
        model.addAttribute("listadoProductos", listadoProductos);
        List<MedidaProducto> listadoMedidaProductos = medidaProductoService.obtenerListadoMedidaProductos();
        model.addAttribute("listadoMedidaProductos", listadoMedidaProductos);
        List<ContenedorProducto> listadoContenedorProductos = contenedorProductoService.obtenerListadoContenedorProductos();
        model.addAttribute("listadoContenedorProductos", listadoContenedorProductos);
        List<DescripcionProducto> listadoDescripcionProductos = descripcionProductoService.obtenerListadoDescripcionProductos();
        model.addAttribute("listadoDescripcionProductos", listadoDescripcionProductos);
    }

    @GetMapping("/modulo-producto/detalle-producto/agregar")
    public String agregarDetalleProducto(DetalleProducto detalleProducto, Model model) {
        agregarListadosAlModelo(model);
        return "pages/modulo-producto/detalle-producto/modificar-detalle-producto";
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
        agregarListadosAlModelo(model);
        detalleProducto = detalleProductoService.encontrarDetalleProducto(detalleProducto);
        model.addAttribute("detalleProducto", detalleProducto);
        return "pages/modulo-producto/detalle-producto/modificar-detalle-producto";
    }

    @GetMapping("/modulo-producto/detalle-producto/baja")
    public String darBajaDetalleProducto(DetalleProducto detalleProducto) {
        detalleProductoService.darBajaDetalleProducto(detalleProducto);
        return "redirect:/modulo-producto/detalle-producto/lista";
    }

}
