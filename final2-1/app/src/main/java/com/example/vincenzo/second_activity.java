package com.example.vincenzo;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapView;
import net.daum.mf.map.api.MapPoint;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.view.MenuItem;
public class second_activity extends AppCompatActivity implements View.OnClickListener{

    public String name, address, distance_str, distance, rush_ratio1;
    public Double latitude, longitude, rush_ratio;
    public int rush_level;

    //리사이클러뷰 구현
    private RecyclerView second_recyclerView;
    private SecondRecyclerAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context sContext;
    private TextView textView, RushRatio_View, address_textview, Distance_View;
    private ArrayList<String> mMyData = new ArrayList<>();
    private GpsTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gpsTracker = new GpsTracker(this);

        textView = findViewById(R.id.cafename_view);
        RushRatio_View = findViewById(R.id.RushRatio);
        address_textview = findViewById(R.id.address);
        Distance_View = findViewById(R.id.distance);
        second_recyclerView = findViewById(R.id.second_recyclerView);
        second_recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        second_recyclerView.setLayoutManager(layoutManager);
        adapter = new SecondRecyclerAdapter(mMyData);
        second_recyclerView.setAdapter(adapter);


        Intent intent = getIntent();
        name = intent.getExtras().getString("name");
        address = intent.getExtras().getString("address");
        latitude = intent.getExtras().getDouble("latitude");
        longitude = intent.getExtras().getDouble("longitude");
        rush_ratio = intent.getExtras().getDouble("rush_ratio");
        rush_level = intent.getExtras().getInt("rush_level");
        //rushlevel = 3;
        distance = intent.getExtras().getString("distance");
        //distance_str = Double.toString(distance);

        mMyData.add(name);
        mMyData.add(name);
        mMyData.add(name);
        mMyData.add(name);
        mMyData.add(name);

        adapter.notifyDataSetChanged();

        textView.setText(name);
        address_textview.setText(address);

        rush_ratio1 = String.valueOf(rush_ratio);
        RushRatio_View.setText(rush_ratio1 + "%");
        if(rush_level == 1){
            RushRatio_View.setBackgroundResource(R.drawable.light_blue);
            RushRatio_View.setTextColor(Color.parseColor("#5D5FEF"));
        }else if(rush_level == 2){
            RushRatio_View.setBackgroundResource(R.drawable.light_green);
            RushRatio_View.setTextColor(Color.parseColor("#51F08A"));
        }else if(rush_level == 3){
            RushRatio_View.setBackgroundResource(R.drawable.light_yellow);
            RushRatio_View.setTextColor(Color.parseColor("#F0D046"));
        }else if(rush_level == 4){
            RushRatio_View.setBackgroundResource(R.drawable.light_red);
            RushRatio_View.setTextColor(Color.parseColor("#F07A75"));
        }else{
            RushRatio_View.setBackgroundResource(R.drawable.ic_colorchange);
            RushRatio_View.setTextColor(Color.parseColor("#808080"));
        }

        Location myLocation = new Location("point A");
        Location cafeLocation = new Location("point B");
        myLocation.setLatitude(gpsTracker.getLatitude());
        myLocation.setLongitude(gpsTracker.getLongitude());
        cafeLocation.setLatitude(latitude);
        cafeLocation.setLongitude(longitude);
        String distance_str = String.format("%.2f",myLocation.distanceTo(cafeLocation)/1000);
        Distance_View.setText(distance_str+"km");

        //뒤로가기
        Button back_btn = (Button)findViewById(R.id.back_button);
        back_btn.setOnClickListener(this);


        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
        // 줌 레벨 변경
        //mapView.setZoomLevel(7, true);
        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude,longitude), 1, true);
        //줌 레벨이 낮을수록 첫 화면에서 확대되어 보임!
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(latitude, longitude);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("Default Marker");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);

    }

    //뒤로가기
    @Override
    public void onClick(View view) {
        finish();
    }
}