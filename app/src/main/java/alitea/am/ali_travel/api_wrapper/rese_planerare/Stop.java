package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.GregorianCalendar;

import alitea.am.ali_travel.api_wrapper.util.DateJoiner;

/**
 * Created by axel on 09/03/17.
 */

public class Stop implements Parcelable {
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
            if (stop.has("arrDate")) {
                this.arrival = DateJoiner.getCalendarFromDateTime(stop.getString("arrDate"),
                        stop.getString("arrTime"));
            }
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

    protected Stop(Parcel in) {
        name = in.readString();
        id = in.readString();
        extId = in.readString();
        routeIdx = in.readInt();
        lon = in.readDouble();
        lat = in.readDouble();
    }

    public static final Creator<Stop> CREATOR = new Creator<Stop>() {
        @Override
        public Stop createFromParcel(Parcel in) {
            return new Stop(in);
        }

        @Override
        public Stop[] newArray(int size) {
            return new Stop[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(extId);
        dest.writeInt(routeIdx);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeSerializable(arrival);
        dest.writeSerializable(departure);
    }
}
