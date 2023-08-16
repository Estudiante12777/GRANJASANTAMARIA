package gt.com.granjasantamaria.controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import org.springframework.validation.BindingResult;

@Controller
public class ControladorAlimentacionBecerra {

    private final AlimentacionBecerraService alimentacionBecerraService;

    private final GanadoHembraService ganadoHembraService;

    private final BecerraService becerraService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ControladorAlimentacionBecerra(AlimentacionBecerraService alimentacionBecerraService, GanadoHembraService ganadoHembraService, BecerraService becerraService) {
        this.alimentacionBecerraService = alimentacionBecerraService;
        this.ganadoHembraService = ganadoHembraService;
        this.becerraService = becerraService;
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra")
    public String obtenerListadoAlimentacionBecerrasPaginado(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<AlimentacionBecerra> alimentacionBecerraPage = alimentacionBecerraService.obtenerAlimentacionBecerraPaginado(pageRequest);
        model.addAttribute("alimentacionBecerraPage", alimentacionBecerraPage);
        var alimentacionBecerra = alimentacionBecerraPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("alimentacionBecerra", alimentacionBecerra);
        return "pages/modulo-ganado/alimentacion-becerra/alimentacion";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/lista")
    public String obtenerListadoAlimentacionBecerras(@RequestParam("idProduccionDiariaLeche") Long idProduccionDiariaLeche, Model model) {
        String jpqlQuery = "SELECT gh.nombreGanadoHembra AS nombreBecerra, a.fechaAlimentacionBecerra, " + "a.cantidadManianaAlimentacion, a.cantidadTardeAlimentacion, a.totalAlimentacionBecerra, " + "m.nombreGanadoHembra AS madreBecerra, " + "a.idAlimentacionBecerra " + "FROM AlimentacionBecerra AS a " + "INNER JOIN a.produccionDiariaLeche AS p " + "INNER JOIN a.ganadoHembra AS gh " + "INNER JOIN p.ganadoHembra AS m " + "WHERE p.idProduccionDiariaLeche = :idProduccionDiariaLeche " + "AND a.estadoAlimentacionBecerra = TRUE";
        TypedQuery<Object[]> query = entityManager.createQuery(jpqlQuery, Object[].class);
        query.setParameter("idProduccionDiariaLeche", idProduccionDiariaLeche);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerraList", results);
        return "pages/modulo-ganado/alimentacion-becerra/alimentacion-becerra";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/agregar")
    public String agregarAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra, Model model) {
        List<Becerra> listadoBecerras = becerraService.listadoBecerras();
        model.addAttribute("listadoBecerras", listadoBecerras);
        return "pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
    }

    @GetMapping("/verificar-alimentacion-becerra/{idBecerra}")
    @ResponseBody
    public String verificarAlimentacionBecerra(@PathVariable("idBecerra") Long idBecerra) {
        Becerra becerra = becerraService.encontrarBecerraPorId(idBecerra);
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

    @PostMapping("/modulo-ganado/alimentacion-becerra/guardar")
    public String guardarAlimentacionBecerra(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) AlimentacionBecerra alimentacionBecerra, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
            model.addAttribute("listaGanados", listaGanados);
            return "pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
        }
        try {
            alimentacionBecerraService.guardarAlimentacionBecerra(alimentacionBecerra);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la producción diaria de leche.");
            List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
            model.addAttribute("listaGanados", listaGanados);
            return "pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
        }
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/editar/{idAlimentacionBecerra}")
    public String editarAlimentacionBecerra(@PathVariable("idAlimentacionBecerra") Long idAlimentacionBecerra, AlimentacionBecerra alimentacionBecerra, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
        // Filtrar la lista de ganado para mostrar solo terneras y becerras
        List<GanadoHembra> listaTernerasBecerras = listaGanados.stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Ternera") || ganado.getTipoGanado().getNombreTipoGanado().equals("Becerra")).collect(Collectors.toList());
        System.out.println("Lista de ganados: " + listaGanados);
        System.out.println("Lista de Terneras y Becerras: " + listaTernerasBecerras);
        model.addAttribute("listaGanados", listaTernerasBecerras);
        model.addAttribute("idAlimentacionBecerra", idAlimentacionBecerra);
        alimentacionBecerra = alimentacionBecerraService.encontrarAlimentacionBecerra(alimentacionBecerra);
        model.addAttribute("alimentacionBecerra", alimentacionBecerra);
        return "pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/baja/{idAlimentacionBecerra}")
    public String darDeBajaAlimentacionBecerra(@PathVariable("idAlimentacionBecerra") Long idAlimentacionBecerra) {
        AlimentacionBecerra alimentacionBecerra = new AlimentacionBecerra();
        alimentacionBecerra.setIdAlimentacionBecerra(idAlimentacionBecerra);
        alimentacionBecerraService.darDeBajaAlimentacionBecerra(alimentacionBecerra);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

}
