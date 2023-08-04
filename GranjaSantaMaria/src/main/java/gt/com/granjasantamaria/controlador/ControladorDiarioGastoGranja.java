package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import gt.com.granjasantamaria.reportes.ReporteGastoGranjaFecha;
import gt.com.granjasantamaria.reportes.ReporteGastoGranjaFechaExcel;
import gt.com.granjasantamaria.servicio.DiarioGastoGranjaService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControladorDiarioGastoGranja {

    private final DiarioGastoGranjaService diarioGastoGranjaService;

    @Autowired
    public ControladorDiarioGastoGranja(DiarioGastoGranjaService diarioGastoGranjaService) {
        this.diarioGastoGranjaService = diarioGastoGranjaService;
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/lista")
    public String listaDiarioGastosGranja(Model model) {
        var listaDiarioGastosGranja = diarioGastoGranjaService.obtenerListadoDiarioGastosGranja();
        model.addAttribute("listaDiarioGastosGranja", listaDiarioGastosGranja);
        return "pages/modulo-gasto/gasto-diario-granja/gasto-diario-granja";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha")
    public String obtenerListaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja, Model model) {
        var listaTotalDiarioGastoGranja = diarioGastoGranjaService.obtenerListaTotalDiarioGastoGranja();
        model.addAttribute("listaTotalDiarioGastoGranja", listaTotalDiarioGastoGranja);
        return "pages/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/encontrar-total-gasto-diario-fecha")
    public String encontrarTotalDiarioGastoGranja(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(defaultValue = "0") int pagina, Model model) {
        int pageSize = 10; // Tamaño de cada página
        PageRequest pageRequest = PageRequest.of(pagina, pageSize);
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        Page<DiarioGastoGranja> diarioGastoGranjaPage = diarioGastoGranjaService.obtenerListaTotalDiarioGastoGranjaPaginadoPorFecha(inicio, fin, pageRequest);
        List<DiarioGastoGranja> totalDiarioGastoFecha = diarioGastoGranjaPage.getContent(); // Obtener los elementos de la página actual
        model.addAttribute("totalDiarioGastoFecha", totalDiarioGastoFecha);
        model.addAttribute("diarioGastoGranjaPage", diarioGastoGranjaPage);
        return "pages/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha/pdf")
    public ModelAndView generarPDFTotalDiaroGastoGranja(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Obtener todos los registros de producción de leche para el rango de fechas dado
        List<DiarioGastoGranja> totalDiarioGastoFecha = diarioGastoGranjaService.encontrarTotalDiarioGastoGranja(inicio, fin);
        // Obtener el número total de registros
        long totalRegistros = totalDiarioGastoFecha.size();
        // Crear el modelo para el PDF
        Map<String, Object> model = new HashMap<>();
        model.put("totalRegistros", totalRegistros);
        model.put("totalDiarioGastoFecha", totalDiarioGastoFecha);
        // Devolver la vista PDF y el modelo
        return new ModelAndView(new ReporteGastoGranjaFecha(), model);
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha/excel")
    public ModelAndView generarExcelTotalDiaroGastoGranja(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin) {
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        // Obtener todos los registros de producción de leche para el rango de fechas dado
        List<DiarioGastoGranja> totalDiarioGastoFecha = diarioGastoGranjaService.encontrarTotalDiarioGastoGranja(inicio, fin);
        // Obtener el número total de registros
        long totalRegistros = totalDiarioGastoFecha.size();
        // Crear el modelo para el PDF
        Map<String, Object> model = new HashMap<>();
        model.put("totalRegistros", totalRegistros);
        model.put("totalDiarioGastoFecha", totalDiarioGastoFecha);
        // Devolver la vista PDF y el modelo
        return new ModelAndView(new ReporteGastoGranjaFechaExcel(), model);
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/agregar")
    public String agregarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        return "pages/modulo-gasto/gasto-diario-granja/modificar-gasto-diario-granja";
    }

    @PostMapping("/modulo-gasto/gasto-diario-granja/guardar")
    public String guardarDiarioGastoGranja(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) DiarioGastoGranja diarioGastoGranja, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            diarioGastoGranjaService.guardarDiarioGastoGranja(diarioGastoGranja);
            return "redirect:/modulo-gasto/gasto-diario-granja/lista";
        }
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/editar/{idDiarioGastoGranja}")
    public String editarDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja, Model model) {
        diarioGastoGranja = diarioGastoGranjaService.encontrarDiarioGastoGranja(diarioGastoGranja);
        model.addAttribute("diarioGastoGranja", diarioGastoGranja);
        return "pages/modulo-gasto/gasto-diario-granja/modificar-gasto-diario-granja";
    }

    @GetMapping("/modulo-gasto/gasto-diario-granja/baja")
    public String darBajaDiarioGastoGranja(DiarioGastoGranja diarioGastoGranja) {
        diarioGastoGranjaService.darBajaDiarioGastoGranja(diarioGastoGranja);
        return "redirect:/modulo-gasto/gasto-diario-granja/lista";
    }

}
