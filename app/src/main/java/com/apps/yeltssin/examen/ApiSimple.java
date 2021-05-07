package com.apps.yeltssin.examen;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiSimple {

    @GET("s/5u21281sca8gj94/getFile.json?dl=0/")
    Call<Response> getResponse();

    static ApiSimple Create(){
        String url="https://dl.dropboxusercontent.com/";
        Retrofit.Builder retrofit=new Retrofit.Builder();
        retrofit.baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        return retrofit.build().create(ApiSimple.class);

    }
}
