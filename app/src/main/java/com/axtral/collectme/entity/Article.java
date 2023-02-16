package com.axtral.collectme.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class Article implements Parcelable {

    private String id;

    private String nom;
    private String description;
    private String idUser;
    private String idCategorie;
    private Timestamp dateCreation;

    public Article(String id, String nom, String description, String idUser, String idCategorie, Timestamp dateCreation) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.idUser = idUser;
        this.idCategorie = idCategorie;
        this.dateCreation = dateCreation;
    }

    public Article() {
        this.dateCreation = Timestamp.now();
    }

    protected Article(Parcel in) {
        id = in.readString();
        nom = in.readString();
        description = in.readString();
        idUser = in.readString();
        idCategorie = in.readString();
        dateCreation = in.readParcelable(Timestamp.class.getClassLoader());
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Map<String, Object> mapForFireBase(){
        Map<String, Object> map = new HashMap<>();
        map.put("nom", getNom());
        map.put("description", getDescription());
        map.put("idUser", getIdUser());
        map.put("idCategorie", getIdCategorie());
        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(String idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(idUser);
        dest.writeString(idCategorie);
        dest.writeParcelable(dateCreation, flags);
    }
}
