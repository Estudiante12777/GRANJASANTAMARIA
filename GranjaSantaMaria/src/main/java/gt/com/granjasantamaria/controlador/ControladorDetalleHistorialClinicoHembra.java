package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class ControladorDetalleHistorialClinicoHembra {

    @Autowired
    private DetalleHistorialClinicoHembraService detalleHistorialClinicoHembraService;

    @Autowired
    private HistorialClinicoHembraService historialClinicoHembraService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/lista")
    public String obtenerListadoDetalleHistorialClinicoHembra(@RequestParam("idHistorialClinicoHembra") Long idHistorialClinicoHembra, Model model) {
        String sqlQuery = "SELECT gm.nombre_ganado_macho AS nombre_becerro, a.fecha_alimentacion_becerro, "
                + "a.cantidad_maniana_alimentacion, a.cantidad_tarde_alimentacion, g.nombre_ganado_hembra AS nombre_madre "
                + "FROM alimentacion_becerro AS a "
                + "INNER JOIN produccion_diaria_leche AS p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche "
                + "INNER JOIN ganado_macho AS gm ON gm.id_ganado_macho = a.id_ganado_macho "
                + "INNER JOIN ganado_hembra AS g ON g.id_ganado_hembra = p.id_ganado_hembra "
                + "WHERE p.id_produccion_diaria_leche = :idProduccionDiariaLeche";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idProduccionDiariaLeche", idHistorialClinicoHembra);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerroList", results);
        return "/pages/modulo-ganado/detalle-historial-clinico-hembra/detalle-historial-clinico-hembra";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/agregar")
    public String agregarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra, Model model) {
        List<HistorialClinicoHembra> listaGanados = historialClinicoHembraService.obtenerListadoHistorialClinicoHembras();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-ganado/detalle-historial-clinico-hembra/modificar-detalle-historial-clinico-hembra";
    }

    @PostMapping("/modulo-ganado/detalle-historial-clinico-hembra/guardar")
    public String guardarDetalleHistorialClinicoHembra(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) DetalleHistorialClinicoHembra detalleHistorialClinicoHembra, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            detalleHistorialClinicoHembraService.guardarDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
            return "redirect:/modulo-ganado/detalle-historial-clinico-hembra/lista";
        }
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/editar/{idDetalleHistorialClinicoHembra}")
    public String editarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra, Model model) {
        List<HistorialClinicoHembra> listaGanados = historialClinicoHembraService.obtenerListadoHistorialClinicoHembras();
        model.addAttribute("listaGanados", listaGanados);
        detalleHistorialClinicoHembra = detalleHistorialClinicoHembraService.encontrarDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
        model.addAttribute("detalleHistorialClinicoHembra", detalleHistorialClinicoHembra);
        return "/pages/modulo-ganado/detalle-historial-clinico-hembra/modificar-detalle-historial-clinico-hembra";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/eliminar")
    public String eliminarDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembraService.eliminarDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
        return "redirect:/modulo-ganado/detalle-historial-clinico-hembra/lista";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-hembra/baja")
    public String darBajaDetalleHistorialClinicoHembra(DetalleHistorialClinicoHembra detalleHistorialClinicoHembra) {
        detalleHistorialClinicoHembraService.darBajaDetalleHistorialClinicoHembra(detalleHistorialClinicoHembra);
        return "redirect:/modulo-ganado/detalle-historial-clinico-hembra/lista";
    }

}
