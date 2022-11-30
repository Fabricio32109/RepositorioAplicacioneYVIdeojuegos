package com.example.practiceforandroid.servicios;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practiceforandroid.entidades.UbicacionEnt;

import java.util.List;

public interface PokeDao {

    @Query("SELECT * FROM Ubicaciones")
    List<UbicacionEnt> getAll();

    @Query("SELECT * FROM ubicaciones where id = :abc")
    UbicacionEnt find(int abc); //1,2,3,4,5

    @Insert
    void create(UbicacionEnt ubicacionEnt);

    @Update
    void update(UbicacionEnt ubicacionEnt);
}
