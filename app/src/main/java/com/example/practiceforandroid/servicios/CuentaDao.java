package com.example.practiceforandroid.servicios;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practiceforandroid.entidades.Movimientos;
import com.example.practiceforandroid.entidades.UbicacionEnt;

import java.util.List;

public interface CuentaDao {

    @Query("SELECT * FROM movimientos")
    List<Movimientos>getAll();

    @Query("SELECT * FROM movimientos where id = :abc")
    List<Movimientos> find(int abc); //1,2,3,4,5

    @Insert
    void create(Movimientos movimiento);

    @Update
    void update(Movimientos movimiento);
}
