package com.cit.clonedetection.rules;
import java.time.ZonedDateTime;
import java.time.Duration;

public interface ITimeDiff {
    long timeInMiliSecondsBetweenTimeStamps(ZonedDateTime t1, ZonedDateTime t2);
}
