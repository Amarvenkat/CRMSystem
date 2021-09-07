package com.example.crmsystem;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ElectroicProduct {

    @SerializedName("organic_results")
        private List<OrganicResults> organicResults;

    public ElectroicProduct(List<OrganicResults> organicResults) {
        this.organicResults = organicResults;
    }

    public List<OrganicResults> getOrganicResults() {
        return organicResults;
    }

    public void setOrganicResults(List<OrganicResults> organicResults) {
        this.organicResults = organicResults;
    }
}
