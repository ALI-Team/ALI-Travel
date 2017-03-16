package alitea.am.ali_travel.api_wrapper;

import android.annotation.SuppressLint;

import java.util.Locale;

/**
 * Created by axel on 05/03/17.
 */

public class Walk {
    private boolean walk;
    private int minWalkDistance;
    private int maxWalkDistance;
    private int walkSpeed;

    /**
     * Constructs a new Walk, used in ResePlanerareRequest$Builder::originWalk and
     * ResePlanerareRequest$Builder::destWalk
     * @param walk Whether or not to allow walk
     * @param minWalkDistance minimum distance to walk in meters
     * @param maxWalkDistance maximum distance to walk in meters
     * @param walkSpeed speed as a percentage of default speed(5km/h + 2min)
     */
    public Walk(boolean walk, int minWalkDistance, int maxWalkDistance, int walkSpeed) {
        this.walk = walk;
        this.minWalkDistance = minWalkDistance;
        this.maxWalkDistance = maxWalkDistance;
        this.walkSpeed = walkSpeed;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%1$d,%2$d,%3$d,%4$d", walk ? 0 : 1, minWalkDistance,
                maxWalkDistance, walkSpeed);
    }
}
