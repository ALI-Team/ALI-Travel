package alitea.am.ali_travel.api_wrapper.rese_planerare;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;

import alitea.am.ali_travel.api_wrapper.APIError;
import alitea.am.ali_travel.api_wrapper.APIKeyHolder;
import alitea.am.ali_travel.api_wrapper.Coordinates;
import alitea.am.ali_travel.api_wrapper.TrafikSlag;
import alitea.am.ali_travel.api_wrapper.Walk;
import alitea.am.ali_travel.api_wrapper.util.DateFormats;

/**
 * Created by axel on 05/03/17.
 */

public class ResePlanerareRequest {
    private String date, time;
    private String searchForArrival;
    private String originId;
    private String originCoordLat, originCoordLong;
    private String destId;
    private String destCoordLat, destCoordLong;
    private String viaId;
    private String numF;
    private String numB;
    private String context;
    private String maxChange;
    private String products;
    private String operators;
    private String passList;
    private String originWalk;
    private String destWalk;

    private Context ctx;
    private final Uri API_ENDPOINT;

    private ResePlanerareRequest(Context ctx) {
        this.ctx = ctx;
        this.API_ENDPOINT = new Uri.Builder()
                .scheme("https")
                .authority("api.resrobot.se")
                .path("/v2/trip")
                .appendQueryParameter("key", APIKeyHolder.API_KEY)
                .appendQueryParameter("format", "json")
                .build();
    }

    public void fetch(final ResponseHandler rHandler, final ErrorHandler eHandler) {
        RequestQueue queue = Volley.newRequestQueue(ctx);
        Uri.Builder uriBuilder = API_ENDPOINT.buildUpon();

        if (this.date != null && !this.date.equals("")) {
            uriBuilder.appendQueryParameter("date", this.date);
        }

        if (this.time != null && !this.time.equals("")) {
            uriBuilder.appendQueryParameter("time", this.time);
        }

        if (this.searchForArrival != null && !this.searchForArrival.equals("")) {
            uriBuilder.appendQueryParameter("searchForArrival", this.searchForArrival);
        }

        if (this.originId != null && !this.originId.equals("")) {
            uriBuilder.appendQueryParameter("originId", this.originId);
        }

        if (this.originCoordLat != null && !this.originCoordLat.equals("")) {
            uriBuilder.appendQueryParameter("originCoordLat", this.originCoordLat);
        }

        if (this.originCoordLong != null && !this.originCoordLong.equals("")) {
            uriBuilder.appendQueryParameter("originCoordLong", this.originCoordLong);
        }

        if (this.destId != null && !this.destId.equals("")) {
            uriBuilder.appendQueryParameter("destId", this.destId);
        }

        if (this.destCoordLat != null && !this.destCoordLat.equals("")) {
            uriBuilder.appendQueryParameter("destCoordLat", this.destCoordLat);
        }

        if (this.destCoordLong != null && !this.destCoordLong.equals("")) {
            uriBuilder.appendQueryParameter("destCoordLong", this.destCoordLong);
        }

        if (this.viaId != null && !this.viaId.equals("")) {
            uriBuilder.appendQueryParameter("viaId", this.viaId);
        }

        if (this.numF != null && !this.numF.equals("")) {
            uriBuilder.appendQueryParameter("numF", this.numF);
        }

        if (this.numB != null && !this.numB.equals("")) {
            uriBuilder.appendQueryParameter("numB", this.numB);
        }

        if (this.context != null && !this.context.equals("")) {
            uriBuilder.appendQueryParameter("context", this.context);
        }

        if (this.maxChange != null && !this.maxChange.equals("")) {
            uriBuilder.appendQueryParameter("maxChange", this.maxChange);
        }

        if (this.products != null && !this.products.equals("")) {
            uriBuilder.appendQueryParameter("products", this.products);
        }

        if (this.operators != null && !this.operators.equals("")) {
            uriBuilder.appendQueryParameter("operators", this.operators);
        }

        if (this.passList != null && !this.passList.equals("")) {
            uriBuilder.appendQueryParameter("passlist", this.passList);
        }

        if (this.originWalk != null && !this.originWalk.equals("")) {
            uriBuilder.appendQueryParameter("originWalk", this.originWalk);
        }

        if (this.destWalk != null && !this.destWalk.equals("")) {
            uriBuilder.appendQueryParameter("destWalk", this.destWalk);
        }


        String url = uriBuilder.build().toString();

        Log.i("RPR", url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i("RPR", response.toString(2));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (response.has("Trip")) {
                                rHandler.handleResponse(new ResePlanerareResponse(response));
                            } else {
                                eHandler.handleError(new APIError(response.getString("errorCode"),
                                        response.getString("errorText")));
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

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(jsonObjectRequest);
    }

    public interface ResponseHandler {
        void handleResponse(ResePlanerareResponse rpr);
    }

    public interface ErrorHandler {
        void handleError(APIError error);
    }

    public static class Builder {
        private GregorianCalendar time;
        private boolean searchForArrival;
        private String originId;
        private Coordinates originCoords;
        private boolean hasOrigin;
        private String destId;
        private Coordinates destCoords;
        private boolean hasDest;
        private String viaId;
        private int numF = -1, numB = -1;
        private String context;
        private int maxChange = -1;
        private int products = -1;
        private String operators;
        private boolean passList = true;
        private Walk originWalk;
        private Walk destWalk;

        private Context ctx;

        /**
         * Creates a new Builder
         *
         * @param ctx
         */
        public Builder(Context ctx) {
            this.ctx = ctx;
        }

        /**
         * Sets the calendar to a new GregorianCalendar
         *
         * @param time GregorianCalendar, new calendar
         * @return this
         */
        public ResePlanerareRequest.Builder calendar(GregorianCalendar time) {
            this.time = time;
            return this;
        }

        private void initCalendarIfNeeded() {
            if (this.time == null) {
                this.time = new GregorianCalendar(DateFormats.SV_LOCALE);
            }
        }

        /**
         * Sets the time and date of the calendar
         *
         * @param year      new year
         * @param month     new month
         * @param date      new date
         * @param hourOfDay new hourOfDay
         * @param minute    new minute
         * @param second    new second
         * @return this
         */
        public ResePlanerareRequest.Builder calendar(int year, int month, int date, int hourOfDay,
                                                     int minute, int second) {
            initCalendarIfNeeded();
            this.time.set(year, month, date, hourOfDay, minute, second);
            return this;
        }

        /**
         * Sets the date of the calendar
         *
         * @param year  new year
         * @param month new month
         * @param date  new date
         * @return this
         */
        public ResePlanerareRequest.Builder date(int year, int month, int date) {
            initCalendarIfNeeded();
            this.time.set(year, month, date);
            return this;
        }

        /**
         * Sets the time of the calendar
         *
         * @param hourOfDay new hourOfDay
         * @param minute    new minute
         * @param second    new second
         * @return this
         */
        public ResePlanerareRequest.Builder time(int hourOfDay, int minute, int second) {
            initCalendarIfNeeded();
            this.time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            this.time.set(Calendar.MINUTE, minute);
            this.time.set(Calendar.SECOND, second);
            return this;
        }

        /**
         * Sets the time of the calendar
         *
         * @param hourOfDay new hourOfDay
         * @param minute    new minute
         * @return this
         */
        public ResePlanerareRequest.Builder time(int hourOfDay, int minute) {
            initCalendarIfNeeded();
            return time(hourOfDay, minute, 0);
        }

        /**
         * Sets a specific field in the calendar
         *
         * @param field field
         * @param value new value
         * @return
         */
        public ResePlanerareRequest.Builder calendarField(int field, int value) {
            initCalendarIfNeeded();
            this.time.set(field, value);
            return this;
        }

        /**
         * Sets whether time is for arrival or departure
         *
         * @param searchForArrival new value
         * @return this
         */
        public ResePlanerareRequest.Builder searchForArrival(boolean searchForArrival) {
            this.searchForArrival = searchForArrival;
            return this;
        }

        /**
         * Search for arrival time
         *
         * @return this
         */
        public ResePlanerareRequest.Builder searchForArrival() {
            return searchForArrival(true);
        }

        /**
         * Search for departure time
         *
         * @return this
         */
        public ResePlanerareRequest.Builder searchForDeparture() {
            return searchForArrival(false);
        }

        /**
         * Sets originId
         * this method or originCoords is required to have been called to fetch or build
         *
         * @param originId Id of stop, obtainer from OGT::platsUppslag
         * @return this
         */
        public ResePlanerareRequest.Builder originID(String originId) {
            this.originId = originId;
            this.hasOrigin = true;
            return this;
        }

        /**
         * Sets originCoords
         * this method or originId is required to have been called to fetch or build
         *
         * @param originCoords Coordinate object containing coordinates of origin
         * @return this
         */
        public ResePlanerareRequest.Builder originCoords(Coordinates originCoords) {
            this.originCoords = originCoords;
            this.hasOrigin = true;
            return this;
        }

        /**
         * Sets destId
         * this method or destCoords is required to have been called to fetch or build
         *
         * @param destId Id of stop, obtainer from OGT::platsUppslag
         * @return this
         */
        public ResePlanerareRequest.Builder destID(String destId) {
            this.destId = destId;
            this.hasDest = true;
            return this;
        }

        /**
         * Sets destCoords
         * this method or destId is required to have been called to fetch or build
         *
         * @param destCoords Coordinate object containing coordinates of dest
         * @return this
         */
        public ResePlanerareRequest.Builder destCoords(Coordinates destCoords) {
            this.destCoords = destCoords;
            this.hasDest = true;
            return this;
        }

        /**
         * Sets station to pass by
         *
         * @param viaId stop id
         * @return this
         */
        public ResePlanerareRequest.Builder via(String viaId) {
            this.viaId = viaId;
            return this;
        }

        /**
         * Sets number of rides to receive after time
         *
         * @param numF 0-6, after + before <= 6
         * @return this
         */
        public ResePlanerareRequest.Builder after(int numF) {
            this.numF = numF;
            return this;
        }

        /**
         * Sets number of rides to receive before time
         *
         * @param numB 0-6, after + before <= 6
         * @return this
         */
        public ResePlanerareRequest.Builder before(int numB) {
            this.numB = numB;
            return this;
        }

        /**
         * Sets context for searching after or before already searched rides
         *
         * @param context String received from last search as scrF or scrB
         * @return this
         */
        public ResePlanerareRequest.Builder context(String context) {
            this.context = context;
            return this;
        }

        /**
         * Sets maxChange
         *
         * @param maxChange 1-3
         * @return this
         */
        public ResePlanerareRequest.Builder maxChange(int maxChange) {
            this.maxChange = maxChange;
            return this;
        }

        /**
         * Sets modes of transport
         * OVERWRITES
         *
         * @param modes new modes
         * @return this
         */
        public ResePlanerareRequest.Builder modes(int modes) {
            this.products = modes;
            return this;
        }

        /**
         * Sets modes of transport
         * OVERWRITES
         *
         * @param modes EnumSet<TrafikSlag> of modes to be set
         * @return this
         */
        public ResePlanerareRequest.Builder modes(EnumSet<TrafikSlag> modes) {
            this.products = 0;
            for (TrafikSlag ts : modes) {
                this.products += ts.getNum();
            }
            return this;
        }

        /**
         * Sets mode
         *
         * @param ts  TrafikSlag to be change
         * @param use wheter to use ts or not
         * @return this
         */
        public ResePlanerareRequest.Builder setMode(TrafikSlag ts, boolean use) {
            this.products |= TrafikSlag.allInt & (use ? ts.getNum() : 0);
            return this;
        }

        /**
         * Adds mode of transport to products
         *
         * @param ts mode to be added
         * @return this
         */
        public ResePlanerareRequest.Builder addMode(TrafikSlag ts) {
            this.products |= ts.getNum();
            return this;
        }

        /**
         * Adds modes of transport with binary OR
         * DOES NOT REMOVE OLD MODES
         *
         * @param modes modes to be added
         * @return this
         */
        public ResePlanerareRequest.Builder modesOR(int modes) {
            this.products |= modes;
            return this;
        }

        /**
         * Adds modes of transport with binary OR
         * DOES NOT REMOVE OLD MODES
         *
         * @param modes EnumSet<TrafikSlag> of modes to be added
         * @return this
         */
        public ResePlanerareRequest.Builder modesOR(EnumSet<TrafikSlag> modes) {
            for (TrafikSlag ts : modes) {
                this.products |= ts.getNum();
            }
            return this;
        }

        /**
         * Sets operators to allow
         *
         * @param operators comma separated string
         * @return this
         */
        public ResePlanerareRequest.Builder operators(String operators) {
            this.operators = operators;
            return this;
        }

        /**
         * Adds operators to list of operators
         *
         * @param operator to add
         * @return this
         */
        public ResePlanerareRequest.Builder addOperator(String operator) {
            if (this.operators == null || this.operators.equals("")) {
                this.operators = operator;
            } else {
                this.operators += "," + operator;
            }
            return this;
        }

        /**
         * Sets whether to receive all stops passed on journey
         *
         * @param passList boolean, true = receive passList, false = don't receive passList
         * @return this
         */
        public ResePlanerareRequest.Builder passList(boolean passList) {
            this.passList = passList;
            return this;
        }

        /**
         * Sets originWalk
         *
         * @param originWalk Walk object @see am.alite.ali_travel.api_wrapper.Walk
         * @return this
         */
        public ResePlanerareRequest.Builder originWalk(Walk originWalk) {
            this.originWalk = originWalk;
            return this;
        }

        /**
         * Sets destWalk
         *
         * @param destWalk Walk object @see am.alite.ali_travel.api_wrapper.Walk
         * @return this
         */
        public ResePlanerareRequest.Builder destWalk(Walk destWalk) {
            this.destWalk = destWalk;
            return this;
        }

        public void fetch(ResponseHandler rHandler, ErrorHandler eHandler) {
            try {
                this.build().fetch(rHandler, eHandler);
            } catch (ResePlanerareIllegalArgumentsException e) {
                e.printStackTrace();
            }
        }

        /**
         * Builds and checks input for ResePlanerareRequest
         *
         * @return A built ResePlanerareRequest
         * @throws ResePlanerareIllegalArgumentsException if input is malformed
         */
        public ResePlanerareRequest build() throws ResePlanerareIllegalArgumentsException {
            ResePlanerareRequest rpr = new ResePlanerareRequest(ctx);

            if (this.time != null) {
                rpr.date = DateFormats.DATE_FORMAT.format(time);
                rpr.time = DateFormats.TIME_FORMAT.format(time);
            }

            if (searchForArrival) {
                rpr.searchForArrival = "1";
            }

            if (!hasOrigin || !hasDest) {
                throw new MustProvideOriginAndDestinationException("Origin and destination not provided");
            } else {
                if (this.originId != null && !this.originId.equals("")) {
                    rpr.originId = this.originId;
                } else {
                    rpr.originCoordLat = this.originCoords.getLatString();
                    rpr.originCoordLong = this.originCoords.getLonString();
                }
                if (this.destId != null && !this.destId.equals("")) {
                    rpr.destId = this.destId;
                } else {
                    rpr.destCoordLat = this.destCoords.getLatString();
                    rpr.destCoordLong = this.destCoords.getLonString();
                }
            }

            if (this.viaId != null && !this.viaId.equals("")) {
                rpr.viaId = this.viaId;
            }

            int numSum = numB + numF;
            if (numSum > 6) {
                throw new IllegalNumFNumBRangeException("numB + numF > 6 (" +
                        Integer.toString(numSum) + ")");
            } else {
                if (numB >= 0) {
                    rpr.numB = Integer.toString(numB);
                }
                if (numF >= 0) {
                    rpr.numF = Integer.toString(numF);
                }
            }

            if (this.context != null && !this.context.equals("")) {
                rpr.context = this.context;
            }

            if (this.maxChange != -1) {
                if (this.maxChange >= 1 && this.maxChange <= 3) {
                    rpr.maxChange = Integer.toString(maxChange);
                } else throw new IllegalMaxChangeRangeException("max change must be >= 1 and <= 3");
            }

            if (this.products >= 1 && this.products <= TrafikSlag.allInt) {
                rpr.products = Integer.toString(this.products);
            } else if (this.products != -1) {
                throw new IllegalProductsException("Products must be in range 1 to " +
                        Integer.toString(TrafikSlag.allInt));
            }

            if (this.operators != null) {
                rpr.operators = this.operators;
            }

            if (!this.passList) {
                rpr.passList = "0";
            }

            if (this.originWalk != null) {
                rpr.originWalk = this.originWalk.toString();
            }

            if (this.destWalk != null) {
                rpr.destWalk = this.destWalk.toString();
            }

            return rpr;
        }

    }
}
