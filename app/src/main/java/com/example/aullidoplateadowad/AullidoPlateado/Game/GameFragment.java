package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aullidoplateadowad.AullidoPlateado.PrincipalViewModel;
import com.example.aullidoplateadowad.AullidoPlateado.DB.Character;
import com.example.aullidoplateadowad.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    Button startBattle;

    public GameFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_game, container,false);
        Toast.makeText(getActivity(), "Recuerda que puedes activar la música en la configuración!!", Toast.LENGTH_LONG).show();

        startBattle = view.findViewById(R.id.combatir);

        startBattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.battleFragment);
            }
        });

        return view;
    }

}
