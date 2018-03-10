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

    private List<Question> questionList = new ArrayList<>();
    private static int questionIndice = 0;
    private static int questionEvoyer = 0;
    private static int nbrQuestion = 1;

    public Question() {}



    public void addAllQuestionList(List<Question> questionList) { this.questionList.addAll(questionList); }

    /**
     * Méthode qui permet de reçevoir une question
     *
     * @param key
     * @return
     */
    public String getQuestion(String key, String serverKey) {

        if(nbrQuestion > 10) {
            nbrQuestion = 1;
            questionEvoyer = 0;
            questionIndice = 0;
            return "fin";
        }

        Gson gson = new Gson();
        int indice = questionIndice;

        questionEvoyer++;
        if(questionEvoyer == 2) {
            questionEvoyer = 0;
            questionIndice++;
        }

        if(questionIndice == questionList.size())
            questionIndice = 0;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";

        System.out.println(questionList.get(0).text);

        nbrQuestion++;


        return gson.toJson(questionList.get(indice));
    }
}

