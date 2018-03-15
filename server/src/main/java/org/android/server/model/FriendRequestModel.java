package org.android.server.model;

import org.android.server.bean.Amis;
import org.android.server.bean.FriendRequest;
import org.android.server.bean.Jeu;
import org.android.server.bean.Joueur;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kevin Dupeyrat on 10/03/18.
 *
 * Class qui permet d'effectuer des actions
 * sur une liste de Requête d'Amis.
 *
 */
public class FriendRequestModel {

    private List<FriendRequest> friendRequest = new ArrayList<>();

    public FriendRequestModel(){}


    public List<FriendRequest> getFriendRequest() {
        return friendRequest;
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


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        boolean my_idOk = this.verifId(amisList, my_id);
        boolean friend_idOk = this.verifId(amisList, friend_id);


        if(!my_idOk && !friend_idOk)
            return "{'error' : 'You cannot be checked !!!'}";


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

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";

        Boolean my_idOk = this.verifId(amisList, my_id);
        Boolean friend_idOk = this.verifId(amisList, friend_id);


        if(!my_idOk && !friend_idOk)
            return "{'error' : 'You cannot be checked !!!'}";


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

            if(fr.getDemandeur().equals(my_id)){
                indice = i;
            } else {
                i++;
            }
        }

        if (this.friendRequest.get(indice).getResponse() != null) {
            if (this.friendRequest.get(indice).getResponse()){
                jeuList.add(
                        new Jeu(
                                new Joueur(my_id),
                                new Joueur(this.friendRequest.get(indice).getMyFriend())));
                this.friendRequest.clear();
                return "yes";
            } else if (!this.friendRequest.get(indice).getResponse()) {
                return "no";
            }
        }

        return "wait";
    }


    /**
     * Méthode qui permet la vérification des ID
     *
     * @param amisList
     * @param my_id
     */
    private boolean verifId(List<Amis> amisList, String my_id) {

        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id))
                return true;
        }

        return false;
    }
}
