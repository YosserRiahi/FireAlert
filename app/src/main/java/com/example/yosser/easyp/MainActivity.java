package com.example.yosser.easyp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yosser.easyp.R;

public class MainActivity extends AppCompatActivity {
    Button     btn;
    Button bb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.buttonstart);

    }


    public void start(View v) {
        if(btn.getId()== v.getId())
        {
            Intent I;
            I =new Intent(this,acceuil.class);
            startActivity(I);
        }
    }

    }
