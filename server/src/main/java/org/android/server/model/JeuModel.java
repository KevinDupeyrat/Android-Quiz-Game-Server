package org.android.server.model;

import org.android.server.bean.Jeu;
import org.android.server.bean.Joueur;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Kevin Dupeyrat on 10/03/18.
 *
 * Class qui permet d'effectuer des actions
 * sur une liste de Jeu.
 *
 */
public class JeuModel {

    private List<Jeu> jeuList = new ArrayList<>();


    public JeuModel() {}

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

        int result;

        result = (reponse == true) ? 5 : 0;

        int i = 0;
        boolean find = false;
        while (i < jeuList.size() && !find) {

//            System.out.println("Id joueur 1 : " + jeuList.get(i).getJoueur1().getId());
//            System.out.println("Id joueur 2 : " + jeuList.get(i).getJoueur2().getId());
//            System.out.println("Id : " + id);

            if(jeuList.get(i).getJoueur1().getId().equals(id)) {

                this.setScore(jeuList.get(i).getJoueur1(), result);
                find = true;
            }
            else if(jeuList.get(i).getJoueur2().getId().equals(id)) {

                this.setScore(jeuList.get(i).getJoueur2(), result);
                find = true;
            }

            i++;
        }

    }


    private void setScore (Joueur joueur, int result) {

        int score = joueur.getScore();

        score += result;

        joueur.setScore(score);
       // System.out.println("JeuModel : Joueur " + joueur.getId() + " : score = " + joueur.getScore());

    }


    /**
     * Méthode qui permet de récuperer le score de
     * l'adversaire
     *
     * @param id
     * @return
     */
    public int getScoreAdv(String id) {

        //TODO: faire méthode qui permet de renvoyer
        //TODO: le score de l'adversaire

        int i = 0;
        while (i < jeuList.size()) {

            if(jeuList.get(i).getJoueur1().getId().equals(id)) {

                System.out.println("Score Joueur 2 : " + jeuList.get(i).getJoueur2().getScore());

                return jeuList.get(i).getJoueur2().getScore();
            }
            else if(jeuList.get(i).getJoueur2().getId().equals(id)) {

                System.out.println("Score Joueur 1 : " + jeuList.get(i).getJoueur1().getScore());

                return jeuList.get(i).getJoueur1().getScore();
            }

        }

        return 0;
    }
}
