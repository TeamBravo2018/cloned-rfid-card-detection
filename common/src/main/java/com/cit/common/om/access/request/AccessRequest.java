package com.cit.common.om.access.request;

import com.cit.common.om.access.device.TokenReader;
import com.cit.common.om.access.token.Token;
import com.cit.common.om.location.GeoLocation;

/**
 * Class to represent a building access request
 * @author odziea
 */
public class AccessRequest<T extends Token, R extends TokenReader> {

    private T accessToken;
    private R accessIssuer;
    private boolean requestGranted;

    public AccessRequest() {
    }

    public T getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(T accessToken) {
        this.accessToken = accessToken;
    }

    public R getAccessIssuer() {
        return accessIssuer;
    }

    public void setAccessIssuer(R accessIssuer) {
        this.accessIssuer = accessIssuer;
    }

    public boolean isRequestGranted() {
        return requestGranted;
    }

    public void setRequestGranted(boolean requestGranted) {
        this.requestGranted = requestGranted;
    }


}
