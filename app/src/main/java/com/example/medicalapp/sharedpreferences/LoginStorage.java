package com.example.medicalapp.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class LoginStorage {

    // Almacena las credenciales en SharedPreferences
    public static void saveCredentials(Context context, String user, String password, String type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("password", password);
        editor.putString("type", type);
        editor.apply();
    }

    public static void clearCredentials(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public static String[] getCredentials(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_credentials", context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", null);
        String password = sharedPreferences.getString("password", null);
        String type = sharedPreferences.getString("type", null);
        return new String[] {user, password, type};
    }

    public static boolean autoLogin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_credentials", context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user", null);
        String password = sharedPreferences.getString("password", null);
        if (user != null && password != null) {
            return true;
        }

        return false;
    }


}
