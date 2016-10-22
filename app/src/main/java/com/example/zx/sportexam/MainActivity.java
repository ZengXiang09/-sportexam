package com.example.zx.sportexam;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int page = 0;
    private LinearLayout mTabHomeLy;
    private LinearLayout mTabCheckLy;
    private LinearLayout mtabStandardLy;

    private Fragment mTabHome;
    private Fragment mTabCheck;
    private Fragment mTabStandard;

    private ImageButton mImageBtnHome;
    private ImageButton mImageBtnCheck;
    private ImageButton mImageBtnStandard;

    public static Context context = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        initView();
        initEvent();
        String x = getIntent().getStringExtra("page");
        if ((x!= null)&&(!x.equals(""))){
            page = Integer.parseInt(x);
        }
        setSelect(page);
    }

    private void initEvent() {
        mTabHomeLy.setOnClickListener(this);
        mTabCheckLy.setOnClickListener(this);
        mtabStandardLy.setOnClickListener(this);
    }

    private void initView() {
        mTabHomeLy = (LinearLayout) findViewById(R.id.id_tab_home);
        mTabCheckLy = (LinearLayout) findViewById(R.id.id_tab_check);
        mtabStandardLy = (LinearLayout) findViewById(R.id.id_tab_standard);

        mImageBtnHome = (ImageButton) findViewById(R.id.id_tab_home_image);
        mImageBtnCheck = (ImageButton) findViewById(R.id.id_tab_check_image);
        mImageBtnStandard = (ImageButton) findViewById(R.id.id_tab_standard_image);

    }

    private void setSelect(int i){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i)
        {
            case 0:
                if (mTabHome == null)
                {
                    mTabHome = new HomeFragment();
                    transaction.add(R.id.id_content, mTabHome);
                } else
                {
                    transaction.show(mTabHome);
                }
                mImageBtnHome.setImageResource(R.drawable.home_pressed);
                break;
            case 1:
                if (mTabCheck == null)
                {
                    mTabCheck = new CheckFragment();
                    transaction.add(R.id.id_content, mTabCheck);
                } else
                {
                    transaction.show(mTabCheck);
                }
                mImageBtnCheck.setImageResource(R.drawable.check_pressed);
                break;
            case 2:
                if (mTabStandard == null)
                {
                    mTabStandard = new StandardFragment();
                    transaction.add(R.id.id_content, mTabStandard);
                } else
                {
                    transaction.show(mTabStandard);
                }
                mImageBtnStandard.setImageResource(R.drawable.standard_pressed);
                break;
            default:
                break;
        }

        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mTabHome != null){
            transaction.hide(mTabHome);
        }
        if (mTabCheck != null){
            transaction.hide(mTabCheck);
        }
        if (mTabStandard != null){
            transaction.hide(mTabStandard);
        }
    }

    @Override
    public void onClick(View view) {
        resetImages();
        switch (view.getId())
        {
            case R.id.id_tab_home:
                setSelect(0);
                break;
            case R.id.id_tab_check:
                setSelect(1);
                break;
            case R.id.id_tab_standard:
                setSelect(2);
                break;
            default:
                break;
        }
    }

    private void resetImages() {
        mImageBtnStandard.setImageResource(R.drawable.standard_normal);
        mImageBtnHome.setImageResource(R.drawable.home_normal);
        mImageBtnCheck.setImageResource(R.drawable.check_normal);
    }
}
