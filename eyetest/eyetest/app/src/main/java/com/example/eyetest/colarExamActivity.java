package com.example.eyetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class colarExamActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editText;
    Button check_btn;
    ImageButton homebtn;
    TextView textView;
    ConstraintLayout layout;

    int i=(int)(Math.random()*100+1)%6;
    public int[] repeat = new int[]{i, 7, 7, 7, 7};
    boolean endddwhile=false;
    int k=1;
    public boolean isblindness=false;


    int[] selectcolor = {R.drawable.color_num_6,R.drawable.color_num_26,R.drawable.color_num_12,R.drawable.color_num_15,R.drawable.color_num_29,R.drawable.color_num_73};

    public ArrayList<String> colorlist = new ArrayList<>();


    //-------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colar_exam);

        initViews();
        initData();
        setListener();
    }
    @Override
    protected void onResume() {
        //讀取上次的紀錄
        SharedPreferences geti = getSharedPreferences("color_record",MODE_PRIVATE);
        int last_time_i = geti.getInt("EnvironNums",0);
        for(int i=0;i<last_time_i;i++){
            String environItem = geti.getString("item"+i,null);
            colorlist.add(environItem);
        }
        super.onResume();
    }

    private void initViews() {
        homebtn=findViewById(R.id.homebutton);
        imageView=findViewById(R.id.exam_img);
        editText=findViewById(R.id.inputnumber_Edittext);
        check_btn=findViewById(R.id.check_btn);
        textView=findViewById(R.id.result_textview);
        layout=(ConstraintLayout)findViewById(R.id.result_layout);
    }

    private void initData() {
        imageView.setImageResource(selectcolor[i]);
    }

    private void setListener() {
        homebtn.setOnClickListener(new MyOnTouchListeners());
        check_btn.setOnClickListener(new ColorOnTouchListeners());
    }

    private final class MyOnTouchListeners implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

    private final class ColorOnTouchListeners implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            AlertDialog.Builder alert_result = new AlertDialog.Builder(colarExamActivity.this);
            String nowDate = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒").format(new Date());
            String num;


            if(isEmpty(editText)==true){
                new AlertDialog.Builder(colarExamActivity.this)
                        .setTitle("無數字")
                        .setMessage("請輸入數字")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
            else{

                num=editText.getText().toString();
                switch(num){
                    case "6":
                        if(i==0){
                            break;
                        }
                        else{
                            isblindness=true;
                            break;
                        }
                    case "26":
                        if(i==1){
                            break;
                        }
                        else{
                            isblindness=true;
                            break;
                        }
                    case "12":
                        if(i==2){
                            break;
                        }
                        else{
                            isblindness=true;
                            break;
                        }
                    case "15":
                        if(i==3){
                            break;
                        }
                        else{
                            isblindness=true;
                            break;
                        }
                    case "29":
                        if(i==4){
                            break;
                        }
                        else{
                            isblindness=true;
                            break;
                        }
                    case "73":
                        if(i==5){
                            break;
                        }
                        else{
                            isblindness=true;
                            break;
                        }
                    default:
                        isblindness=true;
                        break;
                }

                endddwhile=true;
                while(endddwhile){
                    i=(int)(Math.random()*100+1)%6;
                    if(!isrepeat(repeat, i)){
                        repeat[k]=i;
                        endddwhile=false;
                    }
                }
                if(k<5){
                    imageView.setImageResource(selectcolor[i]);
                    k++;
                }
                if(k==5){
                    imageView.setImageResource(R.drawable.intro_background);
                    alert_result.setTitle("測驗結束");
                    alert_result.setCancelable(false);
                    alert_result.setPositiveButton("回主畫面", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(v.getContext(),MainActivity2.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    if(isblindness){
                        alert_result.setMessage("辨色能力有異常");
                        colorlist.add(nowDate+"-變色能力有異常");
                    }
                    else {
                        alert_result.setMessage("恭喜!辨色能力沒問題");
                        colorlist.add(nowDate+"-辨色能力沒問題");
                    }
                    alert_result.show();



                    SharedPreferences.Editor editor = getSharedPreferences("color_record",MODE_PRIVATE).edit();

                        editor.putInt("EnvironNums",colorlist.size());
                        for(int i=0;i<colorlist.size();i++){
                            editor.putString("item"+i,colorlist.get(i));
                        }
                        editor.commit();



                }

            }
            editText.setText(null);
        }
        private boolean isEmpty(EditText etText) {
            if (etText.getText().toString().trim().length() > 0)
                return false;

            return true;
        }
        private boolean isrepeat(int[] array,int i){
            for(int j=0;j<5;j++){
                if(i==array[j]){
                    return true;
                }
            }
            return false;
        }

    }

}