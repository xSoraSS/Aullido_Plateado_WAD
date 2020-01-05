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

    private int characterHP = 300, enemyHP = 250, enemyDP;
    int attMin1 = 15, attMax1 = 25, attMin2 = 50, attMax2 = 60, damageAttk, manaCharacter = 20, enemyMana = 20;
    final Random random = new Random();
    TextView damageEnemyTextView, healthEnemyTextView, victoryTextView, healthCharacterTextView, damageCharacterTextView, manaCharacterTextView;
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

        //HEALTH && MANA
        healthEnemyTextView = view.findViewById(R.id.enemyHP);
        healthCharacterTextView = view.findViewById(R.id.characterHP);
        manaCharacterTextView = view.findViewById(R.id.characterMana);
        healthEnemyTextView.setText("HP: " + enemyHP);
        healthCharacterTextView.setText("HP: " + characterHP);
        manaCharacterTextView.setText("MP: " + manaCharacter);

        //DAMAGE && ATTACK
        attack1 = view.findViewById(R.id.attack1);
        attack2 = view.findViewById(R.id.attack2);
        damageEnemyTextView = view.findViewById(R.id.damageEnemy);
        damageCharacterTextView = view.findViewById(R.id.damageCharacter);
        victoryTextView = view.findViewById(R.id.victory);

        //COLMILLO DE LOBO
        attack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                damageAttk = (random.nextInt((attMax1 - attMin1) + 1) + attMin1);
                enemyHP -= damageAttk;
                enemyTurn = true;

                update();
                enemyAttack(enemyTurn);
                verifyEnemyHP(view);
                manaCharacter += 10;
            }
        });

        //CORTE DIMENSIONAL QUE REQUIERE 40 DE MANA
        attack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (manaCharacter >= 40) {
                    damageAttk = (random.nextInt((attMax2 - attMin2) + 1) + attMin2);
                    enemyHP -= damageAttk;
                    enemyTurn = true;
                    manaCharacter = 20;

                    update();
                    enemyAttack(enemyTurn);
                    verifyEnemyHP(view);
                }else if (manaCharacter < 40){
                    victoryTextView.setText("MANA INSUFICIENTE (40 MP)");
                }
            }
        });

        return view;
    }

    //SE CALCULA EL ATAQUE DEL ENEMIGO MEDIANTE UN RANDOM Y SE COMPRUEBA SI ES MAYOR A 40 PARA ESPECIFICAR QUE ATAQUE HA REALIZADO
    private void enemyAttack(boolean enemyTurn){
        if (enemyTurn){
            if (enemyMana < 50){
                enemyDP = random.nextInt((40 - 10) + 1) + 10;
            }else if (enemyMana > 50) {
                enemyDP = random.nextInt((70 - 10) + 1) + 10;
            }

            if (enemyDP <= 40) {
                victoryTextView.setText("ASESINO IMPERIAL HA USADO CORTE");
            }else if (enemyDP > 40) {
                victoryTextView.setText("ASESINO IMPERIAL HA USADO CORTE OSCURO");
            }

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        if (enemyDP <= 40) {
                            System.out.println("EL ATAQUE ENEMIGO ES MENOR O IGUAL QUE 40: " + enemyDP);
                            enemyMana += 10;
                        }else if (enemyDP > 40){
                            System.out.println("EL ATAQUE ENEMIGO ES MAYOR QUE 40: " + enemyDP);
                            enemyMana = 20;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    characterHP -= enemyDP;
                    damageCharacterTextView.setText("DAÑO RECIBIDO: " + enemyDP);
                    update();
                }
            }, 10);
        }
    }

    //COMPRUEBA SI LA SALUD DEL ENEMIGO ES 0, ESPERA 2 SEGUNDOS PARA MOSTRAR EL MENSAJE DE VICTORIA Y VUELVE A LA HISTORIA.
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
    private void update(){
        damageEnemyTextView.setText(String.valueOf(damageAttk));
        healthEnemyTextView.setText("HP: " + enemyHP);
        healthCharacterTextView.setText("HP: " + characterHP);
        manaCharacterTextView.setText("MP: " + manaCharacter);
        victoryTextView.setText("");
    }

}
