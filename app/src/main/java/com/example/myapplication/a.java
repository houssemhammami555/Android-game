package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class a extends AppCompatActivity {
    private ImageView imageViewDice;
    private Random rng = new Random();
    private MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diceroller);
        imageViewDice = findViewById(R.id.image_view_dice);
        imageViewDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
        this.sound= MediaPlayer.create(a.this,R.raw.activi);
        sound.start();
    }

        private void rollDice() {
            int randomNumber = rng.nextInt(6) + 1;

            switch (randomNumber) {
                case 1:
                    imageViewDice.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    imageViewDice.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    imageViewDice.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    imageViewDice.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    imageViewDice.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    imageViewDice.setImageResource(R.drawable.dice6);
                    break;
            }
    }

}
