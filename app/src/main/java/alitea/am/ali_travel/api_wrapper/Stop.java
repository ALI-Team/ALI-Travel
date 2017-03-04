package alitea.am.ali_travel.api_wrapper;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by axel on 04/03/17.
 */

public class Stop {
    private String id, extID, name;
    private double lon, lat;
    private int weights, products;

    public Stop(JSONObject json) {
        try {
            this.id = json.getString("id");
            this.extID = json.getString("extId");
            this.name = json.getString("name");
            this.lon = json.getDouble("lon");
            this.lat = json.getDouble("lat");
            this.weights = json.getInt("weight");
            this.products = json.getInt("products");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }

    public String getExtID() {
        return extID;
    }

    public String getName() {
        return name;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public int getWeights() {
        return weights;
    }

    public int getProducts() {
        return products;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("{id: %1$s, extId: %2$s, name: %3$s, lon: %4$f, lat: %5$f, weights: %6$d, product: %7$d}",
                this.id, this.extID, this.name, this.lon, this.lat, this.weights, this.products);
    }
}
