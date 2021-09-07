package com.example.crmsystem.salesstaff;

public class AttendencePojo {
    String empid,date,lat,lon;

    public AttendencePojo(){

    }
    public AttendencePojo(String empid, String date, String lat, String lon) {
        this.empid = empid;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
