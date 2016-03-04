package com.support.android.designlibdemo;

import android.app.Activity;
import android.graphics.Color;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.support.android.designlibdemo.model.MarkerData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MapActivity extends Activity{

    private GoogleMap mMap;
    private ArrayList<MarkerData> mMarkerDatasArray = new ArrayList<>();
    private HashMap<Marker, MarkerData> mMarkersHashMap;

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

        mMarkerDatasArray.add(new MarkerData("ОШ 'Антон Скала' ","Петра Чајковског 21, Савски Венац", "antonskala", Double.parseDouble("44.787583"), Double.parseDouble("20.441149"), "#ff0000","http://www.antonskala.rs/"));
        mMarkerDatasArray.add(new MarkerData("ОШ 'Војвода Мишић'","Милутина Ивковића 4, Савски Венац", "vojvodamisic", Double.parseDouble("44.786568"), Double.parseDouble("20.448922"),"#ff0000","htpp://www.osvojvodamisic.edu.rs/"));
        mMarkerDatasArray.add(new MarkerData("ОШ 'Војвода Мишић'","Милутина Ивковића 4, Савски Венац", "vojvodamisic", Double.parseDouble("44.786568"), Double.parseDouble("20.448922"),"#ff0000",""));
        mMarkerDatasArray.add(new MarkerData("ОШ 'Стефан Немања'","Љубе Јовановића 2а", "stefannemanja", Double.parseDouble("44.791841"), Double.parseDouble("20.443647"),"#ff0000","http://www.ossnemanja.znanje.info"));
        mMarkerDatasArray.add(new MarkerData("Техничка школа за дизајн коже","Бранкова 17", "tehnickadizajn", Double.parseDouble("44.814977"), Double.parseDouble("20.454049"),"#0000ff","http://www.skoladizajnkoze.edu.rs"));
        mMarkerDatasArray.add(new MarkerData("Филолошка гимназија","Каменичка 2, Савски Венац", "filoloska", Double.parseDouble("44.812671"), Double.parseDouble("20.456992"),"#0000ff","http://www.filoloska.edu.rs/filoloska"));
        mMarkerDatasArray.add(new MarkerData("ОШ 'Исидора Секулић'","Гаврила Принципа 42 Савски Венац", "isidorasekulic", Double.parseDouble("44.813933"), Double.parseDouble("20.453892"),"#ff0000","http://www.isidorasekulic.rs"));
        mMarkerDatasArray.add(new MarkerData("Угоститељско-туристичка школа","Југ Богданова 28,Савски Венац", "ugostiteljskoturisticka", Double.parseDouble("44.814173"), Double.parseDouble("20.454782"),"#0000ff","http://www.ut-skola.znanje.info"));
        mMarkerDatasArray.add(new MarkerData("Гимназија 'Свети Сава'", "Ресавска 58,Савски Венац", "svsava", Double.parseDouble("44.801235"), Double.parseDouble("20.457811"),"#0000ff","http://www.sveti-sava.edu.rs"));
        mMarkerDatasArray.add(new MarkerData("ОШ 'Драган Херцог'", "Војводе Миленка33,Савски Венац", "dragance", Double.parseDouble("44.803943"), Double.parseDouble("20.457461"),"#ff0000","http://www.osdrdragangercog.edu.rs"));
        mMarkerDatasArray.add(new MarkerData("ОШ 'Радојка Лакић'", "Др Александра Костића 1-7", "radojka", Double.parseDouble("44.806508"), Double.parseDouble("20.456568"),"#ff0000","http://www.rlakic.edu.rs"));


        setUpMap();

        LatLng beograd = new LatLng(44.801235, 20.457811);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beograd));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13.0f));

        mMap.getUiSettings().setZoomControlsEnabled(true);

        plotMarkers(mMarkerDatasArray);

        JsonArrayRequest mapRequest = new JsonArrayRequest(Request.Method.GET, "http://46.101.91.164/skoleproduction/index.php/api/v/2981/", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("CHEESE", response.toString());
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

    private void plotMarkers(ArrayList<MarkerData> markers) {
        if(markers.size() > 0)
        {
            for (MarkerData MarkerData : markers)
            {

                // Create user marker with custom icon and other options
                MarkerOptions markerOption = new MarkerOptions().position(new LatLng(MarkerData.getmLatitude(), MarkerData.getmLongitude()));
                markerOption.icon(getMarkerIcon(MarkerData.getClr()));

              /*  BitmapDescriptor bitmapDescriptor
                        = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_AZURE); */


                Marker currentMarker = mMap.addMarker(markerOption);
                mMarkersHashMap.put(currentMarker, MarkerData);

                mMap.setInfoWindowAdapter(new MarkerInfoWindowAdapter());
            }
        }
    }


    public BitmapDescriptor getMarkerIcon(String color) {
        float[] hsv = new float[3];
        Color.colorToHSV(Color.parseColor(color), hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

    private int manageMarkerIcon(String markerIcon)
    {
//        if (markerIcon.equals("antonskala"))
//            return R.drawable.antonskala;
//        else if(markerIcon.equals("vojvodamisic"))
//            return R.drawable.vojvodamisic;
//        else if(markerIcon.equals("stefannemanja"))
//            return R.drawable.stefannemanja;
//        else if(markerIcon.equals("tehnickadizajn"))
//            return R.drawable.tehnickadizajn;
//        else if(markerIcon.equals("filoloska"))
//            return R.drawable.filoloska;
//        else if(markerIcon.equals("isidorasekulic"))
//            return R.drawable.isidorasekulic;
//        else if(markerIcon.equals("ugostiteljskoturisticka"))
//            return R.drawable.ugostiteljskoturisticka;
//        else if(markerIcon.equals("svsava"))
//            return R.drawable.svsava;
//        else if(markerIcon.equals("dragance"))
//            return R.drawable.dragance;
//        else if(markerIcon.equals("radojka"))
//            return R.drawable.radojka;
//        else
//            return R.drawable.icondefault;
        return 0;
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
    public class MarkerInfoWindowAdapter implements GoogleMap.InfoWindowAdapter
    {
        public MarkerInfoWindowAdapter()
        {
        }

        @Override
        public View getInfoWindow(Marker marker)
        {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker)
        {
            View v  = getLayoutInflater().inflate(R.layout.question_view, null);
//            View v  = getLayoutInflater().inflate(R.layout.infowindow_layout, null);
//
//            MarkerData MarkerData = mMarkersHashMap.get(marker);
////
//            ImageView markerIcon = (ImageView) v.findViewById(R.id.marker_icon);
//
//            TextView markerLabel = (TextView)v.findViewById(R.id.marker_label);
//
//            TextView markerSajt = (TextView)v.findViewById(R.id.tekst_sajt);
//
//            TextView anotherLabel = (TextView)v.findViewById(R.id.another_label);

//            markerIcon.setImageResource(manageMarkerIcon(MarkerData.getmIcon()));
//
//            markerLabel.setText(MarkerData.getmLabel());
//            anotherLabel.setText(MarkerData.getmSnippet());
//            markerSajt.setText(MarkerData.getSite());
            return v;
        }
    }
}
