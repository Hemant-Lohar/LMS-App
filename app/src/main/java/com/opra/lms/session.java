package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class session extends AppCompatActivity {
    //String api="AIzaSyAua7eSpOr9Wl-JvJ6GjiK8XbCnv3-rQsk";
   // String api_key = "AIzaSyBCVvsijV5UqsJmWqMVDYYjKJsrfCgczfI";
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        Intent i=getIntent();
        String id=i.getStringExtra("id");
        Log.d("Document", id);

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("session").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot= task.getResult();
                    if(!documentSnapshot.exists()){
                        Log.d("Document","No data");
                    }else{
                        String link= (String) documentSnapshot.get("link");
                        String title= (String) documentSnapshot.get("title");
                        String desc=(String) documentSnapshot.get("description");
                        String course=(String) documentSnapshot.get("course");




                    }
                }
                else{
                    Log.d("Document","No data");
                }
            }
        });
    }
}