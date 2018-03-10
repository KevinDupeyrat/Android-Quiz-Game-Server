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

    public Reponse() {
    }

}
