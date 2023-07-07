package gt.com.granjasantamaria.reportes;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import gt.com.granjasantamaria.modelo.VentaProducto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-venta/venta-producto/total-venta-producto-fecha")
public class ReporteVentaProductoFecha extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<VentaProducto> listaVentaProducto = (List<VentaProducto>) model.get("totalVentaProductoFecha");

        document.setPageSize(PageSize.A4.rotate());
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);

        PdfPCell celda = null;
        celda = new PdfPCell(new Phrase("Reporte - Venta Producto"));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(173, 255, 47));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        PdfPTable tablaVentaProducto = new PdfPTable(8);

        float[] columnWidths = {0.5f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
        tablaVentaProducto.setWidths(columnWidths);

        //Establece que la primera linea sera el header
        tablaVentaProducto.setHeaderRows(1);

        //Obtiene la celda por defecto de la tabla
        PdfPCell cellHeaderId = new PdfPCell(new Phrase("No."));
        cellHeaderId.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderId);

        PdfPCell cellHeaderFecha = new PdfPCell(new Phrase("Fecha"));
        cellHeaderFecha.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderFecha);

        PdfPCell cellHeaderCliente = new PdfPCell(new Phrase("Cliente"));
        cellHeaderCliente.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderCliente);

        PdfPCell cellHeaderProducto = new PdfPCell(new Phrase("Producto"));
        cellHeaderProducto.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderProducto);


        PdfPCell cellHeaderCantidad = new PdfPCell(new Phrase("Cantidad"));
        cellHeaderCantidad.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderCantidad);

        PdfPCell cellHeaderPrecio = new PdfPCell(new Phrase("Precio"));
        cellHeaderPrecio.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderPrecio);

        PdfPCell cellHeaderDescuento = new PdfPCell(new Phrase("Descuento"));
        cellHeaderDescuento.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderDescuento);

        PdfPCell cellHeaderTotal = new PdfPCell(new Phrase("Total"));
        cellHeaderTotal.setBackgroundColor(Color.LIGHT_GRAY);
        tablaVentaProducto.addCell(cellHeaderTotal);

        //Antes de construir la tabla, calcula la suma total de la venta
        double sumaTotalVentaProducto = listaVentaProducto.stream().mapToDouble(VentaProducto::getTotalPrecioProducto).sum();

        //Agrega los datos en cada fila como celdas
        listaVentaProducto.forEach(ventaProducto -> {
            tablaVentaProducto.addCell(ventaProducto.getIdVentaProducto().toString());
            tablaVentaProducto.addCell(ventaProducto.getFechaVentaProducto().toString());
            tablaVentaProducto.addCell(ventaProducto.getCliente().getNombreCliente() + " " + ventaProducto.getCliente().getApellidoCliente());
            tablaVentaProducto.addCell(ventaProducto.getInventarioProducto().getDetalleProducto().toString());
            tablaVentaProducto.addCell(ventaProducto.getCantidadProducto().toString());
            tablaVentaProducto.addCell("Q. " + ventaProducto.getPrecioPorUnidad().toString());
            tablaVentaProducto.addCell("Q. " + ventaProducto.getDescuentoProducto().toString());
            tablaVentaProducto.addCell("Q. " +ventaProducto.getTotalPrecioProducto().toString());
        });

        document.add(tablaTitulo);
        document.add(tablaVentaProducto);

        PdfPTable tablaFooter = new PdfPTable(1);
        tablaFooter.setWidthPercentage(100);

        PdfPCell celdaFooterTotalVenta = new PdfPCell(new Phrase("Total venta: " +String.valueOf(sumaTotalVentaProducto) + " Quetzales"));
        celdaFooterTotalVenta.setPaddingTop(15);
        celdaFooterTotalVenta.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotalVenta.setVerticalAlignment(Element.ALIGN_CENTER);
        celdaFooterTotalVenta.setBorder(0);

        tablaFooter.setSpacingAfter(20);
        tablaFooter.addCell(celdaFooterTotalVenta);

        document.add(tablaFooter);

    }

}
