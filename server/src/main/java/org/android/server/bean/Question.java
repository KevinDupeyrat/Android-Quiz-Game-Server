package org.android.server.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kevin Dupeyrat on 24/02/18.
 *
 * Class qui représente une Question.
 * Elle est composé d'une Question et d'une liste
 * de reponses qui lui sont associées
 *
 */

public class Question {


    @SerializedName("id")
    private String id;
    @SerializedName("text")
    private String text;
    @SerializedName("duration")
    private String duration;
    @SerializedName("answers")
    private List<Reponse> answers;



    public Question() {}


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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Reponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Reponse> answers) {
        this.answers = answers;
    }
}

