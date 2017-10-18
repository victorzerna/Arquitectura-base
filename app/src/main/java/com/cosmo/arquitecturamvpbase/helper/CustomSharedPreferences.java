package com.cosmo.arquitecturamvpbase.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.cosmo.arquitecturamvpbase.model.User;
import com.google.gson.Gson;

/**
 * Created by victorhugosernasuarez on 17/10/17.
 */

public class CustomSharedPreferences {

    private SharedPreferences sharedPreferences;

    public CustomSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES, Constants.MODE_PRIVATE);

    }

    public void saveObjectUser(String key, User user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        addValue(key,json);
    }

    private void addValue(String key, String json){
        sharedPreferences.edit().putString(key, json).commit();
    }

    public User getObjectUser(String key){
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key,"");
        User user = gson.fromJson(json, User.class);
        return user;
    }

    public void deleteObjetUser(String key){
        sharedPreferences.edit().remove(key);
    }
}
