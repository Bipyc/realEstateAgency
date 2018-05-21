package by.bsuir.realEstateAgency.web.controller.profile;

import by.bsuir.realEstateAgency.core.service.documentGeneration.impl.AbstractDocumentService;
import by.bsuir.realEstateAgency.web.exceptions.BadRequestException;
import by.bsuir.realEstateAgency.web.security.AuthUserDetails;
import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    static Logger log = Logger.getLogger(DocumentController.class.getName());

    @Resource
    private List<AbstractDocumentService<Integer>> abstractDocumentServices;

    @GetMapping
    public String getDocumentPage(){
        return "documentPage";
    }

    @GetMapping(params = {"type", "documentId"})
    public void generateDocument(@RequestParam("type") int type,
                                 @RequestParam("documentId") int documentId,
                                 HttpServletResponse httpServletResponse,
                                    Authentication authentication){
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if(documentId >= abstractDocumentServices.size() || documentId <0 || type<0 || type>2){
            log.error("Bad request get");
            throw new BadRequestException();
        }
        AbstractDocumentService<Integer> integerAbstractDocumentService = abstractDocumentServices.get(documentId);
        try {
            String filename;
            ByteArrayOutputStream outputStream = null;
            switch (type){
                case 0:
                    httpServletResponse.addHeader("Content-Type", "application/pdf");
                    filename = "output.pdf";
                    httpServletResponse.addHeader("Content-Disposition", "inline; filename=" + filename);
                    outputStream = integerAbstractDocumentService.generatePdf(userDetails.getUser().getId().intValue());
                    break;
                case 1:
                    httpServletResponse.addHeader("Content-Type", "application/vnd.ms-excel");
                    filename = "output.xlsx";
                    httpServletResponse.addHeader("Content-Disposition", "inline; filename=" + filename);
                    outputStream = integerAbstractDocumentService.generateExcel(userDetails.getUser().getId().intValue());
                    break;
                case 2:
                    httpServletResponse.addHeader("Content-Type", "application/vnd.ms-excel");
                    filename = "output.csv";
                    httpServletResponse.addHeader("Content-Disposition", "inline; filename=" + filename);
                    outputStream = integerAbstractDocumentService.generateCSV(userDetails.getUser().getId().intValue());
            }
            httpServletResponse.getOutputStream().write(outputStream.toByteArray());
        } catch (IOException | DocumentException e) {
            log.error("Internat server error",e);
            throw new RuntimeException(e);
        }
    }
}
