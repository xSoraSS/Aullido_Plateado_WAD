package com.example.aullidoplateadowad.AullidoPlateado.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Character.class}, views = {CharacterDetalle.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {

    private static CharacterDatabase INSTANCE;

    public abstract CharacterDao characterDao();

    public static CharacterDatabase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, CharacterDatabase.class, "tareas-db").build();
        }
        return INSTANCE;
    }
}
