package org.android.server.web;

import com.google.gson.Gson;
import org.android.server.bean.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Class contrôler qui permet d'effectuer des traitement en fonction de l'URL.
 * Il s'agit d'un Web Service.
 * Voici les etats pour que deux joueurs puissent jouer enssemble :
 *
 *  1 - Ce connecter au server : checkAttending()
 *  2 - Récupérer la liste d'amis : getFriends()
 *  3 - Envoyer une requête pour un amis : putRequestFriend()
 *  4 - L'ami verifie périodiquement qu'il n'a pas une requête pour lui sur le server : checkRequest()
 *  5 - L'ami renvoie sa reponse sur le serveur : responceRequestFriend()
 *  6 - Si l'ami à dit 'oui' on lui envoie la question
 *  5'- Nous vérifions periodiquement que l'amis a repondu à la requête : getResponseFriend()
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
    private Amis amisControler = new Amis();
    private Question questionControler = new Question();
    private FriendRequest friendRequestControler = new FriendRequest();
    private Jeu jeuControler = new Jeu();




    public ServerControler() {


        amisControler.addFriendList(new Amis("1", "Ahmed", "Chaouche", "0","http://localhost/classroom_server/photos/photo-chaouche.jpg", "15"));
        amisControler.addFriendList(new Amis("2", "Jean-Michel", "Ilié", "0","", "0"));
        amisControler.addFriendList(new Amis("3", "Abdouradjack", "Allmmin", "0","", "0"));
        amisControler.addFriendList(new Amis("4", "Ali", "Asic", "0","", "0"));
        amisControler.addFriendList(new Amis("5", "Amzil", "Inamm", "0","", "0"));
        amisControler.addFriendList(new Amis("6", "Belarousse", "Driss", "0","", "0"));
        amisControler.addFriendList(new Amis("7", "Bourgis", "Jeremy", "0","", "0"));
        amisControler.addFriendList(new Amis("8", "Demmas", "Anas", "0","", "0"));
        amisControler.addFriendList(new Amis("9", "Dia", "Ndoumbe", "0","", "0"));
        amisControler.addFriendList(new Amis("10", "Desableau", "Quentin", "0","", "0"));
        amisControler.addFriendList(new Amis("11", "Dupeyrat", "Kevin", "0","", "0"));
        amisControler.addFriendList(new Amis("12", "Eddaoudi", "Atimad", "0","", "0"));
        amisControler.addFriendList(new Amis("13", "Ferreira", "Hugo", "0","", "0"));
        amisControler.addFriendList(new Amis("14", "Goual", "Amine", "0","", "0"));
        amisControler.addFriendList(new Amis("15", "Hamoudi", "Jonathan", "0","", "0"));
        amisControler.addFriendList(new Amis("16", "Helderal", "Mike", "0","", "0"));
        amisControler.addFriendList(new Amis("17", "Kathirgamanathan", "Gamaliny", "0","", "0"));
        amisControler.addFriendList(new Amis("18", "Kial", "Ramdane", "0","", "0"));
        amisControler.addFriendList(new Amis("19", "Kulanathan", "Vijay", "0","", "0"));
        amisControler.addFriendList(new Amis("20", "Laplace", "Junior", "0","", "0"));
        amisControler.addFriendList(new Amis("21", "Lim", "Patrick", "0","", "0"));
        amisControler.addFriendList(new Amis("22", "Picois", "Charlotte", "0","", "0"));
        amisControler.addFriendList(new Amis("23", "Rosaz", "Boris", "0","", "0"));
        amisControler.addFriendList(new Amis("24", "Salemi", "Mehdi", "0","", "0"));
        amisControler.addFriendList(new Amis("25", "Seymour", "Alex", "0","", "0"));
        amisControler.addFriendList(new Amis("26", "Tahri", "Abderrahman", "0","", "0"));
        amisControler.addFriendList(new Amis("27", "Touresse", "Clement", "0","", "0"));
        amisControler.addFriendList(new Amis("28", "Vincent", "Kevin", "0","", "0"));




        Reponse responseControler = new Reponse();
        responseControler.addReponseLiser(new Reponse("1", "Smeagole", "1"));
        responseControler.addReponseLiser(new Reponse("2", "Gandalf", "0"));
        responseControler.addReponseLiser(new Reponse("3", "Frondo", "0"));
        responseControler.addReponseLiser(new Reponse("4", "Bruce Wayne", "0"));

        Reponse responseControler2 = new Reponse();
        responseControler2.addReponseLiser(new Reponse("1", "Marta", "1"));
        responseControler2.addReponseLiser(new Reponse("2", "Penny", "0"));
        responseControler2.addReponseLiser(new Reponse("3", "Sophia", "0"));
        responseControler2.addReponseLiser(new Reponse("4", "Laura", "0"));

        Reponse responseControler3 = new Reponse();
        responseControler3.addReponseLiser(new Reponse("1", "Shrodinger", "1"));
        responseControler3.addReponseLiser(new Reponse("2", "Le chat potté", "0"));
        responseControler3.addReponseLiser(new Reponse("3", "Catwoman", "0"));
        responseControler3.addReponseLiser(new Reponse("4", "Grominet", "0"));

        questionControler.addQuestionList(new Question("1","Quel est le nom de Golumm", responseControler.getReponseList(), "10"));
        questionControler.addQuestionList(new Question("2","Quel est le prénom de la mère de Clark Kent ?", responseControler2.getReponseList(), "10"));
        questionControler.addQuestionList(new Question("3","Comment s'appelle le chat à la fois mort et vivant", responseControler3.getReponseList(), "10"));


    }


    


    @RequestMapping(value = "/checkAttending", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkAttending(@RequestParam("key") String key,
    @RequestParam("id") String id) throws Exception{

        return amisControler.checkAttending(key, id, serverKey);
    }


    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String disconnect(@RequestParam("key") String key,
                                 @RequestParam("id") String id) throws Exception{

        return amisControler.disconnect(key, id, serverKey);
    }


    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getFriends(@RequestParam("key") String key) throws Exception{

        return amisControler.getFriends(key, serverKey);
    }


    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getQuestions(@RequestParam("key") String key) throws Exception{

        return questionControler.getQuestion(key, serverKey);
    }


    @RequestMapping(value = "/putRequestFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String putRequestFriend(@RequestParam("key") String key,
    @RequestParam("my_id") String my_id, @RequestParam("friend_id") String friend_id) throws Exception{

        return friendRequestControler.putRequestFriend(
                key, serverKey, my_id, friend_id, amisControler.getAmisList());
    }


    @RequestMapping(value = "/checkRequest", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkRequest(@RequestParam("key") String key,
                                   @RequestParam("my_id") String my_id) throws Exception{

        return friendRequestControler.checkRequest(key, serverKey, my_id, amisControler.getAmisList());
    }


    @RequestMapping(value = "/responceRequestFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String responceRequestFriend(@RequestParam("key") String key,
                                     @RequestParam("my_id") String my_id,
                                        @RequestParam("friend_id") String friend_id,
                                        @RequestParam("response") String response) throws Exception{

        return friendRequestControler.responceRequestFriend(key, serverKey, my_id, friend_id,
                amisControler.getAmisList(), response);
    }


    @RequestMapping(value = "/getResponseFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getResponseFriend(@RequestParam("key") String key,
                                     @RequestParam("my_id") String my_id) throws Exception{

        return friendRequestControler.getResponseFriend(key, serverKey, my_id, amisControler.getAmisList(),
                jeuControler.getJeuList());
    }



    /**
     * Méthode qui permet de vérifier la reponse obtenu et
     * de renvoyer une autre question
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkResponseQuestion", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkResponseQuestion(@RequestParam("key") String key) throws Exception   {



        //TODO: traitement de la reponse

        return getQuestions(key);
    }


}
