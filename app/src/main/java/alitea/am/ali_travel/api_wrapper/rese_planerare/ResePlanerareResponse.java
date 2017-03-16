package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by axel on 05/03/17.
 */

public class ResePlanerareResponse implements Parcelable{
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

    protected ResePlanerareResponse(Parcel in) {
        tripList = in.createTypedArrayList(Trip.CREATOR);
        scrF = in.readString();
        scrB = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(tripList);
        dest.writeString(scrF);
        dest.writeString(scrB);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResePlanerareResponse> CREATOR = new Creator<ResePlanerareResponse>() {
        @Override
        public ResePlanerareResponse createFromParcel(Parcel in) {
            return new ResePlanerareResponse(in);
        }

        @Override
        public ResePlanerareResponse[] newArray(int size) {
            return new ResePlanerareResponse[size];
        }
    };

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
