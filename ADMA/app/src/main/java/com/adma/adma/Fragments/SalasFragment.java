package com.adma.adma.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adma.adma.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalasFragment extends Fragment {
    private RecyclerView rvSalas;


    public SalasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_salas, container, false);
    }

}
