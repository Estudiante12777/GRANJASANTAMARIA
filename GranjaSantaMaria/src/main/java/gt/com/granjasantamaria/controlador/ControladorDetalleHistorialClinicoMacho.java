package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.DetalleHistorialClinicoMacho;
import gt.com.granjasantamaria.modelo.HistorialClinicoMacho;
import gt.com.granjasantamaria.servicio.*;
import java.util.List;
import javax.persistence.*;
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
public class ControladorDetalleHistorialClinicoMacho {

    @Autowired
    private DetalleHistorialClinicoMachoService detalleHistorialClinicoMachoService;

    @Autowired
    private HistorialClinicioMachoService historialClinicioMachoService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/lista")
    public String obtenerListadoDetalleHistorialClinicoMacho(@RequestParam("idHistorialClinicoMacho") Long idHistorialClinicoMacho, Model model) {
        String sqlQuery = "SELECT gm.nombre_ganado_macho AS nombre_becerro, a.fecha_alimentacion_becerro, "
                + "a.cantidad_maniana_alimentacion, a.cantidad_tarde_alimentacion, g.nombre_ganado_hembra AS nombre_madre "
                + "FROM alimentacion_becerro AS a "
                + "INNER JOIN produccion_diaria_leche AS p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche "
                + "INNER JOIN ganado_macho AS gm ON gm.id_ganado_macho = a.id_ganado_macho "
                + "INNER JOIN ganado_hembra AS g ON g.id_ganado_hembra = p.id_ganado_hembra "
                + "WHERE p.id_produccion_diaria_leche = :idProduccionDiariaLeche";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idHistorialClinicoMacho", idHistorialClinicoMacho);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerroList", results);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/detalle-historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/agregar")
    public String agregarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, Model model) {
        List<HistorialClinicoMacho> listaGanados = historialClinicioMachoService.obtenerListadoHistorialClinicoMachos();
        model.addAttribute("listaGanados", listaGanados);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/modificar-detalle-historial-clinico-macho";
    }

    @PostMapping("/modulo-ganado/detalle-historial-clinico-macho/guardar")
    public String guardarDetalleHistorialClinicoMacho(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, BindingResult bindingResult, Model model) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception("Error, no puede estar vacío el campo");
        } else {
            detalleHistorialClinicoMachoService.guardarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
            return "redirect:/modulo-ganado/detalle-historial-clinico-macho/lista";
        }
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/editar/{idDetalleHistorialClinicoMacho}")
    public String editarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, Model model) {
        List<HistorialClinicoMacho> listaGanados = historialClinicioMachoService.obtenerListadoHistorialClinicoMachos();
        model.addAttribute("listaGanados", listaGanados);
        detalleHistorialClinicoMacho = detalleHistorialClinicoMachoService.encontrarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        model.addAttribute("detalleHistorialClinicoMacho", detalleHistorialClinicoMacho);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/modificar-detalle-historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/eliminar")
    public String eliminarDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        detalleHistorialClinicoMachoService.eliminarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        return "redirect:/modulo-ganado/detalle-historial-clinico-macho/lista";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/baja")
    public String darBajaDetalleHistorialClinicoMacho(DetalleHistorialClinicoMacho detalleHistorialClinicoMacho) {
        detalleHistorialClinicoMachoService.darBajaDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        return "redirect:/modulo-ganado/detalle-historial-clinico-macho/lista";
    }

}
