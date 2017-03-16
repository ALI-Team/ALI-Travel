package alitea.am.ali_travel.api_wrapper.rese_planerare;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import alitea.am.ali_travel.api_wrapper.util.DurationFormatter;

/**
 * Created by axel on 06/03/17.
 */

public class Leg {
    private OriginOrDestination origin, destination;
    private String type, name, direction, duration, transportCategory;
    private int dist;
    private int transportNumber;
    private int index;
    private Product product;
    private ArrayList<Stop> stopList;
    public Leg(JSONObject leg) {
        try {
            this.origin = new OriginOrDestination(leg.getJSONObject("Origin"));
            this.destination = new OriginOrDestination(leg.getJSONObject("Destination"));
            this.type = leg.getString("type");
            if(this.isWalk()) {
                this.duration = leg.getString("duration");
                this.dist = leg.getInt("dist");
            } else {
                this.direction = leg.getString("direction");
                this.transportCategory = leg.getString("transportCategory");
                this.transportNumber = leg.getInt("transportNumber");
                this.product = new Product(leg.getJSONObject("Product"));
                JSONArray stops = leg.getJSONObject("Stops").getJSONArray("Stop");
                stopList = new ArrayList<>();
                for(int i = 0; i < stops.length(); i++) {
                    stopList.add(new Stop(stops.getJSONObject(i)));
                }
            }
            this.name = leg.getString("name");
            this.index = leg.getInt("idx");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets origin
     * @return origin as OriginOrDestination instance
     */
    public OriginOrDestination getOrigin() {
        return origin;
    }

    /**
     * Gets destination
     * @return destination as OriginOrDestination instance
     */
    public OriginOrDestination getDestination() {
        return destination;
    }

    /**
     * Gets type
     * @return String, either "JNY", "TRSF" eller "WALK"
     */
    public String getType() {
        return type;
    }

    /**
     * Gets name
     * @return String, example "Öresundståg 1038"
     */
    public String getName() {
        return name;
    }

    /**
     * Gets index
     * @return int index of leg
     */
    public int getIndex() {
        return index;
    }

    /**
     * Gets direction
     * @return String, end station of leg(not the station where you step off)
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Gets duration
     * @return String duration according to ISO-8601 standard
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Gets duration in human format
     * @return String duration, ie 06:45:23
     */
    public String getDurationHuman() {
        return DurationFormatter.ISO_8601ToHuman(getDuration());
    }

    /**
     * Gets transport category
     * Not documented in API docs
     * @return String transport category
     */
    public String getTransportCategory() {
        return transportCategory;
    }

    /**
     * Gets dist, only available if type is WALK
     * @return distance in m
     */
    public int getDist() {
        return dist;
    }

    /**
     * Gets transport number
     * Line number for busses, train number for trains
     * @return transportnumber
     */
    public int getTransportNumber() {
        return transportNumber;
    }

    /**
     * Gets info about the company behind the leg
     * @return instance of Product containing the info
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets stoplist
     * @return list of stops
     */
    public ArrayList<Stop> getStopList() {
        return stopList;
    }

    /**
     * Is walk?
     * @return return true if type is WALK
     */
    public boolean isWalk() {
        return this.type.equals("WALK");
    }
}
