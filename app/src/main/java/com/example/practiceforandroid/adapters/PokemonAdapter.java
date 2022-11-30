package com.example.practiceforandroid.adapters;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practiceforandroid.ListaMovimientos;
import com.example.practiceforandroid.PokeMapsActivity;
import com.example.practiceforandroid.PokeUbicacionActivity;
import com.example.practiceforandroid.R;
import com.example.practiceforandroid.RegistrarMovimiento;
import com.example.practiceforandroid.entidades.CuentaEnt;
import com.example.practiceforandroid.entidades.PokemonEnt;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter{
    List<CuentaEnt> datos;
    Button button,button2,button3;


    public PokemonAdapter(List<CuentaEnt> datos) {
        this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_pokemon,parent,false);
        return new PokemonAdapter.PokemonAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final PokemonAdapter.PokemonAdapterViewHolder viewHolder = (PokemonAdapterViewHolder) holder;
        TextView tvnombre = holder.itemView.findViewById(R.id.tvnombre);
        tvnombre.setText(datos.get(position).name);
        int aux=position;
/*
        TextView tvTipo = holder.itemView.findViewById(R.id.tvTipo);
        tvTipo.setText(datos.get(position).type);

        ImageView ivImg = holder.itemView.findViewById(R.id.ivPokeImg);
        Picasso.get().load(datos.get(position).imgURL).into(ivImg);
*/
        button = holder.itemView.findViewById(R.id.btnUbicacion);//btnRegistrarPokemon
        viewHolder.btnUbicacion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), RegistrarMovimiento.class);
                intent.putExtra("id",datos.get(aux).id);
                intent.putExtra("name",datos.get(aux).name);
                intent.putExtra("saldo",datos.get(aux).saldo);
                Log.i("MAIN_APP", "Antes de intent" );
                holder.itemView.getContext().startActivity(intent);
            }
        });
        button2=holder.itemView.findViewById(R.id.btnUbicacion2);
        viewHolder.btnUbicacion2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ListaMovimientos.class);
                //Nota enviar la cuenta con el intent
                intent.putExtra("id",datos.get(aux).id);
                intent.putExtra("name",datos.get(aux).name);
                intent.putExtra("saldo",datos.get(aux).saldo);
                Log.i("MAIN_APP", "Antes de intent" );
                holder.itemView.getContext().startActivity(intent);
            }
        });
        button3=holder.itemView.findViewById(R.id.btnUbicacion3);
        viewHolder.btnUbicacion3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    static class PokemonAdapterViewHolder extends RecyclerView.ViewHolder{

        Button btnUbicacion,btnUbicacion2,btnUbicacion3;

        public PokemonAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            btnUbicacion = (Button)itemView.findViewById(R.id.btnUbicacion);
            btnUbicacion2 = (Button)itemView.findViewById(R.id.btnUbicacion2);
            btnUbicacion3 = (Button)itemView.findViewById(R.id.btnUbicacion3);
        }
    }

}