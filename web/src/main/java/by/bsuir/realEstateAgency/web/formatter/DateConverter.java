package by.bsuir.realEstateAgency.web.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateConverter implements Formatter<Date> {
    final String defaultDateFormat = "yyyy-MM-dd";

    @Override
    public String print(Date object, Locale locale) {
        return new SimpleDateFormat(defaultDateFormat).format(object.getTime());
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(defaultDateFormat);
        return sdf.parse(text);
    }
}