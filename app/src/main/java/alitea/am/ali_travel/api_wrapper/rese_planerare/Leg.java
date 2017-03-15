package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by axel on 06/03/17.
 */

public class Leg implements Parcelable {
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

    public String getDirection() {
        return direction;
    }

    public String getDuration() {
        return duration;
    }

    public String getTransportCategory() {
        return transportCategory;
    }

    public int getDist() {
        return dist;
    }

    public int getTransportNumber() {
        return transportNumber;
    }

    public Product getProduct() {
        return product;
    }

    public ArrayList<Stop> getStopList() {
        return stopList;
    }

    public boolean isWalk() {
        return this.type.equals("WALK");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(direction);
        dest.writeString(duration);
        dest.writeString(transportCategory);
        dest.writeInt(dist);
        dest.writeInt(transportNumber);
        dest.writeInt(index);
        dest.writeArray(stopList.toArray());
    }
}
