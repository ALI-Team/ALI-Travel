package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by axel on 06/03/17.
 */

public class Leg {
    private OriginOrDestination origin, destination;
    private String type, name;
    private int index;
    public Leg(JSONObject leg) {
        try {
            this.origin = new OriginOrDestination(leg.getJSONObject("Origin"));
            this.destination = new OriginOrDestination(leg.getJSONObject("Destination"));
            this.type = leg.getString("type");
            this.name = leg.getString("name");
            this.index = leg.getInt("idx");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public OriginOrDestination getOrigin() {
        return origin;
    }

    public OriginOrDestination getDestination() {
        return destination;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
