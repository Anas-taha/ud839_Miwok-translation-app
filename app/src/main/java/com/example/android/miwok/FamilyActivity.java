package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Context;
import android.media.AudioManager;
import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS||focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);

            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        final ArrayList<Words> words  = new ArrayList<Words>();
        words.add(new Words("father" ,"әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Words("mother" ,"әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Words("son" ,"angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Words("daughter" ,"tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Words("older brother" ,"taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Words("younger brother" ,"chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Words("older sister" ,"teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Words("younger sister" ,"kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Words("grandmother" ,"ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Words("grandfather" ,"paapa",R.drawable.family_grandfather,R.raw.family_grandfather));



        NumsAdapter adapter = new NumsAdapter(this,words,R.color.category_family );
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final int result = audioManager.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = words.get(position);
                releaseMediaPlayer();
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mcompletionListener);}
            }
        });


    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}