package by.bsuir.realEstateAgency.core.service.documentGeneration.impl;

import by.bsuir.realEstateAgency.core.exception.ServiceException;
import by.bsuir.realEstateAgency.core.model.Deal;
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
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@Service
public class HistoryDealsDocumentService extends AbstractDocumentService<Integer> {

    static Logger log = Logger.getLogger(HistoryDealsDocumentService.class.getName());

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    DecimalFormat df = new DecimalFormat();

    public HistoryDealsDocumentService() {
        df.setMaximumFractionDigits(2);

        df.setMinimumFractionDigits(0);

        df.setGroupingUsed(false);
    }

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

            Calendar cal = Calendar.getInstance();
            int currentYear = cal.get(Calendar.YEAR);
            cal.clear();

            cal.set(Calendar.YEAR, currentYear);
            cal.set(Calendar.MONTH, Calendar.JANUARY);
            cal.set(Calendar.DATE, 1);

            Date startDate = cal.getTime();
            cal.add(Calendar.YEAR, 1);
            Date finishDate = cal.getTime();



            java.util.List<Deal> deals = dealService.findAllInTimeIntervalBuUser(Long.valueOf(id), startDate, finishDate);

            PdfUtil.addTitle(document, "History Deals for " +currentYear+ " year");
            PdfPTable table = new PdfPTable(6);
            table.setWidths(new int[]{1,2,2,2,2,2});
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "ID");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Date");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Immob. ID");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Realtor");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Price");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Commiss.");
            for (Deal deal: deals) {
                PdfUtil.addCell(table, Element.ALIGN_LEFT,Long.toString(deal.getId()),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,dateFormat.format(deal.getDate()),1);

                if(deal.getApplication() != null && deal.getApplication().getImmobility() != null){
                    PdfUtil.addCell(table, Element.ALIGN_LEFT,Long.toString(deal.getApplication().getImmobility().getId()),1);
                }
                else {
                    PdfUtil.addCell(table, Element.ALIGN_LEFT,"NaN",1);
                }
                if(deal.getApplication() != null){
                    PdfUtil.addCell(table, Element.ALIGN_LEFT,deal.getApplication().getRealtor().getLogin(),1);
                }
                else {
                    PdfUtil.addCell(table, Element.ALIGN_LEFT,"NaN",1);
                }


                PdfUtil.addCell(table, Element.ALIGN_LEFT,"$"+deal.getPrice().toString() ,1);

                PdfUtil.addCell(table, Element.ALIGN_LEFT,"$"+deal.getCommission(),1);
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
        saveFile("historyDeals", "pdf", outputStream);

        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateCSV(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        CSVWriter csvWriter = new CSVWriter(writer, ';');

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        cal.clear();

        cal.set(Calendar.YEAR, currentYear);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        Date startDate = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date finishDate = cal.getTime();

        java.util.List<Deal> deals = dealService.findAllInTimeIntervalBuUser(Long.valueOf(key), startDate, finishDate);
        csvWriter.writeNext(new String[]{"ID", "Date", "Immob. ID", "Realtor", "Price", "Commiss."});
        for (Deal deal: deals) {
            String immobility = "NaN";
            String realtor = "NaN";

            if(deal.getApplication() != null && deal.getApplication().getImmobility() != null){
                immobility = String.valueOf(deal.getApplication().getImmobility().getId());
            }

            if(deal.getApplication() != null && deal.getApplication().getRealtor() != null){
                realtor = deal.getApplication().getRealtor().getLogin();
            }
            csvWriter.writeNext(new String[]{String.valueOf(deal.getId()), dateFormat.format(deal.getDate()), immobility, realtor,
                    "$"+deal.getPrice().toString(),"$"+deal.getCommission()});
        }
        csvWriter.flush();
        csvWriter.close();
        saveFile("historyDeals", "csv", outputStream);
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateExcel(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet agreementInfo = workbook.createSheet("History Deals");
        java.util.List<Object[]> wardStat = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        cal.clear();

        cal.set(Calendar.YEAR, currentYear);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);

        Date startDate = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date finishDate = cal.getTime();

        java.util.List<Deal> deals = dealService.findAllInTimeIntervalBuUser(Long.valueOf(key), startDate, finishDate);
        wardStat.add(new String[]{"ID", "Date", "Immob. ID", "Realtor", "Price", "Commiss."});
        for (Deal deal: deals) {

            String immobility = "NaN";
            String realtor = "NaN";

            if(deal.getApplication() != null && deal.getApplication().getImmobility() != null){
                immobility = String.valueOf(deal.getApplication().getImmobility().getId());
            }

            if(deal.getApplication() != null && deal.getApplication().getRealtor() != null){
                realtor = deal.getApplication().getRealtor().getLogin();
            }
            wardStat.add(new String[]{String.valueOf(deal.getId()), dateFormat.format(deal.getDate()), immobility, realtor,
                    "$"+deal.getPrice().toString(),"$"+deal.getCommission()});
        }

        ExcelUtil.createTable(agreementInfo, wardStat, "History Deals for " +currentYear+ " year", 0, 0);
        styleTable(workbook, agreementInfo);
        workbook.write(outputStream);
        saveFile("historyDeals", "xlsx", outputStream);

        return outputStream;
    }
}
