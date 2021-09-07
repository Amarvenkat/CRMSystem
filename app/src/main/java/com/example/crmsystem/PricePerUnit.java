package com.example.crmsystem;

import com.google.gson.annotations.SerializedName;

public class PricePerUnit {

    @SerializedName("amount")
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PricePerUnit(String amount) {
        this.amount = amount;
    }
}
