package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.servicio.DiarioGastoGranjaService;
import gt.com.granjasantamaria.servicio.VentaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBalanceVentasGastos {

    @Autowired
    private VentaProductoService ventaProductoService;

    @Autowired
    private DiarioGastoGranjaService diarioGastoGranjaService;

    @GetMapping("/modulo-balance/balance/balance-fecha-actual")
    public String mostrarBalanaceFechaActual(Model model) {
        Double totalVentas = ventaProductoService.obtenerTotalVentas();
        model.addAttribute("totalVentas", totalVentas);
        Double totalGastos = diarioGastoGranjaService.obtenerTotalGasto();
        model.addAttribute("totalGastos", totalGastos);
        Double balanceVentasGastos = totalVentas - totalGastos;
        model.addAttribute("balanceVentasGastos", balanceVentasGastos);
        return "/pages/modulo-balance/balance/balance";
    }

}
