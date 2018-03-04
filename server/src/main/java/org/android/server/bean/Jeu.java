package org.android.server.bean;

public class Jeu {

    private String joueur1;
    private String joueur2;
    private int scoreJoueur1;
    private int scoreJoueur2;


    public Jeu(String joueur1, String joueur2, int scoreJoueur1, int scoreJoueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.scoreJoueur1 = scoreJoueur1;
        this.scoreJoueur2 = scoreJoueur2;
    }


    public String getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(String joueur1) {
        this.joueur1 = joueur1;
    }

    public String getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(String joueur2) {
        this.joueur2 = joueur2;
    }

    public int getScoreJoueur1() {
        return scoreJoueur1;
    }

    public void setScoreJoueur1(int scoreJoueur1) {
        this.scoreJoueur1 = scoreJoueur1;
    }

    public int getScoreJoueur2() {
        return scoreJoueur2;
    }

    public void setScoreJoueur2(int scoreJoueur2) {
        this.scoreJoueur2 = scoreJoueur2;
    }
}
