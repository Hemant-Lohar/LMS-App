package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_signin);

        Button btnSignIn = (Button) findViewById(R.id.btn_login);
        ImageView backArrow = (ImageView) findViewById(R.id.signin_backarrow);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();

        EditText username = (EditText) findViewById(R.id.etxt_username);
        EditText pass = (EditText) findViewById(R.id.etxt_password);



        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if(TextUtils.isEmpty((email))) {
                    username.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty((password))) {
                    pass.setError("Password is required");
                    return;
                }
                if(pass.length() < 8) {
                    pass.setError("Password length should be at least 8");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(signin.this, "Login Successfully !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signin.this,home.class));

                        }else {
                            Toast.makeText(signin.this, "Login Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signin.this,MainActivity.class));
            }
        });



    }
}
