package com.apps.yeltssin.examen;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    Context _ctx;
    SharedPreferences preferences;

    public Preferences(Context _ctx, String nameShared) {
        this._ctx = _ctx;
        this.preferences = _ctx.getSharedPreferences(nameShared,Context.MODE_PRIVATE);
    }

    public void setString(String key, String value){
        preferences.edit().putString(key,value).apply();
    }

    public String  getString(String key,String defValue){
        return preferences.getString(key,defValue);
    }

    public void setInt(String key,int value){
        preferences.edit().putInt(key,value).apply();
    }

    public int getInt(String key,int defValue){
        return preferences.getInt(key,defValue);
    }
}
