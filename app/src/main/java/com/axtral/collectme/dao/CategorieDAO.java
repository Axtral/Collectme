package com.axtral.collectme.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.axtral.collectme.AjoutArticleActivity;
import com.axtral.collectme.MainActivity;
import com.axtral.collectme.entity.Categorie;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategorieDAO extends AbstractDAO{

    public void getListCategories(Context context){
        List<Categorie> categories = new ArrayList<>();
        db.collection("Categorie")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> result = task.getResult().getDocuments();
                            for (DocumentSnapshot document : result){
                                categories.add(new Categorie(document.getId(), document.getString("nom")));

                            }
                            ((MainActivity) context).getAllCategorie(categories);
                        } else {
                            Log.w("testListCategorie", "Error getting document."+task.getException());
                        }
                    }
                });
    }

    public void getListCategories(Context context, String nameActivity){
        List<Categorie> categories = new ArrayList<>();
        db.collection("Categorie")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> result = task.getResult().getDocuments();
                            for (DocumentSnapshot document : result){
                                categories.add(new Categorie(document.getId(), document.getString("nom")));

                            }
                            switch (nameActivity){
                                case "MainActivity" :
                                    ((MainActivity) context).getAllCategorie(categories);
                                    break;
                                case "AjoutArticleActivity":
                                    ((AjoutArticleActivity) context).getAllCategorie(categories);
                                    break;
                                default:
                                    break;
                            }

                        } else {
                            Log.w("testListCategorie", "Error getting document."+task.getException());
                        }
                    }
                });
    }

}
