# Android-Quiz-Game-Server

Server Web écrit en Java avec le Framwork
Spring boot.
Ce serveur est utilisé comme Web Service pour
faire fonctionner l'application Android de Jeu de Quiz
avec une connexion sur serveur avec un QR Code.

Il permet un Jeu Synchrone entre deux Joueurs

-----------------------------------------------------------------


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
