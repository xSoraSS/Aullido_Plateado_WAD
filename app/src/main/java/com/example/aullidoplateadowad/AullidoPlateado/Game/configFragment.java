package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.aullidoplateadowad.R;

import org.w3c.dom.Text;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class configFragment extends Fragment {

    ToggleButton musicButton;
    TextView musicTextView;

    public configFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_config, container,false);
        final MediaPlayer musicMediaPlayer = MediaPlayer.create(getActivity(), R.raw.song);

        musicButton = view.findViewById(R.id.musicButton);
        musicTextView = view.findViewById(R.id.music);

        musicButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (musicButton.isChecked()){
                    musicMediaPlayer.setLooping(true);
                    if (musicMediaPlayer.isLooping()){
                        musicMediaPlayer.start();
                    }
                }else{
                    musicMediaPlayer.pause();
                    musicMediaPlayer.seekTo(0);
                }
            }
        });

        return view;
    }
}
