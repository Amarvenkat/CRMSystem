package com.example.crmsystem.salesstaff;

public class TrackLeadPojo {

    String leadname,leadcontact,leadaddress,totalstock,stocksold,stockleft,profit;

    public  TrackLeadPojo(){

    }
    public TrackLeadPojo(String leadname, String leadcontact, String leadaddress, String totalstock, String stocksold, String stockleft, String profit) {
        this.leadname = leadname;
        this.leadcontact = leadcontact;
        this.leadaddress = leadaddress;
        this.totalstock = totalstock;
        this.stocksold = stocksold;
        this.stockleft = stockleft;
        this.profit = profit;
    }

    public String getLeadname() {
        return leadname;
    }

    public void setLeadname(String leadname) {
        this.leadname = leadname;
    }

    public String getLeadcontact() {
        return leadcontact;
    }

    public void setLeadcontact(String leadcontact) {
        this.leadcontact = leadcontact;
    }

    public String getLeadaddress() {
        return leadaddress;
    }

    public void setLeadaddress(String leadaddress) {
        this.leadaddress = leadaddress;
    }

    public String getTotalstock() {
        return totalstock;
    }

    public void setTotalstock(String totalstock) {
        this.totalstock = totalstock;
    }

    public String getStocksold() {
        return stocksold;
    }

    public void setStocksold(String stocksold) {
        this.stocksold = stocksold;
    }

    public String getStockleft() {
        return stockleft;
    }

    public void setStockleft(String stockleft) {
        this.stockleft = stockleft;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }
}
