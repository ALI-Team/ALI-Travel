package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by axel on 05/03/17.
 */

public class ResePlanerareResponse {
    private ArrayList<Trip> tripList;
    private String scrF;
    private String scrB;

    public ResePlanerareResponse(JSONObject jsonObject) {
        try {
            this.tripList = new ArrayList<>();
            this.scrF = jsonObject.getString("scrF");
            this.scrB = jsonObject.getString("scrB");

            JSONArray trips = jsonObject.getJSONArray("Trip");
            for(int i = 0; i < trips.length(); i++) {
                tripList.add(new Trip(trips.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets list of trips
     * @return ArrayList<Trip>
     */
    public ArrayList<Trip> getTripList() {
        return tripList;
    }

    /**
     * Gets context for searching earlier departures
     * Use return value of this method in ResePlanerareRequest$Builder::context
     * @return String containing info for searching earlier departures
     */
    public String getScrF() {
        return scrF;
    }

    /**
     * Gets context for searching later departures
     * Use return value of this method in ResePlanerareRequest$Builder::context
     * @return String containing info for searching later departures
     */
    public String getScrB() {
        return scrB;
    }
}
