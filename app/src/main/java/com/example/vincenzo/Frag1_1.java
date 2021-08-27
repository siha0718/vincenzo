package com.example.vincenzo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.widget.ImageView;

import android.graphics.drawable.Drawable;



public class Frag1_1 extends Fragment {
    private View view;
    private RecyclerView recyclerView1;
    private CafeRecyclerAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mMyData;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag1_1,container,false);


        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setHasFixedSize(true);

        adapter = new CafeRecyclerAdapter();
        recyclerView1.setAdapter(adapter);

        ThreadProc();




        return view;
    }


    private void ThreadProc() {

        new Thread() {
            @Override
            public void run() {
                //superrun();
                String str,receiveMsg = "";

                String urlStr = "http://3.36.111.114/jsonprint4.php";
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    //conn.setRequestProperty("x-waple-authorization", clientKey);
                    if (conn.getResponseCode() == conn.HTTP_OK) {
                        InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuffer buffer = new StringBuffer();
                        while ((str = reader.readLine()) != null) {
                            buffer.append(str);
                        }
                        receiveMsg = buffer.toString();
                        //Log.i("receiveMsg : ", receiveMsg);
                        reader.close();

                        Bundle bun = new Bundle();
                        bun.putString("jsonGap",receiveMsg);
                        Message msg = handler.obtainMessage();
                        msg.setData(bun);
                        handler.sendMessage(msg);

                    } else {
                        Log.i("통신 결과", conn.getResponseCode() + "에러");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void jsonParsing(String json) {




        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray cafeArr = jsonObject.getJSONArray("cafe");

            for (int i=0;i<cafeArr.length();i++) {
                JSONObject cafeObj = cafeArr.getJSONObject(i);

                cafe cafe = new cafe();

                cafe.setName(cafeObj.getString("name"));
                cafe.setResId(cafe.getResId());


//                cafe.setAddress(cafeObj.getString("address"));
//                cafe.setSize(cafeObj.getString("size"));
//                cafe.setLatitude(cafeObj.getString("latitude"));
//                cafe.setLongitude(cafeObj.getString("longitude"));
                // BookRecyclerAdapter에 Book
                adapter.addItem(cafe);
            }

            adapter.notifyDataSetChanged();


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON Parsing Error",e.getMessage());
        }
    }

    /*
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            Bundle bun = msg.getData();
            String str = bun.getString("jsonGap");

            jsonParsing(str);
        }
    };
     */
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Bundle bun = msg.getData();
            String str = bun.getString("jsonGap");

            jsonParsing(str);
            return true;
        }
    });

}
