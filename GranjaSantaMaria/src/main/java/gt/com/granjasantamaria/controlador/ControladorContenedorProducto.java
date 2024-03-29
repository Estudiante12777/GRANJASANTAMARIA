package gt.com.granjasantamaria.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import java.util.stream.Collectors;

@Controller
public class ControladorContenedorProducto {

    private final ContenedorProductoService contenedorProductoService;

    @Autowired
    public ControladorContenedorProducto(ContenedorProductoService contenedorProductoService) {
        this.contenedorProductoService = contenedorProductoService;
    }

    @GetMapping("/modulo-producto/contenedor-producto/lista")
    public String obtenerListadContenedorProductos(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 10);
        Page<ContenedorProducto> contenedorProductoPage = contenedorProductoService.obtenerListadoContenedorProductoPaginado(pageRequest);
        model.addAttribute("contenedorProductoPage", contenedorProductoPage);
        var contenedorProductos = contenedorProductoPage.getContent().stream().limit(10).collect(Collectors.toList());
        model.addAttribute("contenedorProductos", contenedorProductos);
        return "pages/modulo-producto/contenedor-producto/contenedor-producto";
    }

    @GetMapping("/modulo-producto/contenedor-producto/agregar")
    public String agregarContenedorProducto(ContenedorProducto contenedorProducto) {
        return "pages/modulo-producto/contenedor-producto/modificar-contenedor-producto";
    }

    @PostMapping("/modulo-producto/contenedor-producto/guardar")
    public String guardarContenedorProducto(@Valid ContenedorProducto contenedorProducto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacio el campo");
        } else {
            contenedorProductoService.guardarContenedorProducto(contenedorProducto);
            return "redirect:/modulo-producto/contenedor-producto/lista";
        }
    }

    @GetMapping("/modulo-producto/contenedor-producto/editar/{idContenedorProducto}")
    public String editarContenedorProducto(ContenedorProducto contenedorProducto, Model model) {
        contenedorProducto = contenedorProductoService.encontrarContenedorProducto(contenedorProducto);
        model.addAttribute("contenedorProducto", contenedorProducto);
        return "pages/modulo-producto/contenedor-producto/modificar-contenedor-producto";
    }

    @GetMapping("/modulo-producto/contenedor-producto/baja")
    public String darBajaContenedorProducto(ContenedorProducto contenedorProducto) {
        contenedorProductoService.darBajaContenedorProducto(contenedorProducto);
        return "redirect:/modulo-producto/contenedor-producto/lista";
    }

}
