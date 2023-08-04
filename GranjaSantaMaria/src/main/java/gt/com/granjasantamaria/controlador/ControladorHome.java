package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import gt.com.granjasantamaria.servicio.ProduccionDiariaLecheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class ControladorHome {

    private final ProduccionDiariaLecheService produccionDiariaLecheService;

    @Autowired
    public ControladorHome(ProduccionDiariaLecheService produccionDiariaLecheService) {
        this.produccionDiariaLecheService = produccionDiariaLecheService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        BigDecimal totalProduccionDiaria = produccionDiariaLecheService.obtenerListaTotalProduccionDiariaLeche().stream()
                .map(ProduccionDiariaLeche::getTotalProduccionLeche).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("totalProduccionDiaria", totalProduccionDiaria);
        return "inicio";
    }

}
