
package com.example.dell.car_login;



import android.app.Activity;
import android.content.Context;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
/**
 * Created by DELL on 3/1/2017.
 */

public class AppController  {

    private RequestQueue mRequestQueue;
    private Activity c;


    private static AppController mInstance;

    public AppController(Activity c)
    {
        mInstance = this;
        this.c = c;

    }

    public static synchronized AppController getInstance()
    {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(c);
        }

        return mRequestQueue;
    }



    public  void addToRequestQueue(Request req) {

        getRequestQueue().add(req);
    }


}