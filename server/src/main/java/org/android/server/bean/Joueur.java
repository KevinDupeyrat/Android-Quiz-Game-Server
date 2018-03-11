package org.android.server.bean;

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

