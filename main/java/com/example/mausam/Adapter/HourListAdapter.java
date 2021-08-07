package com.example.mausam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mausam.Data.HourData;
import com.example.mausam.HourDetailsActivity;
import com.example.mausam.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HourListAdapter extends RecyclerView.Adapter<HourListAdapter.HourViewHolder> {
    Context context;
    ArrayList<HourData> hourDataList = new ArrayList<HourData>();
    public HourListAdapter(Context context,List<HourData> hourDataList){
        this.context = context;
        this.hourDataList = (ArrayList<HourData>) hourDataList;
    }
    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hour_data_item,parent,false);
        return new HourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        holder.temp.setText(hourDataList.get(position).getTemp());
        holder.time.setText(hourDataList.get(position).getTime());
        Glide.with(holder.itemView.getContext()).load("https:"+hourDataList.get(position).getImageUrl()).into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, HourDetailsActivity.class);
                i.putExtra("position",position);
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return hourDataList.size();
    }
    public static final class HourViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView temp,time;
        public HourViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            temp = itemView.findViewById(R.id.temp);
            time = itemView.findViewById(R.id.time);
        }

    }
}
