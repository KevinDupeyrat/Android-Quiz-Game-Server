package org.android.server.bean;

import java.util.ArrayList;
import java.util.List;

public class FriendRequest {

    private String demandeur;
    private String myFriend;
    private Boolean response;


    public FriendRequest() {}

    public FriendRequest(String demandeur, String myFriend, Boolean response) {
        this.demandeur = demandeur;
        this.myFriend = myFriend;
        this.response = response;
    }


    public String getDemandeur() {
        return demandeur;
    }

    public String getMyFriend() {
        return myFriend;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }


}
