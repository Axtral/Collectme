package com.axtral.collectme.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.R;

public class CategorieViewHolder extends RecyclerView.ViewHolder {

    private TextView et_nom_label, et_nom, et_idCategorie;

    public CategorieViewHolder(@NonNull View itemView) {
        super(itemView);
        //On récup les composants de la ligne
        et_nom_label = itemView.findViewById(R.id.nom_categorie_label);
        et_nom = itemView.findViewById(R.id.nom_categorie_list);
        et_idCategorie = itemView.findViewById(R.id.id_categorie_list);
    }

    public TextView getEt_nom_label() {
        return et_nom_label;
    }

    public void setEt_nom_label(TextView et_nom_label) {
        this.et_nom_label = et_nom_label;
    }

    public TextView getEt_nom() {
        return et_nom;
    }

    public void setEt_nom(TextView et_nom) {
        this.et_nom = et_nom;
    }

    public TextView getEt_idCategorie() {
        return et_idCategorie;
    }

    public void setEt_idCategorie(TextView et_idCategorie) {
        this.et_idCategorie = et_idCategorie;
    }
}
