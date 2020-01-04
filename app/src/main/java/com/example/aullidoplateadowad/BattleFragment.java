package com.example.aullidoplateadowad;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class BattleFragment extends Fragment {

    private int characterHP = 300, enemyHP = 250, enemyDP, characterDP;
    int attMin1 = 15, attMax1 = 25, attMin2 = 50, attMax2 = 60, damageAttk = 0;
    final Random random = new Random();
    TextView damageEnemyTextView, healthEnemyTextView, victoryTextView;
    Button attack1, attack2;
    boolean victory = false, enemyTurn = false;

    public BattleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_battle, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        healthEnemyTextView = view.findViewById(R.id.enemyHP);
        attack1 = view.findViewById(R.id.attack1);
        attack2 = view.findViewById(R.id.attack2);
        damageEnemyTextView = view.findViewById(R.id.damageEnemy);
        victoryTextView = view.findViewById(R.id.victory);

        attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damageAttk = (random.nextInt((attMax1 - attMin1) + 1) + attMin1);
                enemyHP -= damageAttk;
                enemyTurn = true;

                updateDamage();
                verifyEnemyHP(view);
            }
        });

        attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damageAttk = (random.nextInt((attMax2 - attMin2) + 1) + attMin2);
                enemyHP -= damageAttk;
                enemyTurn = true;

                updateDamage();
                verifyEnemyHP(view);
            }
        });

        return view;
    }

    private void verifyEnemyHP(final View view){
        if (enemyHP<=0) {
            victory = true;
            if (victory) {
                System.out.println("FINAL" + enemyHP);
                victoryTextView.setText("¡HAS DERROTADO AL ASESINO IMPERIAL!");
                damageEnemyTextView.setText(String.valueOf(0));
                healthEnemyTextView.setText(String.valueOf(0));
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            Navigation.findNavController(view).navigate(R.id.gameFragment);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 10);
            }
        }
    }
    private void updateDamage(){
        damageEnemyTextView.setText(String.valueOf(damageAttk));
        healthEnemyTextView.setText(String.valueOf(enemyHP));
        System.out.println(enemyHP);
    }

}
