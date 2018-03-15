package org.android.server.model;

import com.google.gson.Gson;
import org.android.server.bean.Amis;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kevin Dupeyrat on 10/03/18.
 *
 * Class qui permet d'effectuer des actions
 * sur une liste d'Amis.
 *
 */
public class AmisModel {

    private List<Amis> amisList = new ArrayList<>();


    public AmisModel() {}

    public void addAllFriendListe(List<Amis> amisList) {

        this.amisList.addAll(amisList) ;
    }


    public List<Amis> getAmisList() {

        return amisList;
    }


    /**
     * Méthode qui permet de ce connecter au server
     * et donc de ce rendre disponnible pour jouer
     *
     * @param key
     * @param id
     * @param serverKey
     * @return
     */
    public String checkAttending(String key, String id, String serverKey) {


        Boolean idOk = false;


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(id)) {
                idOk = true;
                amis.setIs_present("1");
            }
        }

        if(!idOk)
            return "{'error' : 'You cannot be checked !!!'}";



        return "";

    }


    /**
     * Méthode qui permet de nous deconnecter du server
     * et nous rendre donc indisponnible pour jouer
     *
     * @param key
     * @param id
     * @param serverKey
     * @return
     */
    public String disconnect(String key, String id, String serverKey) {

        Boolean idOk = false;


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(id)) {
                idOk = true;
                amis.setIs_present("0");
            }
        }

        if(!idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        return "";
    }


    /**
     * Méthode qui nous permet de reçevoir la liste d'amis
     *
     * @param key
     * @param serverKey
     * @return
     */
    public String getFriends(String key, String serverKey) {

        Gson gson = new Gson();

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        return gson.toJson(amisList);
    }
}
