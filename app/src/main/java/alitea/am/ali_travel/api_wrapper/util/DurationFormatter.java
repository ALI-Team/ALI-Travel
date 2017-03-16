package alitea.am.ali_travel.api_wrapper.util;

import android.support.annotation.NonNull;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by axel on 13/03/17.
 */

public class DurationFormatter {
    /**
     * Ish standard compliant
     * https://xkcd.com/1171/
     *
     * @param iso String to be converted
     * @return formatted string
     */
    public static String ISO_8601ToHuman(String iso) {
        String regex = "P(\\d{1,4}Y)?(\\d{1,2}M)?(\\d{1,2}D)?(T(\\d{1,2}H)?(\\d{1,2}M)?(\\d{1,2}S)?)?";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(iso);

        String yr = null, mnth = null, day = null, hrs = null, min = null, sec = null;

        if (matcher.find()) {
            if (matcher.group(1) != null) {
                yr = popLast(matcher.group(1));
            }
            if (matcher.group(2) != null) {
                mnth = popLast(matcher.group(2));
            }
            if (matcher.group(3) != null) {
                day = popLast(matcher.group(3));
            }
            if (matcher.group(5) != null) {
                hrs = popLast(matcher.group(5));
            }
            if (matcher.group(6) != null) {
                min = popLast(matcher.group(6));
            }
            if (matcher.group(7) != null) {
                sec = popLast(matcher.group(7));
            }

            //TODO: maybe fix this?
            int h = 0, d = 0;
            if(day != null) {
                d = Integer.parseInt(day);
            }
            if(hrs != null) {
                h += Integer.parseInt(hrs);
            }

            h += 24 * d;
            if (h > 0) {
                hrs = Integer.toString(h);
            }
        }

        return String.format(DateFormats.SV_LOCALE, "%1$s:%2$s:%3$s", padZeros(hrs, 2),
                padZeros(min, 2), padZeros(sec, 2));
    }

    public static String popLast(String str) {
        if (str != null && str.length() > 0) return str.substring(0, str.length() - 1);
        else return "";
    }

    public static String padZeros(String number, int zeros) {
        return String.format(DateFormats.SV_LOCALE, String.format(Locale.US, "%%0%dd", zeros),
                number == null ? 0 : Integer.parseInt(number));
    }
}
