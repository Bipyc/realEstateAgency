package by.bsuir.realEstateAgency.core.service.documentGeneration.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfUtil {

    public static String USER_PASSWORD = "password";
    public static String OWNER_PASSWORD = "secured";

    public static Font boldFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 14, BaseColor.BLACK);
    public static Font textFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, BaseColor.BLACK);

    public static void addTitle(Document document, String text) throws DocumentException {
        Font titleFont = FontFactory.getFont("times.ttf", "Cp1251", 24, Font.BOLD);
        Paragraph title = new Paragraph(text, titleFont);
        title.setSpacingAfter(10f);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
    }

    public static void addUnderlinedHeader(Document document, String text, int alignment) throws DocumentException {
        Font underlineFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.UNDERLINE);

        Chunk patientChunk = new Chunk(text, underlineFont);
        Paragraph header = new Paragraph(patientChunk);
        header.setSpacingAfter(10f);
        header.setAlignment(alignment);
        document.add(header);
    }

    public static void addParagraph(Document document, Chunk ...chunks) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setSpacingAfter(10f);

        for (Chunk chunk : chunks) {
            paragraph.add(chunk);
        }

        document.add(paragraph);
    }

    public static void addListItem(Document document, Chunk chunk) throws DocumentException {
        Paragraph listItem = new Paragraph();

        Font zapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 8);
        Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);

        listItem.setSpacingAfter(10f);
        listItem.add(Chunk.TABBING);
        listItem.add(bullet);
        listItem.add("   ");
        listItem.add(chunk);

        document.add(listItem);
    }

    public static void addParagraphsToListItem(Document document, Chunk ...chunks) throws DocumentException {
        for (Chunk chunk : chunks) {
            Paragraph paragraph = new Paragraph();

            paragraph.setSpacingAfter(10f);
            paragraph.add(Chunk.TABBING);
            paragraph.add(Chunk.TABBING);
            paragraph.add(chunk);

            document.add(paragraph);
        }
    }

    public static void addTableHeader(PdfPTable table, int horizontalAlignment, String ...headers) {
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase(header, FontFactory.getFont("times.ttf", "Cp1251", 14, Font.BOLD)));
            cell.setLeft(0);
            cell.setBorder(0);
            cell.setHorizontalAlignment(horizontalAlignment);
            cell.setVerticalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

    public static PdfPCell CreateCell( int horizontalAlignment, String rowCell, int border){

        PdfPCell cell = new PdfPCell(new Phrase(rowCell, FontFactory.getFont("times.ttf", "Cp1251", 14, Font.NORMAL)));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setBorder(border);
        return cell;
    }

    public static void addCell(PdfPTable table, int horizontalAlignment, String rowCell) {
        PdfPCell cell = CreateCell(horizontalAlignment, rowCell, 0);
        table.addCell(cell);
    }

    public static void addCell(PdfPTable table, int horizontalAlignment, String rowCell, int border) {
        PdfPCell cell = CreateCell(horizontalAlignment, rowCell, border);
        table.addCell(cell);
    }
    public static void addTableRow(PdfPTable table, int horizontalAlignment, String ...rowCells) {
        for (String cellText : rowCells) {
            PdfPCell cell = CreateCell(horizontalAlignment, cellText, 0);
            table.addCell(cell);
        }
    }
}