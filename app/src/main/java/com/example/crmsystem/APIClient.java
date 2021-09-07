package com.example.crmsystem;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class APIClient {

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://serpapi.com/";
//&query=electronics&api_key=593c2a056d9fe9ce24e78aae534141c2f1505db40b8978e95a82e7cf1e85255b
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
