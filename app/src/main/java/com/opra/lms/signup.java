package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class signup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_signup);

        Button btnSignUp = (Button) findViewById(R.id.btn_reg);
        ImageView backArrow = (ImageView) findViewById(R.id.signup_backarrow);
        EditText fName = (EditText) findViewById(R.id.etxt_fname);
        EditText lName = (EditText) findViewById(R.id.etxt_lname);
        EditText regEmail = (EditText) findViewById(R.id.etxt_reg_username);
        EditText pass = (EditText) findViewById(R.id.etxt_reg_password);
        EditText confPass = (EditText) findViewById(R.id.etxt_reg_conf_password);
        ProgressBar loading = (ProgressBar) findViewById(R.id.loadingbar);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();



        if(fAuth.getCurrentUser() != null) {
            startActivity(new Intent(signup.this,signin.class));
            finish();
        }


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f_name = fName.getText().toString().trim();
                String l_name = lName.getText().toString().trim();
                String email = regEmail.getText().toString().trim();
                String password = pass.getText().toString().trim();
                String confPassword = confPass.getText().toString().trim();
                if(TextUtils.isEmpty((email))) {
                    regEmail.setError("Email is required");
                    return;
                }
                if(password != confPassword) {
                    Toast.makeText(signup.this, "Password Not match", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty((password))) {
                    pass.setError("Password is required");
                    return;
                }
                if(pass.length() < 8) {
                    pass.setError("Password length should be at least 8");
                    return;
                }
                if(TextUtils.isEmpty((confPassword))) {
                    confPass.setError("Confirm Password is required");
                    return;
                }

                Map<String, Object> user = new HashMap<>();
                user.put("first_name", f_name);
                user.put("last_name", l_name);
                user.put("course_com", "");
                user.put("course_reg", "");



//                db.collection("User").document(email).add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//
//                        Log.d("Document", "Info Registered" + documentReference.getId());
//                    }
//                })
//
//
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.w("Document", "Error adding document", e);
//                            }
//                        });

                db.collection("User").document(email)
                        .set(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Document", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Document", "Error writing document", e);
                            }
                        });


                loading.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(signup.this, "Registered Successfully !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signup.this,signin.class));

                        }else {
                            Toast.makeText(signup.this, "Registration Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this,MainActivity.class));
            }
        });

    }

    }


