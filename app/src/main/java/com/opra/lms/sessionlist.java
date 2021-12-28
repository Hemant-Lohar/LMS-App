package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class sessionlist extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i=getIntent();
        String course=i.getStringExtra("COURSE");
        Toast.makeText(sessionlist.this, course, Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_sessionlist);
        layout=findViewById(R.id.layout_seshlist);
        Intent intent=new Intent(sessionlist.this, session.class);
        FirebaseFirestore.getInstance().collection("session")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ArrayList<String> user;
                                Log.d("Document", document.getId());
                                user = (ArrayList<String>) document.get("user");
                                String current_user=fAuth.getCurrentUser().getEmail();
                                String current_course= document.getId();
                                Log.d("Document", current_user);
                                Log.d("Document", user.toString());
                                Log.d("Document", document.getId());
                                final View addedview = getLayoutInflater().inflate(R.layout.seshlist, null, false);
                                TextView name=addedview.findViewById(R.id.outer);
                                CheckBox checkdone=addedview.findViewById(R.id.checkdone);
                                Button btn_attend=(Button)addedview.findViewById(R.id.btn_attend);
                                name.setText(document.getId());
                                if(user.contains(current_user)) {
                                    checkdone.setChecked(true);
                                }
                                else{
                                    checkdone.setChecked(false);
                                }
                                btn_attend.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        intent.putExtra("id",document.getId());
                                        startActivity(intent);
                                    }
                                });
                                if(current_course.contains(course)){
                                    layout.addView(addedview);
                                }





                                }

                            }
                         else {
                            Log.d("Document", task.getException().toString());
                        }
                    }
                });


    }

}