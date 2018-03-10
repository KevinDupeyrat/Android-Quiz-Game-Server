package org.android.server.bean;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private String joueur1;
    private String joueur2;
    private int scoreJoueur1;
    private int scoreJoueur2;

    private List<Jeu> jeuList = new ArrayList<>();


    public Jeu() {}

    public Jeu(String joueur1, String joueur2, int scoreJoueur1, int scoreJoueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.scoreJoueur1 = scoreJoueur1;
        this.scoreJoueur2 = scoreJoueur2;
    }



    public List<Jeu> getJeuList() {
        return jeuList;
    }


    /**
     * Méthode qui permet la mise à jour
     * des score en fonction de l'id du joueur
     * et du resultat de la reponse (true ou false)
     *
     * @param id
     * @param reponse
     */
    public void updateScore(String id, boolean reponse){

        int result = 0;

        result = (reponse) ? 5 : -5;



       int i = 0;
       boolean find = false;
       while (i < jeuList.size() && !find) {

           if(jeuList.get(i).joueur1.equals(id)) {
               scoreJoueur1 += result;
               find = true;
           }
           else if(jeuList.get(i).joueur2.equals(id)) {
               scoreJoueur2 += result;
               find = true;
           }

       }

    }


    /**
     * Méthode qui permet de récuperer le score de
     * l'adversaire
     *
     * @param id
     * @return
     */
    public int  getScoreAdv(String id) {

        //TODO: faire méthode qui permet de renvoyer
        //TODO: le score de l'adversaire

        int i = 0;
        while (i < jeuList.size()) {

            if(jeuList.get(i).joueur1.equals(id)) {
                return jeuList.get(i).scoreJoueur2;
            }
            else if(jeuList.get(i).joueur2.equals(id)) {
                return jeuList.get(i).scoreJoueur1;
            }

        }

        return 0;
    }
}


