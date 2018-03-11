package org.android.server.model;

import com.google.gson.Gson;
import org.android.server.bean.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {


    private List<Question> questionList = new ArrayList<>();
    private static int questionIndice = 0;
    private static int questionEvoyer = 0;
    private static int nbrQuestion = 1;

    public void addAllQuestionList(List<Question> questionList) { this.questionList.addAll(questionList); }

    /**
     * Méthode qui permet de reçevoir une question
     *
     * @param key
     * @return
     */
    public String getQuestion(String key, String serverKey, JeuModel jeuModel) {

        if(nbrQuestion > 10) {
            if(questionEvoyer == 2){
                nbrQuestion = 1;
                questionEvoyer = 0;
                questionIndice = 0;
            }

            jeuModel.getJeuList().clear();

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


        nbrQuestion++;


        return gson.toJson(questionList.get(indice));
    }

}
