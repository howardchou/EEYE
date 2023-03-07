package com.example.eyetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int SPLASH_SCREEN=3000;

    //variables
    Animation topAnim,bottomAnim;
    ImageView image,appname;
    TextView slogon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image=findViewById(R.id.imageView);
        appname=findViewById(R.id.introappname);
        slogon=findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        appname.setAnimation(bottomAnim);
        slogon.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);


    }
}