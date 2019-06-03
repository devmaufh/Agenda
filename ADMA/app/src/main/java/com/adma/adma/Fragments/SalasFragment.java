package com.adma.adma.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.adma.adma.Adapters.SalaAdapter;
import com.adma.adma.Models.SalaModel;
import com.adma.adma.R;
import com.adma.adma.Utils.Utilerias;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalasFragment extends Fragment {
    private RecyclerView rvSalas;
    SalaAdapter adapter;
    List<SalaModel>salasList;
    private RequestQueue queue;



    public SalasFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_salas, container, false);
        bindUI(view);
        JsonRequest jsonRequest= new JsonObjectRequest(Request.Method.POST, Utilerias.url + "MostrarSala.php",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray cast=response.getJSONArray("room");
                    for (int i = 0; i < cast.length(); i++) {
                        JSONObject jsonObject= cast.getJSONObject(i);
                        SalaModel salaModel= new SalaModel();
                        salaModel.setNombre(jsonObject.getString("nom_sala"));
                        salaModel.setCapacidad(jsonObject.getInt("capacidad_sala")+"");
                        salasList.add(salaModel);
                    }
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    adapter= new SalaAdapter(salasList,R.layout.card_sala,getContext());
                    rvSalas.setLayoutManager(layoutManager);
                    rvSalas.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsonRequest);

        return view;
    }
    private void bindUI(View view){
        salasList= new ArrayList<SalaModel>();
        queue= Volley.newRequestQueue(getContext());
        rvSalas=(RecyclerView)view.findViewById(R.id.rvSalas);
    }
}
