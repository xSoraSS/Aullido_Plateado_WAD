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
                itemDao.insertarItem(new Item("Silver Moon", "Katana|Espada", "Katana grabada en runas oscuras con la empuñadura en forma de una cabeza de lobo.", false, 1, "https://firebasestorage.googleapis.com/v0/b/aullido-plateado-wad.appspot.com/o/sword.png?alt=media&token=8f63a54e-ee0b-4e41-8f91-54d1e9dce5b7"));
                itemDao.insertarItem(new Item("Czar", "Mascara|Accesorio", "Mascara con forma de la cabeza de un lobo negro y ojos rojos.", false, 1, "https://firebasestorage.googleapis.com/v0/b/aullido-plateado-wad.appspot.com/o/head.png?alt=media&token=8099b8e5-adf8-4602-84b1-1ac6d1cd6904"));
                itemDao.insertarItem(new Item("Abrigo Lobo Plateado", "Abrigo|Ropa", "Abrigo negro con el emblema del lobo plateado grabado en la espalda.", false,1, "https://firebasestorage.googleapis.com/v0/b/aullido-plateado-wad.appspot.com/o/body.png?alt=media&token=380d4762-bdb9-4b21-8e23-19cb2c234947"));
                itemDao.insertarItem(new Item("Pantalones simples", "Pantalones|Armadura", "Pantalones simples y negrgos con bolsillos para portar diversos objetos.", false, 1, "https://firebasestorage.googleapis.com/v0/b/aullido-plateado-wad.appspot.com/o/legs.png?alt=media&token=1a4387d5-9a5b-4a40-87c8-69ce2ca8973d"));
                itemDao.insertarItem(new Item("Poción de Curación", "Medicina|Pociones", "Frasco con un liquido rojo para disminuir el dolor y aumentar la regeneración vital.", true, 5, "https://firebasestorage.googleapis.com/v0/b/aullido-plateado-wad.appspot.com/o/potion.png?alt=media&token=bcc58406-eea3-45ad-82a2-c57fb21c171e"));
            }
        });
    }
}
