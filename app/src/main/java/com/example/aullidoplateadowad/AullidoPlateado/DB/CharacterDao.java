package com.example.aullidoplateadowad.AullidoPlateado.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public abstract class CharacterDao {

    @Insert
    public abstract void insertarCharacter(Character character);

    @Query("DELETE FROM Character WHERE id=:id")
    public abstract void deleteCharacter(int id);

    @Query("SELECT * FROM Character")
    public abstract LiveData<List<Character>> getCharacters();

    @Query("SELECT * FROM Character")
    public abstract List<Character> getCharactersList();
}