package org.android.server.controler;

import org.android.server.model.AmisModel;
import org.android.server.model.FriendRequestModel;
import org.android.server.model.JeuModel;
import org.android.server.model.QuestionModel;
import org.android.server.tool.JsonTools;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * Class contrôler qui permet d'effectuer des traitement en fonction de l'URL.
 * Il s'agit d'un Web Service.
 * Voici les etats pour que deux joueurs puissent jouer enssemble :
 *
 *  1 - Ce connecter au server : checkAttending()
 *  2 - Récupérer la liste d'amis : getFriends()
 *  3 - Envoyer une requête pour un amis : putRequestFriend()
 *  4 - L'ami verifie périodiquement qu'il n'a pas une requête pour lui sur le server
 *      à l'aide d'un Timer qui envoie des requêtes : checkRequest()
 *  5 - L'ami renvoie sa reponse sur le serveur : responceRequestFriend()
 *  6 - Si l'ami à dit 'oui' on lui envoie la question
 *  5'- Nous vérifions periodiquement que l'amis a repondu à la requête à
 *      l'aide d'un Timer en envoyant une requête: getResponseFriend()
 *  6'- Si la requête est ok on m'envoie la question
 *
 *  7 - Les reponses sont envoyer sur le server et renvoie le score de chaque joueur
 *  (Nous laisson au client la gestion de la reponse (bonne ou fausse)), puis une autre
 *  question en renvoyer aux deux joueurs.
 *
 *  8 - Au bout de 5 Questions (sauf en cas dégalité dans ce cas on renvoie une question
 *  jusqu'a trouver un gagnant) on déclare la fin du jeu
 *  (Nous laisson au client la gestion de l'affichage du gagnant).
 *  Nous supprimons la demande de requête.
 *
 */



@Controller
public class ServerControler {


    private final String serverKey = "123456";
    private AmisModel amisModel = new AmisModel();
    private QuestionModel questionModel = new QuestionModel();
    private FriendRequestModel friendRequestModel = new FriendRequestModel();
    private JeuModel jeuModel = new JeuModel();




    public ServerControler() {

        // Récupération des amis du fichier JSON
        try {
            amisModel.addAllFriendListe(JsonTools.getAmis());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Récupération des question du fichier JSON
        try {
            questionModel.addAllQuestionList(JsonTools.getQuestion());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    


    @RequestMapping(value = "/checkAttending", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkAttending(@RequestParam("key") String key,
    @RequestParam("id") String id) throws Exception{

        return amisModel.checkAttending(key, id, serverKey);
    }


    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String disconnect(@RequestParam("key") String key,
                                 @RequestParam("id") String id) throws Exception{

        return amisModel.disconnect(key, id, serverKey);
    }


    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getFriends(@RequestParam("key") String key) throws Exception{

        return amisModel.getFriends(key, serverKey);
    }


    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getQuestions(@RequestParam("key") String key) throws Exception{

        return questionModel.getQuestion(key, serverKey);
    }


    @RequestMapping(value = "/putRequestFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String putRequestFriend(@RequestParam("key") String key,
    @RequestParam("my_id") String my_id, @RequestParam("friend_id") String friend_id) throws Exception{

        return friendRequestModel.putRequestFriend(
                key, serverKey, my_id, friend_id, amisModel.getAmisList());
    }


    @RequestMapping(value = "/checkRequest", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkRequest(@RequestParam("key") String key,
                                   @RequestParam("my_id") String my_id) throws Exception{

        return friendRequestModel.checkRequest(key, serverKey, my_id, amisModel.getAmisList());
    }


    @RequestMapping(value = "/responceRequestFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String responceRequestFriend(@RequestParam("key") String key,
                                     @RequestParam("my_id") String my_id,
                                        @RequestParam("friend_id") String friend_id,
                                        @RequestParam("response") String response) throws Exception{

        return friendRequestModel.responceRequestFriend(key, serverKey, my_id, friend_id,
                amisModel.getAmisList(), response);
    }


    @RequestMapping(value = "/getResponseFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getResponseFriend(@RequestParam("key") String key,
                                     @RequestParam("my_id") String my_id) throws Exception{

        return friendRequestModel.getResponseFriend(key, serverKey, my_id, amisModel.getAmisList(),
                jeuModel.getJeuList());
    }



    /**
     * Méthode qui permet mettre à jour les score en fonction
     * de notre reponse (true ou false)
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/put_response", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String put_response(@RequestParam("key") String key,
                               @RequestParam("my_id") String my_id,
                               @RequestParam("reponse") boolean reponse) throws Exception   {


        System.out.println("Le joueur " + my_id + " a repondu " + reponse);
        this.jeuModel.updateScore(my_id, reponse);

        //TODO: traitement de la reponse

        return "";
    }


    /**
     * Méthode qui permet mettre à jour les score en fonction
     * de notre reponse (true ou false)
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get_score_advers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public int get_score_advers(@RequestParam("key") String key,
                               @RequestParam("my_id") String my_id) throws Exception   {


        return this.jeuModel.getScoreAdv(my_id);

    }

}
