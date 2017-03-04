package alitea.am.ali_travel.api_wrapper;

import android.content.Context;
import android.net.Uri;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by axel on 04/03/17.
 */

public class PlatsUppslagRequest {
    private String input;
    private int limit = -1;
    private Context context;
    private final Uri API_ENDPOINT;

    private PlatsUppslagRequest(Context context, String input, int limit) {
        this.context = context;
        this.input = input;
        this.limit = limit;
        this.API_ENDPOINT = new Uri.Builder()
                .scheme("https")
                .authority("api.resrobot.se")
                .path("/v2/location.name")
                .appendQueryParameter("key", APIKeyHolder.API_KEY)
                .appendQueryParameter("format", "json")
                .build();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    private void fetch(final ResponseHandler rHandler, final ErrorHandler eHandler) {
        RequestQueue queue = Volley.newRequestQueue(context);
        Uri.Builder urlBuilder = API_ENDPOINT.buildUpon();
        if (input != null) {
            urlBuilder.appendQueryParameter("input", this.input);
        } else {
            urlBuilder.appendQueryParameter("input", "");
        }
        if (limit != -1) {
            urlBuilder.appendQueryParameter("maxNo", Integer.toString(this.limit));
        }

        String url = urlBuilder.build().toString();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("StopLocation")) {
                                JSONArray jsonStops = response.getJSONArray("StopLocation");
                                ArrayList<Stop> stops = new ArrayList<>();
                                for(int i = 0; i < jsonStops.length(); i++) {
                                    stops.add(new Stop(jsonStops.getJSONObject(i)));
                                }

                                rHandler.handleResponse(stops);
                            } else if (response.has("errorCode") && response.has("errorText")) {
                                Error requestError = new Error(response.getString("errorCode"),
                                        response.getString("errorText"));
                                eHandler.handleError(requestError);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(jsonObjectRequest);

    }

    public static class Builder {
        private String input;
        private int limit;
        private Context ctx;

        public Builder(Context ctx) {
            this.ctx = ctx;
        }

        public PlatsUppslagRequest.Builder input(String input) {
            this.input = input;
            return this;
        }

        public PlatsUppslagRequest.Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public PlatsUppslagRequest build() {
            return new PlatsUppslagRequest(ctx, this.input, this.limit);
        }

        public void fetch(ResponseHandler rHandler, ErrorHandler eHandler) {
            PlatsUppslagRequest pur = this.build();
            pur.fetch(rHandler, eHandler);
        }
    }

    public interface ErrorHandler {
        void handleError(Error error);
    }

    public interface ResponseHandler {
        void handleResponse(ArrayList<Stop> stops);
    }
}
