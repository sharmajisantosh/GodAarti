package com.example.santosh.godaarti;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.santosh.godaarti.helper.PlayMedia;

import pl.droidsonroids.gif.GifImageView;

public class PlayMusic extends AppCompatActivity {

    ImageView playImage, stopImage, nextImage, pauseImage;
    GifImageView fullImage;
    int[] soundIDs = {R.raw.a1ga,R.raw.b2gc,R.raw.c3la,R.raw.d4lc,R.raw.e5ha,R.raw.f6hc,R.raw.g7hbb};
    PlayMedia playAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getSupportActionBar().setTitle("");

        playImage= (ImageView) findViewById(R.id.play);
        stopImage= (ImageView) findViewById(R.id.stop);
        nextImage= (ImageView) findViewById(R.id.next);
        pauseImage= (ImageView) findViewById(R.id.pause);
        fullImage= (GifImageView) findViewById(R.id.fullImage);

        playImage.setVisibility(View.INVISIBLE);

        final Handler handler = new Handler();

        final int [] arrayimage={R.drawable.ganeshgif1,R.drawable.ganeshgif2,R.drawable.ganeshgif3};

        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                fullImage.setImageResource(arrayimage[i]);
                i++;
                if(i>arrayimage.length-1)
                {
                    i=0;
                }
                handler.postDelayed(this, 3000);  //changes after 3 sec
            }
        };
        handler.postDelayed(runnable, 2000);
        playAudio = new PlayMedia(this,soundIDs);
        playAudio.execute();

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio.nextMediaFile();

            }
        });

        playImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseImage.setVisibility(View.VISIBLE);
                playImage.setVisibility(View.INVISIBLE);
                Toast.makeText(PlayMusic.this, "play clicked", Toast.LENGTH_SHORT).show();
                playAudio.playMediaFile();
            }

        });

        pauseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseImage.setVisibility(View.INVISIBLE);
                playImage.setVisibility(View.VISIBLE);
                Toast.makeText(PlayMusic.this, "pause clicked", Toast.LENGTH_SHORT).show();
                playAudio.pauseMediaFile();
            }
        });

        stopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseImage.setVisibility(View.INVISIBLE);
                playImage.setVisibility(View.VISIBLE);
                playAudio.stopMediaFile();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        playAudio.stopMediaFile();

    }
}
