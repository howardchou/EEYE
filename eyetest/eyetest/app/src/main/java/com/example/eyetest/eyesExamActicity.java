package com.example.eyetest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class eyesExamActicity extends AppCompatActivity {

    ImageButton homebtn;
    ImageView exam_imageView;
    Button btnyes,btnno;

    int index=10;

    int[] vision = {R.drawable.s12,
            R.drawable.s10,
            R.drawable.s09,
            R.drawable.s08,
            R.drawable.s07,
            R.drawable.s06,
            R.drawable.s05,
            R.drawable.s04,
            R.drawable.s03,
            R.drawable.s02,
            R.drawable.s01,};//index=10,最大

    String[] visionstring = {"1.2","1.0","0.9","0.8","0.7","0.6","0.5","0.4","0.3","0.2","0.1"};
    int[] count = new int[11];
    //------------------------------sharedpreference------------------
    public ArrayList<String> viewlist = new ArrayList<>();

    String nowDate = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒").format(new Date());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyes_exam_acticity);

        initViews();
        initData();
        setListener();

        AlertDialog.Builder start_alert = new AlertDialog.Builder(eyesExamActicity.this);


        start_alert.setTitle("注意!");
        start_alert.setCancelable(false);
        start_alert.setMessage("請距離70~80(cm)");
        start_alert.setPositiveButton("確定",null);
        start_alert.show();

    }

    @Override
    protected void onResume() {
        SharedPreferences geti = getSharedPreferences("color_record",MODE_PRIVATE);
        int last_time_i = geti.getInt("EnvironNums",0);
        for(int i=0;i<last_time_i;i++){
            String environItem = geti.getString("item"+i,null);
            viewlist.add(environItem);
        }
        super.onResume();
    }

    @Override
    protected  void onStop() {
        SharedPreferences.Editor editor = getSharedPreferences("color_record",MODE_PRIVATE).edit();

        editor.putInt("EnvironNums",viewlist.size());
        for(int i=0;i<viewlist.size();i++){
            editor.putString("item"+i,viewlist.get(i));
        }
        editor.commit();
        super.onStop();
    }

    private void initViews() {
        homebtn=findViewById(R.id.homebutton);
        exam_imageView=findViewById(R.id.exam_img);
        btnyes=findViewById(R.id.yes_btn);
        btnno=findViewById(R.id.no_btn);
    }

    private void initData() {
        exam_imageView.setImageResource(vision[index]);
    }

    private void setListener() {
        homebtn.setOnClickListener(new MyOnTouchListeners());
        btnyes.setOnClickListener(new YesbtnOnTouchListeners());
        btnno.setOnClickListener(new NobtnOnTouchListeners());
    }

    private final class MyOnTouchListeners implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    //
    //看得到
    private final class YesbtnOnTouchListeners implements View.OnClickListener{


        AlertDialog.Builder showresult = new AlertDialog.Builder(eyesExamActicity.this);

        @Override
        public void onClick(View v) {
            if(index==0){
                showresult.setTitle("測驗結束");
                showresult.setCancelable(false);
                showresult.setMessage("度數為"+visionstring[index]);
                viewlist.add(nowDate+"度數為"+visionstring[index]);
                showresult.setPositiveButton("回主畫面", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(v.getContext(),MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }
                });
                showresult.show();
            }
            else{
                index--;
                exam_imageView.setImageResource(vision[index]);
            }
        }
    }
    //
    //看不到
    private final class NobtnOnTouchListeners implements View.OnClickListener{

        AlertDialog.Builder showresult = new AlertDialog.Builder(eyesExamActicity.this);

        @Override
        public void onClick(View v) {
            if(index==10){
                showresult.setTitle("測驗結束");
                showresult.setCancelable(false);
                showresult.setMessage("度數為"+visionstring[index]);
                viewlist.add(nowDate+"度數為"+visionstring[index]);
                showresult.setPositiveButton("回主畫面", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(v.getContext(),MainActivity2.class);
                        startActivity(intent);
                        finish();
                    }
                });
                showresult.show();
            }
            else{
                count[index]++;

                if(count[index]==2){
                    showresult.setTitle("測驗結束");
                    showresult.setCancelable(false);
                    showresult.setMessage("度數為"+visionstring[index+1]);
                    viewlist.add(nowDate+"度數為"+visionstring[index+1]);
                    showresult.setPositiveButton("回主畫面", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(v.getContext(),MainActivity2.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    showresult.show();
                }
                else{
                    index++;
                    exam_imageView.setImageResource(vision[index]);
                }
            }
        }
    }
/*
    private void smaller(){
        if(index==10){

        }
        else{
            index++;
            exam_imageView.setImageResource(vision[index]);
        }
    }

    private int bigger(){
        if(index==0){
            return index;
        }
        else{
            index--;
            exam_imageView.setImageResource(vision[index]);
            return index;
        }
    }
*/
}