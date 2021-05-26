package com.albertlopez.cocinaparavagos.recipes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewRecipesAdaptador extends RecyclerView.Adapter<RecyclerViewRecipesAdaptador.ViewHolder> implements View.OnClickListener {
    private final Context context;
    private final ArrayList<Recipe> recipesArray;
    private View.OnClickListener listener;

    public RecyclerViewRecipesAdaptador(Context context, ArrayList<Recipe> recipesArray) {
        this.context = context;
        this.recipesArray = recipesArray;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView nombreReceta;
        private final ImageView imagenReceta;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreReceta = (TextView)itemView.findViewById(R.id.nombreReceta);
            imagenReceta = (ImageView)itemView.findViewById(R.id.imagenReceta);
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_recetas,parent,false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipesArray.get(position);

        String image = recipe.getImagenReceta();
        String nombre = recipe.getNombreReceta();


        holder.nombreReceta.setText(nombre);
        Picasso.with(context).load(image).fit().centerInside().into(holder.imagenReceta);
    }

    @Override
    public int getItemCount() {
        return recipesArray.size();
    }

    public void setOnClickListener (View.OnClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

}
