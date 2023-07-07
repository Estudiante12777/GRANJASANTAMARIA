package gt.com.granjasantamaria.reportes;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gt.com.granjasantamaria.modelo.InventarioProducto;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.print.PageFormat;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-inventario/inventario-producto/total-inventario-producto-fecha")
public class ReporteInventarioProductoFecha extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<InventarioProducto> listaInventarioProducto = (List<InventarioProducto>) model.get("totalInventarioProductoFecha");

        document.setPageSize(PageSize.A4.rotate());
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);

        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Reporte - Inventario Producto"));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(173, 255, 47));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaInventarioProducto = new PdfPTable(7);

        float[] columnWidths = {0.5f, 1f, 1f, 1f, 1f, 1f, 1f};
        tablaInventarioProducto.setWidths(columnWidths);

        // Establece que la primera linea sera el header
        tablaInventarioProducto.setHeaderRows(1);

        // Obtiene la celda por defecto de la tabla
        PdfPCell cellHeaderId = new PdfPCell(new Phrase("No."));
        cellHeaderId.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderId);

        PdfPCell cellHeaderFecha = new PdfPCell(new Phrase("Fecha"));
        cellHeaderFecha.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderFecha);

        PdfPCell cellHeaderProducto = new PdfPCell(new Phrase("Producto"));
        cellHeaderProducto.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderProducto);

        PdfPCell cellHeaderCantidadIngresada = new PdfPCell(new Phrase("Ingreso"));
        cellHeaderCantidadIngresada.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderCantidadIngresada);

        PdfPCell cellHeaderCantidadSalida = new PdfPCell(new Phrase("Salida"));
        cellHeaderCantidadSalida.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderCantidadSalida);

        PdfPCell cellHeaderCantidadFinal = new PdfPCell(new Phrase("Final"));
        cellHeaderCantidadFinal.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderCantidadFinal);

        PdfPCell cellHeaderCantidadVendida = new PdfPCell(new Phrase("Vendido"));
        cellHeaderCantidadVendida.setBackgroundColor(Color.LIGHT_GRAY);
        tablaInventarioProducto.addCell(cellHeaderCantidadVendida);

        // Calcular la suma de productos ingresados
        long sumaProductosIngresados = listaInventarioProducto.stream().mapToLong(InventarioProducto::getCantidadIngresadaProducto).sum();

        // Calcular la suma de productos vendidos
        long sumaProductosVendidos = listaInventarioProducto.stream().mapToLong(InventarioProducto::getCantidadVendidaHastaHoy).sum();

        listaInventarioProducto.forEach(inventarioProducto -> {
            tablaInventarioProducto.addCell(inventarioProducto.getIdInventarioProducto().toString());
            tablaInventarioProducto.addCell(inventarioProducto.getFechaInventarioProducto().toString());
            tablaInventarioProducto.addCell(inventarioProducto.getDetalleProducto().toString());
            tablaInventarioProducto.addCell(inventarioProducto.getCantidadIngresadaProducto().toString());
            tablaInventarioProducto.addCell(inventarioProducto.getCantidadSalidaProducto().toString());
            tablaInventarioProducto.addCell(inventarioProducto.getCantidadFinalProducto().toString());
            tablaInventarioProducto.addCell(inventarioProducto.getCantidadVendidaHastaHoy().toString());
        });

        document.add(tablaTitulo);
        document.add(tablaInventarioProducto);

        PdfPTable tablaFooter = new PdfPTable(1);
        tablaFooter.setWidthPercentage(100);

        PdfPCell celdaFooterTotalIngreso = new PdfPCell(new Phrase("Productos Ingresados: " + String.valueOf(sumaProductosIngresados)));
        celdaFooterTotalIngreso.setPaddingTop(15);
        celdaFooterTotalIngreso.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotalIngreso.setVerticalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotalIngreso.setBorder(0);

        PdfPCell celdaFooterTotalVendido = new PdfPCell(new Phrase("Productos Vendidos: " + String.valueOf(sumaProductosVendidos)));
        celdaFooterTotalVendido.setPaddingTop(5);
        celdaFooterTotalVendido.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotalVendido.setVerticalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotalVendido.setBorder(0);

        tablaFooter.setSpacingAfter(20);
        tablaFooter.addCell(celdaFooterTotalIngreso);
        tablaFooter.addCell(celdaFooterTotalVendido);

        document.add(tablaFooter);

    }

}
