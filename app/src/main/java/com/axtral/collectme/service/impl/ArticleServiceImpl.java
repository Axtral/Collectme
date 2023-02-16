package com.axtral.collectme.service.impl;

import android.content.Context;

import com.axtral.collectme.dao.ArticleDAO;
import com.axtral.collectme.dao.CategorieDAO;
import com.axtral.collectme.entity.Article;
import com.axtral.collectme.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

    private ArticleDAO articleDAO = new ArticleDAO();
    private CategorieDAO categorieDAO = new CategorieDAO();

    @Override
    public void insertArticle(Article article) {
        articleDAO.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDAO.updateArticle(article);
    }

    @Override
    public void getArticle(String idArticle) {
        articleDAO.getArticle(idArticle);
    }

    @Override
    public void getArticles(String idUser, Context context, String nameActivity, String idCategorieItem) {
        articleDAO.getArticles(idUser, context, nameActivity, idCategorieItem);
    }

    @Override
    public void deleteArticle(Article article) {
        articleDAO.deleteArticle(article.getId());
    }
}
