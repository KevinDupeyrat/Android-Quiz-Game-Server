package org.android.server.bean;

public class FriendRequest {

    private String demandeur;
    private String myFriend;
    private Boolean response;

    public FriendRequest(String demandeur, String myFriend, Boolean response) {
        this.demandeur = demandeur;
        this.myFriend = myFriend;
        this.response = response;
    }


    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getMyFriend() {
        return myFriend;
    }

    public void setMyFriend(String myFriend) {
        this.myFriend = myFriend;
    }

    public Boolean getResponse() {
        return response;
    }

    public void setResponse(Boolean response) {
        this.response = response;
    }
}
