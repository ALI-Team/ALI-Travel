package alitea.am.ali_travel.api_wrapper.plats_uppslag;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumSet;
import java.util.Locale;

import alitea.am.ali_travel.api_wrapper.TrafikSlag;

/**
 * Created by axel on 04/03/17.
 */

public class Stop {
    private String id, extID, name;
    private double lon, lat;
    private int weight, products;

    public Stop(JSONObject json) {
        try {
            this.id = json.getString("id");
            this.extID = json.getString("extId");
            this.name = json.getString("name");
            this.lon = json.getDouble("lon");
            this.lat = json.getDouble("lat");
            this.weight = json.getInt("weight");
            this.products = json.getInt("products");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Checks if a trafikslag trafics stop
     * @param ts TrafikSlag to check
     * @return boolean whether or not stop is traficked by ts
     */
    public boolean uses(TrafikSlag ts) {
        return ts.in(this.products);
    }

    /**
     * Gets list of modes of transport that trafic this stop
     * @return EnumSet<TrafikSlag> of modes
     */
    public EnumSet<TrafikSlag> getModes() {
        return TrafikSlag.getModes(this.products);
    }

    /**
     * Gets ID of stop
     * Used in other API like reseplanerare
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Gets extID
     * @return usually same as Id
     */
    public String getExtID() {
        return extID;
    }

    /**
     * Gets name of stop
     * @return name of stop, ie "Berga Söderleden"
     */
    public String getName() {
        return name;
    }

    /**
     * Gets longitude
     * @return double longitude of stop
     */
    public double getLon() {
        return lon;
    }

    /**
     * Gets latitude
     * @return double latitude of stop
     */
    public double getLat() {
        return lat;
    }

    /**
     * Gets weight
     * @return int how much trafic is on stop
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets products
     * @return int bitflags of what modes of trafic trafic stop
     */
    public int getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "{id: %1$s, extId: %2$s, name: %3$s, lon: %4$f, lat: %5$f, weight: %6$d, products: %7$d}",
                this.id, this.extID, this.name, this.lon, this.lat, this.weight, this.products);
    }
}
