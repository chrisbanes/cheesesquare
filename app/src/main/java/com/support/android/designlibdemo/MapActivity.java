package com.support.android.designlibdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.support.android.designlibdemo.model.SchoolData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MapActivity extends Activity implements GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ArrayList<SchoolData> mSchoolDatasArray = new ArrayList<>();
    private HashMap<Marker, SchoolData> mMarkersHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
       /* SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); */

        mMarkersHashMap = new HashMap<>();

        setUpMap();

        LatLng beograd = new LatLng(44.801235, 20.457811);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beograd));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnInfoWindowClickListener(this);

        JsonArrayRequest mapRequest = new JsonArrayRequest(Request.Method.GET, "http://46.101.91.164/skoleproduction/index.php/api/v/2981/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("CHEESE", response.toString());
                for(int i=0; i < response.length(); i++){
                    try {
                        JSONObject json = response.getJSONObject(i);
                        SchoolData school = new SchoolData(json);
                        mSchoolDatasArray.add(school);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                plotSchools(mSchoolDatasArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("MAP CHEESE", error.getMessage());
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(mapRequest);
    }

    private void plotSchools(ArrayList<SchoolData> markers) {
        if(markers.size() > 0)
        {
            for (SchoolData school : markers)
            {
                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(school.getLatitude(), school.getLongitude()));
                markerOption.icon(getMarkerIcon(school.getType()));

                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, school);

                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
            }
        }
    }


    public BitmapDescriptor getMarkerIcon(int type) {
        Log.e("FUCKING COLOR", ""+ type);
//        float[] hsv = new float[3];
//        Color.colorToHSV(Color.parseColor(color), hsv);
//        BitmapDescriptor bd;

        switch (type){
            case 1: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
            case 2: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
            case 3: return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
        }
        return BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
    }

    private void setUpMap()
    {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

            // Check if we were successful in obtaining the map.

            if (mMap != null)
            {
                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {
                    @Override
                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker)
                    {
                        marker.showInfoWindow();
                        return true;
                    }
                });
            }
            else
                Toast.makeText(getApplicationContext(), "Unable to create Maps", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        SchoolData school = mMarkersHashMap.get(marker);
        Uri uri = Uri.parse(school.getSite());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter() {

        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(final Marker marker)
        {
            View v  = getLayoutInflater().inflate(R.layout.map_info_window_layout, null);

            SchoolData school = mMarkersHashMap.get(marker);
            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);
            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);
            TextView markerSajt = (TextView)v.findViewById(R.id.tekst_sajt);
            TextView anotherLabel = (TextView)v.findViewById(R.id.another_label);

            Glide.with(getApplicationContext())
                    .load("http://46.101.91.164/skoleproduction/slike/" + school.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.cheese_1)
                    .crossFade()
                    .into(markerIcon);

            markerLabel.setText(school.getName());
            anotherLabel.setText(school.getAddress());
            markerSajt.setText(school.getSite());

            return v;
        }
    }
}
