package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


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
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtubeplayer);
        getLifecycle().addObserver(youTubePlayerView);

        DocumentReference docRef = FirebaseFirestore.getInstance().collection("session").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot= task.getResult();
                    if(!documentSnapshot.exists()){
                        Log.d("Document","No data");
                    }else{

                        TextView title=findViewById(R.id.textView3);
                        title.setText(documentSnapshot.getId());

                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                String video = documentSnapshot.get("link").toString();
                                Log.d("Document",video);
                                youTubePlayer.loadVideo(video, 0);
                            }
                        });




                    }
                }
                else{
                    Log.d("Document","No data");
                }
            }
        });
    }
}