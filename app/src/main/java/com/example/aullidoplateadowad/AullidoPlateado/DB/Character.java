package com.example.aullidoplateadowad.AullidoPlateado.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;

@Entity(tableName = "Personaje")
public abstract class Character extends RoomDatabase {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Nombre")
    private String characterName;
    @ColumnInfo(name = "Nivel de Personaje")
    private int characterLevel;
    @ColumnInfo(name = "Experiencia x/n")
    private int levelExperience;
    @ColumnInfo(name = "Herido")
    private boolean injured;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public int getLevelExperience() {
        return levelExperience;
    }

    public void setLevelExperience(int levelExperience) {
        this.levelExperience = levelExperience;
    }

    public boolean isInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }
}
