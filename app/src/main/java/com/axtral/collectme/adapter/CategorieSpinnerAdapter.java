package com.axtral.collectme.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.axtral.collectme.R;
import com.axtral.collectme.entity.Categorie;

import java.util.ArrayList;
import java.util.List;

public class CategorieSpinnerAdapter extends ArrayAdapter<Categorie> {

    public CategorieSpinnerAdapter(Context context, ArrayList<Categorie> categorieList){
        super(context, 0, categorieList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    public View initView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_categorie, parent, false);
        }
        TextView et_nom = convertView.findViewById(R.id.nom_categorie_list);
        TextView et_idCategorie = convertView.findViewById(R.id.id_categorie_list);
        TextView et_nom_label = convertView.findViewById(R.id.nom_categorie_label);
        Categorie categorie = getItem(position);
        if (categorie != null){
            et_nom.setText(categorie.getNom());
            /*et_nom.setTextSize(20f);
            et_nom.setTextColor(Color.BLACK);*/
            //Log.d("TestForSpinnerCustom", "spinner custom ok");
            et_idCategorie.setText(categorie.getId());
            et_idCategorie.setVisibility(View.INVISIBLE);
            et_nom_label.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }
}
