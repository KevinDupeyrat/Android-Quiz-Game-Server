package org.android.server.bean;

import java.util.ArrayList;
import java.util.List;

public class FriendRequest {

    private String demandeur;
    private String myFriend;
    private Boolean response;

    private List<FriendRequest> friendRequest = new ArrayList<>();

    public FriendRequest() {}

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

    public void addFriendRequest(FriendRequest friendRequest) {
        this.friendRequest.add(friendRequest);
    }


    /**
     * Méthode qui permet d'envoyer sur le server
     * une requête pour un amis
     *
     * @param key
     * @param serverKey
     * @param my_id
     * @param friend_id
     * @param amisList
     * @return
     */
    public String putRequestFriend(String key, String serverKey, String my_id,
                                   String friend_id, List<Amis> amisList) {

        Boolean my_idOk = false;
        Boolean friend_idOk = false;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id))
                my_idOk = true;
            if(amis.getId().equals(friend_id))
                friend_idOk = true;
        }


        if(!my_idOk && !friend_idOk)
            return "{'error' : 'You cannot be checked !!!'}";



        System.out.println("Je push une requête ...");

        friendRequest.add(new FriendRequest(my_id, friend_id, null));


        return null;
    }


    /**
     * Méthode qui permet de vérifier si nous avons une requête
     * qui nous attend sur le server
     *
     * @param key
     * @param serverKey
     * @param my_id
     * @param amisList
     * @return
     */
    public String checkRequest(String key, String serverKey, String my_id,
                               List<Amis> amisList) {

        Boolean idOk = false;


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id)) {
                idOk = true;
            }
        }

        if(!idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        // S'il y a une requête on envoie le nom et le prénom de
        // celui qui a fait la demande
        for (FriendRequest request: friendRequest)
            if(request.getMyFriend().equals(my_id)){
                for(Amis amis: amisList){
                    if(amis.getId().equals(request.getDemandeur()))
                        return amis.getId();
                }
            }




        return "please wait";

    }


    /**
     * Méthode qui permet de renvoyer notre reponse
     * au server
     *
     * @param key
     * @param serverKey
     * @param my_id
     * @param friend_id
     * @param amisList
     * @param updateResponse
     * @return
     */
    public String responceRequestFriend(String key, String serverKey, String my_id,
                                        String friend_id, List<Amis> amisList,
                                        String updateResponse) {

        Boolean my_idOk = false;
        Boolean friend_idOk = false;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id))
                my_idOk = true;
            if(amis.getId().equals(friend_id))
                friend_idOk = true;
        }


        if(!my_idOk && !friend_idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        System.out.println("J'attend que mon pote reponde");

        for (FriendRequest request: friendRequest)
            if(request.getMyFriend().equals(my_id) && request.getDemandeur().equals(friend_id)) {
                if(updateResponse.equals("yes"))
                    request.setResponse(true);
                else
                    request.setResponse(false);
                return "{'responseRequeste' : 'your response as been post'}";
            }


        return null;
    }


    /**
     * Méthode qui permet de vérifier si notre amis
     * a repondu à notre requête
     *
     * @param key
     * @param my_id
     * @param amisList
     * @param jeuList
     * @param serverKey
     * @return
     */
    public String getResponseFriend(String key, String serverKey, String my_id,
                                    List<Amis> amisList, List<Jeu> jeuList) {

        Boolean my_idOk = false;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList)
            if (amis.getId().equals(my_id))
                my_idOk = true;



        if(!my_idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        int i = 0;
        int indice = 0;
        for(FriendRequest fr: this.friendRequest) {

            System.out.println("Indice : " + i + "\nDemandeur : "
                    + fr.getDemandeur() + "\nFriend : "
                    + fr.getMyFriend() + "\nResponse : "
                    + fr.getResponse());

            if(fr.getDemandeur().equals(my_id)){
                indice = i;
            } else {
                i++;
            }
        }

        System.out.println("J'attend la réponse du copain");

        if(this.friendRequest.get(i).getResponse() != null) {
            if(this.friendRequest.get(i).getResponse()){
                jeuList.add(new Jeu(my_id, this.friendRequest.get(i).getMyFriend(),
                        0, 0));
                this.friendRequest.remove(indice);
                return "yes";
            }
        }

        System.out.println("JE VAIS RENVOYER NO");
        return "no";
    }
}
