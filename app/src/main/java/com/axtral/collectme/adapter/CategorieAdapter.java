package com.axtral.collectme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.R;
import com.axtral.collectme.entity.Categorie;
import com.axtral.collectme.viewHolder.CategorieViewHolder;

import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieViewHolder> {

    private List<Categorie> categories;

    public CategorieAdapter(List<Categorie> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategorieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // On va crée une vue qui à besoin du contexr ici pour "gonfler" c'est à dire ajouter
        // un composant qui n'est pas encore defini dans notre vue. Pour cela il a besoin
        // de la vu que l'on souhaite ajouter, du parent et du recyclerView
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ligne_categorie, parent, false);
        CategorieViewHolder categorieViewHolder = new CategorieViewHolder(v);
        return categorieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategorieViewHolder holder, int position) {
        //On recup le produit de la ligne conserné(position) et on set les composants avec les bonne donnée
        //Log.d("testListCategorie","position de categorie : "+ position);
        Categorie categorie = categories.get(position);
        holder.getEt_nom().setText(categorie.getNom());
        holder.getEt_idCategorie().setText(categorie.getId());
        holder.getEt_idCategorie().setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        //Log.d("testListCategorieAdapter","nbs de categorie : "+ categories.size());
        return categories.size();
    }

}
