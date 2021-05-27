package com.albertlopez.cocinaparavagos.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertlopez.cocinaparavagos.R;
import com.albertlopez.cocinaparavagos.model.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorIngredientesBase  extends BaseAdapter {
    private final ArrayList<Ingredient> ingredients;
    private final Context context;

    public AdaptadorIngredientesBase(ArrayList<Ingredient> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ingredient ingrediente =(Ingredient)getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.items_ingredientesbase,null);
        ImageView imgFoto = convertView.findViewById(R.id.imageView3);
        TextView textView = convertView.findViewById(R.id.tvIngrediente);

        imgFoto = convertView.findViewById(R.id.imagenIngrediente);

        String image = ingrediente.getImagen();
        textView.setText(ingrediente.getClasificacionIngredientes());
        Picasso.with(context).load(image).fit().centerInside().into(imgFoto);

        return convertView;
    }
}
