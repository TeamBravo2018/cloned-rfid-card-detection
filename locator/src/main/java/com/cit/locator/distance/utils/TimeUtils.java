package com.cit.locator.distance.utils;

import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Created by odziea on 11/20/2018.
 */
public class TimeUtils {

    // Average walk speed of 1.4 mtrs per second = AVERAGE_WALK_SPEED_MTRS_PER_SECOND
    public static final double AVERAGE_WALK_SPEED_MTRS_PER_SECOND = 1.4;

    public static long timeInMiliSecondsBetweenTimeStamps(ZonedDateTime t1, ZonedDateTime t2) {
        // get time difference in milliseconds
        long milliSeconds = Duration.between(t1,t2).toMillis();
        return milliSeconds;
    }


    /**
     * Used to calculate the time in milli seconds between two timestamps
     * @param t1 first timestamp
     * @param t2 second timestamp
     * @return time in milli seconds between two timestamps
     */
    public static long timeInMiliSecondsBetweenDates(ZonedDateTime t1, ZonedDateTime t2) {
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
