package org.android.server.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dupeyrat on 28/02/18.
 *
 * Class qui représente une reponse.
 * Elle est composé de la reponse et
 * d'un tratus (bonne ou mauvaise reponse)
 */

public class Reponse {

    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("is_right")
    private String is_right;

    private List<Reponse> reponseList = new ArrayList<>();

    public Reponse() {}


    public Reponse(String id, String reponse, String bonneReponse) {

        this.id = id;
        this.text = reponse;
        this.is_right = bonneReponse;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIs_right() {
        return is_right;
    }

    public void setIs_right(String is_right) {
        this.is_right = is_right;
    }

    public List<Reponse> getReponseList(){
        return this.reponseList;
    }

    public void addReponseLiser(Reponse reponse) {
        this.reponseList.add(reponse);
    }
}
