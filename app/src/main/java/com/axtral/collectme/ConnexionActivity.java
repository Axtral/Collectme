package com.axtral.collectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConnexionActivity extends AbstracCollectmeActivity {

    private EditText et_mail, et_mdp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        init();
    }

    private void init(){
        et_mail = findViewById(R.id.et_connexion_mail);
        et_mdp = findViewById(R.id.et_connexion_motDePasse);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickGoToInscription(View view){
        //Intent intent = new Intent(this, InscriptionActivity.class);
        //startActivity(intent);
        Log.d("testConnexion", "test de retour à la page précédante");
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
        finish();

    }

    public void onClickConnexionUser(View view){
        String mail = et_mail.getText().toString();
        String mdp = et_mdp.getText().toString();
        Log.d("testConnexion", "mail : "+mail+" , mdp : "+mdp);
        checkUserFireBase(mail, mdp);

    }

    public void checkUserFireBase(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("testConnexion", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("testConnexion", "signInWithEmail:failure",
                                            task.getException());
                                    Toast.makeText(ConnexionActivity.this,
                                            "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
    }

    public void updateUI(FirebaseUser user){
        if (user != null){
            Log.d("testConnexion", user.getUid());
            Toast.makeText(ConnexionActivity.this,
                    R.string.connexion_reussite, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(ConnexionActivity.this,
                    R.string.connexion_echec, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean test = super.onCreateOptionsMenu(menu) ;
        menu.findItem(R.id.menu_login).setVisible(true);
        menu.findItem(R.id.menu_logout).setVisible(false);
        menu.findItem(R.id.menu_ajout_article).setVisible(false);
        menu.findItem(R.id.menu_change_password).setVisible(false);
        return  test;
    }
}