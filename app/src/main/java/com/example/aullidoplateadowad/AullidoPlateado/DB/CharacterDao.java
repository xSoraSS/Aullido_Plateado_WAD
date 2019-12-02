package com.example.aullidoplateadowad.AullidoPlateado.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public abstract class CharacterDao {

    @Insert
    public abstract void insertarPersonaje(Character character);

    @Query("DELETE FROM Character WHERE id=:id")
    public abstract void deleteCharacter(int id);

    @Query("SELECT * FROM CharacterDetalle")
    public abstract LiveData<List<CharacterDetalle>> getCharacterDetalle();

}