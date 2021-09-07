package com.example.crmsystem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiProduct {

    @GET("search.json?engine=walmart&query=electronics&api_key=593c2a056d9fe9ce24e78aae534141c2f1505db40b8978e95a82e7cf1e85255b")
    Call<ElectroicProduct> getResourse();


}
