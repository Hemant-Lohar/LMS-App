package com.opra.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.VideoView;

public class session extends AppCompatActivity {
VideoView video;
String url="https://www.youtube.com/watch?v=hEgO047GxaQ";
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        video=(VideoView) findViewById(R.id.videoView);
        pd = new ProgressDialog(session.this);
        pd.setMessage("Buffering...");
        pd.show();
        Uri uri= Uri.parse(url);
        video.setVideoURI(uri);
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pd.dismiss();
            }
        });

    }
}