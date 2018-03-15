package org.android.server.bean;

/**
 * Created by Kevin Dupeyrat on 18/02/18.
 *
 * Class qui permet de gérer la partie
 * entre deux joueur.
 * Elle permet de sauvegarder l'état des joueur
 * avec chacun leurs données (comme le score)
 */
public class Jeu {

    private Joueur joueur1;
    private Joueur joueur2;



    public Jeu() {}

    public Jeu(Joueur joueur1, Joueur joueur2) {

        this.joueur1 = joueur1;
        this.joueur2 = joueur2;

    }


    public Joueur getJoueur1() {
        return joueur1;
    }


    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }


    public Joueur getJoueur2() {
        return joueur2;
    }


    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
}


