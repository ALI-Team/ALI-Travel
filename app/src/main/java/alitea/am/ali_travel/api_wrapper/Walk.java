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
