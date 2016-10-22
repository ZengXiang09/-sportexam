package com.example.zx.sportexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bean.DetailGradeBean;

/**
 * Created by zx on 2016/10/16.
 */

public class DetailActivity extends AppCompatActivity {
    private LinearLayout firstLy,secondLy,thirdLy;
    private TextView firstTime,firstFoul,firstGrade,secondTime,secondFoul,secondGrade,thirdTime,thirdFoul,thirdGrade;
    private Button btnBack;
    private ArrayList<DetailGradeBean> list;
    private String sportName = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.detail_layout);
        list = (ArrayList<DetailGradeBean>) getIntent().getSerializableExtra("detailList");//取到前一个MultiActivity传来的值
        sportName = getIntent().getStringExtra("sport");
        Log.e("sport",sportName);

        Log.e("list",list.size()+" ");
        initView();
        setAllVisable();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this,MultiActivity.class);
                intent.putExtra("sport",sportName);

                startActivity(intent);
            }
        });


        switch (list.size()){
            case 1:
            {
                firstTime.setText(list.get(0).getDate());
                firstGrade.setText(list.get(0).getScore());
                firstFoul.setText(list.get(0).getFoul());

                secondLy.setVisibility(View.INVISIBLE);
                thirdLy.setVisibility(View.INVISIBLE);
                break;
            }
            case 2:
            {
                firstTime.setText(list.get(0).getDate());
                firstGrade.setText(list.get(0).getScore());
                firstFoul.setText(list.get(0).getFoul());

                secondTime.setText(list.get(1).getDate());
                secondGrade.setText(list.get(1).getScore());
                secondFoul.setText(list.get(1).getFoul());

                thirdLy.setVisibility(View.INVISIBLE);
                break;
            }
            case 3:
            {
                firstTime.setText(list.get(0).getDate());
                firstGrade.setText(list.get(0).getScore());
                firstFoul.setText(list.get(0).getFoul());

                secondTime.setText(list.get(1).getDate());
                secondGrade.setText(list.get(1).getScore());
                secondFoul.setText(list.get(1).getFoul());

                thirdTime.setText(list.get(2).getDate());
                thirdGrade.setText(list.get(2).getScore());
                thirdFoul.setText(list.get(2).getFoul());
                break;
            }
            default:
                break;
        }



    }

    private void initView() {
        firstLy = (LinearLayout) findViewById(R.id.id_first_ly);
        firstTime = (TextView) findViewById(R.id.id_first_time);
        firstGrade = (TextView) findViewById(R.id.id_first_grade);
        firstFoul = (TextView) findViewById(R.id.id_first_foul);

        secondLy = (LinearLayout) findViewById(R.id.id_second_ly);
        secondTime = (TextView) findViewById(R.id.id_second_time);
        secondGrade  = (TextView) findViewById(R.id.id_second_grade);
        secondFoul = (TextView) findViewById(R.id.id_second_foul);

        thirdLy = (LinearLayout) findViewById(R.id.id_third_ly);
        thirdTime = (TextView) findViewById(R.id.id_third_time);
        thirdGrade = (TextView) findViewById(R.id.id_third_grade);
        thirdFoul = (TextView) findViewById(R.id.id_third_foul);

        btnBack = (Button) findViewById(R.id.id_btn_back);
    }

    private void setAllVisable(){
        firstLy.setVisibility(View.VISIBLE);
        secondLy.setVisibility(View.VISIBLE);
        thirdLy.setVisibility(View.VISIBLE);
    }
}
