package org.android.server.bean;

import com.google.gson.annotations.SerializedName;


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

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
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

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getLast_score() {
        return last_score;
    }

    public void setLast_score(String last_score) {
        this.last_score = last_score;
    }
}
