package gt.com.granjasantamaria.reportes;

import gt.com.granjasantamaria.modelo.InventarioProducto;
import gt.com.granjasantamaria.modelo.VentaProducto;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-inventario/inventario-producto/total-inventario-producto-fecha.xlsx")
public class ReporteInventarioProductoFechaExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<InventarioProducto> listaInventarioProducto = (List<InventarioProducto>) model.get("totalInventarioProductoFecha");

        response.setHeader("Content-Disposition", "attachment; filename=\"reporte-inventario-de-productos.xlsx\"");

        Sheet reporteInventarioProducto = workbook.createSheet("Inventario-Producto");

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
        Row headerRow = reporteInventarioProducto.createRow(0);
        headerRow.createCell(0).setCellValue("No.");
        headerRow.createCell(1).setCellValue("Fecha");
        headerRow.createCell(2).setCellValue("Producto");
        headerRow.createCell(3).setCellValue("Ingreso");
        headerRow.createCell(4).setCellValue("Salida");
        headerRow.createCell(5).setCellValue("Final");
        headerRow.createCell(6).setCellValue("Vendido");

        // Aplicar estilo al encabezado
        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }

        // Llenar datos
        int rowNum = 1;
        for (InventarioProducto inventarioProducto : listaInventarioProducto) {
            Row row = reporteInventarioProducto.createRow(rowNum++);
            row.createCell(0).setCellValue(inventarioProducto.getIdInventarioProducto());
            row.createCell(1).setCellValue(inventarioProducto.getFechaInventarioProducto());
            row.createCell(2).setCellValue(inventarioProducto.getDetalleProducto().toString());
            row.createCell(3).setCellValue(inventarioProducto.getCantidadIngresadaProducto());
            row.createCell(4).setCellValue(inventarioProducto.getCantidadSalidaProducto());
            row.createCell(5).setCellValue(inventarioProducto.getCantidadFinalProducto());
            row.createCell(6).setCellValue(inventarioProducto.getCantidadVendidaHastaHoy());
            for (Cell cell : row) {
                cell.setCellStyle(dataStyle);
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < 7; i++) {
            reporteInventarioProducto.autoSizeColumn(i);
        }

        // Calcular la suma total de productos ingresados
        double sumaTotalCantidadIngresada = listaInventarioProducto.stream()
                .mapToDouble(InventarioProducto::getCantidadIngresadaProducto)
                .sum();

        // Calcular la suma total de productos vendidos
        double sumaTotalCantidadVendida = listaInventarioProducto.stream()
                .mapToDouble(InventarioProducto::getCantidadVendidaHastaHoy)
                .sum();

        // Crear la fila para el total de productos ingresados
        Row totalRow1 = reporteInventarioProducto.createRow(rowNum);
        totalRow1.createCell(0).setCellValue("Productos Ingresados: ");
        CellStyle totalLabelStyle1 = workbook.createCellStyle();
        Font totalLabelFont1 = workbook.createFont();
        totalLabelFont1.setBold(true);
        totalLabelStyle1.setFont(totalLabelFont1);
        totalRow1.getCell(0).setCellStyle(totalLabelStyle1);
        CellStyle totalValueStyle1 = workbook.createCellStyle();
        totalValueStyle1.setAlignment(HorizontalAlignment.CENTER);
        totalRow1.createCell(3).setCellValue(sumaTotalCantidadIngresada);
        totalRow1.getCell(3).setCellStyle(totalValueStyle1);

        // Crear la fila para el total de productos vendidos
        Row totalRow2 = reporteInventarioProducto.createRow(rowNum + 1);
        totalRow2.createCell(0).setCellValue("Productos Vendidos: ");
        CellStyle totalLabelStyle2 = workbook.createCellStyle();
        Font totalLabelFont2 = workbook.createFont();
        totalLabelFont2.setBold(true);
        totalLabelStyle2.setFont(totalLabelFont2);
        totalRow2.getCell(0).setCellStyle(totalLabelStyle2);
        CellStyle totalValueStyle2 = workbook.createCellStyle();
        totalValueStyle2.setAlignment(HorizontalAlignment.CENTER);
        totalRow2.createCell(3).setCellValue(sumaTotalCantidadVendida);
        totalRow2.getCell(3).setCellStyle(totalValueStyle2);

    }

}
