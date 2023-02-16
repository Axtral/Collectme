package com.axtral.collectme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.axtral.collectme.adapter.CategorieSpinnerAdapter;
import com.axtral.collectme.dao.CategorieDAO;
import com.axtral.collectme.entity.Article;
import com.axtral.collectme.entity.Categorie;
import com.axtral.collectme.service.ArticleService;
import com.axtral.collectme.service.impl.ArticleServiceImpl;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class AjoutArticleActivity extends AppCompatActivity {

    private Button b_ajout_article, b_cancel;
    private EditText et_nom, etml_description;
    private Spinner spinnerAllCategorie;
    private CategorieSpinnerAdapter categorieSpinnerAdapter;
    private CategorieDAO categorieDAO;
    private FirebaseUser user;
    private ArticleService articleService;
    private String idCategorie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_article);
        init();
    }

    private void init(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        articleService = new ArticleServiceImpl();
        b_ajout_article = (Button) findViewById(R.id.b_form_ajout_article);
        b_cancel = (Button) findViewById(R.id.b_form_annulee_ajout_article);
        et_nom = (EditText) findViewById(R.id.et_nom_form_article);
        etml_description = (EditText) findViewById(R.id.etml_form_description);
        spinnerAllCategorie = (Spinner) findViewById(R.id.chooseCategorieAjoutArticle);
        categorieDAO = new CategorieDAO();
        categorieDAO.getListCategories(this, "AjoutArticleActivity");

    }

    public void getAllCategorie(List<Categorie> categories){

        ArrayList<Categorie> categorieArrayList = (ArrayList<Categorie>) categories;

        categorieSpinnerAdapter = new CategorieSpinnerAdapter(this,categorieArrayList);
        spinnerAllCategorie.setAdapter(categorieSpinnerAdapter);

        spinnerAllCategorie.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Categorie categorieItem = (Categorie) parent.getItemAtPosition(position);
                        String nomCategorie = categorieItem.getNom();
                        idCategorie = categorieItem.getId();
                        Toast.makeText(AjoutArticleActivity.this, nomCategorie + " selected", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

    }

    public void onClickCancelAjoutArticle(View view){
        finish();
    }

    public void onClickAjoutArticle(View view){
        // Vérification qu'on set bien tout les valeurs
        Log.d("testFormAjoutArticle", "id categorie : " + idCategorie);
        String nom = et_nom.getText().toString();
        String description = etml_description.getText().toString();
        Article newArticle = new Article();
        newArticle.setNom(nom);
        newArticle.setDescription(description);
        newArticle.setIdCategorie(idCategorie);
        Log.d("testFormAjoutArticle", "nom : " + nom);
        Log.d("testFormAjoutArticle", "description : " + description);
        if (user != null) {
            String uid = user.getUid();
            Log.d("testFormAjoutArticle", "uid : " + uid);
            newArticle.setIdUser(uid);
            articleService.insertArticle(newArticle);
            finish();
        }else {
            Log.d("testFormAjoutArticle", "création impossible");
        }


    }
}