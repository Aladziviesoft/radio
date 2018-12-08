package com.aladziviesoft.radio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button playbutton;
    boolean prepared = false;

    public void playMusic(View view) throws IOException {
        playbutton = (Button) findViewById(R.id.playbutton);
        playPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = new MediaPlayer();
        String url = "http://live.radiorodja.com:80/;stream.mp3?_=3";
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mediaPlayer) {
                prepared = true;
            }
        });
    }


    public void playPause() {
        if (!mediaPlayer.isPlaying() && prepared) {
            mediaPlayer.start();
            playbutton.setBackgroundResource(R.drawable.stop);
        } else if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playbutton.setBackgroundResource(R.drawable.next);
        }
    }
}
