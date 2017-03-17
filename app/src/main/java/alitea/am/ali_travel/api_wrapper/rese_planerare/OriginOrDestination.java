package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

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

public class OriginOrDestination implements Parcelable {
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

    protected OriginOrDestination(Parcel in) {
        name = in.readString();
        type = in.readString();
        id = in.readString();
        extId = in.readString();
        lon = in.readDouble();
        lat = in.readDouble();
    }

    public static final Creator<OriginOrDestination> CREATOR = new Creator<OriginOrDestination>() {
        @Override
        public OriginOrDestination createFromParcel(Parcel in) {
            return new OriginOrDestination(in);
        }

        @Override
        public OriginOrDestination[] newArray(int size) {
            return new OriginOrDestination[size];
        }
    };

    /**
     * Gets name
     * @return string name of stop
     */
    public String getName() {
        return name;
    }

    /**
     * Gets type
     * @return always "ST", more alternatives might be added in future
     */
    public String getType() {
        return type;
    }

    /**
     * Gets ID of stop
     * @return string ID, used in other api calls
     */
    public String getId() {
        return id;
    }

    /**
     * Gets exdId
     * @return string ID, usually same as ID
     */
    public String getExtId() {
        return extId;
    }

    /**
     * Gets lon
     * @return double longitude
     */
    public double getLon() {
        return lon;
    }

    /**
     * Gets lat
     * @return double latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Gets date of when you arrive there
     * @return GregorianCalendar, when you arrive
     */
    public GregorianCalendar getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(id);
        dest.writeString(extId);
        dest.writeDouble(lon);
        dest.writeDouble(lat);
        dest.writeSerializable(date);
    }
}
