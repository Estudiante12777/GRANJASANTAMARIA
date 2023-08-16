package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.reportes.ReporteProduccionLecheFecha;
import gt.com.granjasantamaria.reportes.ReporteProduccionLecheFechaExcel;
import gt.com.granjasantamaria.servicio.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorProduccionDiaraLeche {

    private final GanadoHembraService ganadoHembraService;

    private final PreniesGanadoHembraService preniesGanadoHembraService;

    private final ProduccionDiariaLecheService produccionDiariaLecheService;

    @Autowired
    public ControladorProduccionDiaraLeche(GanadoHembraService ganadoHembraService, PreniesGanadoHembraService preniesGanadoHembraService, ProduccionDiariaLecheService produccionDiariaLecheService) {
        this.ganadoHembraService = ganadoHembraService;
        this.preniesGanadoHembraService = preniesGanadoHembraService;
        this.produccionDiariaLecheService = produccionDiariaLecheService;
    }

    private LocalDate parseFecha(String fecha) {
        return LocalDate.parse(fecha);
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/lista")
    public String listaProduccionDiariaLeche(Model model) {
        var listaProduccionDiariaLeche = produccionDiariaLecheService.obtenerListaProduccionDiariaLeche();
        BigDecimal sumaTotalProduccionDiariaLeche = listaProduccionDiariaLeche.stream().map(ProduccionDiariaLeche::getTotalProduccionLeche).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("listaProduccionDiariaLeche", listaProduccionDiariaLeche);
        model.addAttribute("sumaTotalProduccionDiariaLeche", sumaTotalProduccionDiariaLeche);
        model.addAttribute("produccionDiariaLecheService", produccionDiariaLecheService);
        return "pages/modulo-produccion-lacteos/produccion-diaria-leche/produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha")
    public String listaTotalProduccionFecha(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        var listaTotalProduccionFecha = produccionDiariaLecheService.obtenerListaTotalProduccionLeche();
        model.addAttribute("listaTotalProduccionFecha", listaTotalProduccionFecha);
        var listadoGanadoHembra = ganadoHembraService.obtenerListadoGanadosHembra();
        List<GanadoHembra> listaVacasNovillas = listadoGanadoHembra.stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Vaca") || ganado.getTipoGanado().getNombreTipoGanado().equals("Novilla")).collect(Collectors.toList());
        model.addAttribute("listadoGanadoHembra", listaVacasNovillas);
        return "pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/encontrar-total-produccion-fecha")
    public String encontrarTotalProduccionFecha(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(value = "ganadoHembra", required = false) Long idGanadoHembra, @RequestParam(defaultValue = "0") int pagina, Model model) {
        int pageSize = 10; // Tamaño de cada página
        PageRequest pageRequest = PageRequest.of(pagina, pageSize);
        // Convertir las fechas de String a LocalDate
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);
        Page<ProduccionDiariaLeche> produccionDiariaLechePage;
        if (Objects.isNull(idGanadoHembra) || idGanadoHembra.equals(Long.valueOf("0"))) {
            produccionDiariaLechePage = produccionDiariaLecheService.obtenerProduccionDiaraLechePaginadoPorFecha(inicio, fin, pageRequest);
        } else {
            produccionDiariaLechePage = produccionDiariaLecheService.obtenerProduccionDiaraLechePaginadoPorFechaAndIdGanadoHembra(inicio, fin, idGanadoHembra, pageRequest);
        }
        List<ProduccionDiariaLeche> totalProduccionesFecha = produccionDiariaLechePage.getContent(); // Obtener los elementos de la página actual
        BigDecimal sumaTotalProduccionDiariaLeche = totalProduccionesFecha.stream().map(ProduccionDiariaLeche::getTotalProduccionLeche).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalProduccionesFecha", totalProduccionesFecha);
        model.addAttribute("produccionDiariaLechePage", produccionDiariaLechePage);
        model.addAttribute("sumaTotalProduccionDiariaLeche", sumaTotalProduccionDiariaLeche);
        return "pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha";
    }

    private List<ProduccionDiariaLeche> obtenerProduccionPorFechaYPorGanado(LocalDate fechaInicio, LocalDate fechaFin, Long idGanadoHembra) {
        if (Objects.isNull(idGanadoHembra) || idGanadoHembra.equals(Long.valueOf("0"))) {
            return produccionDiariaLecheService.encontrarTotalProduccionFecha(fechaInicio, fechaFin);
        } else {
            return produccionDiariaLecheService.encontrarTotalProduccionFechaAndIdGanadoHembra(fechaInicio, fechaFin, idGanadoHembra);
        }
    }

    private Map<String, Object> produccionLecheReportes(String fechaInicio, String fechaFin, Long idGanadoHembra) {
        LocalDate inicio = parseFecha(fechaInicio);
        LocalDate fin = parseFecha(fechaFin);
        List<ProduccionDiariaLeche> totalProduccionesFecha = obtenerProduccionPorFechaYPorGanado(inicio, fin, idGanadoHembra);
        long totalRegistros = totalProduccionesFecha.size();
        Map<String, Object> model = new HashMap<>();
        model.put("totalProduccionesFecha", totalProduccionesFecha);
        model.put("totalRegistros", totalRegistros);
        return model;
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha/pdf")
    public ModelAndView generarPDFTotalProduccionFecha(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(value = "ganadoHembra", required = false) Long idGanadoHembra) {
        Map<String, Object> model = produccionLecheReportes(fechaInicio, fechaFin, idGanadoHembra);
        return new ModelAndView(new ReporteProduccionLecheFecha(), model);
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha/excel")
    public ModelAndView generarExcelTotalProduccionFecha(@RequestParam("fechaInicio") String fechaInicio, @RequestParam("fechaFin") String fechaFin, @RequestParam(value = "ganadoHembra", required = false) Long idGanadoHembra) {
        Map<String, Object> model = produccionLecheReportes(fechaInicio, fechaFin, idGanadoHembra);
        return new ModelAndView(new ReporteProduccionLecheFechaExcel(), model);
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/agregar")
    public String agregarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra().stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Vaca") || ganado.getTipoGanado().getNombreTipoGanado().equals("Novilla")).collect(Collectors.toList());
        model.addAttribute("listaGanados", listaGanados);
        return "pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
    }

    @GetMapping("/verificar-prenes/{idGanadoHembra}")
    @ResponseBody
    public int verificarPrenez(@PathVariable("idGanadoHembra") Long idGanadoHembra) {
        GanadoHembra ganadoHembra = ganadoHembraService.encontrarGanadoHembraPorId(idGanadoHembra);
        int prenado = 0; //Variable para guardar el número de días
        if (ganadoHembra != null) {
            List<PreniesGanadoHembra> prenies = preniesGanadoHembraService.obtenerListadoPreniesGanadoHembraPorGanadoHembra(ganadoHembra);
            for (PreniesGanadoHembra prenie : prenies) {
                if (prenie.isEstadoPreniesGanadoHembra()) {
                    //Obtener la fecha actual
                    LocalDate hoy = LocalDate.now();
                    //Calcular el número de días desde la fecha de preñes
                    long dias = ChronoUnit.DAYS.between(prenie.getFechaPrenies(), hoy);
                    //Comparar el número de días con los límites
                    if (dias == 189 || dias == 210) {
                        prenado = (int) dias; //Asignar el número de días a la variable
                    }
                    break;
                }
            }
        }
        return prenado;
    }

    @PostMapping("/modulo-produccion-lacteos/produccion-diaria-leche/guardar")
    public String guardarProduccionDiariaLeche(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) ProduccionDiariaLeche produccionDiariaLeche, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            produccionDiariaLecheService.guardarProduccionDiariaLeche(produccionDiariaLeche);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        }
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/editar/{idProduccionDiariaLeche}")
    public String editarProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
        // Filtrar la lista de ganado para mostrar solo vacas y novillas
        List<GanadoHembra> listaVacasNovillas = listaGanados.stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Vaca") || ganado.getTipoGanado().getNombreTipoGanado().equals("Novilla")).collect(Collectors.toList());
        model.addAttribute("listaGanados", listaVacasNovillas);
        produccionDiariaLeche = produccionDiariaLecheService.encontrarProduccionDiariaLeche(produccionDiariaLeche);
        model.addAttribute("produccionDiariaLeche", produccionDiariaLeche);
        return "pages/modulo-produccion-lacteos/produccion-diaria-leche/modificar-produccion-diaria-leche";
    }

    @GetMapping("/modulo-produccion-lacteos/produccion-diaria-leche/baja")
    public String darBajaProduccionDiariaLeche(ProduccionDiariaLeche produccionDiariaLeche) {
        produccionDiariaLecheService.darDeBajaProduccionDiariaLeche(produccionDiariaLeche);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

}
