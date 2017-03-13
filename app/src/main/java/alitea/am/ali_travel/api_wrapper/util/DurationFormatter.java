package alitea.am.ali_travel.api_wrapper.util;

/**
 * Created by axel on 13/03/17.
 */

public class DurationFormatter {
    /**
     * NOT STANDARDS COMPLIANT
     * @param iso String to be converted
     * @return formatted string
     */
    public static String ISO_8601ToHuman(String iso) {
        String[] parts = iso.split("T");
        String date = parts[0];
        String time = parts[1];
        date = date.replace("Y", "-").replace("M", "-").replace("D", "");
        time = time.replace("H", ":").replace("M", "");
        return date + " " + time;
    }
}
