package com.cit.clonedetection;

import com.cit.clonedetection.rules.EventValidationBean;
import com.cit.locator.distance.om.Distance;
import com.cit.locator.distance.om.TravelMeans;
import com.cit.locator.distance.service.IDistanceService;
import com.cit.clonedetection.service.IAccessRequestManager;
import com.cit.common.om.access.device.RfidReaderPanel;
import com.cit.common.om.access.request.AccessRequest;
import com.cit.common.om.access.token.RfidBadge;
import com.cit.locator.panellocator.IPanelLocatorService;
import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.RuleBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by odziea on 11/12/2018.
 */
@Service
public class CloneDetectionService implements ICloneDetectionService{

    @Autowired
    IPanelLocatorService panelLocatorService;

    @Autowired
    IAccessRequestManager accessRequestManager;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private RuleBook eventRuleBook;

    @Autowired
    private IDistanceService distanceService;

    @Override
    public CloneDetectionResult checkForClonedCard(AccessRequest accessRequest) {


        CloneDetectionResult validationRulesResult = new CloneDetectionResult();

        AccessRequest<RfidBadge, RfidReaderPanel> previousAccessRequest = this.accessRequestManager.findPreviousAccessRequestForCard(accessRequest.getAccessToken().getTokenId());


        if(previousAccessRequest!=null){
            RfidReaderPanel previousAccessReaderPanel = panelLocatorService.findPanelById(previousAccessRequest.getAccessIssuer().getId());

            RfidReaderPanel currentAccessReaderPanel = panelLocatorService.findPanelById(accessRequest.getAccessIssuer().getId());

            Distance distance = this.distanceService.execute(currentAccessReaderPanel.getGeoLocation(), previousAccessReaderPanel.getGeoLocation(), TravelMeans.driving);

            NameValueReferableMap facts = new FactMap();
            facts.setValue("eventValidation", new EventValidationBean(accessRequest, previousAccessRequest, distanceService));

            eventRuleBook.run(facts);


            Optional<Result> optionalResult = eventRuleBook.getResult();
            if (optionalResult.isPresent()) {

                CloneDetectionResult result = (CloneDetectionResult) optionalResult.get().getValue();
                validationRulesResult.setReason(result.getReason());
                validationRulesResult.setGenuineCard(result.isGenuineCard());
            }

        }else{
            validationRulesResult.setReason("Possible time-distance event.");
            validationRulesResult.setGenuineCard(true);
        }

        this.accessRequestManager.recordAccessRequest(accessRequest);

        return validationRulesResult;
    }
}
