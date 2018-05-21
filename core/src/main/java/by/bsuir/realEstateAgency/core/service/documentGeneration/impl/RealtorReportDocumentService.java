package by.bsuir.realEstateAgency.core.service.documentGeneration.impl;

import by.bsuir.realEstateAgency.core.exception.ServiceException;
import by.bsuir.realEstateAgency.core.model.Realtor;
import by.bsuir.realEstateAgency.core.service.DealService;
import by.bsuir.realEstateAgency.core.service.UserService;
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
import java.math.BigDecimal;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class RealtorReportDocumentService extends AbstractDocumentService<Integer> {

    static Logger log = Logger.getLogger(VisitingScheduleDocumentService.class.getName());

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    private UserService userService;

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

            java.util.List<Realtor> realtors = userService.findAllRealtor();

            PdfUtil.addTitle(document, "Realtor report");
            PdfPTable table = new PdfPTable(6);
            table.setWidths(new int[]{1,2,2,2,2,2});
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Login");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "First Name");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Last Name");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Salary");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Count of Deals");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Average Commission");
            for (Realtor realtor : realtors) {
                Object[] listDeal = dealService.getDealAverageByUser(realtor.getId());
                PdfUtil.addCell(table, Element.ALIGN_LEFT,realtor.getLogin() ,1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,realtor.getFirstName() ,1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,realtor.getLastName() ,1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,realtor.getSalary().toString() ,1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,Long.toString((Long) listDeal[0]) ,1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,"$" + new BigDecimal((Double) listDeal[1]).setScale(2).toString() ,1);
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
        //saveFile("realtorReport", "pdf", outputStream);

        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateCSV(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        CSVWriter csvWriter = new CSVWriter(writer, ';');
        java.util.List<Realtor> realtors = userService.findAllRealtor();

        csvWriter.writeNext(new String[]{"Login", "First Name", "Last Name", "Patronymic", "Salary", "Count of Deals", "Average Commission"});
        for (Realtor realtor : realtors) {
            Object[] listDeal = dealService.getDealAverageByUser(realtor.getId());
            csvWriter.writeNext(new String[]{realtor.getLogin(), realtor.getFirstName(),
                    realtor.getLastName(), realtor.getPatronymic(), realtor.getSalary().toString(),
                    Long.toString((Long) listDeal[0]), "$" + new BigDecimal((Double) listDeal[1]).setScale(2).toString()});
        }
        csvWriter.flush();
        csvWriter.close();
        //saveFile("realtorsReport", "csv", outputStream);
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateExcel(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet agreementInfo = workbook.createSheet("Realtor report");
        java.util.List<Object[]> wardStat = new ArrayList<>();


        java.util.List<Realtor> realtors = userService.findAllRealtor();

        wardStat.add(new String[]{"Login", "First Name", "Last Name", "Patronymic", "Salary", "Count of Deals", "Average Commission"});
        for (Realtor realtor : realtors) {
            Object[] listDeal = dealService.getDealAverageByUser(realtor.getId());
            wardStat.add(new String[]{realtor.getLogin(), realtor.getFirstName(),
                    realtor.getLastName(), realtor.getPatronymic(), realtor.getSalary().toString(),
                    Long.toString((Long) listDeal[0]), "$" + new BigDecimal((Double) listDeal[1]).setScale(2).toString()});
        }
        ExcelUtil.createTable(agreementInfo, wardStat, "Realtor report", 0, 0);
        styleTable(workbook, agreementInfo);
        workbook.write(outputStream);
        //saveFile("realtorsReport", "xlsx", outputStream);

        return outputStream;
    }
}