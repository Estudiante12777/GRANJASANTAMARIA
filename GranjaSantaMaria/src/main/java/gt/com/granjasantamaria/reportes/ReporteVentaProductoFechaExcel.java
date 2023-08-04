package gt.com.granjasantamaria.reportes;

import gt.com.granjasantamaria.modelo.VentaProducto;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-venta/venta-producto/total-venta-producto-fecha.xlsx")
public class ReporteVentaProductoFechaExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<VentaProducto> listaVentaProducto = (List<VentaProducto>) model.get("totalVentaProductoFecha");

        response.setHeader("Content-Disposition", "attachment; filename=\"reporte-venta-de-productos.xlsx\"");

        Sheet reporteVentaProducto = workbook.createSheet("Venta-Producto");

        // Establecer estilos para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE1.getIndex());
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // Establecer estilos para los datos
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);

        // Crear la fila del encabezado
        Row headerRow = reporteVentaProducto.createRow(0);
        headerRow.createCell(0).setCellValue("No.");
        headerRow.createCell(1).setCellValue("Fecha");
        headerRow.createCell(2).setCellValue("Cliente");
        headerRow.createCell(3).setCellValue("Producto");
        headerRow.createCell(4).setCellValue("Cantidad");
        headerRow.createCell(5).setCellValue("Precio");
        headerRow.createCell(6).setCellValue("Descuento");
        headerRow.createCell(7).setCellValue("Total");

        // Aplicar estilo al encabezado
        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }

        // Llenar datos
        int rowNum = 1;
        for (VentaProducto ventaProducto : listaVentaProducto) {
            Row row = reporteVentaProducto.createRow(rowNum++);
            row.createCell(0).setCellValue(ventaProducto.getIdVentaProducto());
            row.createCell(1).setCellValue(ventaProducto.getFechaVentaProducto().toString());
            row.createCell(2).setCellValue(ventaProducto.getCliente().getNombreCliente() + " " + ventaProducto.getCliente().getApellidoCliente());
            row.createCell(3).setCellValue(ventaProducto.getInventarioProducto().getDetalleProducto().toString());
            row.createCell(4).setCellValue(ventaProducto.getCantidadProducto());
            row.createCell(5).setCellValue(ventaProducto.getPrecioPorUnidad().doubleValue());
            row.createCell(6).setCellValue(ventaProducto.getDescuentoProducto().doubleValue());
            row.createCell(7).setCellValue(ventaProducto.getTotalPrecioProducto().doubleValue());
            for (Cell cell : row) {
                cell.setCellStyle(dataStyle);
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < 8; i++) {
            reporteVentaProducto.autoSizeColumn(i);
        }

        // Calcular la suma total de la venta de productos
        BigDecimal sumaTotalVentaProducto = listaVentaProducto.stream().map(VentaProducto::getTotalPrecioProducto).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Crear la fila para el total
        Row totalRow = reporteVentaProducto.createRow(rowNum);
        totalRow.createCell(0).setCellValue("Total Venta");
        CellStyle totalLabelStyle = workbook.createCellStyle();
        Font totalLabelFont = workbook.createFont();
        totalLabelFont.setBold(true);
        totalLabelStyle.setFont(totalLabelFont);
        totalRow.getCell(0).setCellStyle(totalLabelStyle);
        CellStyle totalValueStyle = workbook.createCellStyle();
        totalValueStyle.setAlignment(HorizontalAlignment.CENTER);
        totalRow.createCell(7).setCellValue("Q. " + sumaTotalVentaProducto);
        totalRow.getCell(7).setCellStyle(totalValueStyle);
    }

}
