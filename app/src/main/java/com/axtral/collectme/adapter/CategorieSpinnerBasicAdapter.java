package com.axtral.collectme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.axtral.collectme.R;
import com.axtral.collectme.entity.Categorie;

import java.util.List;

public class CategorieSpinnerBasicAdapter extends BaseAdapter {

    private Context context;
    private List<Categorie> categories;

    public CategorieSpinnerBasicAdapter(Context context, List<Categorie> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories != null ? categories.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.ligne_categorie, parent, false);
        TextView et_nom = rootView.findViewById(R.id.nom_categorie_list);
        TextView et_idCategorie = rootView.findViewById(R.id.id_categorie_list);
        TextView et_nom_label = rootView.findViewById(R.id.nom_categorie_label);
        Categorie categorie = categories.get(position);
        if (categorie != null){
            et_nom.setText(categorie.getNom());
            et_idCategorie.setText(categorie.getId());
            et_idCategorie.setVisibility(View.GONE);
            et_nom_label.setVisibility(View.GONE);
        }
        return rootView;
    }
}
