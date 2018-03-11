package org.android.server.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dupeyrat on 24/02/18.
 *
 * Class qui représente une Question.
 * Elle est composé d'une Question et d'une liste
 * de reponses
 */

public class Question {

    // TODO: @SerializedName("") avec le bon nom

    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("duration")
    private String duration;
    @SerializedName("answers")
    private List<Reponse> answers;



    public Question() {}

}

