package com.example.dell.car_login.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dell.car_login.R;
import com.example.dell.car_login.ViewHolder.view_holdermaintenc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DELL on 4/20/2017.
 */

public class adapter_viewmaintenance extends RecyclerView.Adapter<view_holdermaintenc> {
    // creating JSONArray variable globally
    org.json.JSONArray jsarr;

    // creating Activity variable globally
    Activity a;   // making constructer for adapter class which will be
    // called when object of this class is created

    public adapter_viewmaintenance(JSONArray jsarr , Activity a)
    {
        this.jsarr = jsarr;

        this.a = a;
    }


    @Override
    public view_holdermaintenc onCreateViewHolder(ViewGroup parent, int viewType) {
        // creating view_holder object and passing cell layout as parameter
        view_holdermaintenc view = new view_holdermaintenc(LayoutInflater.from(a).inflate(R.layout.cell_maintenance,parent,false));

        return view;

    }

    @Override
    public void onBindViewHolder(view_holdermaintenc holder, int position) {
        try {
            // iterating for each json object in json array
            JSONObject job = (JSONObject) jsarr.get(position);

            // binding values from json object to cell layout via view holder
            holder.veh_numb.setText(job.getString("VehicleNO"));
            holder.veh_mdate.setText(job.getString("Date"));
            holder.veh_mbillno.setText(job.getString("BillNo"));
            holder.mn_billamt.setText(job.getString("Amount"));
            holder.wrkdn.setText(job.getString("WorkDone"));




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
