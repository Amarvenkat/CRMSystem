package com.example.crmsystem.admin;

import com.google.android.material.textfield.TextInputEditText;

public class NewCampPojo {

    String newcampname,newcamparea,newcampnosales,newcampproduct,newcampstartdate,newcampenddate;

    public NewCampPojo(){

    }
    public NewCampPojo(String newcampname, String newcamparea, String newcampnosales, String newcampproduct, String newcampstartdate, String newcampenddate) {
        this.newcampname = newcampname;
        this.newcamparea = newcamparea;
        this.newcampnosales = newcampnosales;
        this.newcampproduct = newcampproduct;
        this.newcampstartdate = newcampstartdate;
        this.newcampenddate = newcampenddate;
    }

    public String getNewcampname() {
        return newcampname;
    }

    public void setNewcampname(String newcampname) {
        this.newcampname = newcampname;
    }

    public String getNewcamparea() {
        return newcamparea;
    }

    public void setNewcamparea(String newcamparea) {
        this.newcamparea = newcamparea;
    }

    public String getNewcampnosales() {
        return newcampnosales;
    }

    public void setNewcampnosales(String newcampnosales) {
        this.newcampnosales = newcampnosales;
    }

    public String getNewcampproduct() {
        return newcampproduct;
    }

    public void setNewcampproduct(String newcampproduct) {
        this.newcampproduct = newcampproduct;
    }

    public String getNewcampstartdate() {
        return newcampstartdate;
    }

    public void setNewcampstartdate(String newcampstartdate) {
        this.newcampstartdate = newcampstartdate;
    }

    public String getNewcampenddate() {
        return newcampenddate;
    }

    public void setNewcampenddate(String newcampenddate) {
        this.newcampenddate = newcampenddate;
    }
}
