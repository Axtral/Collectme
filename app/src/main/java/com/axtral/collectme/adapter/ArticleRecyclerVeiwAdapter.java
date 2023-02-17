package com.axtral.collectme.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.ArticleDetailActivity;
import com.axtral.collectme.MainActivity;
import com.axtral.collectme.R;
import com.axtral.collectme.entity.Article;
import com.axtral.collectme.viewHolder.ArticleViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ArticleRecyclerVeiwAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> articles;
    private Context context;
    private String nameContext;

    public ArticleRecyclerVeiwAdapter(List<Article> articles, Context context, String nameContext) {
        this.articles = articles;
        this.context = context;
        this.nameContext = nameContext;

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
        holder.getIdUser().setText(article.getIdUser());
        holder.getIdCategorie().setText(article.getIdCategorie());
        holder.getIdArticle().setText(article.getId());
        holder.getIdCategorie().setVisibility(View.GONE);
        holder.getIdUser().setVisibility(View.GONE);
        holder.getIdArticle().setVisibility(View.GONE);
        holder.getLayoutVLigneArticle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("remplirRecyclerViewArticle", "dans le onClick ");
                if ("MainActivity".equals(nameContext)) {
                    Log.d("remplirRecyclerViewArticle", "dans l'appel ");
                    ((MainActivity) context).gotToDetailArticle(article);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }
}
