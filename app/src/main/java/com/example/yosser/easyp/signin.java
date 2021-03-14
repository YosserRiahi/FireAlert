package com.example.yosser.easyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signin extends AppCompatActivity {
    Button btn, btn2;
    EditText pw,Em;

    private ProgressBar progressBar;
    private FirebaseAuth auth;

    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btn=(Button)findViewById(R.id.submit_area);
        btn2=(Button)findViewById(R.id.cancel_action);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        pw=(EditText)findViewById(R.id.password);
        Em=(EditText)findViewById(R.id.Email);

    }

    public void cancel(View v) {

        if(btn2.getId()==v.getId())
        {
            Intent i;
            i  =new Intent(this,MainActivity.class);
            startActivity(i);
        }}


    public void submit(View v) {
        String pass=pw.getText().toString();
        String email = Em.getText().toString().trim();
        if (btn.getId()==v.getId()){

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Entrer l'adresse e-mail!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(getApplicationContext(), "Entrer le mot de passe!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (pass.length() < 6) {
                Toast.makeText(getApplicationContext(), "Mot de passe trop court, entrez au moins 6 caractères!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(signin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signin.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    Toast.makeText(signin.this, "Authentification échouée." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(signin.this, Notification.class));
                                    finish();
                                }
                            }
                        });



        }


    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
