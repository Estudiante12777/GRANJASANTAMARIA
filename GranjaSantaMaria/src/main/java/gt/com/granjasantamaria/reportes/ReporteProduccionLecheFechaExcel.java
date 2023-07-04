package gt.com.granjasantamaria.reportes;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;
import gt.com.granjasantamaria.modelo.ProduccionDiariaLeche;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("/pages/modulo-produccion-lacteos/produccion-diaria-leche/total-produccion-fecha.xlsx")
public class ReporteProduccionLecheFechaExcel extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ProduccionDiariaLeche> listaProduccionDiariaLeche = (List<ProduccionDiariaLeche>) model.get("totalProduccionesFecha");

        response.setHeader("Content-Disposition", "attachment; filename=\"reporte-produccion-de-leche.xlsx\"");

        Sheet reporteProduccionLeche = workbook.createSheet("Produccion-Leche");

        // Establecer estilos para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        // Establecer estilos para los datos
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);

        // Crear la fila del encabezado
        Row headerRow = reporteProduccionLeche.createRow(0);
        headerRow.createCell(0).setCellValue("No.");
        headerRow.createCell(1).setCellValue("Fecha");
        headerRow.createCell(2).setCellValue("Productora");
        headerRow.createCell(3).setCellValue("Produccion mañana");
        headerRow.createCell(4).setCellValue("Produccion tarde");
        headerRow.createCell(5).setCellValue("Total produccion");

        // Aplicar estilo al encabezado
        for (Cell cell : headerRow) {
            cell.setCellStyle(headerStyle);
        }

        // Llenar datos
        int rowNum = 1;
        for (ProduccionDiariaLeche produccionDiariaLeche : listaProduccionDiariaLeche) {
            Row row = reporteProduccionLeche.createRow(rowNum++);
            row.createCell(0).setCellValue(produccionDiariaLeche.getIdProduccionDiariaLeche());
            row.createCell(1).setCellValue(produccionDiariaLeche.getFechaProduccionLeche().toString());
            row.createCell(2).setCellValue(produccionDiariaLeche.getGanadoHembra().getNombreGanadoHembra());
            row.createCell(3).setCellValue(produccionDiariaLeche.getProduccionManianaLeche());
            row.createCell(4).setCellValue(produccionDiariaLeche.getProduccionTardeLeche());
            row.createCell(5).setCellValue(produccionDiariaLeche.getTotalProduccionLeche());
            for (Cell cell : row) {
                cell.setCellStyle(dataStyle);
            }
        }

        // Ajustar el tamaño de las columnas
        for (int i = 0; i < 6; i++) {
            reporteProduccionLeche.autoSizeColumn(i);
        }

        // Calcular la suma total de la producción de leche
        double sumaTotalProduccion = listaProduccionDiariaLeche.stream()
                .mapToDouble(ProduccionDiariaLeche::getTotalProduccionLeche)
                .sum();

        // Crear la fila para el total
        Row totalRow = reporteProduccionLeche.createRow(rowNum);
        totalRow.createCell(0).setCellValue("Total produccion de leche");
        CellStyle totalLabelStyle = workbook.createCellStyle();
        Font totalLabelFont = workbook.createFont();
        totalLabelFont.setBold(true);
        totalLabelStyle.setFont(totalLabelFont);
        totalRow.getCell(0).setCellStyle(totalLabelStyle);
        CellStyle totalValueStyle = workbook.createCellStyle();
        totalValueStyle.setAlignment(HorizontalAlignment.CENTER);
        totalRow.createCell(5).setCellValue(sumaTotalProduccion + " Litros.");
        totalRow.getCell(5).setCellStyle(totalValueStyle);
    }

}
