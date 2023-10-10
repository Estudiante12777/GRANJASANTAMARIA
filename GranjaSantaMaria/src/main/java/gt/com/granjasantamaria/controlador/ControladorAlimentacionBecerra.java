package gt.com.granjasantamaria.controlador;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

@Controller
@RequiredArgsConstructor
public class ControladorAlimentacionBecerra {

    private final AlimentacionBecerraService alimentacionBecerraService;

    private final BecerraService becerraService;

    private final ProduccionDiariaLecheService produccionDiariaLecheService;

    private static final Logger logger = LoggerFactory.getLogger(ControladorAlimentacionBecerra.class);

    @GetMapping("/modulo-ganado/alimentacion-becerra/agregar")
    public String agregarAlimentacionBecerra(@RequestParam Long idProduccionDiariaLeche, AlimentacionBecerra alimentacionBecerra, Model model) {
        ProduccionDiariaLeche produccionDiariaLeche = produccionDiariaLecheService.obtenerProduccionDiariaLechePorId(idProduccionDiariaLeche);
        List<Becerra> listadoBecerras = produccionDiariaLecheService.obtenerRelacionMadreBecerra(produccionDiariaLeche);
        model.addAttribute("listadoBecerras", listadoBecerras);
        model.addAttribute("alimentacionBecerra", alimentacionBecerra);
        return "pages/modulo-ganado/alimentacion-becerra/agregar-alimentacion-becerra";
    }

    @PostMapping("/modulo-ganado/alimentacion-becerra/guardar")
    public String guardarAlimentacionBecerra(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) AlimentacionBecerra alimentacionBecerra, Model model) {
        try {
            logger.debug("Iniciando el proceso de guardarAlimentacionBecerra...");
            logger.debug("ID de AlimentacionBecerra: " + alimentacionBecerra.getIdAlimentacionBecerra());
            alimentacionBecerraService.guardarAlimentacionBecerra(alimentacionBecerra);
            logger.debug("AlimentacionBecerra guardada con éxito.");
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        } catch (Exception e) {
            logger.debug("Error al guardar la alimentación de la becerra: " + e.getMessage(), e);
            model.addAttribute("error", "Error al guardar la alimentación de la becerra.");
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        }
    }

    @GetMapping("/verificar-alimentacion-becerra/{id_relacion_madre_becerra}")
    @ResponseBody
    public String verificarAlimentacionBecerra(@PathVariable("id_relacion_madre_becerra") Long id_relacion_madre_becerra) {
        Becerra becerra = becerraService.encontrarBecerraPorId(id_relacion_madre_becerra);
        String mensaje = ""; // Variable para almacenar el mensaje
        if (becerra != null) {
            LocalDate fechaNacimiento = becerra.getFechaNacimiento();
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
