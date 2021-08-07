package com.example.mausam.Data;


import android.widget.ImageView;

import java.net.HttpURLConnection;

public class DaysData {
    String date,temperature,visibility,wind,humidity,status,ImageURL;
    public DaysData(String date,String temperature,String visibility,String wind,String humidity,String status,String ImageURL){
        this.date = date;
        this.temperature = temperature;
        this.visibility = visibility;
        this.wind = wind;
        this.humidity = humidity;
        this.status = status;
        this.ImageURL = ImageURL;
    }
    public String getDate(){
        return  date;
    }
    public void setDate(String Date){
        date = Date;
    }
    public String getTemperature(){
        return  temperature;
    }
    public void setTemperature(String Temperature){
        temperature = Temperature;
    }
    public String getVisibility(){
        return  visibility;
    }
    public void setVisibility(String Visibility){
        visibility = Visibility;
    }
    public String getWind(){
        return  wind;
    }
    public void setWind(String Wind){
        wind = Wind;
    }
    public String getHumidity(){
        return  humidity;
    }
    public void setHumidity(String Humidity){
        humidity = Humidity;
    }
    public String setStatus(){
        return  status;
    }
    public void setStaus(String Status){
        status = Status;
    }
    public String getImageURL(){
        return ImageURL;
    }
    public void setImageURL(String imageurl){
        ImageURL = imageurl;
    }

}
