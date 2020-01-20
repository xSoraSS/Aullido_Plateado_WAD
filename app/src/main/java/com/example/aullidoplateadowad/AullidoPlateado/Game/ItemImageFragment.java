package com.example.aullidoplateadowad.AullidoPlateado.Game;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aullidoplateadowad.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemImageFragment extends Fragment {
    public ItemImageFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_item_image, container,false);
    }
}
