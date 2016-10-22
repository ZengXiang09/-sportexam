package com.example.zx.sportexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by zx on 2016/10/9.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    private View view;
    private ImageButton btn_football,btn_volleyball,btn_basketball,btn_pullup,btn_situp,btn_manrun,btn_solidball,btn_womanrun;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_home, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        btn_football = (ImageButton) view.findViewById(R.id.id_btn_football);
        btn_basketball = (ImageButton) view.findViewById(R.id.id_btn_basketball);
        btn_pullup = (ImageButton) view.findViewById(R.id.id_btn_pullup);
        btn_volleyball = (ImageButton) view.findViewById(R.id.id_btn_volleyball);
        btn_situp = (ImageButton) view.findViewById(R.id.id_btn_situp);
        btn_manrun = (ImageButton) view.findViewById(R.id.id_btn_manrun);
        btn_solidball = (ImageButton) view.findViewById(R.id.id_btn_solidball);
        btn_womanrun = (ImageButton) view.findViewById(R.id.id_btn_womenrun);
    }
    private  void initEvent(){
        btn_football.setOnClickListener(this);
        btn_volleyball.setOnClickListener(this);
        btn_womanrun.setOnClickListener(this);
        btn_solidball.setOnClickListener(this);
        btn_situp.setOnClickListener(this);
        btn_basketball.setOnClickListener(this);
        btn_manrun.setOnClickListener(this);
        btn_pullup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.context,MultiActivity.class);
        switch (view.getId()){
            case R.id.id_btn_football:
            {
                intent.putExtra("sport","football");
                break;
            }
            case R.id.id_btn_basketball:
            {
                intent.putExtra("sport","basketball");
                break;
            }
            case R.id.id_btn_pullup:
            {
                intent.putExtra("sport","pullup");
                break;
            }
            case R.id.id_btn_volleyball:
            {
                intent.putExtra("sport","volleyball");
                break;
            }
            case R.id.id_btn_manrun:
            {
                intent.putExtra("sport","manrun");
                break;
            }
            case R.id.id_btn_solidball:
            {
                intent.putExtra("sport","solidball");
                break;
            }
            case R.id.id_btn_womenrun:
            {
                intent.putExtra("sport","womanrun");
                break;
            }
            case R.id.id_btn_situp:
            {
                intent.putExtra("sport","situp");
                break;
            }
            default:
                break;
        }
        startActivity(intent);

    }
}
