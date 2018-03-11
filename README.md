# Android-Quiz-Game-Server

Server Web écrit en Java avec le Framwork
Spring boot.
Ce serveur est utilisé comme Web Service pour
faire fonctionner l'application Android de Jeu de Quiz
avec une connexion sur serveur avec un QR Code.

Il permet un Jeu Synchrone entre deux Joueurs

-----------------------------------------------------------------


 *  1 - Ce connecter au server
 *  2 - Récupérer la liste d'amis
 *  3 - Envoyer une requête pour un amis
 *  4 - L'ami verifie périodiquement qu'il n'a pas une requête pour lui sur le server
 *  5 - L'ami renvoie sa reponse sur le serveur
 *  6 - Si l'ami à dit 'oui' on lui envoie la question
 *  5'- Nous vérifions periodiquement que l'amis a repondu à la requête
 *  6'- Si la requête est ok on m'envoie la question
 *  7 - Les reponses sont envoyer sur le server sous la forme de true/false 
 *  8 - Le server ajoute 5 pts si la reponse est true.
 *  9 - Le client recupère le score à chaque fois qu'il répond à une question et
 *      donc en raffiche une autre. Cela évite le raffraichissement permanent du score
 *      ce qui ralentis l'application.
 * 10 - Au bout de 5 questions, la fin de partie est déclaré par le server, il appartient
 *      au client d'indiquer les resultats a son joueur.
 * 11 - Une fois la partie fini nous revenons sur la page principale pour
 *      permettre au joueur de refaire une partie avec un autre ami s'il le souhaire
