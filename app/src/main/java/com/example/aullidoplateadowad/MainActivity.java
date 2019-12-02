package com.example.aullidoplateadowad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aullidoplateadowad.AullidoPlateado.DB.CharacterDetalle;
import com.example.aullidoplateadowad.AullidoPlateado.DB.CharacterViewModel;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    CharacterViewModel characterViewModel;
    CharactersAdapter charactersAdapter;
    List<CharacterDetalle> characterDetalleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.gameFragment, R.id.configFragment,
                R.id.inventoryFragment, R.id.mapFragment, R.id.statsFragment)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        characterViewModel.getCharacterDetalle().observe(this, new Observer<List<CharacterDetalle>>() {
            @Override
            public void onChanged(List<CharacterDetalle> queryResult) {
                characterDetalleList = queryResult;
                charactersAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //
    //*******************************************
    //*******                             *******
    //*******        BASE DE DATOS        *******
    //*******                             *******
    //*******************************************
    //
    //
    // ERROR DB CIERRA APP REVISAR CHARACTERVIEWGOLDER, ADAPTERS Y VIEWMODEL

    class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.characterName);
        }
    }

    class CharactersAdapter extends RecyclerView.Adapter<CharacterViewHolder>{
        @NonNull
        @Override
        public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_game, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
            final CharacterDetalle characterDetalle = characterDetalleList.get(position);

            holder.nombre.setText(characterDetalle.nombre);
        }

        @Override
        public int getItemCount() {
            return characterDetalleList != null ? characterDetalleList.size() : 0;
        }
    }
}