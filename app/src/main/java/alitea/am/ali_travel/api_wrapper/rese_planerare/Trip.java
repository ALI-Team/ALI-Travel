package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import alitea.am.ali_travel.api_wrapper.util.DurationFormatter;

/**
 * Created by axel on 05/03/17.
 */

public class Trip implements Parcelable {
    private String duration;
    private int index;
    private String ctxRecon;
    private ServiceDays serviceDays;
    private ArrayList<Leg> legList;

    public Trip(JSONObject trip) {
        try {
            this.duration = trip.getString("duration");
            this.index = trip.getInt("idx");
            this.ctxRecon = trip.getString("ctxRecon");
            legList = new ArrayList<>();
            JSONArray legs = trip.getJSONObject("LegList").getJSONArray("Leg");
            for(int i = 0; i < legs.length(); i++) {
                legList.add(new Leg(legs.getJSONObject(i)));
            }
            this.serviceDays = new ServiceDays(trip.getJSONArray("ServiceDays").getJSONObject(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected Trip(Parcel in) {
        duration = in.readString();
        index = in.readInt();
        ctxRecon = in.readString();
        legList = in.createTypedArrayList(Leg.CREATOR);
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
        }
    };

    /**
     * Gets duration as String
     * @return Duration formatted according to ISO-8601
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Gets duration and converts it to human readable format
     * @return human readable duration, ie 06:09:00
     */
    public String getDurationHuman() {
        return DurationFormatter.ISO_8601ToHuman(duration);
    }

    /**
     * Gets index of this trip
     * @return index of trip
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets ctxRecon
     * @return String describing trip
     */
    public String getCtxRecon() {
        return ctxRecon;
    }

    /**
     * Gets serviceDays
     * @return ServiceDays instance
     */
    public ServiceDays getServiceDays() {
        return serviceDays;
    }

    /**
     * Gets legList
     * @return ArrayList<Leg> containing all the subparts of this trip
     */
    public ArrayList<Leg> getLegList() {
        return legList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(duration);
        dest.writeInt(index);
        dest.writeString(ctxRecon);
        dest.writeList(legList);
    }
}
