package alitea.am.ali_travel.api_wrapper;

import java.util.Locale;

/**
 * Created by axel on 05/03/17.
 */

public class Coordinates {
    private double lon, lat;

    public Coordinates(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    public String getLatString() {
        return Double.toString(lat);
    }

    public String getLonString() {
        return Double.toString(lon);
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%1$fN. %1$fE");
    }
}
