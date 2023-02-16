package com.axtral.collectme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.axtral.collectme.adapter.ArticleRecyclerVeiwAdapter;
import com.axtral.collectme.adapter.CategorieAdapter;
import com.axtral.collectme.adapter.CategorieSpinnerAdapter;
import com.axtral.collectme.adapter.CategorieSpinnerBasicAdapter;
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

// Page de connexion
public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CategorieDAO categorieDAO;
    private List<Categorie> categories;
    private Spinner spinnerChooseCategorie;
    private RecyclerView rvListCategorie, rvListArticle;
    private CategorieAdapter categorieAdapter;
    private CategorieSpinnerAdapter categorieSpinnerAdapter;
    private CategorieSpinnerBasicAdapter categorieSpinnerBasicAdapter;
    private FirebaseUser user;
    private ArticleService articleService;
    private ArticleRecyclerVeiwAdapter articleRecyclerVeiwAdapter;

    //articles
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        articleService = new ArticleServiceImpl();
        categorieDAO = new CategorieDAO();
        categorieDAO.getListCategories(this);
        spinnerChooseCategorie = (Spinner) findViewById(R.id.chooseCategory);
        rvListCategorie = (RecyclerView) findViewById(R.id.rvListeCategorie);
        rvListArticle = (RecyclerView) findViewById(R.id.rv_list_article);
        initArticlesFireBases();

    }

    public void getAllCategorie(List<Categorie> categories){
        List<String> nomCategories = new ArrayList<>();
        for (Categorie categorie : categories){
            //Log.d("testListCategorie", categorie.getId() + " => "+ categorie.getNom());
            nomCategories.add(categorie.getNom());
        }

        ArrayList<Categorie> categorieArrayList = (ArrayList<Categorie>) categories;
        categorieSpinnerAdapter = new CategorieSpinnerAdapter(this,categorieArrayList);
        spinnerChooseCategorie.setAdapter(categorieSpinnerAdapter);
        spinnerChooseCategorie.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Categorie categorieItem = (Categorie) parent.getItemAtPosition(position);
                        String nomCategorie = categorieItem.getNom();
                        Toast.makeText(MainActivity.this, nomCategorie + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );
        /*categorieSpinnerBasicAdapter = new CategorieSpinnerBasicAdapter(MainActivity.this, categories);
        spinnerChooseCategorie.setAdapter(categorieSpinnerBasicAdapter);*/
        //remplierRecyclerView(categories);
    }

    public void remplierRecyclerView(List<Categorie> categories){
        //Gestion du recyclerView
        //on renseigne la liste des categorie a notre adapter
        //Log.d("testListCategorie","nbs de categorie : "+ categories.size());
        rvListCategorie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        categorieAdapter = new CategorieAdapter(categories);
        rvListCategorie.setAdapter(categorieAdapter);

    }

    public void remplirRecyclerViewArticles(List<Article> articles){
        Log.d("remplirRecyclerViewArticle", "log de size : "+ articles.size());
        if(articles.size() > 0) {
            Log.d("remplirRecyclerViewArticle", articles.get(0).getNom());
            rvListArticle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            articleRecyclerVeiwAdapter = new ArticleRecyclerVeiwAdapter(articles);
            rvListArticle.setAdapter(articleRecyclerVeiwAdapter);
        }

    }

    public void initArticlesFireBases(){
        Log.d("initArticlesFireBases", "on va remplir la recyclerView ");
        if(user != null){
            Log.d("initArticlesFireBases", "on a un user. id : "+ user.getUid());
            List<Article> articles = articleService.getArticles(user.getUid(), this, "MainActivity");

        }
    }

    public void onClickGoToFormAjoutArticle(View view){
        Intent intent = new Intent(this, AjoutArticleActivity.class);
        startActivity(intent);
    }

}