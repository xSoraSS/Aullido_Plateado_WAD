package com.example.aullidoplateadowad.AullidoPlateado.DB;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public abstract class ItemDao {

    @Insert
    public abstract void insertarItem(Item item);

    @Query("DELETE FROM Item WHERE id=:id")
    public abstract void deleteItem(int id);

    @Query("SELECT * FROM Item")
    public abstract LiveData<List<Item>> getItems();

    @Query("SELECT * FROM Item")
    public abstract List<Item> getItemsList();
}