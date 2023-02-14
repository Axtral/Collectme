package com.axtral.collectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InscriptionActivity extends AppCompatActivity {

    private EditText et_mail, et_mdp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        init();

    }
    // permet d'initialiser les composants en les récupérant de la vue
    public void init(){
        et_mail = findViewById(R.id.et_inscription_email);
        et_mdp = findViewById(R.id.et_inscription_motDePasse);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onCLickInscription(View view){
        // Afficher dans les log bonjour, tu es dans le onClickInscription
        //Log.d("testInscription", "bonjour, tu es dans le onClickInscription");

        // Afficher dans les logs les variables saisi par l'utilisateur
        String mail = et_mail.getText().toString();
        String mdp = et_mdp.getText().toString();
        Log.d("testInscription", "mail : "+mail+" , mdp : "+mdp);
        insertUserFireBase(mail, mdp);
    }

    public void onClickGoToConnexion(View view) {
        // Afficher à l'utilisateur bonjour, tu es dans le onClickGoToConnexion
        Toast.makeText(this, "bonjour, tu es dans le onClickGoToConnexion", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);

    }

    public void insertUserFireBase(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new
                        OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("testInscription", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("testInscription", "createUserWithEmail:failure",
                                            task.getException());
                                    Toast.makeText(InscriptionActivity.this,
                                            "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
    }
    public void updateUI(FirebaseUser user){
        if (user != null){
            Toast.makeText(InscriptionActivity.this, R.string.inscription_reussite, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(InscriptionActivity.this, R.string.inscription_echec, Toast.LENGTH_LONG).show();
        }
    }
}