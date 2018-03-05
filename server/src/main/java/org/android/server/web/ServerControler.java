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
    private List<Amis> amisList;
    private List<Question> questionList;
    private List<FriendRequest> friendRequest;
    private List<Jeu> jeuList;

    private static int questionIndice = 0;
    private static int questionEvoyer = 0;

    private static int nbrQuestion = 1;


    public ServerControler() {


        // Liste d'amis
        amisList = new ArrayList<>();
        amisList.add(new Amis("1", "Ahmed", "Chaouche", "0","http://localhost/classroom_server/photos/photo-chaouche.jpg", "15"));
        amisList.add(new Amis("2", "Jean-Michel", "Ilié", "0","", "0"));
        amisList.add(new Amis("3", "Abdouradjack", "Allmmin", "0","", "0"));
        amisList.add(new Amis("4", "Ali", "Asic", "0","", "0"));
        amisList.add(new Amis("5", "Amzil", "Inamm", "0","", "0"));
        amisList.add(new Amis("6", "Belarousse", "Driss", "0","", "0"));
        amisList.add(new Amis("7", "Bourgis", "Jeremy", "0","", "0"));
        amisList.add(new Amis("8", "Demmas", "Anas", "0","", "0"));
        amisList.add(new Amis("9", "Dia", "Ndoumbe", "0","", "0"));
        amisList.add(new Amis("10", "Desableau", "Quentin", "0","", "0"));
        amisList.add(new Amis("11", "Dupeyrat", "Kevin", "0","", "0"));
        amisList.add(new Amis("12", "Eddaoudi", "Atimad", "0","", "0"));
        amisList.add(new Amis("13", "Ferreira", "Hugo", "0","", "0"));
        amisList.add(new Amis("14", "Goual", "Amine", "0","", "0"));
        amisList.add(new Amis("15", "Hamoudi", "Jonathan", "0","", "0"));
        amisList.add(new Amis("16", "Helderal", "Mike", "0","", "0"));
        amisList.add(new Amis("17", "Kathirgamanathan", "Gamaliny", "0","", "0"));
        amisList.add(new Amis("18", "Kial", "Ramdane", "0","", "0"));
        amisList.add(new Amis("19", "Kulanathan", "Vijay", "0","", "0"));
        amisList.add(new Amis("20", "Laplace", "Junior", "0","", "0"));
        amisList.add(new Amis("21", "Lim", "Patrick", "0","", "0"));
        amisList.add(new Amis("22", "Picois", "Charlotte", "0","", "0"));
        amisList.add(new Amis("23", "Rosaz", "Boris", "0","", "0"));
        amisList.add(new Amis("24", "Salemi", "Mehdi", "0","", "0"));
        amisList.add(new Amis("25", "Seymour", "Alex", "0","", "0"));
        amisList.add(new Amis("26", "Tahri", "Abderrahman", "0","", "0"));
        amisList.add(new Amis("27", "Touresse", "Clement", "0","", "0"));
        amisList.add(new Amis("28", "Vincent", "Kevin", "0","", "0"));



        // Liste de question
        questionList = new ArrayList<>();
        List<Reponse> reponseList = new ArrayList<>();
        reponseList.add(new Reponse("1", "Smeagole", "1"));
        reponseList.add(new Reponse("2", "Gandalf", "0"));
        reponseList.add(new Reponse("3", "Frondo", "0"));
        reponseList.add(new Reponse("4", "Bruce Wayne", "0"));
        questionList.add(new Question("1","Quel est le nom de Golumm", reponseList, "10"));




        // Liste de requete d'amis
        friendRequest = new ArrayList<>();

    }


    

    /**
     * Méthode qui permet de ce connecter au server
     * et donc de ce rendre disponnible pour jouer
     *
     * @param key
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkAttending", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkAttending(@RequestParam("key") String key,
    @RequestParam("id") String id) throws Exception{

        Boolean idOk = false;


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(id)) {
                idOk = true;
                amis.setIs_present("1");
            }
        }

        if(!idOk)
            return "{'error' : 'You cannot be checked !!!'}";



        return "";
    }






    /**
     * Méthode qui permet de nous deconnecter du server
     * et nous rendre donc indisponnible pour jouer
     *
     * @param key
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String disconnect(@RequestParam("key") String key,
                                 @RequestParam("id") String id) throws Exception{

        Boolean idOk = false;


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(id)) {
                idOk = true;
                amis.setIs_present("0");
            }
        }

        if(!idOk)
            return "{'error' : 'You cannot be checked !!!'}";



        for(Amis amis: amisList)
            System.out.println(amis.getFirst_name() + "  " + amis.getIs_present());

        return "";
    }





    /**
     * Méthode qui nous permet de reçevoir la liste d'amis
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getFriends(@RequestParam("key") String key) throws Exception{


        Gson gson = new Gson();

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


            return gson.toJson(amisList);
    }





    /**
     * Méthode qui permet de reçevoir une question
     *
     * @param key
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getQuestions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getQuestions(@RequestParam("key") String key) throws Exception{


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


        return gson.toJson(questionList.get(indice));
    }






    /**
     * Méthode qui permet d'envoyer sur le server
     * une requête pour un amis
     *
     * @param key
     * @param my_id
     * @param friend_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/putRequestFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String putRequestFriend(@RequestParam("key") String key,
    @RequestParam("my_id") String my_id, @RequestParam("friend_id") String friend_id) throws Exception{

        Boolean my_idOk = false;
        Boolean friend_idOk = false;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id))
                my_idOk = true;
            if(amis.getId().equals(friend_id))
                friend_idOk = true;
        }


        if(!my_idOk && !friend_idOk)
            return "{'error' : 'You cannot be checked !!!'}";



        System.out.println("Je push une requête ...");

        friendRequest.add(new FriendRequest(my_id, friend_id, null));


        return null;
    }





    /**
     * Méthode qui permet de vérifier si nous avons une requête
     * qui nous attend sur le server
     *
     * @param key
     * @param my_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkRequest", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String checkRequest(@RequestParam("key") String key,
                                   @RequestParam("my_id") String my_id) throws Exception{

        Boolean idOk = false;


        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id)) {
                idOk = true;
            }
        }

        if(!idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        // S'il y a une requête on envoie le nom et le prénom de
        // celui qui a fait la demande
        for (FriendRequest request: friendRequest)
            if(request.getMyFriend().equals(my_id)){
                for(Amis amis: amisList){
                    if(amis.getId().equals(request.getDemandeur()))
                        return amis.getFirst_name() + " " + amis.getLast_name();
                }
            }




        return "please wait";
    }





    /**
     * Méthode qui permet de renvoyer notre reponse
     * au server
     *
     * @param key
     * @param my_id
     * @param friend_id
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/responceRequestFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String responceRequestFriend(@RequestParam("key") String key,
                                     @RequestParam("my_id") String my_id,
                                        @RequestParam("friend_id") String friend_id,
                                        @RequestParam("response") String response) throws Exception{



        Boolean my_idOk = false;
        Boolean friend_idOk = false;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList) {
            // Si l'id est dans la liste
            if (amis.getId().equals(my_id))
                my_idOk = true;
            if(amis.getId().equals(friend_id))
                friend_idOk = true;
        }


        if(!my_idOk && !friend_idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        System.out.println("J'attend que mon pote reponde");

        for (FriendRequest request: friendRequest)
            if(request.getMyFriend().equals(my_id) && request.getDemandeur().equals(friend_id)) {
                request.setDemandeur(response);
                return "{'responseRequeste' : 'your response as been post'}";
            }


        return null;
    }





    /**
     * Méthode qui permet de vérifier si notre amis
     * a repondu à notre requête
     *
     * @param key
     * @param my_id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getResponseFriend", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getResponseFriend(@RequestParam("key") String key,
                                     @RequestParam("my_id") String my_id) throws Exception{

        Boolean my_idOk = false;

        // Vérification du code
        if(!key.equals(serverKey))
            return "{'error' : 'Server key not defined yet !!!'}";


        // Parcour de la liste d'amis
        for(Amis amis: amisList)
            if (amis.getId().equals(my_id))
                my_idOk = true;



        if(!my_idOk)
            return "{'error' : 'You cannot be checked !!!'}";


        int i = 0;
        int indice = 0;
        for(FriendRequest fr: this.friendRequest) {

            System.out.println("Indice : " + i + "\nDemandeur : " + fr.getDemandeur() + "\nFriend : " + fr.getMyFriend());

            if(fr.getDemandeur().equals(my_id)){
                indice = i;
            } else {
                i++;
            }
        }

        System.out.println("J'attend la réponse du copain");

        if(this.friendRequest.get(i).getResponse() != null) {
            if(this.friendRequest.get(i).getResponse().equals("no")){
                this.jeuList.add(new Jeu(my_id, this.friendRequest.get(i).getMyFriend(),
                        0, 0));
                this.friendRequest.remove(indice);
                return getQuestions(my_id);
            }
        }

        return "no";
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
