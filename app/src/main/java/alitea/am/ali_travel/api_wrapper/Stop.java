package alitea.am.ali_travel.api_wrapper;

import android.annotation.SuppressLint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumSet;

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

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("{id: %1$s, extId: %2$s, name: %3$s, lon: %4$f, lat: %5$f, weight: %6$d, products: %7$d}",
                this.id, this.extID, this.name, this.lon, this.lat, this.weight, this.products);
    }

    public enum TrafikSlag {
        FLYG (1 << 0),       //0000000001
        SNABBTÅG (1 << 1),   //0000000010
        TÅG (1 << 2),        //0000000100
        EXPRESSBUSS (1 << 3),//0000001000
        LOKALTÅG (1 << 4),   //0000010000
        TUNNELBANA (1 << 5), //0000100000
        SPÅRVAGN (1 << 6),   //0001000000
        BUSS (1 << 7),       //0010000000
        BÅT (1 << 8),        //0100000000
        TAXI (1 << 9);       //1000000000


        private int num;
        public static EnumSet<TrafikSlag> all = EnumSet.allOf(TrafikSlag.class);

        TrafikSlag(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public boolean in(int products) {
            return (this.getNum() & products) == this.getNum();
        }

        public static EnumSet<TrafikSlag> getModes(int products) {
            EnumSet<TrafikSlag> enumSet = EnumSet.noneOf(TrafikSlag.class);
            for(TrafikSlag ts : TrafikSlag.all) {
                if(ts.in(products)) {
                    enumSet.add(ts);
                }
            }
            return enumSet;
        }

    }
}
