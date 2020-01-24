package com.example.aullidoplateadowad;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aullidoplateadowad.AullidoPlateado.PrincipalViewModel;
import com.example.aullidoplateadowad.AullidoPlateado.DB.Character;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    PrincipalViewModel principalViewModel;
    List<Character> charactersList;
    TextView characterName;
    ImageView characterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.gameFragment, R.id.configFragment,
                R.id.inventoryFragment, R.id.mapFragment)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        NavigationView infoMenuLateral = findViewById(R.id.nav_view);

        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null);
        infoMenuLateral.addHeaderView(header);

        //Mostrar personaje e imagen en el menu lateral
        characterName = header.findViewById(R.id.characterNameMenu);
        characterImage = header.findViewById(R.id.characterImageMenu);



        principalViewModel.getCharacter().observe(this, new Observer<List<Character>>() {
            @Override
            public void onChanged(List<Character> queryResult) {
                charactersList = queryResult;
                characterName.setText(charactersList.get(0).toString());
                characterImage.setImageDrawable(Drawable.createFromPath("character.jpg"));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void lanzaShowCaseView(View view){

        View ataquePrincipal    = findViewById(R.id.attack1);
        View ataqueSecundario   = findViewById(R.id.attack2);


        Animation animation = new AlphaAnimation(0.0f,1.0f);
        animation.setDuration(250);

        final FancyShowCaseView fancyShowCaseWelcome = new FancyShowCaseView.Builder(this)
                .title("Bienvenido al combate a continuación vamos a echar un vistazo.")
                .titleStyle(R.style.showcaseText, Gravity.CENTER)
                .enableAutoTextPosition()
                .backgroundColor(R.color.showCaseBackground)
                .build();


        final FancyShowCaseView fancyShowCaseAtaque = new FancyShowCaseView.Builder(this)
                .title("Con este botón de aquí podrás lanzar tu ataque principal.")
                .titleStyle(R.style.showcaseText, Gravity.CENTER)
                .enableAutoTextPosition()
                .focusCircleRadiusFactor(1.5)
                .backgroundColor(R.color.showCaseBackground)
                .focusOn(ataquePrincipal)
                .build();

        final FancyShowCaseView fancyShowCaseExit = new FancyShowCaseView.Builder(this)
                .title("Con este botón de aquí podrás lanzar tu ataque secundario el cual necesita 40 puntos de mana.")
                .titleStyle(R.style.showcaseText, Gravity.CENTER)
                .enableAutoTextPosition()
                .focusCircleRadiusFactor(1.5)
                .backgroundColor(R.color.showCaseBackground)
                .focusOn(ataqueSecundario)
                .build();

        FancyShowCaseQueue mQueue = new FancyShowCaseQueue()
                .add(fancyShowCaseWelcome)
                .add(fancyShowCaseAtaque)
                .add(fancyShowCaseExit);
        mQueue.show();

    }

}