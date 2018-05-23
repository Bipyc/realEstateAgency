package by.bsuir.realEstateAgency.web.formatter;

import by.bsuir.realEstateAgency.web.facade.impl.DealFacadeImpl;
import org.apache.log4j.Logger;
import org.springframework.format.Formatter;

import java.text.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DateConverter implements Formatter<Date> {

    private static Logger log = Logger.getLogger(DealFacadeImpl.class.getName());

    private static final List<? extends DateFormat> DATE_FORMATS = Arrays.asList(
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("HH:mm"));

    private class DateTimeFormat extends DateFormat {

        @Override
        public StringBuffer format(final Date date, final StringBuffer toAppendTo, final FieldPosition fieldPosition) {
            return DATE_FORMATS.get(0).format(date, toAppendTo, fieldPosition);
        }

        @Override
        public Date parse(final String source, final ParsePosition pos) {
            Date res = null;
            for (final DateFormat dateFormat : DATE_FORMATS) {
                if ((res = dateFormat.parse(source, pos)) != null) {
                    return res;
                }
            }
            log.error("Can't parse value " + source);
            return null;
        }
    }

    @Override
    public String print(Date object, Locale locale) {
        return new DateTimeFormat().format(object.getTime());
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        DateTimeFormat dtf = new DateTimeFormat();
        return dtf.parse(text);
    }
}