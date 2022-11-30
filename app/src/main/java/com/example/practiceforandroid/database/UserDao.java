package com.example.practiceforandroid.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.practiceforandroid.entidades.Movimientos;
import com.example.practiceforandroid.entidades.UbicacionEnt;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM movimientos")
    List<Movimientos> getAll();

    @Query("SELECT * FROM movimientos where id = :abc")
    Movimientos find(int abc); //1,2,3,4,5

    @Query("SELECT * FROM movimientos where id = :abc")
    List<Movimientos> findporid(int abc); //1,2,3,4,5

    @Insert
    void create(Movimientos movs);

    @Update
    void update(Movimientos movs);
}
