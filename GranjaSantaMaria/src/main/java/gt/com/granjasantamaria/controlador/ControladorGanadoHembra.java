package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ControladorGanadoHembra {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images";

    @Autowired
    private GanadoHembraService ganadoHembraService;

    @Autowired
    private TipoGanadoService tipoGanadoService;

    @Autowired
    private RazaGanadoService razaGanadoService;

    @GetMapping("/modulo-ganado/ganado-hembra/lista")
    public String listadoGanadosHembra(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<GanadoHembra> ganadoHembraPage = ganadoHembraService.obtenerGanadoHembraPaginado(pageRequest);
        model.addAttribute("ganadoHembraPage", ganadoHembraPage);
        var ganadosHembra = ganadoHembraPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("ganadosHembra", ganadosHembra);
        return "/pages/modulo-ganado/ganado-hembra/ganado-hembra";
    }

    @GetMapping("/modulo-ganado/ganado-hembra/agregar")
    public String agregarGanadoHembra(GanadoHembra ganadoHembra, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        List<TipoGanado> listadoTiposGanadoHembra = listaTiposGanado.stream().filter(ganado -> {
            String tipoGanado = ganado.getNombreTipoGanado();
            return tipoGanado.equals("Vaca") || tipoGanado.equals("Novilla") || tipoGanado.equals("Ternera") || tipoGanado.equals("Becerra");
        }).collect(Collectors.toList());
        model.addAttribute("listaTiposGanado", listadoTiposGanadoHembra);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        return "/pages/modulo-ganado/ganado-hembra/modificar-ganado-hembra";
    }

    @PostMapping("/modulo-ganado/ganado-hembra/guardar")
    public String guardarGanadoHembra(@RequestParam("image") MultipartFile file, @Valid GanadoHembra ganadoHembra, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            ganadoHembra.setFotografia(file.getOriginalFilename());
            ganadoHembraService.guardarGanadoHembra(ganadoHembra);
            return "redirect:/modulo-ganado/ganado-hembra/lista";
        }
    }

    @GetMapping("/modulo-ganado/ganado-hembra/editar/{idGanadoHembra}")
    public String editarGanadoHembra(GanadoHembra ganadoHembra, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        List<TipoGanado> listadoTiposGanadoHembra = listaTiposGanado.stream().filter(ganado -> {
            String tipoGanado = ganado.getNombreTipoGanado();
            return tipoGanado.equals("Vaca") || tipoGanado.equals("Novilla") || tipoGanado.equals("Ternera") || tipoGanado.equals("Becerra");
        }).collect(Collectors.toList());
        model.addAttribute("listaTiposGanado", listadoTiposGanadoHembra);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        ganadoHembra = ganadoHembraService.encontrarGanadoHembra(ganadoHembra);
        model.addAttribute("ganadoHembra", ganadoHembra);
        return "/pages/modulo-ganado/ganado-hembra/modificar-ganado-hembra";
    }

    @GetMapping("/modulo-ganado/ganado-hembra/eliminar")
    public String eliminarGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembraService.eliminarGanadoHembra(ganadoHembra);
        return "redirect:/modulo-ganado/ganado-hembra/lista";
    }

    @GetMapping("/modulo-ganado/ganado-hembra/baja")
    public String darBajaGanadoHembra(GanadoHembra ganadoHembra) {
        ganadoHembraService.darBajaGanadoHembra(ganadoHembra);
        return "redirect:/modulo-ganado/ganado-hembra/lista";
    }

}
