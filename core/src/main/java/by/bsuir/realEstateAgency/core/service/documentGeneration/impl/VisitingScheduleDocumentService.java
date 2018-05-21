package by.bsuir.realEstateAgency.core.service.documentGeneration.impl;

import by.bsuir.realEstateAgency.core.exception.ServiceException;
import by.bsuir.realEstateAgency.core.model.Inspection;
import by.bsuir.realEstateAgency.core.service.InspectionService;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
public class VisitingScheduleDocumentService extends AbstractDocumentService<Integer> {

    static Logger log = Logger.getLogger(VisitingScheduleDocumentService.class.getName());

    @Resource
    private InspectionService inspectionService;

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat timeFormat = new SimpleDateFormat("HH:mm");

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

            java.util.List<Inspection> inspections = inspectionService.getAllToday(Long.valueOf(id));

            PdfUtil.addTitle(document, "Schedule on " + dateFormat.format(new Date()));
            PdfPTable table = new PdfPTable(4);
            table.setWidths(new int[]{1,2,2,2});
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Time");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Address");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Phone");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "First Name");
            for (Inspection inspection: inspections) {
                PdfUtil.addCell(table, Element.ALIGN_LEFT,timeFormat.format(inspection.getTime()),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,inspection.getImmobility().getAddress(),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,inspection.getImmobility().getOwner().getPhone(),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,inspection.getImmobility().getOwner().getFirstName(),1);
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
        saveFile("schedule", "pdf", outputStream);

        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateCSV(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        CSVWriter csvWriter = new CSVWriter(writer, ';');
        java.util.List<Inspection> inspections = inspectionService.getAllToday(Long.valueOf(key));

        csvWriter.writeNext(new String[]{"Time", "Address", "Phone", "First Name"});
        for (Inspection inspection: inspections) {
            csvWriter.writeNext(new String[]{timeFormat.format(inspection.getTime()),
                    inspection.getImmobility().getAddress(),
                    inspection.getImmobility().getOwner().getPhone(),
                    inspection.getImmobility().getOwner().getFirstName()});
        }
        csvWriter.flush();
        csvWriter.close();
        saveFile("schedule", "csv", outputStream);
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateExcel(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        java.util.List<Inspection> inspections = inspectionService.getAllToday(Long.valueOf(key));

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet agreementInfo = workbook.createSheet("Schedule");
        java.util.List<Object[]> wardStat = new ArrayList<>();
        wardStat.add(new Object[]{"Time", "Address", "Phone", "First Name"});
        for (Inspection inspection: inspections) {
            wardStat.add(new Object[]{timeFormat.format(inspection.getTime()),
                    inspection.getImmobility().getAddress(),
                    inspection.getImmobility().getOwner().getPhone(),
                    inspection.getImmobility().getOwner().getFirstName()});
        }
        ExcelUtil.createTable(agreementInfo, wardStat, "Schedule on " + dateFormat.format(new Date()), 0, 1);
        styleTable(workbook, agreementInfo);
        workbook.write(outputStream);
        saveFile("schedule", "xlsx", outputStream);

        return outputStream;
    }
}
