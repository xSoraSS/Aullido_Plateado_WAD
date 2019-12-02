package com.example.aullidoplateadowad.AullidoPlateado.DB;

import androidx.room.DatabaseView;

@DatabaseView("SELECT character.id, character.nombre, character.nivel, character.experiencia, character.herido FROM Character")
    public class CharacterDetalle {

    public int id;
    public String nombre;
    public int nivel;
    public int experiencia;
    public boolean herido;
    }
