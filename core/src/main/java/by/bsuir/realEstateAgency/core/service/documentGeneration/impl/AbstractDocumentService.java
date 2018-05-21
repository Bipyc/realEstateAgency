package by.bsuir.realEstateAgency.core.service.documentGeneration.impl;

import com.itextpdf.text.DocumentException;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

abstract class AbstractDocumentService<T> {
    private static final String OUTPUT_DIRECTORY = "C:/Temp/";
    private static final Integer MAX_WIDTH = 200;
    static void saveFile(String type, String extension, ByteArrayOutputStream outputStream) throws IOException {
        java.sql.Timestamp currentTime = new java.sql.Timestamp(System.currentTimeMillis());
        String filename = type + "_" + currentTime.getTime() + "." + extension;
        File file = new File(AbstractDocumentService.OUTPUT_DIRECTORY + filename);
        file.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(AbstractDocumentService.OUTPUT_DIRECTORY + filename);
        fileOutputStream.write(outputStream.toByteArray());
        fileOutputStream.close();
    }

    private static Integer GetWidth(XSSFCell cell){
        XSSFFont xssfFont = cell.getCellStyle().getFont();
        FontFamily family = FontFamily.valueOf(((XSSFFont) xssfFont).getFamily());
        java.awt.Font font =  new Font(family.name(),Font.PLAIN, xssfFont.getFontHeightInPoints());
        FontMetrics metrics = new FontMetrics(font) {
        };
        Rectangle2D bounds = metrics.getStringBounds(cell.getStringCellValue() + " ", null);
        int widthInPixels = (int) bounds.getWidth();
        if(widthInPixels > MAX_WIDTH)
            return MAX_WIDTH;
        else
            return widthInPixels;
    }

    static void styleTable(XSSFWorkbook workbook, XSSFSheet sheet){
        XSSFCellStyle style = workbook.createCellStyle();
        style.setShrinkToFit(true);
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        Integer maxCount = 0;
        for(int i = 0; i <= sheet.getPhysicalNumberOfRows(); i++){
            if(sheet.getRow(i) == null) continue;
            Integer count = sheet.getRow(i).getPhysicalNumberOfCells();
            if(count > maxCount)
                maxCount = count;
        }

        ArrayList<Integer> columnMaxWidths = new ArrayList<>();
        for (int i = 0; i <= maxCount; i++)
            columnMaxWidths.add(0);

        for(int i = 0; i <= sheet.getPhysicalNumberOfRows(); i++){
            int maxWidth = 0;
            if(sheet.getRow(i) == null) continue;
            for(int j = 0; j <= sheet.getRow(i).getPhysicalNumberOfCells(); j++){
                if(sheet.getRow(i).getCell(j) == null) continue;
                XSSFCell cell = sheet.getRow(i).getCell(j);
                cell.getCellStyle().getFont();
                cell.setCellStyle(style);
                Integer width = GetWidth(cell);
                if(columnMaxWidths.get(j) < width)
                    columnMaxWidths.set(j, width);
            }
        }
        for(int i = 0; i < maxCount; i++)
            sheet.setColumnWidth(i, columnMaxWidths.get(i) * 50);
    }

    abstract ByteArrayOutputStream generatePdf(T key) throws IOException, DocumentException;
    abstract ByteArrayOutputStream generateCSV(T key) throws IOException;
    abstract ByteArrayOutputStream generateExcel(T key) throws IOException;
}
