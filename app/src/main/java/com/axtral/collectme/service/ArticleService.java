package com.axtral.collectme.service;

import android.content.Context;

import com.axtral.collectme.entity.Article;

import java.util.List;

public interface ArticleService {

    void insertArticle(Article article);

    Article updateArticle(Article article);

    Article getArticle(String idArticle);

    List<Article> getArticles(String idUser, Context context, String nameActivity);

    void deleteArticle(Article article);

}
