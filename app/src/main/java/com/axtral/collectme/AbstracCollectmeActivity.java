package com.axtral.collectme;

import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public abstract class AbstracCollectmeActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        menu.findItem(R.id.menu_login).setVisible(false);
        menu.findItem(R.id.menu_logout).setVisible(true);
        menu.findItem(R.id.menu_ajout_article).setVisible(true);
        menu.findItem(R.id.menu_change_password).setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_ajout_article:
                Log.d("menuItemAddArticle", "on va depuis la menu sur l'ajout d'article ");
                intent = new Intent(this, AjoutArticleActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_login:
                Log.d("menuItemLogin", "on va depuis la menu sur le login ");
                intent = new Intent(this, ConnexionActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_change_password:
                Log.d("menuItemChangePwd", "on va depuis la menu sur le changement de pwd ");
                intent = new Intent(this, AjoutArticleActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.menu_logout:
                Log.d("menuItemLogout", "on va depuis la menu sur le logout ");
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(this, ConnexionActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
