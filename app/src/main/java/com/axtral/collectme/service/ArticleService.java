package com.axtral.collectme.service;

import android.content.Context;

import com.axtral.collectme.entity.Article;

public interface ArticleService {

    void insertArticle(Article article);

    void updateArticle(Article article);

    void getArticle(String idArticle);

    void getArticles(String idUser, Context context, String nameActivity, String idCategorieItem);

    void deleteArticle(Article article);

}
