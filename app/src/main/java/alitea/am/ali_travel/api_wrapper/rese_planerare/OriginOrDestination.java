package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import alitea.am.ali_travel.api_wrapper.util.DateFormats;

/**
 * Created by axel on 06/03/17.
 */

public class OriginOrDestination {
    private String name, type, id, extId;
    private double lon, lat;
    private GregorianCalendar date;

    public OriginOrDestination(JSONObject originOrDestination) {
        try {
            this.name = originOrDestination.getString("name");
            this.type = originOrDestination.getString("type");
            this.id = originOrDestination.getString("id");
            this.extId = originOrDestination.getString("extId");
            this.lon = originOrDestination.getDouble("lon");
            this.lat = originOrDestination.getDouble("lat");

            this.date = new GregorianCalendar(DateFormats.SV_LOCALE);

            Date date = DateFormats.DATE_FORMAT.parse(originOrDestination.getString("date"));
            this.date.setTime(date);

            Date time = DateFormats.TIME_FORMAT.parse(originOrDestination.getString("time"));

            GregorianCalendar timeCalendar = new GregorianCalendar(DateFormats.SV_LOCALE);
            timeCalendar.setTime(time);

            this.date.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
            this.date.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
            this.date.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getExtId() {
        return extId;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
