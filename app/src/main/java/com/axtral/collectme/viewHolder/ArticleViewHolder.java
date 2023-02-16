package com.axtral.collectme.viewHolder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    TextView nom, description, dateCreation, idCategorie, idUser, idArticle;
    LinearLayout layoutVLigneArticle;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        nom = itemView.findViewById(R.id.nom_detail_article);
        description = itemView.findViewById(R.id.description_detail_article);
        dateCreation = itemView.findViewById(R.id.date_detail_article);
        idCategorie = itemView.findViewById(R.id.id_categorie_article_ligne);
        idUser = itemView.findViewById(R.id.id_user_article_ligne);
        idArticle = itemView.findViewById(R.id.id_article_ligne);
        layoutVLigneArticle = itemView.findViewById(R.id.layout_v_ligne_article);
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

    public TextView getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(TextView idCategorie) {
        this.idCategorie = idCategorie;
    }

    public TextView getIdUser() {
        return idUser;
    }

    public void setIdUser(TextView idUser) {
        this.idUser = idUser;
    }

    public TextView getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(TextView idArticle) {
        this.idArticle = idArticle;
    }

    public LinearLayout getLayoutVLigneArticle() {
        return layoutVLigneArticle;
    }

    public void setLayoutVLigneArticle(LinearLayout layoutVLigneArticle) {
        this.layoutVLigneArticle = layoutVLigneArticle;
    }
}
