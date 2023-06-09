package gt.com.granjasantamaria.controlador;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;
import org.springframework.validation.BindingResult;

@Controller
public class ControladorAlimentacionBecerra {

    @Autowired
    private AlimentacionBecerraService alimentacionBecerraService;

    @Autowired
    private GanadoHembraService ganadoHembraService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/modulo-ganado/alimentacion-becerra")
    public String obtenerListadoAlimentacionBecerras(Model model) {
        String sqlQuery = "SELECT gh.nombre_ganado_hembra AS nombreBecerra, a.fecha_alimentacion_becerra, " +
                "a.cantidad_maniana_alimentacion, a.cantidad_tarde_alimentacion, a.total_alimentacion_becerra, " +
                "m.nombre_ganado_hembra AS madreBecerra, " +
                "a.id_alimentacion_becerra " +
                "FROM alimentacion_becerra AS a " +
                "INNER JOIN produccion_diaria_leche AS p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche " +
                "INNER JOIN ganado_hembra AS gh ON gh.id_ganado_hembra = a.id_ganado_hembra " +
                "INNER JOIN ganado_hembra AS m ON m.id_ganado_hembra = p.id_ganado_hembra";
        Query query = entityManager.createNativeQuery(sqlQuery);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerraList", results);
        return "/pages/modulo-ganado/alimentacion-becerra/alimentacion";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/lista")
    public String obtenerListadoAlimentacionBecerras(@RequestParam("idProduccionDiariaLeche") Long idProduccionDiariaLeche, Model model) {
        String sqlQuery = "SELECT gh.nombre_ganado_hembra AS nombreBecerra, a.fecha_alimentacion_becerra, " +
                "a.cantidad_maniana_alimentacion, a.cantidad_tarde_alimentacion, a.total_alimentacion_becerra, " +
                "m.nombre_ganado_hembra AS madreBecerra, " +
                "a.id_alimentacion_becerra " +
                "FROM alimentacion_becerra AS a " +
                "INNER JOIN produccion_diaria_leche AS p ON a.id_produccion_diaria_leche = p.id_produccion_diaria_leche " +
                "INNER JOIN ganado_hembra AS gh ON gh.id_ganado_hembra = a.id_ganado_hembra " +
                "INNER JOIN ganado_hembra AS m ON m.id_ganado_hembra = p.id_ganado_hembra " +
                "WHERE p.id_produccion_diaria_leche = :idProduccionDiariaLeche";
        Query query = entityManager.createNativeQuery(sqlQuery);
        query.setParameter("idProduccionDiariaLeche", idProduccionDiariaLeche);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerraList", results);
        return "/pages/modulo-ganado/alimentacion-becerra/alimentacion-becerra";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/agregar")
    public String agregarAlimentacionBecerro(AlimentacionBecerra alimentacionBecerra, Model model) {
        List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
        // Filtrar la lista de ganado para mostrar solo vacas y novillas
        List<GanadoHembra> listaTernerasBecerras = listaGanados.stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Ternera")
                || ganado.getTipoGanado().getNombreTipoGanado().equals("Becerra")).collect(Collectors.toList());
        System.out.println("Lista de ganados: " + listaGanados);
        System.out.println("Lista de Terneras y Becerras: " + listaTernerasBecerras);
        model.addAttribute("listaGanados", listaTernerasBecerras);
        return "/pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
    }

    @PostMapping("/modulo-ganado/alimentacion-becerra/guardar")
    public String guardarProduccionDiariaLeche(@Valid @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) AlimentacionBecerra alimentacionBecerra, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
            model.addAttribute("listaGanados", listaGanados);
            return "/pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
        }
        try {
            alimentacionBecerraService.guardarAlimentacionBecerra(alimentacionBecerra);
            return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
        } catch (Exception e) {
            model.addAttribute("error", "Error al guardar la producción diaria de leche.");
            List<GanadoHembra> listaGanados = ganadoHembraService.obtenerListadoGanadosHembra();
            model.addAttribute("listaGanados", listaGanados);
            return "/pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
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
        return "/pages/modulo-ganado/alimentacion-becerra/modificar-alimentacion-becerra";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/eliminar")
    public String eliminarAlimentacionBecerro(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerraService.eliminarAlimentacionBecerra(alimentacionBecerra);
        return "redirect:/modulo-ganado/alimentacion-becerra/lista";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerra/baja")
    public String darDeBajaAlimentacionBecerra(AlimentacionBecerra alimentacionBecerra) {
        alimentacionBecerraService.darDeBajaAlimentacionBecerra(alimentacionBecerra);
        return "redirect:/modulo-ganado/alimentacion-becerra/lista";
    }

}
