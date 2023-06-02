package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.persistence.*;
import javax.persistence.Query;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author gerso
 */
@Controller
public class ControladorAlimentacionBecerro {

    @Autowired
    private AlimentacionBecerroService alimentacionBecerroService;

    @Autowired
    private GanadoMachoService ganadoMachoService;

    @Autowired
    private ProduccionDiariaLecheService produccionDiariaLecheService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/modulo-ganado/alimentacion-becerro/lista")
    public String obtenerListadoAlimentacionBecerros(@RequestParam("idProduccionDiariaLeche") Long idProduccionDiariaLeche, Model model) {
        String sqlQuery = "SELECT gm.nombre_ganado_macho AS nombre_becerro, a.fecha_alimentacion_becerro, "
                + "a.cantidad_maniana_alimentacion, a.cantidad_tarde_alimentacion, g.nombre_ganado_hembra AS nombre_madre "
                + "FROM alimentacion_becerro AS a "
                + "INNER JOIN produccion_diaria_leche AS p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche "
                + "INNER JOIN ganado_macho AS gm ON gm.id_ganado_macho = a.id_ganado_macho "
                + "INNER JOIN ganado_hembra AS g ON g.id_ganado_hembra = p.id_ganado_hembra "
                + "WHERE p.id_produccion_diaria_leche = :idProduccionDiariaLeche";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idProduccionDiariaLeche", idProduccionDiariaLeche);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerroList", results);
        return "/pages/modulo-ganado/alimentacion-becerro/alimentacion-becerro";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/agregar")
    public String agregarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
    }

    @PostMapping("/modulo-ganado/alimentacion-becerro/guardar")
    public String guardarAlimentacionBecerro(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) AlimentacionBecerro alimentacionBecerro, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
            model.addAttribute("listaGanados", listaGanados);
            return "/pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
        }
        try {
            alimentacionBecerroService.guardarAlimentacionBecerro(alimentacionBecerro);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la producción diaria de leche.");
            List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
            model.addAttribute("listaGanados", listaGanados);
            return "/pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
        }
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/editar/{idAlimentacionBecerro}")
    public String editarAlimentacionBecerro(ProduccionDiariaLeche produccionDiariaLeche, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
        model.addAttribute("listaGanados", listaGanados);
        produccionDiariaLeche = produccionDiariaLecheService.encontrarProduccionDiariaLeche(produccionDiariaLeche);
        model.addAttribute("produccionDiariaLeche", produccionDiariaLeche);
        return "/pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/eliminar")
    public String eliminarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        alimentacionBecerroService.eliminarAlimentacionBecerro(alimentacionBecerro);
        return "redirect:/modulo-ganado/alimentacion-becerro/lista";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/baja")
    public String darDeBajaAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro) {
        alimentacionBecerroService.darDeBajaAlimentacionBecerro(alimentacionBecerro);
        return "redirect:/modulo-ganado/alimentacion-becerro/lista";
    }

}
