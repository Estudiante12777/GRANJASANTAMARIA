package gt.com.granjasantamaria.controlador;

import gt.com.granjasantamaria.servicio.DiarioGastoGranjaService;
import gt.com.granjasantamaria.servicio.VentaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorBalanceVentasGastos {

    private final VentaProductoService ventaProductoService;

    private final DiarioGastoGranjaService diarioGastoGranjaService;

    @Autowired
    public ControladorBalanceVentasGastos(VentaProductoService ventaProductoService, DiarioGastoGranjaService diarioGastoGranjaService) {
        this.ventaProductoService = ventaProductoService;
        this.diarioGastoGranjaService = diarioGastoGranjaService;
    }

    @GetMapping("/modulo-balance/balance/balance-fecha-actual")
    public String mostrarBalanaceFechaActual(Model model) {
        Double totalVentas = ventaProductoService.obtenerTotalVentas();
        Double totalGastos = diarioGastoGranjaService.obtenerTotalGasto();
        if (totalVentas == null || totalGastos == null) {
            model.addAttribute("totalVentas", 0.00);
            model.addAttribute("totalGastos", 0.00);
            model.addAttribute("balanceVentasGastos", 0.00);
        } else {
            model.addAttribute("totalVentas", totalVentas);
            model.addAttribute("totalGastos", totalGastos);
            double balanceVentasGastos = totalVentas - totalGastos;
            model.addAttribute("balanceVentasGastos", balanceVentasGastos);
        }
        return "/pages/modulo-balance/balance/balance";
    }

}
