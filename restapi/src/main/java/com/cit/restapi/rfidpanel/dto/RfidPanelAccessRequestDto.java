package com.cit.restapi.rfidpanel.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by odziea on 11/17/2018.
 */
public class RfidPanelAccessRequestDto extends AccessRequestDto {

    @NotNull
    private String panelId;

    @NotNull
    private String cardId;

    private boolean allowed;

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
}
