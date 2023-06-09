package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import gt.com.granjasantamaria.servicio.ProduccionDiariaLecheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorHome {

    @Autowired
    private ProduccionDiariaLecheService produccionDiariaLecheService;

    @GetMapping("/")
    public String inicio(Model model) {
        double totalProduccionDiaria = produccionDiariaLecheService.obtenerListaTotalProduccionDiariaLeche().stream()
                .mapToDouble(ProduccionDiariaLeche::getTotalProduccionLeche)
                .sum();
        model.addAttribute("totalProduccionDiaria", totalProduccionDiaria);
        return "inicio";
    }

}
