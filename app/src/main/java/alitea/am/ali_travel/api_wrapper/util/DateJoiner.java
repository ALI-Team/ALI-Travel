package alitea.am.ali_travel.api_wrapper.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by axel on 09/03/17.
 */

public class DateJoiner {
    public static GregorianCalendar getCalendarFromDateTime(String dateStr, String timeStr) throws ParseException {
        GregorianCalendar dateC = new GregorianCalendar(DateFormats.SV_LOCALE);

        Date date = DateFormats.DATE_FORMAT.parse(dateStr);
        dateC.setTime(date);

        Date time = DateFormats.TIME_FORMAT.parse(timeStr);

        GregorianCalendar timeCalendar = new GregorianCalendar(DateFormats.SV_LOCALE);
        timeCalendar.setTime(time);

        dateC.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        dateC.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
        dateC.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

        return dateC;
    }
}
