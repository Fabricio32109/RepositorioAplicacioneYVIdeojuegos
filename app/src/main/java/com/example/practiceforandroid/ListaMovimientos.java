package com.example.practiceforandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practiceforandroid.adapters.MovimientosAdapter;
import com.example.practiceforandroid.adapters.PokemonAdapter;
import com.example.practiceforandroid.database.AppDatabase;
import com.example.practiceforandroid.entidades.CuentaEnt;
import com.example.practiceforandroid.entidades.Movimientos;
import com.example.practiceforandroid.servicios.PokeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListaMovimientos extends AppCompatActivity {


    private RecyclerView rvContact;
    private Button volver;
    private TextView name,saldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_movimientos);

        CuentaEnt cuentaux=new CuentaEnt();
        cuentaux.id=getIntent().getIntExtra("id",0);
        cuentaux.name=getIntent().getStringExtra("name");
        cuentaux.saldo=getIntent().getIntExtra("saldo",0);
        name=findViewById(R.id.tvnombre2);
        saldo=findViewById(R.id.tvnombre3);
        name.setText("Hola "+cuentaux.name);
        saldo.setText("Tienes "+cuentaux.saldo+" unidades monetarias");
        volver=findViewById(R.id.button);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListaMovimientos.this, RegistrarMovimiento.class);
                intent.putExtra("id",cuentaux.id);
                intent.putExtra("name",cuentaux.name);
                intent.putExtra("saldo",cuentaux.saldo);
                startActivity(intent);
            }
        });




        AppDatabase db = AppDatabase.getInstance(this);
        List<Movimientos> lista= db.userDao().findporid(cuentaux.id);
        rvContact=findViewById(R.id.recyclerView);

        rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        rvContact.setAdapter(new MovimientosAdapter(lista));


    }
}