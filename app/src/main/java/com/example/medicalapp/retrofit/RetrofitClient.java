package com.example.medicalapp.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String URL_API_SERVICE = "http://144.217.6.213:3014/api/";
    //URL VPS_LUXO
    //public static final String URL_API_SERVICE = "http://204.12.203.112:10046/api/";
    public static String API_TOKEN;

    private static Retrofit retrofit = null;

    private static class AuthInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json");
            //Aqui verificas si tienes un token y lo agregas al encabezado si es necesario
            //String token = obtenerToken();
            String token = API_TOKEN;
            if (token != null && !token.isEmpty()) {
                //Log.e("API TOKEN ->", token);
                requestBuilder.header("Authorization", "Bearer " + token);
            }

            Request request = requestBuilder.build();

            return chain.proceed(requestBuilder.build());
        }
    }

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(new AuthInterceptor());

    public static Retrofit API_SERVICE = new Retrofit.Builder()
            .baseUrl(URL_API_SERVICE)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ApiService createService() {
        return API_SERVICE.create(ApiService.class);
    }
}
