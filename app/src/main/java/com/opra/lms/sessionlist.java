package com.opra.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sessionlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessionlist);
        Button btn1=(Button) findViewById(R.id.button40);
        Button btn2=(Button) findViewById(R.id.button41);
        Button btn3=(Button) findViewById(R.id.button42);
        Button btn4=(Button) findViewById(R.id.button43);
        Button btn5=(Button) findViewById(R.id.button44);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(sessionlist.this, session.class));

            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(sessionlist.this, session.class));

            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(sessionlist.this, session.class));

            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(sessionlist.this, session.class));

            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(sessionlist.this, session.class));

            }
        });

    }

}