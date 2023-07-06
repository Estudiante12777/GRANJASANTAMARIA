package gt.com.granjasantamaria.reportes;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha")
public class ReporteGastoGranjaFecha extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<DiarioGastoGranja> listaDiarioGastoGranja = (List<DiarioGastoGranja>) model.get("totalDiarioGastoFecha");

        document.setPageSize(PageSize.A4.rotate());
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);

        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Reporte - Gasto Granja"));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(173, 255, 47));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaDiarioGastoGranja = new PdfPTable(6);

        float[] columnWidths = {0.5f, 1f, 1f, 1f, 1f, 1f};
        tablaDiarioGastoGranja.setWidths(columnWidths);

        // Establece que la primera linea sera el header
        tablaDiarioGastoGranja.setHeaderRows(1);

        // Obtiene la celda por defecto de la tabla
        PdfPCell cellHeaderId = new PdfPCell(new Phrase("No."));
        cellHeaderId.setBackgroundColor(Color.LIGHT_GRAY);
        tablaDiarioGastoGranja.addCell(cellHeaderId);

        PdfPCell cellHeaderFecha = new PdfPCell(new Phrase("Fecha"));
        cellHeaderFecha.setBackgroundColor(Color.LIGHT_GRAY);
        tablaDiarioGastoGranja.addCell(cellHeaderFecha);

        PdfPCell cellHeaderProducto = new PdfPCell(new Phrase("Unidades"));
        cellHeaderProducto.setBackgroundColor(Color.LIGHT_GRAY);
        tablaDiarioGastoGranja.addCell(cellHeaderProducto);

        PdfPCell cellHeaderCantidadIngresada = new PdfPCell(new Phrase("Inversion"));
        cellHeaderCantidadIngresada.setBackgroundColor(Color.LIGHT_GRAY);
        tablaDiarioGastoGranja.addCell(cellHeaderCantidadIngresada);

        PdfPCell cellHeaderCantidadSalida = new PdfPCell(new Phrase("Valor unitario"));
        cellHeaderCantidadSalida.setBackgroundColor(Color.LIGHT_GRAY);
        tablaDiarioGastoGranja.addCell(cellHeaderCantidadSalida);

        PdfPCell cellHeaderCantidadFinal = new PdfPCell(new Phrase("Valor total"));
        cellHeaderCantidadFinal.setBackgroundColor(Color.LIGHT_GRAY);
        tablaDiarioGastoGranja.addCell(cellHeaderCantidadFinal);

        // Calcular la suma de productos ingresados
        double sumaGastoGranja = listaDiarioGastoGranja.stream().mapToDouble(DiarioGastoGranja::getValorTotal).sum();

        listaDiarioGastoGranja.forEach(diarioGastoGranja -> {
            tablaDiarioGastoGranja.addCell(diarioGastoGranja.getIdDiarioGastoGranja().toString());
            tablaDiarioGastoGranja.addCell(diarioGastoGranja.getFechaGasto().toString());
            tablaDiarioGastoGranja.addCell(diarioGastoGranja.getUnidadesAdquiridas().toString());
            tablaDiarioGastoGranja.addCell(diarioGastoGranja.getDetalleInversion());
            tablaDiarioGastoGranja.addCell(diarioGastoGranja.getValorUnitario().toString());
            tablaDiarioGastoGranja.addCell(diarioGastoGranja.getValorTotal().toString());
        });

        document.add(tablaTitulo);
        document.add(tablaDiarioGastoGranja);

        PdfPTable tablaFooter = new PdfPTable(1);
        tablaFooter.setWidthPercentage(100);

        PdfPCell celdaFooterTotal = new PdfPCell(new Phrase("Valor total"));
        celdaFooterTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotal.setBorder(0);
        celdaFooterTotal.setPadding(10);

        PdfPCell celdaTotalGasto = new PdfPCell(new Phrase("Gasto total: " + String.valueOf(sumaGastoGranja)));
        celdaTotalGasto.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaTotalGasto.setVerticalAlignment(Element.ALIGN_CENTER);
        celdaTotalGasto.setBorder(0);

        tablaFooter.setSpacingAfter(20);
        tablaFooter.addCell(celdaFooterTotal);
        tablaFooter.addCell(celdaTotalGasto);

        document.add(tablaFooter);

    }

}
