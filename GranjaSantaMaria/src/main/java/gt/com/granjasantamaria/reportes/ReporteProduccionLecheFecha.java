package gt.com.granjasantamaria.reportes;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha")
public class ReporteProduccionLecheFecha extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ProduccionDiariaLeche> listaProduccionDiariaLeche = (List<ProduccionDiariaLeche>) model.get("totalProduccionesFecha");

        document.setPageSize(PageSize.A4.rotate());
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);

        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Reporte - produccion de leche"));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(173, 255, 47));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaProduccionLeche = new PdfPTable(6);

        float[] columnWidths = {1f, 1f, 1.5f, 1f, 1f, 1f};
        tablaProduccionLeche.setWidths(columnWidths);

        // Establece que la primera linea sera el header
        tablaProduccionLeche.setHeaderRows(1);

        // Obtiene la celda por defecto de la tabla
        PdfPCell cellHeaderId = new PdfPCell(new Phrase("No."));
        cellHeaderId.setBackgroundColor(Color.darkGray); // Estable el color de fondo de la celda a blue
        tablaProduccionLeche.addCell(cellHeaderId);

        PdfPCell cellHeaderProductora = new PdfPCell(new Phrase("Productora"));
        cellHeaderProductora.setBackgroundColor(Color.darkGray);
        tablaProduccionLeche.addCell(cellHeaderProductora);

        PdfPCell cellHeaderProduccionManiana = new PdfPCell(new Phrase("Produccion mañana"));
        cellHeaderProduccionManiana.setBackgroundColor(Color.darkGray);
        cellHeaderProduccionManiana.setNoWrap(true);
        tablaProduccionLeche.addCell(cellHeaderProduccionManiana);

        PdfPCell cellHeaderProduccionTarde = new PdfPCell(new Phrase("Produccion tarde"));
        cellHeaderProduccionTarde.setBackgroundColor(Color.darkGray);
        tablaProduccionLeche.addCell(cellHeaderProduccionTarde);

        PdfPCell cellHeaderTotalProduccion = new PdfPCell(new Phrase("Total produccion"));
        cellHeaderTotalProduccion.setBackgroundColor(Color.darkGray);
        tablaProduccionLeche.addCell(cellHeaderTotalProduccion);

        PdfPCell cellHeaderFechaProduccion = new PdfPCell(new Phrase("Fecha"));
        cellHeaderFechaProduccion.setBackgroundColor(Color.darkGray);
        tablaProduccionLeche.addCell(cellHeaderFechaProduccion);

        // Agrega los datos en cada fila como celdas
        listaProduccionDiariaLeche.forEach(produccionDiariaLeche -> {
            tablaProduccionLeche.addCell(produccionDiariaLeche.getIdProduccionDiariaLeche().toString());
            tablaProduccionLeche.addCell(produccionDiariaLeche.getGanadoHembra().getNombreGanadoHembra());
            tablaProduccionLeche.addCell(produccionDiariaLeche.getProduccionManianaLeche().toString());
            tablaProduccionLeche.addCell(produccionDiariaLeche.getProduccionTardeLeche().toString());
            tablaProduccionLeche.addCell(produccionDiariaLeche.getTotalProduccionLeche().toString());
            tablaProduccionLeche.addCell(produccionDiariaLeche.getFechaProduccionLeche().toString());
        });

        document.add(tablaTitulo);
        document.add(tablaProduccionLeche);
    }

}
