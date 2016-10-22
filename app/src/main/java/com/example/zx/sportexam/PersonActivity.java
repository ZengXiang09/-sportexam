package com.example.zx.sportexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bean.DetailGradeBean;
import bean.ItemBean;
import bean.PersonGradeBean;
import util.URLUtils;

/**
 * Created by zx on 2016/10/16.
 */

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView name,sex,no,school,birthday,item1,item2,item3,grade1,grade2,grade3;
    private Button back;
    private ImageView img;
    private RelativeLayout item1_ly,item2_ly,item3_ly;
    private List<DetailGradeBean>detailList = null;
    private RequestQueue mQueue = null;
    private  PersonGradeBean bean = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.person_layout);
        mQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        bean = (PersonGradeBean) intent.getSerializableExtra("bean");
        initView();
        initEvent();
        setText(bean);



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PersonActivity.this,MainActivity.class);
        intent.putExtra("page","1");
        startActivity(intent);

    }

    public String transplant(String item, String sex){
        String res="";
        switch (item){
            case "race":
            {
                if (sex.equals("男")){
                    res = "男子一千米";
                }else {
                    res = "女子八百米";
                }
                break;
            }
            case "basketball":res = "篮球";break;
            case "volleyball":res = "排球";break;
            case "football":res = "足球";break;
            case "pullup":res = "引体向上";break;
            case "solidball":res = "实心球";break;
            case "situp":res = "仰卧起坐";break;
            default:break;
        }
        return res;
    }

    public void setText(PersonGradeBean bean){
        name.setText(bean.getName());
        sex.setText(bean.getSex());
        school.setText(bean.getSchool());
        no.setText(bean.getNo());
        birthday.setText(bean.getBirthday());
        ArrayList<ItemBean> list = bean.getData();

        item1.setText(transplant(list.get(0).getItem(),bean.getSex()));
        if (list.get(0).getScore().equals("-1"))
            grade1.setText("未考");
        else
            grade1.setText(list.get(0).getScore());


        item2.setText(transplant(list.get(1).getItem(),bean.getSex()));
        if (list.get(1).getScore().equals("-1"))
            grade2.setText("未考");
        else
            grade2.setText(list.get(1).getScore());


        item3.setText(transplant(list.get(2).getItem(),bean.getSex()));
        Log.e("data",list.get(2).getScore());
        if (list.get(2).getScore().equals("-1"))
            grade3.setText("未考");
        else
        grade3.setText(list.get(2).getScore());




    }

    private void initView() {
        name = (TextView) findViewById(R.id.id_person_name);
        sex = (TextView) findViewById(R.id.id_person_sex);
        no = (TextView) findViewById(R.id.id_person_num);
        school = (TextView) findViewById(R.id.id_person_school);
        birthday = (TextView) findViewById(R.id.id_person_birthday);
        item1 = (TextView) findViewById(R.id.id_person_item1);
        item2 = (TextView) findViewById(R.id.id_person_item2);
        item3 = (TextView) findViewById(R.id.id_person_item3);
        grade1 = (TextView) findViewById(R.id.id_person_grade1);
        grade2 = (TextView) findViewById(R.id.id_person_grade2);
        grade3 = (TextView) findViewById(R.id.id_person_grade3);
        back = (Button) findViewById(R.id.id_person_back);
        img = (ImageView) findViewById(R.id.id_person_img);
        item1_ly = (RelativeLayout) findViewById(R.id.id_person_item1_ly);
        item2_ly = (RelativeLayout) findViewById(R.id.id_person_item2_ly);
        item3_ly = (RelativeLayout) findViewById(R.id.id_person_item3_ly);


    }

    public void initEvent(){
        back.setOnClickListener(this);
        item1_ly.setOnClickListener(this);
        item2_ly.setOnClickListener(this);
        item3_ly.setOnClickListener(this);
    }
    public void checkDetail(final String no,final String item){
        String url = URLUtils.basicURL
                + "PersonDetailServlet?no="
                + no
                +"&item="
                + item;
        Log.e("url",url);
        detailList = new ArrayList<DetailGradeBean>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("data",response.toString());
                        try {
                            JSONArray array = response.getJSONArray("data");
                            for (int i = 0;i < array.length();i ++){
                                JSONObject temp = array.getJSONObject(i);
                                String no = temp.getString("no");
                                String score = temp.getString("score");
                                String num = temp.getString("num");
                                String foul = temp.getString("foul");
                                String time = temp.getString("time");
                                Log.e("add","111111");
                                DetailGradeBean detailGradeBean = new DetailGradeBean(no,num,time,score,foul);
                                Log.e("add","222222");
                                detailList.add(detailGradeBean);
                                Log.e("add","success");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception ex){
                            ex.printStackTrace();;
                        }
                        Intent intent2 = new Intent(PersonActivity.this,DetailActivity.class);
                        intent2.putExtra("detailList",(Serializable) detailList);
                        intent2.putExtra("sport",item);
                        startActivity(intent2);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_person_back:
            {
                Intent intent = new Intent(PersonActivity.this,MainActivity.class);
                intent.putExtra("page","1");
                startActivity(intent);
                break;
            }
            case R.id.id_person_item1_ly:
            {
                String item = bean.getData().get(0).getItem();
                checkDetail(bean.getNo(),item);

                break;
            }
            case R.id.id_person_item2_ly:
            {
                String item = bean.getData().get(1).getItem();
                checkDetail(bean.getNo(),item);

                break;
            }
            case R.id.id_person_item3_ly:
            {
                String item = bean.getData().get(2).getItem();
                checkDetail(bean.getNo(),item);
                break;
            }
            default:break;
        }
    }
}
