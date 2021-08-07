package com.example.mausam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class start_activity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    Animation top_animation,bottom_animation;
    ImageView imageView1,imageView2;
    TextView textView1,textView2;
    String place,region,temp,icon,con,feelslike,humidity,wind,preci,pressure,visibility,uv;
    Bitmap bmp; String[] temparature = new String[24];
    String[] time = new String[24]; String[] icon_hour = new String[24];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_activity);
        imageView1 = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        textView1 = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        top_animation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        imageView1.setAnimation(top_animation);
        imageView1.setAnimation(bottom_animation);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.weatherapi.com/v1/forecast.json?key=b2723b3e988f4f7a971122647201812&q=shimla&days=3", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject l = response.getJSONObject("location");
                    place = l.getString("name");
                    region = l.getString("region");
                    JSONObject current = response.getJSONObject("current");
                    temp = current.getString("temp_c");
                    feelslike = current.getString("feelslike_c");
                    humidity = current.getString("humidity")+"%";
                    wind = current.getString("wind_kph")+"km/h";
                    preci = current.getString("precip_mm")+"mm";
                    pressure = current.getString("pressure_mb")+"hpa";
                    visibility = current.getString("vis_km")+"km";
                    uv = current.getString("uv");
                    JSONObject condition = current.getJSONObject("condition");
                    icon = condition.getString("icon");
                    con = condition.getString("text");
                    JSONObject forecast = response.getJSONObject("forecast");
                    JSONArray forecastday = forecast.getJSONArray("forecastday");
                        JSONObject t = forecastday.getJSONObject(0);
                        JSONArray hour = t.getJSONArray("hour");
                        for(int j=0;j<24;j++) {
                            JSONObject s = hour.getJSONObject(j);
                            JSONObject icon = s.getJSONObject("condition");
                            String icon_url = icon.getString("icon");
                            icon_hour[j] = icon_url;
                            String temp_current = s.getString("temp_c") + "°C";
                            temparature[j] = temp_current;

                            String time_current = s.getString("time").substring(11);
                            time[j] = time_current;
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(start_activity.this,MainActivity.class);
                intent.putExtra("place",place);
                intent.putExtra("region",region);
                intent.putExtra("temp",temp);
                intent.putExtra("icon",icon);
                intent.putExtra("con",con);
                intent.putExtra("feelslike",feelslike);
                intent.putExtra("temparature",temparature);
                intent.putExtra("time",time);
                intent.putExtra("icon_url",icon_hour);
                intent.putExtra("humidity",humidity);
                intent.putExtra("precipitation",preci);
                intent.putExtra("pressure",pressure);
                intent.putExtra("visibility",visibility);
                intent.putExtra("uv",uv);
                intent.putExtra("wind",wind);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }


}