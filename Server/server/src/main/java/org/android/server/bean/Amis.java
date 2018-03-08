package org.android.server.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dupeyrat on 18/02/18.
 *
 * Class qui represente un amis
 * avec son nom et son avatar
 */

public class Amis {

    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("is_present")
    private String is_present;
    @SerializedName("photo_path")
    private String photo_path;
    @SerializedName("last_score")
    private String last_score;

    private List<Amis> amisList = new ArrayList<>();


    public Amis() {}

    public Amis(String id, String nom, String prenom, String present, String avatar, String ls) {

        this.id = id;
        this.first_name = nom;
        this.last_name = prenom;
        this.is_present = present;
        this.photo_path = avatar;
        this.last_score = ls;

    }


    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getIs_present() {
        return is_present;
    }

    public void setIs_present(String is_present) {
        this.is_present = is_present;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public String getLast_score() {
        return last_score;
    }

    public void setLast_score(String last_score) {
        this.last_score = last_score;
    }

    public void addFriendList(Amis amis) {
        this.amisList.add(amis);
    }

    public void addAllFriendListe(List<Amis> amisList) { this.amisList.addAll(amisList) ;}

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



        for(Amis amis: amisList)
            System.out.println(amis.getFirst_name() + "  " + amis.getIs_present());

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
