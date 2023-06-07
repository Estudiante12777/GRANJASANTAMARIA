package gt.com.granjasantamaria.reportes;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;

import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

@Component("/pages/modulo-produccion-lacteos/produccion-diaria-leche/produccion-diaria-leche")
public class ProduccionLecheMensual extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        List<ProduccionDiariaLeche> obtenerListaProduccionDiariaLeche = (List<ProduccionDiariaLeche>) model.get("listaProduccionDiariaLeche");
        document.setPageSize(PageSize.LETTER.rotate());
        document.open();
        document.setMargins(-20, -20, 40 , 40);
        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Reporte - diario - produccion de leche"));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(173, 255, 47));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(20);
        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);
        PdfPTable tablaProduccionDiaria = new PdfPTable(6);
        tablaProduccionDiaria.setHeaderRows(1);
        obtenerListaProduccionDiariaLeche.forEach(produccionDiariaLeche -> {
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getIdProduccionDiariaLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getGanadoHembra().getNombreGanadoHembra());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getFechaProduccionLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getProduccionManianaLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getProduccionTardeLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getTotalProduccionLeche().toString());
        });
        document.add(tablaTitulo);
        document.add(tablaProduccionDiaria);
    }

}
