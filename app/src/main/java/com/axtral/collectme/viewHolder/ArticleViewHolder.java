package com.axtral.collectme.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    TextView nom, description, dateCreation;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        nom = itemView.findViewById(R.id.nom_detail_article);
        description = itemView.findViewById(R.id.description_detail_article);
        dateCreation = itemView.findViewById(R.id.date_detail_article);
    }

    public TextView getNom() {
        return nom;
    }

    public void setNom(TextView nom) {
        this.nom = nom;
    }

    public TextView getDescription() {
        return description;
    }

    public void setDescription(TextView description) {
        this.description = description;
    }

    public TextView getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(TextView dateCreation) {
        this.dateCreation = dateCreation;
    }
}
