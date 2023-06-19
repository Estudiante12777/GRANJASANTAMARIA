package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DetalleProducto;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import gt.com.granjasantamaria.servicio.*;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorInventarioProducto {

    @Autowired
    private InventarioProductoService inventarioProductoService;

    @Autowired
    private DetalleProductoService detalleProductoService;

    @GetMapping("/modulo-inventario/inventario-producto/lista")
    public String obtenerListadoInventarioProductos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<InventarioProducto> inventarioProductoPage = inventarioProductoService.obtenerListadoInventarioProductoPaginado(pageRequest);
        model.addAttribute("inventarioProductoPage", inventarioProductoPage);
        List<InventarioProducto> inventarioProductos = inventarioProductoPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("inventarioProductos", inventarioProductos);
        return "pages/modulo-inventario/inventario-producto/inventario-producto";
    }

    @GetMapping("/modulo-inventario/inventario-producto/agregar")
    public String agregarInventarioProducto(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        return "/pages/modulo-inventario/inventario-producto/modificar-inventario-producto";
    }

    @PostMapping("/modulo-inventario/inventario-producto/guardar")
    public String guardarInventarioProducto(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) InventarioProducto inventarioProducto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pages/modulo-inventario/inventario-producto/modificar-inventario-producto";
        } else {
            List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            inventarioProductoService.guardarInventarioProducto(inventarioProducto);
            model.addAttribute("listaDetalleProductos", listaDetalleProductos);
            return "redirect:/modulo-inventario/inventario-producto/lista";
        }
    }

    @GetMapping("/modulo-inventario/inventario-producto/editar/{idInventarioProducto}")
    public String editarInventarioProducto(InventarioProducto inventarioProducto, Model model) {
        List<DetalleProducto> listaDetalleProductos = detalleProductoService.obtenerListadoDetalleProductos();
        model.addAttribute("listaDetalleProductos", listaDetalleProductos);
        inventarioProducto = inventarioProductoService.encontrarInventarioProducto(inventarioProducto);
        model.addAttribute("inventarioProducto", inventarioProducto);
        return "/pages/modulo-inventario/inventario-producto/modificar-inventario-producto";
    }

    @GetMapping("/modulo-inventario/inventario-producto/eliminar")
    public String eliminarInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoService.eliminarInventarioProducto(inventarioProducto);
        return "redirect:/modulo-inventario/inventario-producto/lista";
    }

    @GetMapping("/modulo-inventario/inventario-producto/baja")
    public String darBajaInventarioProducto(InventarioProducto inventarioProducto) {
        inventarioProductoService.darBajaInventarioProducto(inventarioProducto);
        return "redirect:/modulo-inventario/inventario-producto/lista";
    }

}
