package org.android.server.bean;

/**
 * Created by Kevin Dupeyrat on 18/02/18.
 *
 * Class de gestion de requête d'amis.
 * Elle permet d'enregister la requête d'un amis pour
 * un autre amis.
 * Le Boolean de reponse permet de transmettre la réponse
 * à la requête au demandeur.
 * Si True, alors on lance le jeu,
 * Si False, on ne lance pas le jeu et on l'indique
 * au demandeur.
 * Si null, on continu a demander
 */
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
