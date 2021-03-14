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



public class acceuil extends AppCompatActivity  {

private FirebaseAuth auth;
    EditText pw,Em;
    Button  btn , btn2 ,btnReset;
    Intent intent;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = com.google.firebase.auth.FirebaseAuth.getInstance();

        setContentView(R.layout.acceuil);
        pw=(EditText)findViewById(R.id.password);
        Em=(EditText)findViewById(R.id.Email);
        btn2=(Button)findViewById(R.id.wtv);
        btn=(Button)findViewById(R.id.Signin);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        auth = com.google.firebase.auth.FirebaseAuth.getInstance();
        intent = new Intent(getApplicationContext(), signin.class);

    }

    public void Register(View v) {
        if(btn.getId()== v.getId())
        {
            Intent Gosign;
            Gosign =new Intent(this,signin.class);
            startActivity(Gosign);}

    }

    public void reset(View view) {
        if(btnReset.getId()== view.getId())
        {
            Intent Reset;
            Reset =new Intent(this,ResetPasswordActivity.class);
            startActivity(Reset);}
    }

    public void login(View v) {
        String email = Em.getText().toString().trim();
       final String pass = pw.getText().toString();
        if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(), "Entrer l'adresse e-mail!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }
  progressBar.setVisibility(View.VISIBLE);
             auth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(acceuil.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (!task.isSuccessful()) {
                                            // there was an error
                                            if (pass.length() < 6) {
                                                pw.setError(getString(R.string.minimum_password));
                                            } else {
                                                Toast.makeText(acceuil.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Intent intent = new Intent(acceuil.this, Notification.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                    }
                }







