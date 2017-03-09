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

    public boolean uses(TrafikSlag ts) {
        return ts.in(this.products);
    }

    public EnumSet<TrafikSlag> getModes() {
        return TrafikSlag.getModes(this.products);
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

    public int getWeight() {
        return weight;
    }

    public int getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "{id: %1$s, extId: %2$s, name: %3$s, lon: %4$f, lat: %5$f, weight: %6$d, products: %7$d}",
                this.id, this.extID, this.name, this.lon, this.lat, this.weight, this.products);
    }
}
