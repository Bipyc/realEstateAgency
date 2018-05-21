package by.bsuir.realEstateAgency.core.service.documentGeneration.impl;

import by.bsuir.realEstateAgency.core.exception.ServiceException;
import by.bsuir.realEstateAgency.core.service.DealService;
import by.bsuir.realEstateAgency.core.service.documentGeneration.util.ExcelUtil;
import by.bsuir.realEstateAgency.core.service.documentGeneration.util.ImageBackgroundHelper;
import by.bsuir.realEstateAgency.core.service.documentGeneration.util.PdfUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.Security;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

@Service
public class YearStatisticDocumentService extends AbstractDocumentService<Integer> {

    static Logger log = Logger.getLogger(VisitingScheduleDocumentService.class.getName());

    @Resource
    private DealService dealService;

    @Override
    public ByteArrayOutputStream generatePdf(Integer id) throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();
        document.setMargins(0, 0, 100, 50);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        Security.addProvider(new BouncyCastleProvider());
        writer.setEncryption(null, PdfUtil.OWNER_PASSWORD.getBytes(), 0 , PdfWriter.STANDARD_ENCRYPTION_128);
        try {
            document.open();
            Image image = Image.getInstance(Objects.requireNonNull(this.getClass().getClassLoader().getResource("background.png")));
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            writer.setPageEvent(new ImageBackgroundHelper(image));

            Calendar startCal = Calendar.getInstance();
            startCal.add(Calendar.YEAR, -1);
            startCal.add(Calendar.MONTH, 1);
            int currentYear = startCal.get(Calendar.YEAR);
            int currentMonth = startCal.get(Calendar.MONTH);
            startCal.clear();

            startCal.set(Calendar.YEAR, currentYear);
            startCal.set(Calendar.MONTH, currentMonth);
            startCal.set(Calendar.DATE, 1);

            Calendar finishCal = (Calendar) startCal.clone();
            finishCal.add(Calendar.MONTH, 1);

            PdfUtil.addTitle(document, "Year statistic");
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{1,1,1});
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Month");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Count deals");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Summary commission");
            for (int i=0; i<12;i++) {
                Object[] objects = dealService.getSumDealInTimeInterval(startCal.getTime(), finishCal.getTime());
                PdfUtil.addCell(table, Element.ALIGN_LEFT,Integer.toString(startCal.get(Calendar.MONTH)+1)+"."+Integer.toString(startCal.get(Calendar.YEAR)),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,Long.toString((Long) objects[0]),1);
                if(objects[1] != null){
                    PdfUtil.addCell(table, Element.ALIGN_LEFT,"$" +objects[1].toString(),1);
                }
                else{
                    PdfUtil.addCell(table, Element.ALIGN_LEFT,"$0.00",1);
                }
                finishCal.add(Calendar.MONTH, 1);
                startCal.add(Calendar.MONTH, 1);
            }
            document.add(table);
        }
        catch (Exception e){
            log.error("Document Generation error", e);
            throw new ServiceException(e);
        }
        finally {
            document.close();
        }
        Security.addProvider(new BouncyCastleProvider());
        writer.setEncryption(PdfUtil.USER_PASSWORD.getBytes(), PdfUtil.OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);
        //saveFile("yearStatistic", "pdf", outputStream);

        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateCSV(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        CSVWriter csvWriter = new CSVWriter(writer, ';');

        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.YEAR, -1);
        startCal.add(Calendar.MONTH, 1);
        int currentYear = startCal.get(Calendar.YEAR);
        int currentMonth = startCal.get(Calendar.MONTH);
        startCal.clear();

        startCal.set(Calendar.YEAR, currentYear);
        startCal.set(Calendar.MONTH, currentMonth);
        startCal.set(Calendar.DATE, 1);

        Calendar finishCal = (Calendar) startCal.clone();
        finishCal.add(Calendar.MONTH, 1);


        csvWriter.writeNext(new String[]{"Month", "Count deals", "Summary commission"});
        for (int i=0; i<12;i++) {
            Object[] objects = dealService.getSumDealInTimeInterval(startCal.getTime(), finishCal.getTime());
            String commission = "$0.00";
            if(objects[1] != null){
                commission = "$" +objects[1].toString();
            }
            csvWriter.writeNext(new String[]{Integer.toString(startCal.get(Calendar.MONTH)+1)+"."+Integer.toString(startCal.get(Calendar.YEAR)),
                    Long.toString((Long) objects[0]), commission});
            finishCal.add(Calendar.MONTH, 1);
            startCal.add(Calendar.MONTH, 1);
        }
        csvWriter.flush();
        csvWriter.close();
        //saveFile("yearStatistic", "csv", outputStream);
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateExcel(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet agreementInfo = workbook.createSheet("Year statistic");
        java.util.List<Object[]> wardStat = new ArrayList<>();


        Calendar startCal = Calendar.getInstance();
        startCal.add(Calendar.YEAR, -1);
        startCal.add(Calendar.MONTH, 1);
        int currentYear = startCal.get(Calendar.YEAR);
        int currentMonth = startCal.get(Calendar.MONTH);
        startCal.clear();

        startCal.set(Calendar.YEAR, currentYear);
        startCal.set(Calendar.MONTH, currentMonth);
        startCal.set(Calendar.DATE, 1);

        Calendar finishCal = (Calendar) startCal.clone();
        finishCal.add(Calendar.MONTH, 1);


        wardStat.add(new String[]{"Month", "Count deals", "Summary commission"});
        for (int i=0; i<12;i++) {
            Object[] objects = dealService.getSumDealInTimeInterval(startCal.getTime(), finishCal.getTime());
            String commission = "$0.00";
            if(objects[1] != null){
                commission = "$" +objects[1].toString();
            }
            wardStat.add(new String[]{Integer.toString(startCal.get(Calendar.MONTH)+1)+"."+Integer.toString(startCal.get(Calendar.YEAR)),
                    Long.toString((Long) objects[0]), commission});
            finishCal.add(Calendar.MONTH, 1);
            startCal.add(Calendar.MONTH, 1);
        }

        ExcelUtil.createTable(agreementInfo, wardStat, "Year statistic", 0, 0);
        styleTable(workbook, agreementInfo);
        workbook.write(outputStream);
        //saveFile("yearStatistic", "xlsx", outputStream);

        return outputStream;
    }
}