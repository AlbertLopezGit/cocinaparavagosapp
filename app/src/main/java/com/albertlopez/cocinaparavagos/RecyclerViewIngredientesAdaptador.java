package com.albertlopez.cocinaparavagos;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.albertlopez.cocinaparavagos.model.Ingredient;

import java.util.List;

public class RecyclerViewIngredientesAdaptador extends RecyclerView.Adapter<RecyclerViewIngredientesAdaptador.ViewHolder> {

    public List<Ingredient> ingredientesList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvIngrediente;
        private ImageView imagenIngrediente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIngrediente = (TextView)itemView.findViewById(R.id.tvIngrediente);
            imagenIngrediente = (ImageView)itemView.findViewById(R.id.imagenIngrediente);
        }
    }

    public RecyclerViewIngredientesAdaptador(List<Ingredient> ingredientesLista) {
        this.ingredientesList = ingredientesLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_ingredientes,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvIngrediente.setText(ingredientesList.get(position).getNombreIngrediente());
    }

    @Override
    public int getItemCount() {
        return ingredientesList.size();
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
