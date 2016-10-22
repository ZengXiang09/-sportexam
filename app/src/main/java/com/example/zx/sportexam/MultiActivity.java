package com.example.zx.sportexam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.MultiAdapter;
import bean.DetailGradeBean;
import bean.SimpleGradeBean;
import util.URLUtils;

/**
 * Created by zx on 2016/10/12.
 */

public class MultiActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private Button btn_back;
    private TextView item_name;
    private String sportName ;//用来指示传递过来的项目名字
    private List<SimpleGradeBean> list;
    private RequestQueue mQueue = null;
    private String URL;
    private ArrayList<DetailGradeBean>detailList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.multi_layout);

        final Intent intent = getIntent();
        sportName = intent.getStringExtra("sport");
        if((!sportName.equals("manrun")) && (!sportName.equals("womanrun"))){
            URL = URLUtils.basicURL + "GetItemAllServlet?item=" + sportName;
        }else {
            URL = URLUtils.basicURL + "GetRaceAllServlet?item=" + sportName;
        }


        mQueue = Volley.newRequestQueue(this);

        initView();
        initEvent();
        setHead(sportName);
        new NewsAsyncTask().execute(URL);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SimpleGradeBean bean = list.get(i);
                if (sportName.equals("manrun") || sportName.equals("womanrun")){
                    sportName = "race";
                }
                String url = URLUtils.basicURL
                        + "PersonDetailServlet?no="
                        + bean.getNo()
                        +"&item="
                        +sportName;
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
                                Intent intent2 = new Intent(MultiActivity.this,DetailActivity.class);
                                intent2.putExtra("detailList",(Serializable) detailList);
                                intent2.putExtra("sport",sportName);
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
        });
    }

    public void setHead(String sportName){
        switch (sportName){
            case "football":item_name.setText("足球");break;
            case "basketball":item_name.setText("篮球");break;
            case "volleyball":item_name.setText("排球");break;
            case "situp":item_name.setText("仰卧起坐");break;
            case "manrun":item_name.setText("男子一千米");break;
            case "womanrun":item_name.setText("女子八百米");break;
            case "pullup":item_name.setText("引体向上");break;
            case "solidball":item_name.setText("实心球");break;
            default:break;

        }
    }

    private List<SimpleGradeBean> getJsonData(String url){
        list = new ArrayList<>();
        try {
            String jsonString = readStream(new URL(url).openStream());
            JSONObject object;
            SimpleGradeBean bean;
            object = new JSONObject(jsonString);
            JSONArray jsonArray = object.getJSONArray("data");


            for(int i = 0;i < jsonArray.length();i ++){
                object = jsonArray.getJSONObject(i);
                String no = object.getString("no");
                String score = object.getString("score");
                String time = object.getString("time");
                String name = object.getString("name");
                bean = new SimpleGradeBean(name,no,score,time);
                list.add(bean);
            }
            Log.d("json",jsonString);
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String readStream(InputStream is){
        InputStreamReader isr;
        String result = "";
        try {
            String line;
            isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null){
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.id_multi_lv);
        btn_back = (Button) findViewById(R.id.id_multi_back);
        item_name = (TextView) findViewById(R.id.id_multi_text_name);

    }
    private void initEvent() {
        btn_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_multi_back:
                Intent intent = new Intent(MultiActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }



    class NewsAsyncTask extends AsyncTask<String,Void,List<SimpleGradeBean>> {

        @Override
        protected List<SimpleGradeBean> doInBackground(String... strings) {

            return getJsonData(strings[0]);
        }

        @Override
        protected void onPostExecute(List<SimpleGradeBean> grades) {
            super.onPostExecute(grades);
            Log.e("data","12223131");
            MultiAdapter adapter = new MultiAdapter(MultiActivity.this,grades);
            listView.setAdapter(adapter);
        }
    }
}
