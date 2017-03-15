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

    /**
     * Gets name of stop
     * @return name, ie "Berga Söderleden" or "Linköping Station"
     */
    public String getName() {
        return name;
    }

    /**
     * Gets ID of station
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets extID
     * @return in most cases same as id
     */
    public String getExtId() {
        return extId;
    }

    /**
     * Index of stop
     * @return int index
     */
    public int getRouteIdx() {
        return routeIdx;
    }

    /**
     * Gets lon
     * @return longitude of stop
     */
    public double getLon() {
        return lon;
    }

    /**
     * Gets lat
     * @return latitude of stop
     */
    public double getLat() {
        return lat;
    }

    /**
     * Gets time of arrival as a calendar
     * @return time of arrival
     */
    public GregorianCalendar getArrival() {
        return arrival;
    }

    /**
     * Gets time of departure as a calendar
     * @return time of departure
     */
    public GregorianCalendar getDeparture() {
        return departure;
    }
}
