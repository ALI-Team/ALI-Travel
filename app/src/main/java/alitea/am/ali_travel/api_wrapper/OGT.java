package alitea.am.ali_travel.api_wrapper;

import android.content.Context;

import alitea.am.ali_travel.api_wrapper.plats_uppslag.PlatsUppslagRequest;
import alitea.am.ali_travel.api_wrapper.rese_planerare.ResePlanerareRequest;

/**
 * Created by axel on 04/03/17.
 */

public abstract class OGT {
    public static PlatsUppslagRequest.Builder platsUppslag(Context ctx) {
        return new PlatsUppslagRequest.Builder(ctx);
    }

    public static ResePlanerareRequest.Builder resePlanerare(Context ctx) {
        return new ResePlanerareRequest.Builder(ctx);
    }
}
