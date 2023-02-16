package com.axtral.collectme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.adapter.ArticleRecyclerVeiwAdapter;
import com.axtral.collectme.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleDetailActivity extends AppCompatActivity {

    private RecyclerView rvForDetailArticle;
    private ArticleRecyclerVeiwAdapter articleRecyclerVeiwAdapter;
    private Article currentArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        init();
    }

    private void init(){
        rvForDetailArticle = (RecyclerView) findViewById(R.id.rv_for_detail_article);
        Intent intent = getIntent();
        currentArticle = intent.getParcelableExtra("articleSelected");
        List<Article> articleList = new ArrayList<>();
        articleList.add(currentArticle);
        remplirRecyclerViewArticles(articleList);

    }

    public void remplirRecyclerViewArticles(List<Article> articles){
        //Gestion du recyclerView
        //on renseigne la liste des categorie a notre adapter
        //Log.d("testListCategorie","nbs de categorie : "+ categories.size());
        Log.d("remplirRecyclerViewArticle", "log de size : "+ articles.size());
        rvForDetailArticle.setLayoutManager(new LinearLayoutManager(ArticleDetailActivity.this));
        articleRecyclerVeiwAdapter = new ArticleRecyclerVeiwAdapter(articles, this, "ArticleDetailActivity");
        rvForDetailArticle.setAdapter(articleRecyclerVeiwAdapter);

    }

    public void onCLickBack(View view){
        Intent intent = new Intent(ArticleDetailActivity.this, MainActivity.class);
        intent.putExtra("articleSelected", currentArticle);
        ArticleDetailActivity.this.startActivity(intent);
        finish();
    }

    public void onClickUpdateArticle(View view){
        Intent intent = new Intent(ArticleDetailActivity.this, AjoutArticleActivity.class);
        intent.putExtra("articleSelected", currentArticle);
        ArticleDetailActivity.this.startActivity(intent);
        finish();
    }
}