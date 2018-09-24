package org.tensorflow.demo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class VideoActivity extends Activity {

    String currentKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video);

        Bundle extras = getIntent().getExtras();
        currentKey = extras != null ? extras.getString("next") : "wdr";

        VideoView videoview = (VideoView) findViewById(R.id.videoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+ClassifierActivity.locationsMap.get(currentKey).video);

        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.wtf("WTF", "whyyy");
                Intent intent = new Intent(VideoActivity.this, ClassifierActivity.class);
                intent.putExtra("next", currentKey);
                startActivity(intent);
                finish();
            }
        });
    }

}
