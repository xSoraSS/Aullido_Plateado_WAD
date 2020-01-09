package com.example.aullidoplateadowad.AullidoPlateado.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Item.class}, version = 3, exportSchema = false)
public abstract class ItemDatabase extends RoomDatabase {

    private static ItemDatabase INSTANCE;

    public abstract ItemDao itemDao();

    public static ItemDatabase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, ItemDatabase.class, "items-db")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            insertarDatosIniciales(getInstance(context).itemDao());
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    static void insertarDatosIniciales(final ItemDao itemDao){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                itemDao.insertarItem(new Item("Silver Moon", "Katana|Espada", "Katana grabada en runas oscuras con la empuñadura en forma de una cabeza de lobo.", false, 1, "/home/dam2a/AndroidStudioProjects/Aullido_Plateado_WAD/app/src/main/res/drawable/sword.png"));
                itemDao.insertarItem(new Item("Czar", "Mascara|Accesorio", "Mascara con forma de la cabeza de un lobo negro y ojos rojos.", false, 1, "head.png"));
                itemDao.insertarItem(new Item("Abrigo Lobo Plateado", "Abrigo|Ropa", "Abrigo negro con el emblema del lobo plateado grabado en la espalda.", false,1, "body.png"));
                itemDao.insertarItem(new Item("Pantalones simples", "Pantalones|Armadura", "Pantalones simples y negrgos con bolsillos para portar diversos objetos.", false, 1, "legs.png"));
                itemDao.insertarItem(new Item("Poción de Curación", "Medicina|Pociones", "Frasco con un liquido rojo para disminuir el dolor y aumentar la regeneración vital.", true, 5, "poti.png"));
            }
        });
    }
}
