package alitea.am.ali_travel.api_wrapper.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by axel on 05/03/17.
 */

public abstract class DateFormats {
    public static final Locale SV_LOCALE = new Locale("sv", "SE");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", SV_LOCALE);
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", SV_LOCALE);
}
