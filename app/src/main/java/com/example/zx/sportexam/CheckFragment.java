package com.example.zx.sportexam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import bean.ItemBean;
import bean.PersonGradeBean;
import util.ToastUtils;
import util.URLUtils;

/**
 * Created by zx on 2016/10/9.
 */

public class CheckFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText checkName,checkNum;
    private Button btnCheck,btnClear;
    private RequestQueue mQueue;
    private String url,name,no;
    private Context context;
    private ArrayList<ItemBean> list;
    private PersonGradeBean bean;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_check, container, false);
        context = MainActivity.context;
        mQueue = Volley.newRequestQueue(context);
        list = new ArrayList<>();

        initView();
        initEvent();
        checkNum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_ENTER){
                    no = checkNum.getText().toString().trim();
                    requestMsgByNo();
                }
                return true;
            }
        });
        checkName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == keyEvent.KEYCODE_ENTER){
                    name = checkName.getText().toString().trim();
                    try {
                        requestMsgByName();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });




        return view;
    }

    private void initEvent() {
        btnCheck.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    public void initView() {
        checkName = (EditText) view.findViewById(R.id.id_text_name);
        checkNum = (EditText) view.findViewById(R.id.id_text_num);
        btnCheck = (Button) view.findViewById(R.id.id_btn_check);
        btnClear = (Button) view.findViewById(R.id.id_btn_clear);
    }

    public void requestMsgByNo(){
        url = URLUtils.basicURL
                + "GetPeronGradeByNoServlet?no="
                + no;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                        public void onResponse(JSONObject response) {
                        try {
                            //如果能够查询到这个人
                            if (!response.getString("no").equals("-1")){
                                Log.e("list","11111");
                                Log.e("data",response.toString());
                                String no = response.getString("no");
                                String name = response.getString("name");
                                String sex = response.getString("sex");
                                String school = response.getString("school");
                                String birthday = response.getString("birthday");

                                JSONArray array = response.getJSONArray("data");
                                for (int i = 0;i < array.length();i ++){
                                    JSONObject temp = array.getJSONObject(i);
                                    String score = temp.getString("score");
                                    String item = temp.getString("item");
                                    ItemBean itemBean = new ItemBean(item,score);
                                    list.add(itemBean);
                                }
                                bean  = new PersonGradeBean(birthday,no,list,school,sex,name);

                                Intent intent = new Intent(context,PersonActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("bean",bean);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }else {
                                ToastUtils. makeLongText("请输入正确的考号",context);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }

    public void requestMsgByName() throws UnsupportedEncodingException {
        String s = URLEncoder.encode(name,"utf-8");
        url = URLUtils.basicURL
                + "GetPeronGradeByNameServlet?name="
                + s;
        Log.e("url",url);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //如果能够查询到这个人
                            if (!response.getString("no").equals("-1")){
                                Log.e("list","11111");
                                String no = response.getString("no");
                                String name = response.getString("name");
                                String sex = response.getString("sex");
                                String school = response.getString("school");
                                String birthday = response.getString("birthday");

                                JSONArray array = response.getJSONArray("data");
                                for (int i = 0;i < array.length();i ++){
                                    JSONObject temp = array.getJSONObject(i);
                                    String score = temp.getString("score");
                                    String item = temp.getString("item");
                                    ItemBean itemBean = new ItemBean(item,score);
                                    list.add(itemBean);
                                }
                                bean  = new PersonGradeBean(birthday,no,list,school,sex,name);

                                Intent intent = new Intent(context,PersonActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("bean",bean);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            }else {
                                ToastUtils. makeLongText("请输入正确的姓名",context);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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
            case R.id.id_btn_check:
            {
                name = checkName.getText().toString();
                no = checkNum.getText().toString();
                if ((!no.equals("")) && (name.equals(""))) {
                    requestMsgByNo();
                }else if (no.equals("") && (!name.equals(""))){
                    Log.e("name",name);
                    try {
                        requestMsgByName();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else {
                    ToastUtils.makeLongText("输入姓名或者学号",context);
                }


                break;
            }
            case R.id.id_btn_clear:
            {
                Log.e("btn","click");
                checkName.setText("");
                checkNum.setText("");
                break;
            }
            default:break;
        }
    }
}
