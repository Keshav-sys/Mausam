package com.example.mausam.Data;

public class HourData {
    String time,temp;
    String ImageUrl;


    public HourData(String temp, String time, String ImageUrl){
        this.temp = temp;
        this.time = time;
        this.ImageUrl = ImageUrl;
    }
    public String getImageUrl() {
        return ImageUrl;
    }
    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
