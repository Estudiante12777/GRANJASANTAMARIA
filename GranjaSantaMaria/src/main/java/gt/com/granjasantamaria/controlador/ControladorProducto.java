package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.Producto;
import gt.com.granjasantamaria.servicio.ProductoService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
public class ControladorProducto {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/modulo-producto/producto/lista")
    public String obtenerListadoProductos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<Producto> productoPage = productoService.obtenerListadoProductoPaginado(pageRequest);
        model.addAttribute("productoPage", productoPage);
        var productos = productoPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "/pages/modulo-producto/producto/producto";
    }

    @GetMapping("/modulo-producto/producto/agregar")
    public String agregarProveedor(Producto producto, Model model) {
        return "/pages/modulo-producto/producto/modificar-producto";
    }

    @PostMapping("/modulo-producto/producto/guardar")
    public String guardarProducto(@Valid Producto prdoProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            productoService.guardarProducto(prdoProducto);
            return "redirect:/modulo-producto/producto/lista";
        }
    }

    @GetMapping("/modulo-producto/producto/editar/{idProducto}")
    public String editarProducto(Producto producto, Model model) {
        producto = productoService.encontranProducto(producto);
        model.addAttribute("producto", producto);
        return "/pages/modulo-producto/producto/modificar-producto";
    }

    @GetMapping("/modulo-producto/producto/eliminar")
    public String eliminarProducto(Producto producto) {
        productoService.eliminarProducto(producto);
        return "redirect:/modulo-producto/producto/lista";
    }

    @GetMapping("/modulo-producto/producto/baja")
    public String darBajaProducto(Producto producto) {
        productoService.darBajaProducto(producto);
        return "redirect:/modulo-producto/producto/lista";
    }

}
