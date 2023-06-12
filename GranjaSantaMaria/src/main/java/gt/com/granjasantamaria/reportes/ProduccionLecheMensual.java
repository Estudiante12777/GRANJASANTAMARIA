package gt.com.granjasantamaria.reportes;

import com.lowagie.text.*;
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
        List<ProduccionDiariaLeche> listaProduccionDiariaLeche = (List<ProduccionDiariaLeche>) model.get("listaProduccionDiariaLeche");

        document.setPageSize(PageSize.A4.rotate());
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);

        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Reporte - diario - produccion de leche"));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(173, 255, 47));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        // Crea una tabla con 6 columnas
        PdfPTable tablaProduccionDiaria = new PdfPTable(6);

        float[] columnWidths = { 1f, 1f, 1.5f, 1f, 1f, 1f };
        tablaProduccionDiaria.setWidths(columnWidths);

        // Establece que la primera linea sera el header
        tablaProduccionDiaria.setHeaderRows(1);

        // Obtiene la celda por defecto de la tabla
        PdfPCell cellHeaderId = new PdfPCell(new Phrase("Id"));
        cellHeaderId.setBackgroundColor(Color.darkGray); // Estable el color de fondo de la celda a blue
        tablaProduccionDiaria.addCell(cellHeaderId);

        PdfPCell cellHeaderProductora = new PdfPCell(new Phrase("Productora"));
        cellHeaderProductora.setBackgroundColor(Color.darkGray);
        tablaProduccionDiaria.addCell(cellHeaderProductora);

        PdfPCell cellHeaderProduccionManiana = new PdfPCell(new Phrase("Produccion mañana"));
        cellHeaderProduccionManiana.setBackgroundColor(Color.darkGray);
        cellHeaderProduccionManiana.setNoWrap(true);
        tablaProduccionDiaria.addCell(cellHeaderProduccionManiana);

        PdfPCell cellHeaderProduccionTarde = new PdfPCell(new Phrase("Produccion tarde"));
        cellHeaderProduccionTarde.setBackgroundColor(Color.darkGray);
        tablaProduccionDiaria.addCell(cellHeaderProduccionTarde);

        PdfPCell cellHeaderTotalProduccion = new PdfPCell(new Phrase("Total produccion"));
        cellHeaderTotalProduccion.setBackgroundColor(Color.darkGray);
        tablaProduccionDiaria.addCell(cellHeaderTotalProduccion);

        PdfPCell cellHeaderFechaProduccion = new PdfPCell(new Phrase("Fecha"));
        cellHeaderFechaProduccion.setBackgroundColor(Color.darkGray);
        tablaProduccionDiaria.addCell(cellHeaderFechaProduccion);

        // Agrega los datos en cada fila como celdas
        listaProduccionDiariaLeche.forEach(produccionDiariaLeche -> {
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getIdProduccionDiariaLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getGanadoHembra().getNombreGanadoHembra());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getProduccionManianaLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getProduccionTardeLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getTotalProduccionLeche().toString());
            tablaProduccionDiaria.addCell(produccionDiariaLeche.getFechaProduccionLeche().toString());
        });

        document.add(tablaTitulo);
        document.add(tablaProduccionDiaria);
    }

}
