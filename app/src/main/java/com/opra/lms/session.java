package com.opra.lms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
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
                        TextView desc=findViewById(R.id.textView4);
                        Button btn_next=(Button)findViewById(R.id.btn_next);
                        title.setText(documentSnapshot.get("title").toString());
                        desc.setText(documentSnapshot.get("description").toString());
                        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                            @Override
                            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                                String videoId = documentSnapshot.get("link").toString();
                                Log.d("Document",videoId);
                                youTubePlayer.loadVideo(videoId, 0);
                            }

                            @Override
                            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                                super.onStateChange(youTubePlayer, state);
                                //Toast.makeText(session.this,state.toString(), Toast.LENGTH_SHORT).show();
                                if(state.toString()=="ENDED"){
                                    String user=fAuth.getCurrentUser().getEmail();
                                    docRef.update("user", FieldValue.arrayUnion(user));

                                }
                            }
                        });
                        btn_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String next=  documentSnapshot.get("next").toString();
                                Log.d("Document",next);
                                if("null".contains(next)){
                                    Toast.makeText(session.this,"Congratulations!!", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(session.this,"You have completed last session of"+documentSnapshot.get("course")+"!!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Intent i = new Intent(session.this,session.class);
                                    i.putExtra("id",next);
                                    startActivity(i);
                                }

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