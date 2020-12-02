package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import android.content.Context;


public class NumbersActivty extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mcompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager mAudioManeger;

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
        words.add(new Words("one" ,"lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Words("two" ,"otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Words("three" ,"tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Words("four" ,"oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Words("five" ,"massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Words("six" ,"temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Words("seven" ,"kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Words("eight" ,"kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Words("nine" ,"wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Words("ten" ,"na’aacha",R.drawable.number_ten,R.raw.number_ten));



        NumsAdapter adapter = new NumsAdapter(this,words,R.color.category_numbers );
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

        mAudioManeger = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final int result = mAudioManeger.requestAudioFocus(audioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = words.get(position);
                releaseMediaPlayer();
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(NumbersActivty.this, word.getmAudioResourceId());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mcompletionListener);
                }


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
            mAudioManeger.abandonAudioFocus(audioFocusChangeListener);

        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}