package com.baray.schoolmanagement;

import android.content.SharedPreferences;

/**
 * Created by Akram on 3/1/2017.
 */
public final class Application extends android.app.Application {
    private static  String configStr;
    private static final String userStr = "userStr";
    private static final String passStr = "passwordStr";


    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        FontsOverride.setDefaultFont(this, "DEFAULT", "IRANSans.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "IRANSans.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "IRANSans.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "IRANSans.ttf");

        configStr = "ScoolBaray" + getString(R.string.school_name);
    }

    public static Application getApplication(){
        return app;
    }

    public void saveUsernamePassword(String username, String password){
        SharedPreferences setting = getSharedPreferences(configStr, 0);
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(userStr, username);
        editor.putString(passStr, password);
        editor.commit();
    }

    public String getUsername(){
        SharedPreferences setting = getSharedPreferences(configStr, 0);
        return setting.getString(userStr, "").toString();
    }

    public String getPassword(){
        SharedPreferences setting = getSharedPreferences(configStr, 0);
        return setting.getString(passStr, "").toString();
    }

    public void clearUsernamPassword(){
        saveUsernamePassword("", "");
    }
}
