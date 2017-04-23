package com.example.dell.car_login.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dell.car_login.R;
import com.example.dell.car_login.ViewHolder.view_holderdistance;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 4/21/2017.
 */

public class adapter_viewdistance extends RecyclerView.Adapter<view_holderdistance> {
    JSONArray jsarr;
    // creating Activity variable globally
    Activity a;   // making constructer for adapter class which will be
    // called when object of this class is created

    public adapter_viewdistance(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }



    @Override
    public view_holderdistance onCreateViewHolder(ViewGroup parent, int viewType) {
        view_holderdistance view = new view_holderdistance(LayoutInflater.from(a).inflate(R.layout.cell_distance,parent,false));

        return view;
    }

    @Override
    public void onBindViewHolder(view_holderdistance holder, int position) {
        try {
            // iterating for each json object in json array
            JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.veh_dnum.setText(job.getString("VehicleNO"));
            holder.ddate.setText(job.getString("Date"));
            holder.usrid.setText(job.getString("UserId"));
            holder.distance.setText(job.getString("Distance"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        // return how many cells will be created i.e length of json array
        return jsarr.length();
    }
}
