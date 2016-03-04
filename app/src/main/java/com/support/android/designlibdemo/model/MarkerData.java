package com.support.android.designlibdemo.model;

public class MarkerData {

    private String mLabel;
    private String mIcon;
    private Double mLatitude;
    private Double mLongitude;
    private String mSnippet;
    private String mColor;
    private String mSite;

    public MarkerData(String label,String snippet, String icon, Double latitude, Double longitude, String color, String site)
    {
        this.mLabel = label;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mIcon = icon;
        this.mSnippet = snippet;
        this.mColor=color;
        this.mSite = site;

    }

    public String getmLabel()
    {
        return mLabel;
    }

    public void setmLabel(String mLabel)
    {
        this.mLabel = mLabel;
    }

    public String getmIcon()
    {
        return mIcon;
    }

    public void setmIcon(String icon)
    {
        this.mIcon = icon;
    }

    public Double getmLatitude()
    {
        return mLatitude;
    }

    public void setmLatitude(Double mLatitude)
    {
        this.mLatitude = mLatitude;
    }

    public Double getmLongitude()
    {
        return mLongitude;
    }

    public void setmLongitude(Double mLongitude)
    {
        this.mLongitude = mLongitude;
    }
    public String getmSnippet()
    {
        return mSnippet;
    }
    public void setmSnippet(String mSnippet){
        this.mSnippet=mSnippet;
    }

    public String getClr(){
        return this.mColor;
    }

    public void setClr(){
        this.mColor=mColor;
    }
    public String getSite(){
        return mSite;
    }

    public void setSite(String mSite){
        this.mSite=mSite;
    }

}
