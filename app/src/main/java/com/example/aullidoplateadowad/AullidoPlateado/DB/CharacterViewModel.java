package com.example.aullidoplateadowad.AullidoPlateado.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    CharacterDao characterDao;

    public CharacterViewModel(@NonNull Application application) {
        super(application);

        characterDao = CharacterDatabase.getInstance(application).characterDao();
    }

    public LiveData<List<CharacterDetalle>> getCharacterDetalle(){
        return characterDao.getCharacterDetalle();
    }

    public void insertarCharacter(final Character character){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                characterDao.insertarPersonaje(character);
            }
        });
    }

    public void deleteCharacter(final int id){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                characterDao.deleteCharacter(id);
            }
        });
    }
}
