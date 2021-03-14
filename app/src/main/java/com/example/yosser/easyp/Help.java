package com.example.yosser.easyp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;



public class Help extends AppCompatActivity {


    TextView help ;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        help=(TextView)findViewById(R.id.help);
    }
}
