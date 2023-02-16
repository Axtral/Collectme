package com.axtral.collectme.service.impl;

import android.content.Context;

import com.axtral.collectme.dao.ArticleDAO;
import com.axtral.collectme.dao.CategorieDAO;
import com.axtral.collectme.entity.Article;
import com.axtral.collectme.service.ArticleService;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO = new ArticleDAO();
    private CategorieDAO categorieDAO = new CategorieDAO();

    @Override
    public void insertArticle(Article article) {
        articleDAO.insertArticle(article);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleDAO.updateArticle(article);
    }

    @Override
    public Article getArticle(String idArticle) {
        return articleDAO.getArticle(idArticle);
    }

    @Override
    public List<Article> getArticles(String idUser, Context context, String nameActivity) {
        return articleDAO.getArticles(idUser, context, nameActivity);
    }

    @Override
    public void deleteArticle(Article article) {
        articleDAO.deleteArticle(article.getId());
    }
}
