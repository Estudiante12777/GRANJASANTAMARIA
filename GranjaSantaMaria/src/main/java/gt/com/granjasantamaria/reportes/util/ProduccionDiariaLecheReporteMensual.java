package gt.com.granjasantamaria.reportes.util;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author gerso
 */
@Component("/pages/modulo-produccion-lacteos/produccion-diaria-leche/produccion-diaria-leche")
public class ProduccionDiariaLecheReporteMensual extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document dcmnt, PdfWriter writer, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        List<ProduccionDiariaLeche> obtenerListaProduccionDiariaLeche = (List<ProduccionDiariaLeche>) map.get("listaProduccionDiariaLeche");
        PdfPTable tablaProduccionDiaria = new PdfPTable(6);
        obtenerListaProduccionDiariaLeche.forEach(produccionDiariaLeche -> {
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getIdProduccionDiariaLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getGanadoHembra().getNombreGanadoHembra());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getFechaProduccionLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getProduccionManianaLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getProduccionTardeLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getTotalProduccionLeche().toString());
        });
        dcmnt.add(tablaProduccionDiaria);
    }

}
