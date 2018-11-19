package com.cit.clonedetection.rules;

import com.cit.clonedetection.models.Distance;
import com.cit.common.om.access.request.AccessRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * This bean is used by the rules engine to write rules and determine facts using the previous event and current to
 * determine if the access card is a possible clone
 *
 * Get time of last event
 * Determine the time in seconds between last event and new event
 * Check is possible to walk between between the panels within this timeframe using haversine formula
 * ( in order to check we need the geo location of each event )
 * if check is yes its not a clone
 * if check is no we need to further check google api's to see if its possible to go between panels by car within the time.
 * if its not then its a possible clone card
 *
 * Created by Eamon on 11/12/2018.
 */
@Slf4j
public class EventValidationBean {

    private IDistanceService distanceService;
    private AccessRequest current;
    private AccessRequest previous;


    public EventValidationBean(AccessRequest current, AccessRequest previous, IDistanceService distanceService ) {
        this.current=current;
        this.previous=previous;
        this.distanceService=distanceService;
    }

    public boolean isThePanelsTheSameforCurrentAndPreviousEvents() {

        boolean result = current.getAccessToken().getTokenId().equals(previous.getAccessToken().getTokenId());
        // if the prev pannel and the current pannel are the same panel

        log.debug( "Fact the events at same panel={}\n, Current event ={},\n PreviousEvent={}", result, this.current, this.previous );

        return result;
    }

    /**
     * Calculate distance in meters between GPS point A and GPS point B = traveldistanceMtrsBetweenGPSPoints
     * @return distance in Mtrs
     */
    double getTravelDistanceInMtrs() {
        double travelDistanceMtrsBetweenGPSPoints  = DistanceBetweenRequests.distanceInMtrsBetweenTwoEvents(this.current, this.previous);
        log.debug( "Travel Distance in Mtrs between Events = {}\n, Current event ={},\n PreviousEvent={}", travelDistanceMtrsBetweenGPSPoints, this.current, this.previous );
        return travelDistanceMtrsBetweenGPSPoints;
    }

    /**
     *  Given average walk speed of 1.4 mtrs per second = AVERAGE_WALK_SPEED_MTRS_PER_SECOND
     *  Calculate time in seconds between two Access Events = t1-t2 = timeInSecBetweenEvents
     *  @return time in seconds between two Access Events = t1-t2
     */
    private int getSecondsBetweenEvents() {

        int secondsBetweenEvents = PanelDistanceCalculator.timeInSecondsBetweenTimeStamps(previous.getAccessTime(), current.getAccessTime());

        log.debug( "Seconds between Events = {}\n, CurrentEvent={},\n PreviousEvent={}", secondsBetweenEvents, this.current, this.previous );

        return secondsBetweenEvents;
    }


    public boolean isTheCurrentAndPreviousEventsInTheSameCountry() {


        String currentRelativeLocation = this.current.getAccessIssuer().getGeoLocation().toString();
        String previousRelativeLocation = this.current.getAccessIssuer().getGeoLocation().toString();

        String countryPrevious = previousRelativeLocation.substring(previousRelativeLocation.lastIndexOf(',') + 1).trim();
        String countryCurrent = currentRelativeLocation.substring(currentRelativeLocation.lastIndexOf(',') + 1).trim();

        boolean result = countryPrevious.equals(countryCurrent);

        // if the prev pannel and the current pannel are the same panel


        log.debug( "Event in same Country ={},\nCurrentEvent={},\nPreviousEvent={}", result, this.current, this.previous );


        return result;
    }


    public boolean isItPossibleToTravelBetweenCurrentAndPreviousEvent(IDistanceService.Mode mode) {

        if (isThePanelsTheSameforCurrentAndPreviousEvents()) {
            return true;
        }

        Distance distance = distanceService.execute(this.current.getAccessIssuer().getGeoLocation(),previous.getAccessIssuer().getGeoLocation(), mode);

        // if the time between events is greater than the normal travel time return true, otherwise false
        return getSecondsBetweenEvents() >= distance.getDuration();

    }

    public boolean isItPossibleToWalkBetweenCurrentAndPreviousEvent() {
        boolean result = false;


        if (isTheCurrentAndPreviousEventsInTheSameCountry())
            result = isItPossibleToTravelBetweenCurrentAndPreviousEvent(IDistanceService.Mode.walking);


        log.debug("Possibility of walking between these events within the timeframe of the last event is = {}\n, CurrentEvent={},\n PreviousEvent={}", result,
                    this.current, this.previous);


        return result;
    }

    public boolean isItPossibleToDriveBetweenCurrentAndPreviousEvent() {

        boolean result = false;

        if (isTheCurrentAndPreviousEventsInTheSameCountry())
            result = isItPossibleToTravelBetweenCurrentAndPreviousEvent(IDistanceService.Mode.driving);


        log.debug("Possibility of driving between these events within the timeframe of the last event is = {}\n, CurrentEvent={},\n PreviousEvent={}",
                    result, this.current, this.previous);


        return result;
    }



    public boolean isItPossibleToFlyAndDriveBetweenCurrentAndPreviousEvent() {

        boolean result = false;

        if (!isTheCurrentAndPreviousEventsInTheSameCountry())
            result = isItPossibleToTravelBetweenCurrentAndPreviousEvent(IDistanceService.Mode.flyDrive);


        log.debug("Possibility of Fly drive between these events within the timeframe of the last event is = {}\n, CurrentEvent={},\n PreviousEvent={}",
                    result, this.current, this.previous);


        return result;

    }


}
