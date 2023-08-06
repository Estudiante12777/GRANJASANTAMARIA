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
public class ControladorGanadoMacho {

    private final RazaGanadoService razaGanadoService;

    private final TipoGanadoService tipoGanadoService;

    private final GanadoMachoService ganadoMachoService;

    @Autowired
    public ControladorGanadoMacho(RazaGanadoService razaGanadoService, TipoGanadoService tipoGanadoService, GanadoMachoService ganadoMachoService) {
        this.razaGanadoService = razaGanadoService;
        this.tipoGanadoService = tipoGanadoService;
        this.ganadoMachoService = ganadoMachoService;
    }

    @GetMapping("/modulo-ganado/ganado-macho/lista")
    public String listadoGanadoMachods(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<GanadoMacho> ganadoMachoPage = ganadoMachoService.obtenerGanadoMachoPaginado(pageRequest);
        model.addAttribute("ganadoMachoPage", ganadoMachoPage);
        var ganadosMacho = ganadoMachoPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("ganadosMacho", ganadosMacho);
        return "pages/modulo-ganado/ganado-macho/ganado-macho";
    }

    @GetMapping("/modulo-ganado/ganado-macho/agregar")
    public String agregarGanadoMacho(GanadoMacho ganadoMacho, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado();
        List<TipoGanado> listadoTiposGanadoMacho = listaTiposGanado.stream().filter(ganado -> {
            String tipoGanado = ganado.getNombreTipoGanado();
            return tipoGanado.equals("Toro racero") || tipoGanado.equals("Torete") || tipoGanado.equals("Ternero") || tipoGanado.equals("Becerro");
        }).collect(Collectors.toList());
        model.addAttribute("listaTiposGanado", listadoTiposGanadoMacho);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        return "pages/modulo-ganado/ganado-macho/modificar-ganado-macho";
    }

    @PostMapping("/modulo-ganado/ganado-macho/guardar")
    public String guardarGanadoMacho(@RequestParam("image") MultipartFile file, @Valid GanadoMacho ganadoMacho, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            String absoluteUploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/images/";
            Path fileNameAndPath = Paths.get(absoluteUploadDirectory, file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
            ganadoMacho.setFotografia(file.getOriginalFilename());
            ganadoMachoService.guardarGanadoMacho(ganadoMacho);
            return "redirect:/modulo-ganado/ganado-macho/lista";
        }
    }

    @GetMapping("/modulo-ganado/ganado-macho/editar/{idGanadoMacho}")
    public String editarGanadoMacho(GanadoMacho ganadoMacho, Model model) {
        List<TipoGanado> listaTiposGanado = tipoGanadoService.listadoTiposGanado().stream().filter(ganado -> {
            String tipoGanado = ganado.getNombreTipoGanado();
            return tipoGanado.equals("Toro racero") || tipoGanado.equals("Torete") || tipoGanado.equals("Ternero") || tipoGanado.equals("Becerro");
        }).collect(Collectors.toList());
        model.addAttribute("listaTiposGanado", listaTiposGanado);
        List<RazaGanado> listaRazasGanado = razaGanadoService.listadoRazasGanado();
        model.addAttribute("listaRazasGanado", listaRazasGanado);
        ganadoMacho = ganadoMachoService.encontrarGanadoMacho(ganadoMacho);
        model.addAttribute("ganadoMacho", ganadoMacho);
        return "pages/modulo-ganado/ganado-macho/modificar-ganado-macho";
    }

    @GetMapping("/modulo-ganado/ganado-macho/baja")
    public String darBajaGanadoMacho(GanadoMacho ganadoMacho) {
        ganadoMachoService.darBajaGanadoMacho(ganadoMacho);
        return "redirect:/modulo-ganado/ganado-macho/lista";
    }

}
