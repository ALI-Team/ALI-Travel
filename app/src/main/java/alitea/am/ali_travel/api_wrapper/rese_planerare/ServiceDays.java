package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import alitea.am.ali_travel.api_wrapper.util.DateFormats;

/**
 * Created by axel on 05/03/17.
 */

public class ServiceDays {
    private GregorianCalendar planningPeriodBegin;
    private GregorianCalendar planningPeriodEnd;
    private BigInteger sDaysB;
    private String sDaysI, sDaysR;

    public ServiceDays(JSONObject jsonObject) {
        try {
            Date pbdDate = DateFormats.DATE_FORMAT.parse(jsonObject.getString("planningPeriodBegin"));
            planningPeriodBegin = new GregorianCalendar(DateFormats.SV_LOCALE);
            planningPeriodBegin.setTime(pbdDate);
            Date pbeDate = DateFormats.DATE_FORMAT.parse(jsonObject.getString("planningPeriodEnd"));
            planningPeriodEnd = new GregorianCalendar(DateFormats.SV_LOCALE);
            planningPeriodEnd.setTime(pbeDate);
            sDaysB = new BigInteger(jsonObject.getString("sDaysB"), 16);
            if(jsonObject.has("sDaysI")) {
                sDaysI = jsonObject.getString("sDaysI");
            }
            sDaysR = jsonObject.getString("sDaysR");
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets date when planning period begins
     * @return GregorianCalendar with date
     */
    public GregorianCalendar getPlanningPeriodBegin() {
        return planningPeriodBegin;
    }

    /**
     * Gets date when planning period ends
     * @return GregorianCalendar with date
     */
    public GregorianCalendar getPlanningPeriodEnd() {
        return planningPeriodEnd;
    }

    /**
     * Attempt to contextually interpret exceptions to the rule of when trip is going
     * @return String with text, ex "utom 19. jun"
     */
    public String getsDaysI() {
        return sDaysI;
    }

    /**
     * Attempt to contextually interpret when trip is active
     * @return String with text, ex "fre", "varje dag"
     */
    public String getsDaysR() {
        return sDaysR;
    }

    /**
     * Whether or not the trip is active on date
     * The API sent the data as bitflags in hexadecimal....
     * @param date Date to query
     * @return boolean
     */
    public boolean serviceOnDate(Date date) {
        long diff = date.getTime() - planningPeriodBegin.getTime().getTime();
        long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long bitFlag = 1 << diffDays;
        return sDaysB.and(BigInteger.valueOf(bitFlag)).equals(BigInteger.valueOf(bitFlag));
    }
}
