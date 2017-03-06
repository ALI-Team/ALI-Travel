package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by axel on 05/03/17.
 */

public class Trip {
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
            JSONArray legs = trip.getJSONObject("LegList").getJSONArray("Leg");
            for(int i = 0; i < legs.length(); i++) {
                legList.add(new Leg(legs.getJSONObject(i)));
            }
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
}
