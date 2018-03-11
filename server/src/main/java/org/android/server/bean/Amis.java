package org.android.server.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dupeyrat on 18/02/18.
 *
 * Class qui represente un amis
 * avec son nom et son avatar
 */

public class Amis {

    @SerializedName("id")
    private String id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("is_present")
    private String is_present;
    @SerializedName("photo_path")
    private String photo_path;
    @SerializedName("last_score")
    private String last_score;


    public Amis() {}


    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }


    public String getIs_present() {
        return is_present;
    }

    public void setIs_present(String is_present) {
        this.is_present = is_present;
    }


}
