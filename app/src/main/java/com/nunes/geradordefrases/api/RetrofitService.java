package com.nunes.geradordefrases.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://moraislucas.github.io/MeMotive/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build();

}
