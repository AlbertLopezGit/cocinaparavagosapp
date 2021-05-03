package com.albertlopez.cocinaparavagos;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewIngredientesAdaptador extends RecyclerView.Adapter<RecyclerViewIngredientesAdaptador.ViewHolder> {
    private final Context context;
    private final ArrayList<Ingredient> ingredientesArray;

    public RecyclerViewIngredientesAdaptador(Context context,ArrayList<Ingredient> ingredientesArray) {
        this.context = context;
        this.ingredientesArray = ingredientesArray;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvIngrediente;
        private final ImageView imagenIngrediente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIngrediente = (TextView)itemView.findViewById(R.id.tvIngrediente);
            imagenIngrediente = (ImageView)itemView.findViewById(R.id.imagenIngrediente);
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_ingredientes,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientesArray.get(position);

        String image = ingredient.getImagen();
        String nombre = ingredient.getNombreIngrediente();

        holder.tvIngrediente.setText(nombre);
        Picasso.with(context).load(image).fit().centerInside().into(holder.imagenIngrediente);
    }

    @Override
    public int getItemCount() {
        return ingredientesArray.size();
    }





}
