package com.example.dell.car_login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.dell.car_login.R.id.map;

public class Cartracking extends FragmentActivity {

    String stt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stt1= getIntent().getStringExtra("vehnum");

        // Getting reference to the SupportMapFragmegetMap();nt of activity_main.xml
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);

        // Getting GoogleMap object from the fragment
        fm.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                JSONObject jsob = new JSONObject();

                try {
                    jsob.put("numbr_key" , stt1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest req = new JsonObjectRequest("http://192.168.1.114/Cartracking/locations.php",jsob ,  new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject j) {

                        JSONArray response = null;
                        try {
                            response = (JSONArray) j.getJSONArray("key");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        JSONObject obj= null;
                        try {
                            obj = (JSONObject) response.get(0);

                            String lati=obj.getString("Latitude");
                            String longi = obj.getString("Longitude");

                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.valueOf(lati),Double.valueOf(longi)))
                                    .title(Double.valueOf(lati).toString() + "," +Double.valueOf(longi).toString()));
                            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                            LatLng coordinate= new LatLng(Double.valueOf(lati),Double.valueOf(longi));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate,16));



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                AppController app = new AppController(Cartracking.this);
                app.addToRequestQueue(req);

            }
        });










    }
}