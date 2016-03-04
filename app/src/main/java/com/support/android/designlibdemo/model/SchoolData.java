package com.support.android.designlibdemo.model;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SchoolData {

    /*{
        "id": "17",
            "ime": "OŠ \"Mića Stojković\"",
            "adresa": "29. novembra 11, Umcari ",
            "sajt": "http://www.osumcari.rs/",
            "slika": "14493977587671.jpg",
            "longitude": "20.7302132",
            "latitude": "44.5815048",
            "tipskole": "1"
    }*/
    int id;
    String name;
    String address;
    String site;
    String imageUrl;
    double longitude;
    double latitude;
    int type;
    String color;

    public SchoolData(JSONObject json) {
        try {
            this.id = json.getInt("id");
            this.name = json.getString("ime");
            this.address = json.getString("adresa");
            this.site = json.getString("sajt");
            this.imageUrl = json.getString("slika");
            this.longitude = json.getDouble("longitude");
            this.latitude = json.getDouble("latitude");
            this.type = json.getInt("tipskole");
            color = "#ff0000";
            if (type == 2) color = "#0000ff";
            if (type == 3) color = "#00ff00";
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("JSON E", json.toString());
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getSite() {
        return site;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getType() {
        return type;
    }

    public String getColor() {
        return color;
    }
}
