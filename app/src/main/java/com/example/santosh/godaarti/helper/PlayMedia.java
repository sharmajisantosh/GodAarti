package com.example.santosh.godaarti.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by SANTOSH on 12/16/2017.
 */

public class PlayMedia extends AsyncTask<Void, Void, Void> {

    private static final String LOG_TAG = PlayMedia.class.getSimpleName();

    Context context;
    private MediaPlayer mediaPlayer;
    int[] soundIDs;
    int idx =1;

    public PlayMedia(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }
    public PlayMedia(final Context context, final int[] soundIDs) {
        this.context = context;
        this.soundIDs=soundIDs;
        mediaPlayer = MediaPlayer.create(context,soundIDs[0]);
        setNextMediaForMediaPlayer(mediaPlayer);
    }
    public void setNextMediaForMediaPlayer(MediaPlayer player){
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                if(soundIDs.length>idx){
                    mp.release();
                    mp = MediaPlayer.create(context,soundIDs[idx]);
                    setNextMediaForMediaPlayer(mp);
                    mp.start();
                    idx++;
                }
            }
        });
    }


    public void nextMediaFile(){

        mediaPlayer.release();
        if(idx<soundIDs.length){
            mediaPlayer=MediaPlayer.create(context,soundIDs[idx]);
            mediaPlayer.start();
            idx++;
        }else {
            Toast.makeText(context, "Song List ends. Press next again", Toast.LENGTH_SHORT).show();
            idx=0;
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(idx<soundIDs.length) {
                    mediaPlayer.release();
                    mediaPlayer = MediaPlayer.create(context, soundIDs[idx]);
                    mediaPlayer.start();
                    idx++;
                }else {
                    Toast.makeText(context, "Song List ends. Press next again", Toast.LENGTH_SHORT).show();
                    idx=0;
                }
            }
        });

    }

    public void playMediaFile(){
        if(idx==0){
            mediaPlayer = MediaPlayer.create(context,soundIDs[0]);
            setNextMediaForMediaPlayer(mediaPlayer);
            Toast.makeText(context, "Playing from 1st", Toast.LENGTH_SHORT).show();
        }
        if(mediaPlayer.isPlaying()) {
            Toast.makeText(context, "Already playing", Toast.LENGTH_SHORT).show();
        }else {
            mediaPlayer.start();
        }
    }

    public void pauseMediaFile(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }else {
            Toast.makeText(context, "Already paused", Toast.LENGTH_SHORT).show();
        }
    }

    public void stopMediaFile(){
        mediaPlayer.stop();
        mediaPlayer.release();
        idx=0;

    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            Log.e(LOG_TAG, "", e);
        } catch (SecurityException e) {
            Log.e(LOG_TAG, "", e);
        } catch (IllegalStateException e) {
            Log.e(LOG_TAG, "", e);
        }

        return null;
    }


    @Override
    protected void onCancelled() {
        Toast.makeText(context, "On Cancelled", Toast.LENGTH_SHORT).show();
        super.onCancelled();
    }
}
