package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.example.aullidoplateadowad.R;
import com.github.chrisbanes.photoview.PhotoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class mapFragment extends Fragment {

    PhotoView photoView;


    public mapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        photoView = view.findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.map);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        // Inflate the layout for this fragment
        return view;
    }

}
