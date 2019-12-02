package com.example.aullidoplateadowad.AullidoPlateado.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Character.class}, views = {CharacterDetalle.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {

    private static CharacterDatabase INSTANCE;

    public abstract CharacterDao characterDao();

    public static CharacterDatabase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, CharacterDatabase.class, "tareas-db")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            insertarDatosIniciales(getInstance(context).characterDao());
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    static void insertarDatosIniciales(final CharacterDao characterDao){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                characterDao.insertarPersonaje(new Character("SORA", 1, 0001, false));
            }
        });
    }
}
