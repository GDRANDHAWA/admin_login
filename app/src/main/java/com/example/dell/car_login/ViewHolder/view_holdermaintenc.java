package com.example.dell.car_login.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.car_login.R;

/**
 * Created by DELL on 4/20/2017.
 */

public class view_holdermaintenc extends RecyclerView.ViewHolder {
    public TextView veh_numb ,veh_mdate, veh_mbillno, mn_billamt,wrkdn;

    public view_holdermaintenc(View itemView) {
        super(itemView);
        // here itemView contain the cell layout
        // finding view by id , here we use itemView.findViewById becoz itemView contains cell layout
        veh_numb = (TextView) itemView.findViewById(R.id.id_vehnumbr);
        veh_mdate= (TextView) itemView.findViewById(R.id.id_daate);
        veh_mbillno = (TextView) itemView.findViewById(R.id.id_billnumbr);
        mn_billamt = (TextView) itemView.findViewById(R.id.id_amttt);
        wrkdn= (TextView) itemView.findViewById(R.id.id_wrkdne);

    }
}
