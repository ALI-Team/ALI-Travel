package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import alitea.am.ali_travel.api_wrapper.util.DateFormats;
import alitea.am.ali_travel.api_wrapper.util.DateJoiner;

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

            this.date = DateJoiner.getCalendarFromDateTime(originOrDestination.getString("date"),
                    originOrDestination.getString("time"));

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
