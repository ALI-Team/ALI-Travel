package alitea.am.ali_travel.api_wrapper;

import android.content.Context;

/**
 * Created by axel on 04/03/17.
 */

public abstract class OGT {
    public static PlatsUppslagRequest.Builder platsUppslag(Context ctx) {
        return new PlatsUppslagRequest.Builder(ctx);
    }
}
