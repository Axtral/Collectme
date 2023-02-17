package com.axtral.collectme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.axtral.collectme.adapter.ArticleRecyclerVeiwAdapter;
import com.axtral.collectme.adapter.CategorieSpinnerAdapter;
import com.axtral.collectme.dao.CategorieDAO;
import com.axtral.collectme.entity.Article;
import com.axtral.collectme.entity.Categorie;
import com.axtral.collectme.service.ArticleService;
import com.axtral.collectme.service.impl.ArticleServiceImpl;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

// Page d'accueil après connexion
public class MainActivity extends AbstracCollectmeActivity {


    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CategorieDAO categorieDAO;
    private Spinner spinnerChooseCategorie;
    private RecyclerView rvListArticle;
    private CategorieSpinnerAdapter categorieSpinnerAdapter;
    private FirebaseUser user;
    private ArticleService articleService;
    private ArticleRecyclerVeiwAdapter articleRecyclerVeiwAdapter;
    private Article currentIntentArticle;
    private List<Article> articleList;

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d("swipedArticleList", "on swip un article");
            Article article = articleList.get(viewHolder.getAdapterPosition());
            Log.d("swipedArticleList", "on swip l'article : " + article.getNom());
            Log.d("swipedArticleList", "on swip l'article : " + article.getId());
            articleService = new ArticleServiceImpl();
            articleService.deleteArticle(article);
            articleList.remove(viewHolder.getAdapterPosition());
            articleRecyclerVeiwAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

    //articles
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        Intent intent = getIntent();
        currentIntentArticle = intent.getParcelableExtra("articleSelected");
        articleService = new ArticleServiceImpl();
        categorieDAO = new CategorieDAO();
        categorieDAO.getListCategories(this);
        spinnerChooseCategorie = (Spinner) findViewById(R.id.chooseCategory);
        rvListArticle = (RecyclerView) findViewById(R.id.rv_list_article);



    }

    public void getAllCategorie(List<Categorie> categories){

        ArrayList<Categorie> categorieArrayList = (ArrayList<Categorie>) categories;
        categorieSpinnerAdapter = new CategorieSpinnerAdapter(this,categorieArrayList);
        spinnerChooseCategorie.setAdapter(categorieSpinnerAdapter);
        if(currentIntentArticle != null){
            for (Categorie categorie : categories){
                if (currentIntentArticle.getIdCategorie().equals(categorie.getId())){
                    spinnerChooseCategorie.setSelection(categorieSpinnerAdapter.getPosition(categorie));
                }
            }
        }

        spinnerChooseCategorie.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Categorie categorieItem = (Categorie) parent.getItemAtPosition(position);
                        String nomCategorie = categorieItem.getNom();
                        String idCategorieItem = categorieItem.getId();
                        initArticlesFireBases(idCategorieItem);
                        Toast.makeText(MainActivity.this, nomCategorie + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                }
        );

    }


    public void remplirRecyclerViewArticles(List<Article> articles){
        //Gestion du recyclerView
        //on renseigne la liste des categorie a notre adapter
        //Log.d("testListCategorie","nbs de categorie : "+ categories.size());
        Log.d("remplirRecyclerViewArticle", "log de size : "+ articles.size());
        articleList = articles;
        rvListArticle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        articleRecyclerVeiwAdapter = new ArticleRecyclerVeiwAdapter(articles, this, "MainActivity");
        rvListArticle.setAdapter(articleRecyclerVeiwAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvListArticle);

    }

    public void initArticlesFireBases(String idCategorieItem){
        Log.d("initArticlesFireBases", "on va remplir la recyclerView ");
        Log.d("initArticlesFireBases", "l'id categorie est  : " + idCategorieItem);
        if(user != null){
            Log.d("initArticlesFireBases", "on a un user. id : "+ user.getUid());
            articleService.getArticles(user.getUid(), this, "MainActivity", idCategorieItem);

        }
    }

    public void onClickGoToFormAjoutArticle(View view){
        Intent intent = new Intent(this, AjoutArticleActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotToDetailArticle(Article article){
        Intent intent = new Intent(MainActivity.this, ArticleDetailActivity.class);
        intent.putExtra("articleSelected", article);
        MainActivity.this.startActivity(intent);
        finish();
    }



}