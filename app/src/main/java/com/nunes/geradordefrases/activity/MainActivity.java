package com.nunes.geradordefrases.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nunes.geradordefrases.api.IRetrofitService;
import com.nunes.geradordefrases.api.RetrofitService;
import com.nunes.geradordefrases.databinding.ActivityMainBinding;
import com.nunes.geradordefrases.model.Cep;
import com.nunes.geradordefrases.model.Frase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private List<Frase> frasesRecuperadasWeb = null;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate( getLayoutInflater() );
        setContentView(binding.getRoot());

        pegarFrase();

        binding.btnGerarFrase.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (frasesRecuperadasWeb != null){
                            if (!frasesRecuperadasWeb.isEmpty()){

                                count = (count < frasesRecuperadasWeb.size()) ? count + 1 : 0;

                                binding.textFrase.setText( frasesRecuperadasWeb.get(count).getQuote() );
                                binding.textAutorFrase.setText( frasesRecuperadasWeb.get(count).getAuthor() );
                            }
                        }

                    }
                }
        );

    }

    private void pegarFrase(){

        try {

            Retrofit retrofit = new RetrofitService().retrofit;
            IRetrofitService retrofitService = retrofit.create( IRetrofitService.class );
            Call<List<Frase>> recuperarFrase = retrofitService.RecuperarFrases();

            recuperarFrase.enqueue(new Callback<List<Frase>>() {
                @Override
                public void onResponse(Call<List<Frase>> call, Response<List<Frase>> response) {

                    if (response.isSuccessful()){
                        Log.i("teste", "Sucesso ao obter dados");
                        frasesRecuperadasWeb = response.body();
                        binding.textFrase.setText( frasesRecuperadasWeb.get(0).getQuote() );
                        binding.textAutorFrase.setText( frasesRecuperadasWeb.get(0).getAuthor() );

                    }
                }

                @Override
                public void onFailure(Call<List<Frase>> call, Throwable t) {
                    Log.i("teste", "Erro ao executar a chamada Call Call<List<Frase>>");
                    binding.textFrase.setText( "Falha ao tentar recuperar frases na web" );

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            Log.i("teste", "Erro ao recuperar cep: ");
        }

    }

    /*private void pegarCep(){

        try {

            Retrofit retrofit = new RetrofitService().retrofit;
            IRetrofitService retrofitService = retrofit.create( IRetrofitService.class );
            Call<Cep> recuperarCep = retrofitService.RecuperarCep("64100000");

            recuperarCep.enqueue(new Callback<Cep>() {
                @Override
                public void onResponse(Call<Cep> call, Response<Cep> response) {
                    if (response.isSuccessful()){
                        Cep cep = response.body();
                        binding.textFrase.setText( cep.getLocalidade() );
                        Log.i("teste", cep.getLocalidade());
                    }
                }

                @Override
                public void onFailure(Call<Cep> call, Throwable t) {
                    Log.i("teste", "Erro ao pegar cep");
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            Log.i("teste", "Erro ao recuperar cep: ");
        }

    }*/


}