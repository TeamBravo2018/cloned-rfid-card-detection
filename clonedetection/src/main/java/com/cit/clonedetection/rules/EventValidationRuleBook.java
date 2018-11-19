package com.cit.clonedetection.rules;

import com.cit.clonedetection.CloneDetectionResult;
import com.cit.common.om.access.request.AccessRequest;
import com.deliveredtechnologies.rulebook.lang.RuleBuilder;
import com.deliveredtechnologies.rulebook.model.rulechain.cor.CoRRuleBook;

/**
 * Rulebook to handle access
 * Can be expanded upon to allow for further rules
 * Created by Eamon on 11/12/2018.
 */

public class EventValidationRuleBook extends CoRRuleBook<CloneDetectionResult> {

    public static final String POSSIBLE_TIME_DISTANCE_EVENT = "Possible time-distance event";
    public static final String IMPOSSIBLE_TIME_DISTANCE_EVENT = "Impossible time-distance event";
    private AccessRequest test = new AccessRequest();

    @Override
    public void defineRules() {


        //  add a rule which evaluates if the current event and the last event were at the same panel location
        addRule(RuleBuilder.create().withFactType(EventValidationBean.class).withResultType(CloneDetectionResult.class)
                .when(facts ->
                        facts.getOne().isThePanelsTheSameforCurrentAndPreviousEvents()
                )
                .using("isThePanelsTheSameforCurrentAndPreviousEvents()")
                .then((facts, result) -> result.setValue( CloneDetectionResult.builder().previousAccessRequest(test).accessRequest(test).reason(POSSIBLE_TIME_DISTANCE_EVENT).genuineCard(true).build() ))
                .stop()
                .build());


        //  add a rule which evaluates if its possible to walk between events
        addRule(RuleBuilder.create().withFactType(EventValidationBean.class).withResultType(CloneDetectionResult.class)
                .when(facts ->
                        facts.getOne().isItPossibleToWalkBetweenCurrentAndPreviousEvent()
                )
                .using("isItPossibleToWalkBetweenCurrentAndPreviousEvent()")
                .then((facts, result) -> result.setValue( CloneDetectionResult.builder().previousAccessRequest(test).accessRequest(test).reason(POSSIBLE_TIME_DISTANCE_EVENT).genuineCard(true).build() ))
                .stop()
                .build());

        //  evaluate only if preceding rules fails
        //  add a rule which evaluates if not possible to drive between events
        addRule(RuleBuilder.create().withFactType(EventValidationBean.class).withResultType(CloneDetectionResult.class)
                .when(facts ->
                        facts.getOne().isItPossibleToDriveBetweenCurrentAndPreviousEvent()
                )
                .using(".isItPossibleToDriveBetweenCurrentAndPreviousEvent()")
                .then((facts, result) -> result.setValue( CloneDetectionResult.builder().previousAccessRequest(test).accessRequest(test).reason(POSSIBLE_TIME_DISTANCE_EVENT).genuineCard(true).build() ))
                .stop()
                .build());

        //  evaluate only if preceding rules fails
        //  add a rule which evaluates if its not possible to fly + drive between events
        addRule(RuleBuilder.create().withFactType(EventValidationBean.class).withResultType(CloneDetectionResult.class)
                .when(facts ->
                        facts.getOne().isItPossibleToFlyAndDriveBetweenCurrentAndPreviousEvent()
                )
                .using(".isItPossibleToFlyAndDriveBetweenCurrentAndPreviousEvent()")
                .then((facts, result) -> result.setValue( CloneDetectionResult.builder().previousAccessRequest(test).accessRequest(test).reason(POSSIBLE_TIME_DISTANCE_EVENT).genuineCard(true).build() ))
                .stop()
                .build());

        //  evaluate only if preceding rules fails
        //  add this rule so that if all other rules fail it must be an immpossible event
        addRule(RuleBuilder.create().withFactType(EventValidationBean.class).withResultType(CloneDetectionResult.class)
                .using("add this rule so that if all other rules fail it must be an immpossible event")
                .then((facts, result) -> result.setValue( CloneDetectionResult.builder().previousAccessRequest(test).accessRequest(test).reason(IMPOSSIBLE_TIME_DISTANCE_EVENT).genuineCard(false).build() ))
                .stop()
                .build());





/*

From
	CA, USA
To
	London, UK
Route
	LHW-BOS
	Flights
		norweigian 4:20 pm - 6:50 pm
		delta  9:20 am  - 12:19 pm
		virgin 4:35 PM - 7:15 pm
		BA  7:10pm - 9:50 PM
	Earliest departure 9:20 am
	Latest	departure 7:pm
	Duration 7 hr 50m



 */


    }

}
