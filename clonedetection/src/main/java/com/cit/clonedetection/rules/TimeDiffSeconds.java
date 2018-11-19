package com.cit.clonedetection.rules;

import java.time.Duration;
import java.time.ZonedDateTime;

public class TimeDiffSeconds implements ITimeDiff{
    @Override
    public long timeInMiliSecondsBetweenTimeStamps(ZonedDateTime t1, ZonedDateTime t2) {
        // get time difference in milliseconds
        long milliSeconds = Duration.between(t1,t2).toMillis();
        return milliSeconds;
    }
}
