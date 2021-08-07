package com.example.mausam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class HourDetailsActivity extends AppCompatActivity {
     TextView time,temp,text,pressure,preci,feelslike,heatindex,humidity,wind,visibility;
     ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_details);
        Intent intent = getIntent();
        int po = intent.getIntExtra("position",0);
        time = (TextView) findViewById(R.id.time);
        temp = (TextView) findViewById(R.id.temperature);
        text = (TextView) findViewById(R.id.status);
        icon = (ImageView) findViewById(R.id.iconhour);
        pressure = (TextView) findViewById(R.id.pressure1);
        preci = (TextView) findViewById(R.id.prcipitation1);
        feelslike = (TextView) findViewById(R.id.feelslike);
        heatindex = (TextView) findViewById(R.id.uv1);
        humidity = (TextView) findViewById(R.id.humidity1);
        wind = (TextView) findViewById(R.id.wind1);
        visibility = (TextView)findViewById(R.id.visibility1);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://api.weatherapi.com/v1/forecast.json?key=b2723b3e988f4f7a971122647201812&q=shimla&days=3", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject  forecast = response.getJSONObject("forecast");
                    JSONArray forecastday = forecast.getJSONArray("forecastday");
                    JSONObject day = forecastday.getJSONObject(0);
                    JSONArray hour = day.getJSONArray("hour");
                    JSONObject hourdata = hour.getJSONObject(po);
                    time.setText(hourdata.getString("time").substring(11));
                    temp.setText(hourdata.getString("temp_c")+"°C");
                    JSONObject condition = hourdata.getJSONObject("condition");
                    text.setText(condition.getString("text"));
                    Glide.with(getApplicationContext()).load("https:"+condition.getString("icon")).into(icon);
                    pressure.setText(hourdata.getString("pressure_mb"));
                    preci.setText(hourdata.getString("precip_mm"));
                    feelslike.setText(hourdata.getString("feelslike_c")+"°C");
                    heatindex.setText(hourdata.getString("heatindex_c")+"°C"); ;
                    visibility.setText(hourdata.getString("vis_km")+"km");
                    humidity.setText(hourdata.getString("humidity")+"%");
                    wind.setText(hourdata.getString("wind_kph")+"km/h");

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


    }
}
