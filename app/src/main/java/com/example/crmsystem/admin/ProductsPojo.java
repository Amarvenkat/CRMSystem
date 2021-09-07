package com.example.crmsystem.admin;

public class ProductsPojo {


    private String stringproductname;
    private String stringstockavailability;
    private String stringprice;
    private String stringproductdescription;
    private String url;

    public  ProductsPojo(){

    }
    public ProductsPojo(String stringproductname, String stringstockavailability, String stringprice, String stringproductdescription, String url) {
        this.stringproductname = stringproductname;
        this.stringstockavailability = stringstockavailability;
        this.stringprice = stringprice;
        this.stringproductdescription = stringproductdescription;
        this.url = url;
    }

    public String getStringproductname() {
        return stringproductname;
    }

    public void setStringproductname(String stringproductname) {
        this.stringproductname = stringproductname;
    }

    public String getStringstockavailability() {
        return stringstockavailability;
    }

    public void setStringstockavailability(String stringstockavailability) {
        this.stringstockavailability = stringstockavailability;
    }

    public String getStringprice() {
        return stringprice;
    }

    public void setStringprice(String stringprice) {
        this.stringprice = stringprice;
    }

    public String getStringproductdescription() {
        return stringproductdescription;
    }

    public void setStringproductdescription(String stringproductdescription) {
        this.stringproductdescription = stringproductdescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
