package com.example.listofplayers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView;
        TextView name, score, lieu, speed_score, stamina_score, dribbling_score,
                defending_score, passing_score, shooting_score;

        imageView = findViewById(R.id.poster_image);
        name = findViewById(R.id.mName);
        score = findViewById(R.id.mScore);
        lieu = findViewById(R.id.mLieu);
        speed_score = findViewById(R.id.mSpeed_score);
        stamina_score = findViewById(R.id.mStamina_score);
        dribbling_score = findViewById(R.id.mDribbling_score);
        defending_score = findViewById(R.id.mDefending_score);
        passing_score = findViewById(R.id.mPassing_score);
        shooting_score = findViewById(R.id.mShooting_score);


        Bundle bundle = getIntent().getExtras();
        //recuperation des données du bundle crée dans l'adapter
        String mName = bundle.getString("name");
        String mScore = bundle.getString("score");
        String mLieu = bundle.getString("lieu");
        String mPhoto = bundle.getString("photo");
        String mSpeed_score = bundle.getString("speed_score");
        String mStamina_score = bundle.getString("stamina_score");
        String mDribbling_score = bundle.getString("dribbling_score");
        String mDefending_score = bundle.getString("defending_score");
        String mPassing_score = bundle.getString("passing_score");
        String mShooting_score = bundle.getString("shooting_score");

        Glide.with(this).load(mPhoto).into(imageView);

        name.setText(mName);
        score.setText(mScore);
        lieu.setText(mLieu);
        speed_score.setText(mSpeed_score);
        stamina_score.setText(mStamina_score);
        dribbling_score.setText(mDribbling_score);
        defending_score.setText(mDefending_score);
        passing_score.setText(mPassing_score);
        shooting_score.setText(mShooting_score);



    }
}