package com.adma.adma.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adma.adma.Models.SalaModel;
import com.adma.adma.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SalaAdapter extends RecyclerView.Adapter<SalaAdapter.ViewHolder> {
    List<SalaModel> ListSalaModels;
    private int layout;
    Context context;

    public SalaAdapter(List<SalaModel> listSalaModels, int layout, Context context) {
        ListSalaModels = listSalaModels;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        context=parent.getContext();
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(ListSalaModels.get(position));

    }

    @Override
    public int getItemCount() {
        return ListSalaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvNombre,tvCapacidad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre=(TextView)itemView.findViewById(R.id.sala_nombre);
            tvCapacidad=(TextView)itemView.findViewById(R.id.sala_capacidad);
        }
        public void bind(final SalaModel sala){
            tvNombre.setText(sala.getNombre());
            tvCapacidad.setText(sala.getCapacidad()+" personas");
        }
    }
}
