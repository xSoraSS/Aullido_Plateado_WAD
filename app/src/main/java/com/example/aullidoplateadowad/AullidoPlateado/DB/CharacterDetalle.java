package com.example.aullidoplateadowad.AullidoPlateado.DB;

import androidx.room.DatabaseView;

@DatabaseView("SELECT Character.characterName")
    public class CharacterDetalle {

    public int id;
    public String characterName;
    public int characterLevel;
    public int levelExperience;
    public boolean injured;
    }
