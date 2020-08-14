package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5,b6;
    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sound= MediaPlayer.create(MainActivity.this,R.raw.sound);
        sound.start();

        b1=findViewById(R.id.btntic);
        b2=findViewById(R.id.spin);
        b3=findViewById(R.id.paint);
        b4=findViewById(R.id.sp) ;
        b5=findViewById(R.id.histoire);
        b6= findViewById(R.id.di);
        b6.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade);
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        b1.startAnimation(myAnim);
        b2.startAnimation(myAnim);
        b3.startAnimation(myAnim);
        b4.startAnimation(myAnim);
        b5.startAnimation(myAnim);
        b6.startAnimation(myAnim);




    }
    @Override
    public void onStop(){
        super.onStop();
        this.sound.stop();
    }
    @Override
    public void onPause(){
        super.onPause();
        this.sound.pause();
    }
    @Override
    public void onResume(){
        super.onResume();
        this.sound.start();
    }
    @Override
    public void onStart(){
        super.onStart();
        this.sound.start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btntic){
            Intent i=new Intent(this,tictac.class);

            startActivity(i);
        }
        if (view.getId()==R.id.spin){
            Intent i=new Intent(this,spin.class);

            startActivity(i);
        }
        if (view.getId()==R.id.sp){
            Intent intent=new Intent(this,spelling.class);

            startActivity(intent);
        }
        if (view.getId()==R.id.di){
            Intent intent=new Intent(this,QuizMain.class);

            startActivity(intent);
        }
        if (view.getId()==R.id.paint){
            Uri uri = Uri.parse("https://www.autodraw.com/");
            Intent intent =new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }

        if (view.getId()==R.id.histoire){
            Uri uri = Uri.parse("https://www.iletaitunehistoire.com/genres/albums-et-histoires");
            Intent intent =new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }



    }


}
