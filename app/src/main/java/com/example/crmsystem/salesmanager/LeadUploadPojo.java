package com.example.crmsystem.salesmanager;

public class LeadUploadPojo {
    String name,empid,target,startdate,enddate,shift,place,leadname,leadaddress,leadcontact;

    public  LeadUploadPojo(){

    }
    public LeadUploadPojo(String name, String empid, String target, String startdate, String enddate, String shift, String place, String leadname, String leadaddress, String leadcontact) {
        this.name = name;
        this.empid = empid;
        this.target = target;
        this.startdate = startdate;
        this.enddate = enddate;
        this.shift = shift;
        this.place = place;
        this.leadname = leadname;
        this.leadaddress = leadaddress;
        this.leadcontact = leadcontact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getLeadname() {
        return leadname;
    }

    public void setLeadname(String leadname) {
        this.leadname = leadname;
    }

    public String getLeadaddress() {
        return leadaddress;
    }

    public void setLeadaddress(String leadaddress) {
        this.leadaddress = leadaddress;
    }

    public String getLeadcontact() {
        return leadcontact;
    }

    public void setLeadcontact(String leadcontact) {
        this.leadcontact = leadcontact;
    }
}
