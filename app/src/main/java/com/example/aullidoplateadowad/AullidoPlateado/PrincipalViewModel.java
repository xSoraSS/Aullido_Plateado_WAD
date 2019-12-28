package com.example.aullidoplateadowad.AullidoPlateado;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.aullidoplateadowad.AullidoPlateado.DB.Character;
import com.example.aullidoplateadowad.AullidoPlateado.DB.CharacterDao;
import com.example.aullidoplateadowad.AullidoPlateado.DB.CharacterDatabase;
import com.example.aullidoplateadowad.AullidoPlateado.DB.Item;
import com.example.aullidoplateadowad.AullidoPlateado.DB.ItemDao;
import com.example.aullidoplateadowad.AullidoPlateado.DB.ItemDatabase;

import java.util.ArrayList;
import java.util.List;

public class PrincipalViewModel extends AndroidViewModel {

    CharacterDao characterDao;
    ItemDao itemDao;
    MutableLiveData<Item> itemSeleccionado = new MutableLiveData<>();

    public PrincipalViewModel(@NonNull Application application) {
        super(application);

        characterDao = CharacterDatabase.getInstance(application).characterDao();
        itemDao = ItemDatabase.getInstance(application).itemDao();


        Character character = new Character("Sora", 0, 0, false);
        insertarCharacter(character);
    }

    public LiveData<List<Character>> getCharacter() {
        return characterDao.getCharacters();
    }

    public void insertarCharacter(final Character character){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                characterDao.insertarCharacter(character);
            }
        });
    }

    public void deleteCharacter(final int id) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                characterDao.deleteCharacter(id);
            }
        });
    }
//AAAAAAAAAAAAAAAAAAAAAAAAAA

    public LiveData<List<Item>> getItems() {
        return itemDao.getItems();
    }

    public void insertarItem(final Item item){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                itemDao.insertarItem(item);
            }
        });
    }


    public void deleteItem(final int id) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                itemDao.deleteItem(id);
            }
        });
    }

    public void establecerItemSeleccionado(Item item){
        itemSeleccionado.setValue(item);
    }

}
