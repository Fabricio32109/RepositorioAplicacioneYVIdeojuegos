package com.example.practiceforandroid.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.practiceforandroid.entidades.Movimientos;

@Database(entities = {Movimientos.class}, version = 5)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "vj20222_db")
                .allowMainThreadQueries()
                .build();
    }

}
