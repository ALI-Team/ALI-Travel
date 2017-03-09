package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.GregorianCalendar;

import alitea.am.ali_travel.api_wrapper.util.DateJoiner;

/**
 * Created by axel on 09/03/17.
 */

public class Stop {
    private String name, id, extId;
    private int routeIdx;
    private double lon, lat;
    private GregorianCalendar arrival, departure;

    public Stop(JSONObject stop) {
        try {
            this.name = stop.getString("name");
            this.id = stop.getString("id");
            this.extId = stop.getString("extId");
            this.routeIdx = stop.getInt("routeIdx");
            this.lon = stop.getDouble("lon");
            this.lat = stop.getDouble("lat");
            this.arrival = DateJoiner.getCalendarFromDateTime(stop.getString("arrDate"),
                    stop.getString("arrTime"));
            if (stop.has("depDate")) {
                this.departure = DateJoiner.getCalendarFromDateTime(stop.getString("depDate"),
                        stop.getString("depTime"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getExtId() {
        return extId;
    }

    public int getRouteIdx() {
        return routeIdx;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public GregorianCalendar getArrival() {
        return arrival;
    }

    public GregorianCalendar getDeparture() {
        return departure;
    }
}
