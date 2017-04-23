package com.example.dell.car_login.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dell.car_login.R;

/**
 * Created by DELL on 4/21/2017.
 */

public class view_holderdistance extends RecyclerView.ViewHolder {
    public TextView veh_dnum ,ddate,usrid, distance;
    public view_holderdistance(View itemView) {
        super(itemView);
        // here itemView contain the cell layout
        // finding view by id , here we use itemView.findViewById becoz itemView contains cell layout
        veh_dnum = (TextView) itemView.findViewById(R.id.id_distvehnum);
        ddate= (TextView) itemView.findViewById(R.id.id_distdte);
        usrid = (TextView) itemView.findViewById(R.id.id_uid);
        distance = (TextView) itemView.findViewById(R.id.id_disttrvl);

    }
}
