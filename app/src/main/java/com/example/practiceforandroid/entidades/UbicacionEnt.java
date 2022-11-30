package com.example.practiceforandroid.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "ubicaciones")
public class UbicacionEnt {
    @ColumnInfo(name ="id")
    public int id;
    @ColumnInfo(name ="latitud")
    public String latitud;
    @ColumnInfo(name ="longitud")
    public String longitud;
}
