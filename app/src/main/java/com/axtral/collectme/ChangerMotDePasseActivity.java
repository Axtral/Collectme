package com.axtral.collectme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangerMotDePasseActivity extends AppCompatActivity {

    EditText et_password, et_confirm_password;
    TextView tv_message_erreur;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changer_mot_de_passe);
        init();
    }

    private void init(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        et_password = (EditText) findViewById(R.id.et_form_change_pwd);
        et_confirm_password = (EditText) findViewById(R.id.et_form_chage_pwd_confirmation);
        tv_message_erreur = (TextView)  findViewById(R.id.tv_message_erreur_form_change_pwd);

    }

    public void onClickUpdatePasswordUser (View view){
        String mdp = et_password.getText().toString();
        String confirmMdp = et_confirm_password.getText().toString();
        et_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if(mdp.equals(confirmMdp)){
            tv_message_erreur.setVisibility(View.GONE);
            user.updatePassword(mdp)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ChangerMotDePasseActivity.this,R.string.change_pwd_succes, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ChangerMotDePasseActivity.this,R.string.change_pwd_echec, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else {
            tv_message_erreur.setVisibility(View.VISIBLE);
        }
    }

    public void onClickBack(View view){
        finish();
    }
}