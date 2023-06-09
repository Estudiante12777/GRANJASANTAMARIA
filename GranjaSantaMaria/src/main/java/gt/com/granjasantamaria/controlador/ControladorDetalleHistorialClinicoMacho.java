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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
        String sqlQuery = "SELECT gm.nombre_ganado_macho AS nombre_ganado, dhc.fecha_registro_historial_clinico, "
                + "dhc.descripcion_historial_clinico, dhc.id_detalle_historial_clinico_macho "
                + "FROM detalle_historial_clinico_macho AS dhc "
                + "INNER JOIN historial_clinico_macho AS h ON h.id_historial_clinico_macho = dhc.id_historial_clinico_macho "
                + "INNER JOIN ganado_macho AS gm ON gm.id_ganado_macho = h.id_ganado_macho "
                + "WHERE dhc.id_historial_clinico_macho = :idHistorialClinicoMacho "
                + "AND dhc.estado_detalle_historial_clinico_macho = 1"; // Agregar la condición para el estado
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idHistorialClinicoMacho", idHistorialClinicoMacho);
        List<Object[]> results = query.getResultList();
        model.addAttribute("detalleHistorialClinicoMachoList", results);
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
            FieldError fieldError = bindingResult.getFieldError();
            String fieldName = fieldError.getField();
            throw new Exception("Error el campo, " + fieldName + " no puede estar vacío el campo");
        } else {
            detalleHistorialClinicoMachoService.guardarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
            return "redirect:/modulo-ganado/historial-clinico-macho/lista";
        }
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/editar/{idDetalleHistorialClinicoMacho}")
    public String editarDetalleHistorialClinicoMacho(@PathVariable("idDetalleHistorialClinicoMacho") Long idDetalleHistorialClinicoMacho, DetalleHistorialClinicoMacho detalleHistorialClinicoMacho, Model model) {
        List<HistorialClinicoMacho> listaGanados = historialClinicioMachoService.obtenerListadoHistorialClinicoMachos();
        model.addAttribute("listaGanados", listaGanados);
        model.addAttribute("idDetalleHistorialClinicoMacho", idDetalleHistorialClinicoMacho);
        detalleHistorialClinicoMacho = detalleHistorialClinicoMachoService.encontrarDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        model.addAttribute("detalleHistorialClinicoMacho", detalleHistorialClinicoMacho);
        return "/pages/modulo-ganado/detalle-historial-clinico-macho/modificar-detalle-historial-clinico-macho";
    }

    @GetMapping("/modulo-ganado/detalle-historial-clinico-macho/baja/{idDetalleHistorialClinicoMacho}")
    public String darBajaDetalleHistorialClinicoMacho(@PathVariable("idDetalleHistorialClinicoMacho") Long idDetalleHistorialClinicoMacho) {
        DetalleHistorialClinicoMacho detalleHistorialClinicoMacho = new DetalleHistorialClinicoMacho();
        detalleHistorialClinicoMacho.setIdDetalleHistorialClinicoMacho(idDetalleHistorialClinicoMacho);
        detalleHistorialClinicoMachoService.darBajaDetalleHistorialClinicoMacho(detalleHistorialClinicoMacho);
        return "redirect:/modulo-ganado/historial-clinico-macho/lista";
    }

}
