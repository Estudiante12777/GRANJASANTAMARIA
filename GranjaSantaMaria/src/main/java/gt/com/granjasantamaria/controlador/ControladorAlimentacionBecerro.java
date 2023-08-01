package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.*;
import gt.com.granjasantamaria.servicio.*;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControladorAlimentacionBecerro {

    private final GanadoMachoService ganadoMachoService;

    private final AlimentacionBecerroService alimentacionBecerroService;

    @Autowired
    public ControladorAlimentacionBecerro(GanadoMachoService ganadoMachoService, AlimentacionBecerroService alimentacionBecerroService) {
        this.ganadoMachoService = ganadoMachoService;
        this.alimentacionBecerroService = alimentacionBecerroService;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/modulo-ganado/alimentacion-becerro")
    public String obtenerListadoAlimentacionBecerros(@RequestParam(defaultValue = "0") int pagina, Model model) {
        PageRequest pageRequest = PageRequest.of(pagina, 8);
        Page<AlimentacionBecerro> alimentacionBecerroPage = alimentacionBecerroService.obtenerListadoAlimentacionBecerroPaginado(pageRequest);
        model.addAttribute("alimentacionBecerroPage", alimentacionBecerroPage);
        var alimentacionBecerro = alimentacionBecerroPage.getContent().stream().limit(8).collect(Collectors.toList());
        model.addAttribute("alimentacionBecerro", alimentacionBecerro);
        return "/pages/modulo-ganado/alimentacion-becerro/alimentacion";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/lista")
    public String obtenerListadoAlimentacionBecerros(@RequestParam("idProduccionDiariaLeche") Long idProduccionDiariaLeche, Model model) {
        String jpqlQuery = "SELECT gm.nombreGanadoMacho, a.fechaAlimentacionBecerro, " + "a.cantidadManianaAlimentacion, a.cantidadTardeAlimentacion, a.totalAlimentacionBecerro, g.nombreGanadoHembra, " + "a.idAlimentacionBecerro " + "FROM AlimentacionBecerro AS a " + "INNER JOIN a.produccionDiariaLeche AS p " + "INNER JOIN a.ganadoMacho AS gm " + "INNER JOIN p.ganadoHembra AS g " + "WHERE p.idProduccionDiariaLeche = :idProduccionDiariaLeche " + "AND a.estadoAlimentacionBecerro = TRUE";
        TypedQuery<Object[]> query = entityManager.createQuery(jpqlQuery, Object[].class);
        query.setParameter("idProduccionDiariaLeche", idProduccionDiariaLeche);
        List<Object[]> results = query.getResultList();
        model.addAttribute("alimentacionBecerroList", results);
        return "/pages/modulo-ganado/alimentacion-becerro/alimentacion-becerro";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/agregar")
    public String agregarAlimentacionBecerro(AlimentacionBecerro alimentacionBecerro, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos().stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Ternero") || ganado.getTipoGanado().getNombreTipoGanado().equals("Becerro")).collect(Collectors.toList());
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
    public String editarAlimentacionBecerro(@PathVariable("idAlimentacionBecerro") Long idAlimentacionBecerro, AlimentacionBecerro alimentacionBecerro, Model model) {
        List<GanadoMacho> listaGanados = ganadoMachoService.obtenerListadoGanadoMachos();
        // Filtrar la lista de ganado para mostrar solo vacas y novillas
        List<GanadoMacho> listaTernerosBecerros = listaGanados.stream().filter(ganado -> ganado.getTipoGanado().getNombreTipoGanado().equals("Ternero") || ganado.getTipoGanado().getNombreTipoGanado().equals("Becerro")).collect(Collectors.toList());
        System.out.println("Lista de ganados: " + listaGanados);
        System.out.println("Lista de Terneras y Becerras: " + listaTernerosBecerros);
        model.addAttribute("listaGanados", listaTernerosBecerros);
        model.addAttribute("idAlimentacionBecerro", idAlimentacionBecerro);
        alimentacionBecerro = alimentacionBecerroService.encontrarAlimentacionBecerro(alimentacionBecerro);
        model.addAttribute("alimentacionBecerro", alimentacionBecerro);
        return "/pages/modulo-ganado/alimentacion-becerro/modificar-alimentacion-becerro";
    }

    @GetMapping("/modulo-ganado/alimentacion-becerro/baja/{idAlimentacionBecerro}")
    public String darDeBajaAlimentacionBecerro(@PathVariable("idAlimentacionBecerro") Long idAlimentacionBecerro) {
        AlimentacionBecerro alimentacionBecerro = new AlimentacionBecerro();
        alimentacionBecerro.setIdAlimentacionBecerro(idAlimentacionBecerro);
        alimentacionBecerroService.darDeBajaAlimentacionBecerro(alimentacionBecerro);
        return "redirect:/modulo-produccion-lacteos/produccion-diaria-leche/lista";
    }

}
