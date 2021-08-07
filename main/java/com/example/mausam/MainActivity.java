package com.example.mausam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mausam.Adapter.HourListAdapter;
import com.example.mausam.Data.HourData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView place,temperature,con,feelslike,humidity,precipitation,visibility,uv,pressure,wind;
    ImageView image,icon;
    Bitmap bmp; String URL;
    RecyclerView hourRecycler;
    HourListAdapter hourAdapter;
    String[] temparature_current = new String[24];
    String[] time_current = new String[24];
    String icon_hour[] = new String[24];
    Button nextdays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        place = (TextView)findViewById(R.id.textView3);
        temperature = (TextView)findViewById(R.id.textView6);
        image = (ImageView)findViewById(R.id.imageView3);
        icon = (ImageView)findViewById(R.id.imageView3);
        con = (TextView)findViewById(R.id.textView5);
        feelslike = (TextView)findViewById(R.id.feelslike);
        humidity = (TextView)findViewById(R.id.humidity);
        precipitation = (TextView)findViewById(R.id.prcipitation);
        visibility = (TextView)findViewById(R.id.visibility);
        pressure = (TextView)findViewById(R.id.pressure);
        wind=(TextView)findViewById(R.id.wind);
        uv = (TextView)findViewById(R.id.uv);
        nextdays = (Button)findViewById(R.id.button);
        nextdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DaysDetailsActivity.class);
                startActivity(i);
            }
        });
         Intent intent = getIntent();
         wind.setText(intent.getStringExtra("wind"));
         humidity.setText(intent.getStringExtra("humidity"));
         precipitation.setText(intent.getStringExtra("precipitation"));
         visibility.setText(intent.getStringExtra("visibility"));
         pressure.setText(intent.getStringExtra("pressure"));
         uv.setText(intent.getStringExtra("uv"));
        place.setText(intent.getStringExtra("place")+", "+intent.getStringExtra("region"));
        temperature.setText(intent.getStringExtra("temp"));
        con.setText(intent.getStringExtra("con"));
        URL = intent.getStringExtra("icon");
        feelslike.setText("Feels like "+intent.getStringExtra("feelslike")+"°C");
        Glide.with(this).load("https:"+URL).into(icon);
        List<HourData> hourDataList = new ArrayList<HourData>();
        temparature_current = intent.getStringArrayExtra("temparature");
        time_current = intent.getStringArrayExtra("time");
        icon_hour = intent.getStringArrayExtra("icon_url");
        for(int i=0;i<24;i++){

            HourData hour_data = new HourData(temparature_current[i],time_current[i],icon_hour[i]);
            hourDataList.add(hour_data);
        }
        setHourRecycler(hourDataList);



    }
    private void setHourRecycler(List<HourData> hourDataList){
       hourRecycler = findViewById(R.id.hour_recycler);
       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
       hourRecycler.setLayoutManager(layoutManager);
       hourAdapter = new HourListAdapter(this,hourDataList);
       hourRecycler.setAdapter(hourAdapter);
    }


}
