package com.example.mausam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mausam.Data.DaysData;
import com.example.mausam.Data.HourData;
import com.example.mausam.DaysDetailsActivity;
import com.example.mausam.R;

import java.util.ArrayList;
import java.util.List;

public class DaysListAdapter extends RecyclerView.Adapter<DaysListAdapter.DaysViewHolder> {
    Context context;
    ArrayList<DaysData> DaysDataList = new ArrayList<DaysData>();
    public DaysListAdapter(Context context, List<DaysData> DaysDataList){
        this.context = context;
        this.DaysDataList = (ArrayList<DaysData>) DaysDataList;
    }

    @NonNull
    @Override
    public DaysListAdapter.DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.days_data_item,parent,false);
        return new DaysListAdapter.DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysListAdapter.DaysViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static final class DaysViewHolder extends RecyclerView.ViewHolder{
        TextView date,temperature,visibility,wind,humidity,status;
        ImageView icon;
        public DaysViewHolder(@NonNull View itemView) {
            super(itemView);

        }

    }
}
