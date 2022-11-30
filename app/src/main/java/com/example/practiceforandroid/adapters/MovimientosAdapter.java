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
import com.example.practiceforandroid.R;
import com.example.practiceforandroid.RegistrarMovimiento;
import com.example.practiceforandroid.entidades.CuentaEnt;
import com.example.practiceforandroid.entidades.Movimientos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovimientosAdapter extends RecyclerView.Adapter{
    List<Movimientos> datos;
    Button button,button2,button3;


    public MovimientosAdapter(List<Movimientos> datos) {
        this.datos=datos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_movimiento,parent,false);
        return new MovimientosAdapter.PokemonAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final MovimientosAdapter.PokemonAdapterViewHolder viewHolder = (MovimientosAdapter.PokemonAdapterViewHolder) holder;
        TextView tvnombre = holder.itemView.findViewById(R.id.textView);
        tvnombre.setText(datos.get(position).tipo);
        TextView saldo =holder.itemView.findViewById(R.id.textView2);

        tvnombre.setText("sadfa");
        TextView motivo = holder.itemView.findViewById(R.id.textView3);
        tvnombre.setText(datos.get(position).motivo);
        int aux = position;
/*
        TextView tvTipo = holder.itemView.findViewById(R.id.tvTipo);
        tvTipo.setText(datos.get(position).type);
*/
        ImageView ivImg = holder.itemView.findViewById(R.id.imageView);
        Picasso.get().load(datos.get(position).url).into(ivImg);

    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
    static class PokemonAdapterViewHolder extends RecyclerView.ViewHolder{

        Button btnUbicacion,btnUbicacion2,btnUbicacion3;

        public PokemonAdapterViewHolder(@NonNull View itemView) {
            super(itemView);/*
            btnUbicacion = (Button)itemView.findViewById(R.id.btnUbicacion);
            btnUbicacion2 = (Button)itemView.findViewById(R.id.btnUbicacion2);
            btnUbicacion3 = (Button)itemView.findViewById(R.id.btnUbicacion3);*/
        }
    }
}
