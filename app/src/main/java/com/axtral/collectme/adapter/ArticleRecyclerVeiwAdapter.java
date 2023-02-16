package com.axtral.collectme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.R;
import com.axtral.collectme.entity.Article;
import com.axtral.collectme.viewHolder.ArticleViewHolder;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ArticleRecyclerVeiwAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> articles;

    public ArticleRecyclerVeiwAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ligne_article, parent,false);
        ArticleViewHolder articleViewHolder = new ArticleViewHolder(v);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.getNom().setText(article.getNom());
        holder.getDescription().setText(article.getDescription());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);
        holder.getDateCreation().setText(format.format(article.getDateCreation().toDate()));

    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }
}
