package com.example.crmsystem.salesstaff;

import static com.example.crmsystem.util.Constants.SF_ADDRESS;
import static com.example.crmsystem.util.Constants.SF_CITY;
import static com.example.crmsystem.util.Constants.SF_EMAIL;
import static com.example.crmsystem.util.Constants.SF_NAME;
import static com.example.crmsystem.util.Constants.SF_PROCATO;
import static com.example.crmsystem.util.Constants.SF_SHIFT;

public class SalesStaffListCreate {

    String name ,address,mail ,city,procate,shift,role,empid,gender,phoneno;

    public SalesStaffListCreate(){

    }
    public SalesStaffListCreate(String name, String address, String mail, String city, String procate, String shift, String role, String empid, String gender, String phoneno) {
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.city = city;
        this.procate = procate;
        this.shift = shift;
        this.role = role;
        this.empid = empid;
        this.gender = gender;
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProcate() {
        return procate;
    }

    public void setProcate(String procate) {
        this.procate = procate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
