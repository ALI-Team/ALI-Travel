package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private long sDaysB;
    private String sDaysI, sDaysR;

    public ServiceDays(JSONObject jsonObject) {
        try {
            Date pbdDate = DateFormats.DATE_FORMAT.parse(jsonObject.getString("planningPeriodBegin"));
            planningPeriodBegin = new GregorianCalendar(DateFormats.SV_LOCALE);
            planningPeriodBegin.setTime(pbdDate);
            Date pbeDate = DateFormats.DATE_FORMAT.parse(jsonObject.getString("planningPeriodEnd"));
            planningPeriodEnd = new GregorianCalendar(DateFormats.SV_LOCALE);
            planningPeriodEnd.setTime(pbeDate);
            sDaysB = Integer.parseInt(jsonObject.getString("sDaysB"), 16);
            if(jsonObject.has("sDaysI")) {
                sDaysI = jsonObject.getString("sDaysI");
            }
            sDaysR = jsonObject.getString("sDaysR");
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    public GregorianCalendar getPlanningPeriodBegin() {
        return planningPeriodBegin;
    }

    public GregorianCalendar getPlanningPeriodEnd() {
        return planningPeriodEnd;
    }

    public String getsDaysI() {
        return sDaysI;
    }

    public String getsDaysR() {
        return sDaysR;
    }

    public boolean serviceOnDate(Date date) {
        long diff = date.getTime() - planningPeriodBegin.getTime().getTime();
        long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        long bitFlag = 1 << diffDays;
        return (sDaysB & bitFlag) == bitFlag;
    }
}
