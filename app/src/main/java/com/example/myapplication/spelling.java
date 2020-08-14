package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class spelling extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextToSpeech tts;
    EditText editText;
    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling);
        editText=(EditText)findViewById(R.id.editText);
        tts=new TextToSpeech(this,this);
        this.sound= MediaPlayer.create(spelling.this,R.raw.activi);
        sound.start();
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        editText.setAnimation(fadeIn);
        Button btn = findViewById(R.id.speak);
                btn.setAnimation(myAnim);

    }

    public void speak(View view) {
        tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public void onInit(int i) {

    }
}
