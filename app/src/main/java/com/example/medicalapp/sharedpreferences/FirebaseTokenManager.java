package com.example.medicalapp.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class FirebaseTokenManager {

    private static final String PREF_NAME = "SHARED_PREFERENCES_HALCON_ROJO_EESS";
    private static final String KEY_FIREBASE_TOKEN = "firebase_token_cliente";

    // Método para asignar el token
    public static void setFirebaseToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FIREBASE_TOKEN, token);
        editor.apply();
    }

    // Método para leer el token
    public static String getFirebaseToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIREBASE_TOKEN, null);
    }



}