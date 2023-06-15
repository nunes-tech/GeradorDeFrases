package com.nunes.geradordefrases.api;

import com.nunes.geradordefrases.model.Cep;
import com.nunes.geradordefrases.model.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRetrofitService {

    @GET("phrases.json")
    Call<List<Frase>> RecuperarFrases();

    @GET("{cep}/json/")
    Call<Cep> RecuperarCep(@Path("cep") String cep);

}
