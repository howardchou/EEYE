package com.example.eyetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class recordActivity extends AppCompatActivity {

    ImageButton homebtn;
    ListView listView;
    Button clearData;
    public ArrayList<String> arrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        initViews();
        initData();
        setListener();

        SharedPreferences getspfs = getSharedPreferences("color_record",MODE_PRIVATE);
        int environNums = getspfs.getInt("EnvironNums",0);
        for(int i=0;i<environNums;i++){
            String environItem = getspfs.getString("item"+i,null);
            arrayList.add(environItem);
        }
        /*
        arrayList.add("5.0");
        arrayList.add("7.0");
        arrayList.add("6.0");
        arrayList.add("10.0");
        (*/
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);

        listView.setAdapter(arrayAdapter);

        clearData.setOnClickListener((View view)->{
            SharedPreferences.Editor editor = getSharedPreferences("color_record",MODE_PRIVATE).edit();
            editor.clear();
            editor.commit();
            listView.setAdapter(arrayAdapter);
            Intent intent=new Intent(this,recordActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initViews() {
        homebtn=(ImageButton) findViewById(R.id.homebutton);
        listView=(ListView) findViewById(R.id.recordList);
        clearData=(Button) findViewById(R.id.clearData_btn);
    }

    private void initData() {
    }

    private void setListener() {
        homebtn.setOnClickListener(new MyOnTouchListeners());
    }

    private final class MyOnTouchListeners implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(v.getContext(),MainActivity2.class);
            startActivity(intent);
            finish();
        }
    }

}