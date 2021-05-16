package com.albertlopez.cocinaparavagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewIngredientesAdaptador extends RecyclerView.Adapter<RecyclerViewIngredientesAdaptador.ViewHolder> implements View.OnClickListener{
    private final Context context;
    private final ArrayList<Ingredient> ingredientesArray;
    private View.OnClickListener listener;
    private int tipoDeReclicler = 0; //si es 0 no mostrara cantidades si es 1 si mostrara cantidades el 3 son los botones de eliminar receta


    public RecyclerViewIngredientesAdaptador(Context context, ArrayList<Ingredient> ingredientesArray, int tipo) {
        this.context = context;
        this.ingredientesArray = ingredientesArray;
        this.tipoDeReclicler = tipo;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvIngrediente,cantidades;
        private final ImageView imagenIngrediente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngrediente = (TextView)itemView.findViewById(R.id.tvIngrediente);
            cantidades = (TextView)itemView.findViewById(R.id.canitdades);
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
        if (this.tipoDeReclicler == 1) {
            v = LayoutInflater.from(context).inflate(R.layout.items_ingredientesdetails,parent,false);
        } else if (this.tipoDeReclicler == 3) {
            v = LayoutInflater.from(context).inflate(R.layout.bottons_ingredientes,parent,false);
        }

        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ingredient ingredient = ingredientesArray.get(position);

        String image = ingredient.getImagen();
        String nombre = ingredient.getNombreIngrediente();

        if (this.tipoDeReclicler == 1) {
            int cantidades = ingredient.getCantidad();
            String textoCantidadaes = String.valueOf(cantidades) + " " + ingredient.getValorMedida();
            holder.cantidades.setVisibility(View.VISIBLE);
            holder.cantidades.setText(textoCantidadaes);
        }

        if (this.tipoDeReclicler == 1 || this.tipoDeReclicler == 0) {
            holder.tvIngrediente.setText(nombre);
            Picasso.with(context).load(image).fit().centerInside().into(holder.imagenIngrediente);
        }

    }

    @Override
    public int getItemCount() {
        return ingredientesArray.size();
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
