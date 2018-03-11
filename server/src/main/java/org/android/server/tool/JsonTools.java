package org.android.server.tool;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import org.android.server.bean.Amis;
import org.android.server.bean.Question;


/**
 * Created by dupeyrat on 26/02/18.
 *
 * Class Outil pour la gestion des fichier Json
 * Elle permetra de générer à partir de fichier Json
 * des Objets
 */

public class JsonTools {


    private static final String projectPath = "src/main/resources/static/";

    /**
     * Méthode qui permet de récupérer les question/reponses
     * d'un fichier JSON vers un objet Question
     * Utilisation de l'API GSON de Google
     * @return
     */
    public static List<Question> getQuestion() throws IOException {

        File jsonFile = new File(projectPath + "question.json");
        byte[] bytes = Files.readAllBytes(jsonFile.toPath());
        String jsonString = new String(bytes, "UTF-8");


        Gson gson = new Gson();
        Type type = new TypeToken<List<Question>>(){}.getType();
        List<Question> questionList = gson.fromJson(jsonString, type);

        return questionList;
    }


    /**
     * Méthode qui permet de convertir un fichier Json
     * passé en paramètre en une liste
     * de Question qui est renvoyé
     * @return
     */
    public static List<Amis> getAmis() throws IOException {

        File jsonFile = new File(projectPath + "friends.json");
        byte[] bytes = Files.readAllBytes(jsonFile.toPath());
        String jsonString = new String(bytes, "UTF-8");


        Gson gson = new Gson();
        Type type = new TypeToken<List<Amis>>(){}.getType();
        List<Amis> amisList = gson.fromJson(jsonString, type);


        return amisList;
    }

}
