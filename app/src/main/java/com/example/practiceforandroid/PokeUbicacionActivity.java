package com.example.practiceforandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practiceforandroid.entidades.CuentaEnt;
import com.example.practiceforandroid.entidades.PokemonEnt;
import com.example.practiceforandroid.servicios.PokeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PokeUbicacionActivity extends AppCompatActivity {

    public EditText etLongitud, etLatitud;
    public ImageView ivPokemonImg;
    public Button button,buttonMap;
    List<PokemonEnt> datos;

    private RecyclerView rvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_ubicacion);

        etLongitud=findViewById(R.id.etLongitud);
        etLatitud=findViewById(R.id.etLatitud);

        //EL botton para abrir el mapa, funciona en el adapter

        button = findViewById(R.id.btnGuardarCoo);
        button.setOnClickListener(v -> {
            onClick(v);
        });
        buttonMap=findViewById(R.id.btnMapa);
        buttonMap.setOnClickListener(v -> {
            onClick2(v);
        });

        //Setear datos
    }
    private void onClick(View v) {
        enviar(v);

    }
    private void onClick2(View v) {
        Intent intent = new Intent(this, PokeMapsActivity.class);//PokeMapsActivity
        startActivity(intent);

    }

    public void enviar(View v) {
        String longitud = etLongitud.getText().toString();
        String latitud = etLatitud.getText().toString();
        /*CuentaEnt poke = new CuentaEnt();
        poke.longitud = longitud;
        poke.latitud = latitud;
        postRetrofit(poke);*/
    }

    public void postRetrofit(CuentaEnt contactosrg) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://63023872c6dda4f287b57f7c.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        PokeService service = retrofit.create(PokeService.class);
        service.create(contactosrg).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("MAIN_APP", "RESPONSE" + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }



}