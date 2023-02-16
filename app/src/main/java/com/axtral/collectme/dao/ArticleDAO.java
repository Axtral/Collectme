package com.axtral.collectme.dao;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.axtral.collectme.AjoutArticleActivity;
import com.axtral.collectme.MainActivity;
import com.axtral.collectme.entity.Article;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAO extends AbstractDAO{

    public List<Article> getArticles(String idUser, Context context, String nameActivity){
        List<Article> articles = new ArrayList<>();
        db.collection("Article").whereEqualTo("idUser", idUser)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d("testListArticles","Dans onComplete");
                        if(task.isSuccessful()){
                            Log.d("testListArticles","task is success");
                            List<DocumentSnapshot> result = task.getResult().getDocuments();
                            Log.d("testListArticles","result ok");
                            for (DocumentSnapshot document : result){
                                Log.d("testListArticles","for de result");
                                articles.add(new Article(document.getId(),
                                        document.getString("nom"),
                                        document.getString("description"),
                                        document.getString("idUser"),
                                        document.getString("idCategorie"),
                                        document.getTimestamp("dateCreation")));
                            }
                            Log.d("testListArticles","size articles : "+ articles.size());
                            switch (nameActivity){
                                case "MainActivity" :
                                    ((MainActivity) context).remplirRecyclerViewArticles(articles);
                                    break;
                                default:
                                    ((MainActivity) context).remplirRecyclerViewArticles(articles);
                                    break;
                            }
                        } else {
                            Log.w("testListArticles",
                                    "Error getting document."+task.getException());
                        }
                    }
                });

        return articles;
    }

    public void insertArticle(Article article){
        db.collection("Article")
                .add(article)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("insertArticle",
                                "DocumentSnapshot written with ID: "
                                        + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("insertArticle", "Error adding document", e);
                    }
                });
    }

    public Article getArticle(String idArticle){
        final Article[] article = {new Article()};
        DocumentReference docRef = db.collection("Article").document(idArticle);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                article[0] = documentSnapshot.toObject(Article.class);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("getArticle", "Error search document", e);
            }
        });
        return article[0];
    }

    public void deleteArticle(String idArticle){
        db.collection("Article").document(idArticle)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("deleteArticle", "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("deleteArticle", "Error deleting document", e);
                    }
                });
    }

    public Article updateArticle(Article article){
        DocumentReference articleRef = db.collection("Article").document(article.getId());
        articleRef
                .update(article.mapForFireBase())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("updateArticle", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("updateArticle", "Error updating document", e);
                    }
                });
        return article;
    }
}
