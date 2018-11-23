package com.cit.restapi.validationrequest.dto;

import com.cit.restapi.rfidpanel.dto.AccessRequestDto;

import javax.validation.constraints.NotNull;

/**
 * Created by efoy on 11/11/2018.
 */
public class RfidPanelAccessRequestPreviousDto extends AccessRequestDto {

    @NotNull
    private String panelId;

    @NotNull
    private String cardId;

    private boolean allowed;

    private Long timeStamp;

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
