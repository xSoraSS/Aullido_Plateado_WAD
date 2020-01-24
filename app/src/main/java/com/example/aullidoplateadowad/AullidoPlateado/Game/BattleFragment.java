package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.example.aullidoplateadowad.R;

import java.util.Random;

import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BattleFragment extends Fragment {

    private int characterHP = 270, enemyHP = 250, enemyDP;
    int attMin1 = 15, attMax1 = 25, attMin2 = 50, attMax2 = 60, damageAttk, manaCharacter = 20, enemyMana = 20;
    final Random random = new Random();
    TextView damageEnemyTextView, healthEnemyTextView, victoryTextView, healthCharacterTextView, damageCharacterTextView, manaCharacterTextView;
    Button attack1, attack2;
    boolean victory = false, enemyTurn = false, defeat = false;

    public BattleFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        new FancyShowCaseView.Builder(getActivity())
//                .title("Colmillo de Lobo es tu ataque principal.")
//                .focusBorderColor(Color.BLUE)
//                .focusBorderSize(5)
//                .focusOn(attack1)
//                .build();
//
//        final FancyShowCaseView fancyShowCaseView1 = new FancyShowCaseView.Builder(getActivity())
//                .title("Corte Dimensional es tu ataque especial, el cual necesitará mana.")
//                .focusBorderColor(Color.BLUE)
//                .focusBorderSize(5)
//                .focusOn(attack2)
//                .build();
//
//        final FancyShowCaseView fancyShowCaseExit = new FancyShowCaseView.Builder(getActivity())
//                .focusOn(attack1)
//                .build();
//
//        final FancyShowCaseView fancyShowCaseExit2 = new FancyShowCaseView.Builder(getActivity())
//                .focusOn(attack2)
//                .build();
//
//
//        FancyShowCaseQueue mQueue = new FancyShowCaseQueue()
//                .add(fancyShowCaseWelcome)
//                .add(fancyShowCaseView1)
//                .add(fancyShowCaseExit)
//                .add(fancyShowCaseExit2);
//        mQueue.show();

        return  inflater.inflate(R.layout.fragment_battle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                attack1.setEnabled(false);

                update();
                if (characterHP > 0 && enemyHP > 0) {
                    enemyAttack(enemyTurn, view);
                }
                verifyHP(view);
                manaCharacter += 10;
            }
        });
    }

        //SE CALCULA EL ATAQUE DEL ENEMIGO MEDIANTE UN RANDOM Y SE COMPRUEBA SI ES MAYOR A 40 PARA ESPECIFICAR QUE ATAQUE HA REALIZADO
    private void enemyAttack(boolean enemyTurn, final View view){
        //SI EL JUGADOR FINALIZA SU TURNO EL ENEMIGO PODRÁ ATACAR
        if (enemyTurn){
            //SE COMPROBARÁ SI EL MANA DEL ENEMIGO ES SUPER O INFERIOR A 50 PARA DARLE LA POSIBILIDAD DE LANZAR SU ATAQUE MAS FUERTA
            if (enemyMana < 50){
                //ENEIMGO USA CORTE
                enemyDP = random.nextInt((40 - 10) + 1) + 10;
            }else if (enemyMana > 50) {
                //ENEMIGO USA CORTE OSCURO
                enemyDP = random.nextInt((70 - 10) + 1) + 10;
            }


            if (enemyDP <= 40) {
                victoryTextView.setText("ASESINO IMPERIAL HA USADO CORTE");
            }else if (enemyDP > 40) {
                victoryTextView.setText("ASESINO IMPERIAL HA USADO CORTE OSCURO");
            }

            //EL FUNCIONAMIENTO DEL ATAQUE ENEMIGO ES SIMILAR AL DEL JUGADOR, UNA VEZ LANCE SU ATAQUE SE ESPERARÁ DOS SEGUNDOS HASTA PERMITIR ATACAR AL JUGADOR
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        if (enemyDP <= 40) {
                            enemyMana += 10;
                        }else if (enemyDP > 40){
                            enemyMana = 20;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    characterHP -= enemyDP;
                    damageCharacterTextView.setText("DAÑO RECIBIDO: " + enemyDP);
                    update();
                    verifyHP(view);
                }
            }, 10);
        }

        enemyTurn = false;
        if (!enemyTurn){attack1.setEnabled(true);}
        }

    //COMPRUEBA SI LA SALUD DEL ENEMIGO O DEL JUGADOR ES 0, ESPERA 2 SEGUNDOS PARA MOSTRAR EL MENSAJE DE VICTORIA Y VUELVE A LA HISTORIA.
    private void verifyHP(final View view) {
        //GANAS EL COMBATE
        if (enemyHP <= 0) {
            victory = true;
            if (victory) {
                System.out.println("FINAL" + enemyHP);
                victoryTextView.setText("¡HAS DERROTADO AL ASESINO IMPERIAL!");
                damageEnemyTextView.setText(String.valueOf(0));
                healthEnemyTextView.setText("HP: 0");
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

            //PIERDES EL COMBATE
            if (characterHP <= 0) {
                defeat = true;
                if (defeat) {
                    System.out.println("FINAL" + enemyHP);
                    victoryTextView.setText("¡ASESINO IMPERIAL TE HA VENCIDO!");
                    healthCharacterTextView.setText("HP: 0");
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
    }


    private void update(){
        damageEnemyTextView.setText(String.valueOf(damageAttk));
        healthEnemyTextView.setText("HP: " + enemyHP);
        healthCharacterTextView.setText("HP: " + characterHP);
        manaCharacterTextView.setText("MP: " + manaCharacter);
        victoryTextView.setText("");
    }
}
