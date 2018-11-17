package com.cit.common.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by odziea on 11/14/2018.
 */
@Service
public class URILookup implements IURILookup {

    @Value("uri.locator.root")
    private String LOCATOR_ROOT;

    private Map<ApplicationType, String> applicationToUriMap = new HashMap<>();

    public URILookup(){
        applicationToUriMap.put(ApplicationType.LOCATOR, LOCATOR_ROOT);
    }

    public String lookup(ApplicationType applicationType, String request){
        return this.applicationToUriMap.get(applicationType).concat("/").concat(request);
    }

}
