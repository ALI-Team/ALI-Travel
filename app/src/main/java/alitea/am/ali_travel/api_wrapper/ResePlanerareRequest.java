package alitea.am.ali_travel.api_wrapper;

import android.content.Context;

import java.util.Calendar;
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
        private String destId;
        private Coordinates destCoords;
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

        public Builder(Context ctx) {
            this.ctx = ctx;
            this.time = new GregorianCalendar(new Locale("sv", "SE"));
        }

        public ResePlanerareRequest.Builder calendar(GregorianCalendar time) {
            this.time = time;
            return this;
        }

        public ResePlanerareRequest.Builder calendar(int year, int month, int date, int hourOfDay,
                                                 int minute, int second) {
            this.time.set(year, month, date, hourOfDay, minute, second);
            return this;
        }

        public ResePlanerareRequest.Builder date(int year, int month, int date) {
            this.time.set(year, month, date);
            return this;
        }

        public ResePlanerareRequest.Builder time(int hourOfDay, int minute, int second) {
            this.time.set(Calendar.HOUR_OF_DAY, hourOfDay);
            this.time.set(Calendar.MINUTE, minute);
            this.time.set(Calendar.SECOND, second);
            return this;
        }

        public ResePlanerareRequest.Builder time(int hourOfDay, int minute) {
            return time(hourOfDay, minute, 0);
        }

        public ResePlanerareRequest.Builder calendarField(int field, int value) {
            this.time.set(field, value);
            return this;
        }

        public ResePlanerareRequest.Builder searchForArrival(boolean searchForArrival) {
            this.searchForArrival = searchForArrival;
            return this;
        }

        public ResePlanerareRequest.Builder searchForArrival() {
            return searchForArrival(true);
        }

        public ResePlanerareRequest.Builder originID(String originId) {
            this.originId = originId;
            return this;
        }

        public ResePlanerareRequest.Builder originCoords(Coordinates originCoords) {
            this.originCoords = originCoords;
            return this;
        }



    }
}
