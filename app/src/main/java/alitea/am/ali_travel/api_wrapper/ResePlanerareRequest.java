package alitea.am.ali_travel.api_wrapper;

import android.content.Context;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by axel on 05/03/17.
 */

public class ResePlanerareRequest {


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
        private int numF, numB;
        private String context;
        private int maxChange;
        private int products;
        private String operators;
        private boolean passList;
        private Walk originWalk;
        private Walk destWalk;

        private Context ctx;

        /**
         * Creates a new Builder
         * @param ctx
         */
        public Builder(Context ctx) {
            this.ctx = ctx;
            this.time = new GregorianCalendar(new Locale("sv", "SE"));
        }

        /**
         * Sets the calendar to a new GregorianCalendar
         * @param time GregorianCalendar, new calendar
         * @return this
         */
        public ResePlanerareRequest.Builder calendar(GregorianCalendar time) {
            this.time = time;
            return this;
        }

        /**
         * Sets the time and date of the calendar
         * @param year new year
         * @param month new month
         * @param date new date
         * @param hourOfDay new hourOfDay
         * @param minute new minute
         * @param second new second
         * @return this
         */
        public ResePlanerareRequest.Builder calendar(int year, int month, int date, int hourOfDay,
                                                 int minute, int second) {
            this.time.set(year, month, date, hourOfDay, minute, second);
            return this;
        }

        /**
         * Sets the date of the calendar
         * @param year new year
         * @param month new month
         * @param date new date
         * @return this
         */
        public ResePlanerareRequest.Builder date(int year, int month, int date) {
            this.time.set(year, month, date);
            return this;
        }

        /**
         * Sets the time of the calendar
         * @param hourOfDay new hourOfDay
         * @param minute new minute
         * @param second new second
         * @return this
         */
        public ResePlanerareRequest.Builder time(int hourOfDay, int minute, int second) {
            this.time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            this.time.set(Calendar.MINUTE, minute);
            this.time.set(Calendar.SECOND, second);
            return this;
        }

        /**
         * Sets the time of the calendar
         * @param hourOfDay new hourOfDay
         * @param minute new minute
         * @return this
         */
        public ResePlanerareRequest.Builder time(int hourOfDay, int minute) {
            return time(hourOfDay, minute, 0);
        }

        /**
         * Sets a specific field in the calendar
         * @param field field
         * @param value new value
         * @return
         */
        public ResePlanerareRequest.Builder calendarField(int field, int value) {
            this.time.set(field, value);
            return this;
        }

        /**
         * Sets whether time is for arrival or departure
         * @param searchForArrival new value
         * @return this
         */
        public ResePlanerareRequest.Builder searchForArrival(boolean searchForArrival) {
            this.searchForArrival = searchForArrival;
            return this;
        }

        /**
         * Search for arrival time
         * @return this
         */
        public ResePlanerareRequest.Builder searchForArrival() {
            return searchForArrival(true);
        }

        /**
         * Search for departure time
         * @return this
         */
        public ResePlanerareRequest.Builder searchForDeparture() {
            return searchForArrival(false);
        }

        /**
         * Sets originId
         * this method or originCoords is required to have been called to fetch or build
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
         * @param viaId stop id
         * @return this
         */
        public ResePlanerareRequest.Builder via(String viaId) {
            this.viaId = viaId;
            return this;
        }

        /**
         * Sets number of rides to receive after time
         * @param numF 0-6, after + before <= 6
         * @return this
         */
        public ResePlanerareRequest.Builder after(int numF) {
            this.numF = numF;
            return this;
        }

        /**
         * Sets number of rides to receive before time
         * @param numB 0-6, after + before <= 6
         * @return this
         */
        public ResePlanerareRequest.Builder before(int numB) {
            this.numB = numB;
            return this;
        }

        /**
         * Sets context for searching after or before already searched rides
         * @param context String received from last search as scrF or scrB
         * @return this
         */
        public ResePlanerareRequest.Builder context(String context) {
            this.context = context;
            return this;
        }

        /**
         * Sets modes of transport
         * OVERWRITES
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
         * @param modes EnumSet<TrafikSlag> of modes to be set
         * @return this
         */
        public ResePlanerareRequest.Builder modes(EnumSet<TrafikSlag> modes) {
            this.products = 0;
            for(TrafikSlag ts : modes) {
                this.products += ts.getNum();
            }
            return this;
        }

        /**
         * Sets mode
         * @param ts TrafikSlag to be change
         * @param use wheter to use ts or not
         * @return this
         */
        public ResePlanerareRequest.Builder setMode(TrafikSlag ts, boolean use) {
            this.products |= TrafikSlag.allInt & (use ? ts.getNum() : 0);
            return this;
        }

        /**
         * Adds mode of transport to products
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
         * @param modes EnumSet<TrafikSlag> of modes to be added
         * @return this
         */
        public ResePlanerareRequest.Builder modesOR(EnumSet<TrafikSlag> modes) {
            for(TrafikSlag ts : modes) {
                this.products |= ts.getNum();
            }
            return this;
        }

        /**
         * Sets operators to allow
         * @param operators comma separated string
         * @return this
         */
        public ResePlanerareRequest.Builder operators(String operators) {
            this.operators = operators;
            return this;
        }

        /**
         * Adds operators to list of operators
         * @param operator
         * @return
         */
        public ResePlanerareRequest.Builder addOperator(String operator) {
            if(this.operators == null || this.operators.equals("")) {
                this.operators = operator;
            } else {
                this.operators += "," + operator;
            }
            return this;
        }

        /**
         * Sets whether to receive all stops passed on journey
         * @param passList boolean, true = receive passList, false = don't receive passList
         * @return this
         */
        public ResePlanerareRequest.Builder passList(boolean passList) {
            this.passList = passList;
            return this;
        }

        /**
         * Sets originWalk
         * @param originWalk Walk object @see am.alite.ali_travel.api_wrapper.Walk
         * @return this
         */
        public ResePlanerareRequest.Builder originWalk(Walk originWalk) {
            this.originWalk = originWalk;
            return this;
        }

        /**
         * Sets destWalk
         * @param destWalk Walk object @see am.alite.ali_travel.api_wrapper.Walk
         * @return this
         */
        public ResePlanerareRequest.Builder destWalk(Walk destWalk) {
            this.destWalk = destWalk;
            return this;
        }

    }
}
