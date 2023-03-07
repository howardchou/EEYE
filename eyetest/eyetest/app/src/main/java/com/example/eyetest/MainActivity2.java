package com.example.eyetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity2 extends AppCompatActivity {
    ImageButton eyesbton;
    ImageButton colarbtn;
    ImageButton recordbtn;
    ImageButton aboutusbtn;
    ImageButton homebton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initViews();
        initData();
        setListeners();




    }

    private void initViews(){
        homebton=(ImageButton)findViewById(R.id.homebutton);
        eyesbton=(ImageButton) findViewById(R.id.eyesExam);
        colarbtn=(ImageButton) findViewById(R.id.colarExam);
        recordbtn=(ImageButton) findViewById(R.id.record);
        aboutusbtn=(ImageButton) findViewById(R.id.aboutus);
    }

    private  void initData(){

    }

    private void setListeners(){
        eyesbton.setOnTouchListener(new MyOnTouchListeners());

        colarbtn.setOnClickListener((View view)->{
            Intent intent2 = new Intent(this,colarExamActivity.class);
            startActivity(intent2);
            finish();
        });

        recordbtn.setOnClickListener((View view)->{
            Intent intent2 = new Intent(this,recordActivity.class);
            startActivity(intent2);
            finish();
        });

        aboutusbtn.setOnClickListener((View view)->{
            Intent intent2 = new Intent(this,aboutusActivity.class);
            startActivity(intent2);
            finish();
        });

        homebton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backtohome();
            }
        });
    }

    private final class MyOnTouchListeners implements View.OnTouchListener, View.OnClickListener {


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Intent intent2 = new Intent(v.getContext(),eyesExamActicity.class);
            startActivity(intent2);
            finish();
            return false;
        }

        @Override
        public void onClick(View v) {

        }
    }

    public void backtohome(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
        finish();
    }

}