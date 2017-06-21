package com.example.lucia.notificacionfirebase.RestAPI.adapter;

import com.example.lucia.notificacionfirebase.RestAPI.ConstantesRestAPI;
import com.example.lucia.notificacionfirebase.RestAPI.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucia on 20/06/2017.
 */

public class RestApiAdapter {

    public Endpoints establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        return retrofit.create(Endpoints.class);

    }
}
