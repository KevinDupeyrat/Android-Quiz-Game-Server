package org.android.server.bean;


/**
 * Created by Kevin Dupeyrat on 18/02/18.
 *
 * Class qui décrit un Joueur lors qu'un Quiz.
 * Il est composer de son ID aunsi que de son
 * score qui est remsis à jour au fure et a mesure
 * du déroulement du jeu.
 */
public class Joueur {

    private String id;
    private int score;

    public Joueur() {}

    public Joueur(String id) {

        this.id = id;
        this.score = 0;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }
}

