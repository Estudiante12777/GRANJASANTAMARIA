package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ControladorAlimentacionBecerro {

    private final GanadoMachoService ganadoMachoService;

    private final AlimentacionBecerroService alimentacionBecerroService;

    private final ProduccionDiariaLecheService produccionDiariaLecheService;

    private final BecerroService becerroService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/modulo-ganado/alimentacion-becerro")
    public String obtenerListadoAlimentacionBecerros(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<AlimentacionBecerro> alimentacionBecerroPage = alimentacionBecerroService.obtenerListadoAlimentacionBecerroPaginado(pageRequest);
        model.addAttribute("alimentacionBecerroPage", alimentacionBecerroPage);
        var alimentacionBecerro = alimentacionBecerroPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("alimentacionBecerro", alimentacionBecerro);
        return "pages/modulo-ganado/alimentacion-becerro/alimentacion";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/lista")
    public String obtenerListadoAlimentacionBecerros(@RequestParam("idProduccionDiariaLeche") Long idProduccionDiariaLeche, Model model) {
        String sqlQuery = "SELECT gh.nombre_ganado_macho AS nombreBecerra, " +
                "a.fecha_alimentacion_becerro, " + "a.cantidad_maniana_alimentacion, " +
                "a.cantidad_tarde_alimentacion, " + "a.total_alimentacion_becerro, " +
                "m.nombre_ganado_hembra AS madreBecerra, " + "a.id_alimentacion_becerro " +
                "FROM alimentacion_becerro a " +
                "INNER JOIN produccion_diaria_leche p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche " +
                "INNER JOIN ganado_macho gh ON a.id_becerro = gh.id_ganado_macho " +
                "LEFT JOIN relacion_madre_becerro rmb ON gh.id_ganado_macho = rmb.id_becerro " +
                "LEFT JOIN ganado_hembra m ON rmb.id_madre = m.id_ganado_hembra " +
                "WHERE p.id_produccion_diaria_leche = :idProduccionDiariaLeche " +
                "AND a.estado_alimentacion_becerro = TRUE";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idProduccionDiariaLeche", idProduccionDiariaLeche);
        List<?> results = query.getResultList();
        model.addAttribute("alimentacionBecerroList", results);
        return "pages/modulo-ganado/alimentacion-becerro/alimentacion-becerro";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/agregar")
    public String agregarAlimentacionBecerro(@RequestParam Long idProduccionDiariaLeche, AlimentacionBecerro alimentacionBecerro, Model model) {
        ProduccionDiariaLeche produccionDiariaLeche = produccionDiariaLecheService.obtenerProduccionDiariaLechePorId(idProduccionDiariaLeche);
        List<Becerro> listadoBecerros = produccionDiariaLecheService.obtenerRelacionMadreBecerro(produccionDiariaLeche);
        model.addAttribute("listadoBecerros", listadoBecerros);
        return "pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
    }

    @PostMapping("/modulo-ganado/alimentacion-becerro/guardar")
    public String guardarAlimentacionBecerro(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) AlimentacionBecerro alimentacionBecerro, Model model) {
        try {
            alimentacionBecerroService.guardarAlimentacionBecerro(alimentacionBecerro);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        } catch (Exception e) {
            return "pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
        }
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/editar/{idAlimentacionBecerro}")
    public String editarAlimentacionBecerro(@PathVariable("idAlimentacionBecerro") Long idAlimentacionBecerro, AlimentacionBecerro alimentacionBecerro, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
        // Filtrar la lista de ganado para mostrar solo vacas y novillas
        List<GanadoMacho> listaTernerosBecerros = listaGanados.stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Ternero") || ganado.getTipoGanado().getNombreTipoGanado().equals("Becerro")).collect(Collectors.toList());
        model.addAttribute("listaGanados", listaTernerosBecerros);
        model.addAttribute("idAlimentacionBecerro", idAlimentacionBecerro);
        alimentacionBecerro = alimentacionBecerroService.encontrarAlimentacionBecerro(alimentacionBecerro);
        model.addAttribute("alimentacionBecerro", alimentacionBecerro);
        return "pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/baja/{idAlimentacionBecerro}")
    public String darDeBajaAlimentacionBecerro(@PathVariable("idAlimentacionBecerro") Long idAlimentacionBecerro) {
        AlimentacionBecerro alimentacionBecerro = new AlimentacionBecerro();
        alimentacionBecerro.setIdAlimentacionBecerro(idAlimentacionBecerro);
        alimentacionBecerroService.darDeBajaAlimentacionBecerro(alimentacionBecerro);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

    @GetMapping("/verificar-alimentacion-becerro/{idBecerro}")
    @ResponseBody
    public String verificarAlimentacionBecerro(@PathVariable("idBecerro") Long idBecerro) {
        Becerro becerro = becerroService.encontrarBecerroPorId(idBecerro);
        String mensaje = ""; // Variable para almacenar el mensaje
        if (becerro != null) {
            LocalDate fechaNacimiento = becerro.getFechaNacimiento();
            LocalDate hoy = LocalDate.now();
            LocalDate fechaTresMeses = fechaNacimiento.plusMonths(3);
            if (hoy.isBefore(fechaTresMeses)) {
                mensaje = "Alimentar en la mañana y en la tarde";
            } else if (hoy.isEqual(fechaTresMeses) || hoy.isBefore(fechaNacimiento.plusMonths(5))) {
                mensaje = "Reducir alimentación en la mañana y en la tarde";
            } else {
                mensaje = "No es necesario alimentar";
            }
        }
        return mensaje;
    }

}
