package com.example.practiceforandroid.entidades;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movimientos")
public class Movimientos {
    @PrimaryKey(autoGenerate = true)
    public int codigo;
    @ColumnInfo(name ="id")
    public int id;
    @ColumnInfo(name ="tipo")
    public String tipo;
    @ColumnInfo(name ="motivo")
    public String motivo;
    @ColumnInfo(name ="latitud")
    public String latitud;
    @ColumnInfo(name ="longitud")
    public String longitud;
    @ColumnInfo(name ="url")
    public String url;
}
