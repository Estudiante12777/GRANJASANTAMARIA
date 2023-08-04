package gt.com.granjasantamaria.reportes;

import gt.com.granjasantamaria.modelo.DiarioGastoGranja;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-gasto/gasto-diario-granja/total-gasto-diario-fecha.xlsx")
public class ReporteGastoGranjaFechaExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<DiarioGastoGranja> listaDiarioGastoGranja = (List<DiarioGastoGranja>) model.get("totalDiarioGastoFecha");

        response.setHeader("Content-Disposition", "attachment; filename=\"reporte-gasto-granja.xlsx\"");

        Sheet reporteGastoGranja = workbook.createSheet("Inventario-Producto");

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
        Row headerRow = reporteGastoGranja.createRow(0);
        headerRow.createCell(0).setCellValue("No.");
        headerRow.createCell(1).setCellValue("Fecha");
        headerRow.createCell(2).setCellValue("Unidades");
        headerRow.createCell(3).setCellValue("Inversion");
        headerRow.createCell(4).setCellValue("Valor unitario");
        headerRow.createCell(5).setCellValue("Valor total");

        // Aplicar estilo al encabezado
        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }

        // Llenar datos
        int rowNum = 1;
        for (DiarioGastoGranja diarioGastoGranja : listaDiarioGastoGranja) {
            Row row = reporteGastoGranja.createRow(rowNum++);
            row.createCell(0).setCellValue(diarioGastoGranja.getIdDiarioGastoGranja());
            row.createCell(1).setCellValue(diarioGastoGranja.getFechaGasto().toString());
            row.createCell(2).setCellValue(diarioGastoGranja.getUnidadesAdquiridas());
            row.createCell(3).setCellValue(diarioGastoGranja.getDetalleInversion());
            row.createCell(4).setCellValue(diarioGastoGranja.getValorUnitario().doubleValue());
            row.createCell(5).setCellValue(diarioGastoGranja.getValorTotal().doubleValue());
            for (Cell cell : row) {
                cell.setCellStyle(dataStyle);
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < 6; i++) {
            reporteGastoGranja.autoSizeColumn(i);
        }

        // Calcular la suma total gasto granja
        BigDecimal sumaTotalGastoGranja = listaDiarioGastoGranja.stream().map(DiarioGastoGranja::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Crear la fila para el total gasto granja
        Row totalRow = reporteGastoGranja.createRow(rowNum);
        totalRow.createCell(0).setCellValue("Gasto total: ");
        CellStyle totalLabelStyle = workbook.createCellStyle();
        Font totalLabelFont1 = workbook.createFont();
        totalLabelFont1.setBold(true);
        totalLabelStyle.setFont(totalLabelFont1);
        totalRow.getCell(0).setCellStyle(totalLabelStyle);
        CellStyle totalValueStyle = workbook.createCellStyle();
        totalValueStyle.setAlignment(HorizontalAlignment.CENTER);
        totalRow.createCell(6).setCellValue("Q. " + sumaTotalGastoGranja);
        totalRow.getCell(6).setCellStyle(totalValueStyle);

    }

}
