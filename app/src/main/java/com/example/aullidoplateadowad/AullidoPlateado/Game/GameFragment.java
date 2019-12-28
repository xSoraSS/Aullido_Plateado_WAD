package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aullidoplateadowad.AullidoPlateado.PrincipalViewModel;
import com.example.aullidoplateadowad.AullidoPlateado.DB.Character;
import com.example.aullidoplateadowad.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
//
//    PrincipalViewModel principalViewModel;
//    List<Character> charactersList;
//    TextView characterName;

    public GameFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        principalViewModel = ViewModelProviders.of(this).get(PrincipalViewModel .class);
        View view = inflater.inflate(R.layout.fragment_game, container,false);
//        characterName = view.findViewById(R.id.characterName);
//
//        principalViewModel.getCharacter().observe(this, new Observer<List<Character>>() {
//            @Override
//            public void onChanged(List<Character> queryResult) {
//                charactersList = queryResult;
//                characterName.setText(charactersList.get(0).toString());
//            }
//        });

        return view;
    }

}
