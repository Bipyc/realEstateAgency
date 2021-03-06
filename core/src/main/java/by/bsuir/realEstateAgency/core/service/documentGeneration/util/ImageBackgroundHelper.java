package by.bsuir.realEstateAgency.core.service.documentGeneration.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public  class ImageBackgroundHelper extends PdfPageEventHelper {
    private Image img;
    public ImageBackgroundHelper(Image img) {
        this.img = img;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            writer.getDirectContentUnder().addImage(img);
        } catch (DocumentException ignored) {
        }
    }
}
