package com.cit.clonedetection.rules;

import com.cit.common.om.location.GeoLocation;
import com.cit.common.om.access.request.AccessRequest;
import java.time.ZonedDateTime;
import java.time.Duration;

/**
 *  Used by rules engine to calculate distance and time between events
 * Created by Eamon on 11/12/2018.
 */
public class PanelDistanceCalculator {

    private PanelDistanceCalculator() {
        throw new IllegalStateException("Utility class");
    }

    // Average walk speed of 1.4 mtrs per second = AVERAGE_WALK_SPEED_MTRS_PER_SECOND
    public static final double AVERAGE_WALK_SPEED_MTRS_PER_SECOND = 1.4;

    /**
     * Used to calculate the time in milli seconds between two timestamps
     * @param t1 first timestamp
     * @param t2 second timestamp
     * @return time in milli seconds between two timestamps
     */
    public static long timeInMiliSecondsBetweenTimeStamps(ZonedDateTime t1, ZonedDateTime t2) {
        // get time difference in seconds
        long milliSeconds = Duration.between(t1,t2).toMillis();
        return milliSeconds;

    }

    /**
     * Used to calculate the time in seconds between two timestamps
     * @param t1 first timestamp
     * @param t2 second timestamp
     * @return time in seconds between two timestamps
     */
    public static int timeInSecondsBetweenTimeStamps(ZonedDateTime t1, ZonedDateTime t2) {

        // get time difference in seconds
        long milliseconds = timeInMiliSecondsBetweenTimeStamps(t1, t2);

        return (int) milliseconds / 1000;
    }

}
