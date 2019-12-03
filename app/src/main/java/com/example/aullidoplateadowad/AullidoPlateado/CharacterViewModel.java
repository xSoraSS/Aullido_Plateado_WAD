package com.example.aullidoplateadowad.AullidoPlateado;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.aullidoplateadowad.AullidoPlateado.DB.Character;
import com.example.aullidoplateadowad.AullidoPlateado.DB.CharacterDao;
import com.example.aullidoplateadowad.AullidoPlateado.DB.CharacterDatabase;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {

    CharacterDao characterDao;

    public CharacterViewModel(@NonNull Application application) {
        super(application);

        characterDao = CharacterDatabase.getInstance(application).characterDao();
    }

    public LiveData<List<Character>> getCharacter(){
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

    public void deleteCharacter(final int id){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                characterDao.deleteCharacter(id);
            }
        });
    }

    public void mostrarBD(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Character> characters = characterDao.getCharactersList();
                for(Character character: characters){
                    Log.e("ABCD", character.toString());
                }

            }
        });
    }
}
