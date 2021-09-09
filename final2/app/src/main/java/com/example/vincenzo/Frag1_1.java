package com.example.vincenzo;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Frag1_1 extends Fragment {
    private View view;

    //리사이클러뷰 구현
    private RecyclerView recyclerView1;
    private CafeRecyclerAdapter adapter_cafe;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList mMyData;

    //현재위치정보
    private GpsTracker gpsTracker;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private double user_latitude;
    private double user_longitude;

    private String urlStr;

    //spinner 배열
    private static final  String[] spinner_items = {"혼잡도순", "거리순"};

    private SwipeRefreshLayout swipeRefreshLayout;

    private ImageButton search;
    private ImageButton xbutton;
    private EditText editText;

    private ImageButton mapview1;

    public Double rush_ratio;
    public int rush_level;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1_1, container, false);
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView1 = view.findViewById(R.id.recyclerView1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Frag1_1.this.getActivity(), 2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        recyclerView1.setHasFixedSize(true);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        mMyData = new ArrayList<>();

        adapter_cafe = new CafeRecyclerAdapter(this.getContext());
        recyclerView1.setAdapter(adapter_cafe);

        gpsTracker = new GpsTracker(Frag1_1.this.getActivity());


        Spinner spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getActivity(), R.layout.spinner_layout, spinner_items
        );

        // initiate and perform click event on button's
        ImageButton search = (ImageButton) view.findViewById(R.id.imageView13);
        ImageButton xbutton = (ImageButton) view.findViewById(R.id.imageView15);
        EditText editText = (EditText) view.findViewById(R.id.edittext);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);
                xbutton.setVisibility(View.VISIBLE);
            }
        });
        xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setVisibility(View.INVISIBLE);
                xbutton.setVisibility(View.INVISIBLE);
            }
        });



                adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            //선택되면
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT);
                if(spinner.getSelectedItem().toString() == "혼잡도순"){
                    user_latitude = gpsTracker.getLatitude();
                    user_longitude = gpsTracker.getLongitude();
                    urlStr = "http://3.34.139.5/jsonprint9.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
                    adapter_cafe.clearAllItems();
                    ThreadProc(urlStr);

                }else if(spinner.getSelectedItem().toString() == "거리순"){
                    user_latitude = gpsTracker.getLatitude();
                    user_longitude = gpsTracker.getLongitude();
                    urlStr = "http://3.34.139.5/jsonprint7.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
                    adapter_cafe.clearAllItems();
                    ThreadProc(urlStr);

                }
            }

            //아무것도 선택되지 않은 상태일 때
            @Override
            public void onNothingSelected(AdapterView<?> parent){
                spinner.setSelection(0);
                user_latitude = gpsTracker.getLatitude();
                user_longitude = gpsTracker.getLongitude();
                urlStr = "http://3.34.139.5/jsonprint9.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
                ThreadProc(urlStr);

            }
        });

        //새로고침
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gpsTracker = new GpsTracker(Frag1_1.this.getActivity());
                
                if(spinner.getSelectedItem().toString() == "혼잡도순"){
                            user_latitude = gpsTracker.getLatitude();
                            user_longitude = gpsTracker.getLongitude();
                            urlStr = "http://3.34.139.5/jsonprint9.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
                            adapter_cafe.clearAllItems();
                            ThreadProc(urlStr);

                }else if(spinner.getSelectedItem().toString() == "거리순") {
                    user_latitude = gpsTracker.getLatitude();
                    user_longitude = gpsTracker.getLongitude();
                    urlStr = "http://3.34.139.5/jsonprint7.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
                    adapter_cafe.clearAllItems();
                    ThreadProc(urlStr);

                }else{
                        spinner.setSelection(0);
                        user_latitude = gpsTracker.getLatitude();
                        user_longitude = gpsTracker.getLongitude();
                        urlStr = "http://3.34.139.5/jsonprint9.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
                        ThreadProc(urlStr);

                    }

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ThreadProc(urlStr);

        //위치정보 받기
        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }

//        TextView ShowLocationButton = (TextView) view.findViewById(R.id.textView3);
//        ShowLocationButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View arg0)
//            {
//
//                gpsTracker = new GpsTracker(Frag1_1.this.getActivity());
//
//                user_latitude = gpsTracker.getLatitude();
//                user_longitude = gpsTracker.getLongitude();
//
////                String address = getCurrentAddress(latitude, longitude);
////                textview_address.setText(address);
//
//                Toast.makeText(Frag1_1.this.getActivity(), "현재위치 \n위도 " + user_latitude + "\n경도 " + user_longitude, Toast.LENGTH_LONG).show();
//
//            }
//        });


        return view;
    }

    /*
     * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
     */
    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {
        if (permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if (check_result) {

                //위치 값을 가져올 수 있음
                ;
            } else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(Frag1_1.this.getActivity(), REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(Frag1_1.this.getActivity(), REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(Frag1_1.this.getActivity(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    this.getActivity().finish();  ///이게 맞나...

                } else {

                    Toast.makeText(Frag1_1.this.getActivity(), "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }



    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(Frag1_1.this.getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(Frag1_1.this.getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(Frag1_1.this.getActivity(), REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(Frag1_1.this.getActivity(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(Frag1_1.this.getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(Frag1_1.this.getActivity(), REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }

    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Frag1_1.this.getActivity());
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    private void ThreadProc(String urlStr) {

//        gpsTracker = new GpsTracker(Frag1_1.this.getActivity());
//
//        double user_latitude = gpsTracker.getLatitude();
//        double user_longitude = gpsTracker.getLongitude();

        new Thread() {
            @Override
            public void run() {
                //superrun();
                String str,receiveMsg = "";

                //String urlStr = "http://3.35.138.25/jsonprint7.php?user_latitude=" + user_latitude + "&user_longitude=" + user_longitude;
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
                cafe.setAddress(cafeObj.getString("address"));
                cafe.setLatitude(cafeObj.getDouble("latitude"));
                cafe.setLongitude(cafeObj.getDouble("longitude"));
                cafe.setRushLevel(cafeObj.optInt("rush_level"));
                cafe.setRushRatio(cafeObj.optDouble("rush_ratio"));

//                cafe.setAddress(cafeObj.getString("address"));
//                cafe.setSize(cafeObj.getString("size"));
                // BookRecyclerAdapter에 Book
                //adapter.addItem(cafe);
                adapter_cafe.addItem(cafe);

            }


            adapter_cafe.notifyDataSetChanged();



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
