package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

    public String getDuration() {
        return duration;
    }

    public int getIndex() {
        return index;
    }

    public String getCtxRecon() {
        return ctxRecon;
    }

    public ServiceDays getServiceDays() {
        return serviceDays;
    }

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
        dest.writeArray(legList.toArray());
    }
}
