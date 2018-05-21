package by.bsuir.realEstateAgency.core.service.documentGeneration.impl;

import by.bsuir.realEstateAgency.core.exception.ServiceException;
import by.bsuir.realEstateAgency.core.model.City;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.CityService;
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
import java.util.List;
import java.util.Objects;

@Service
public class CityStatisticDocumentService extends AbstractDocumentService<Integer> {

    static Logger log = Logger.getLogger(CityStatisticDocumentService.class.getName());

    @Resource
    private CityService cityService;

    @Resource
    private ApplicationService applicationService;

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

            PdfUtil.addTitle(document, "City statistic");
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{1,1,1});
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "City");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Count orders");
            PdfUtil.addTableHeader(table, Element.ALIGN_LEFT, "Count complete orders");
            List<City> cities = cityService.getAll();
            for (City city: cities) {
                Long[] longs = applicationService.applicationCountByCity(city.getId());
                PdfUtil.addCell(table, Element.ALIGN_LEFT,city.getName(),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,Long.toString(longs[0]),1);
                PdfUtil.addCell(table, Element.ALIGN_LEFT,Long.toString(longs[1]),1);
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
        saveFile("cityStatistic", "pdf", outputStream);

        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateCSV(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(outputStream);
        CSVWriter csvWriter = new CSVWriter(writer, ';');
        List<City> cities = cityService.getAll();
        csvWriter.writeNext(new String[]{"City", "Count orders", "Count complete orders"});
        for (City city: cities) {
            Long[] longs = applicationService.applicationCountByCity(city.getId());
            csvWriter.writeNext(new String[]{city.getName(), Long.toString(longs[0]),Long.toString(longs[1])});
        }
        csvWriter.flush();
        csvWriter.close();
        saveFile("cityStatistic", "csv", outputStream);
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream generateExcel(Integer key) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<City> cities = cityService.getAll();

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet agreementInfo = workbook.createSheet("City Statistic");
        java.util.List<Object[]> wardStat = new ArrayList<>();
        wardStat.add(new Object[]{"City", "Count orders", "Count complete orders"});
        for (City city: cities) {
            Long[] longs = applicationService.applicationCountByCity(city.getId());
            wardStat.add(new String[]{city.getName(), Long.toString(longs[0]),Long.toString(longs[1])});
        }
        ExcelUtil.createTable(agreementInfo, wardStat, "City Statistic", 0, 1);
        styleTable(workbook, agreementInfo);
        workbook.write(outputStream);
        saveFile("schedule", "xlsx", outputStream);

        return outputStream;
    }
}