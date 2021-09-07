package com.example.crmsystem;

import com.google.gson.annotations.SerializedName;

public class OrganicResults {


    @SerializedName("us_item_id")
    private String us_item_id;
    @SerializedName("product_id")
    private String product_id;
    @SerializedName("upc")
    private String upc;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("thumbnail")
    private  String thumbnail;
    @SerializedName("rating")
    private String rating;
    @SerializedName("reviews")
    private  String reviews;
    @SerializedName("special_offer_text")
    private String special_offer_text;
    @SerializedName("seller_id")
    private String seller_id;
    @SerializedName("seller_name")
    private  String seller_name;
    @SerializedName("two_day_shipping")
    private String two_day_shipping;
    @SerializedName("quantity")
    private  String quantity;
    @SerializedName("primary_offer")
    private PrimaryOffer primaryOffer;
    @SerializedName("price_per_unit")
    private PricePerUnit pricePerUnit;
    @SerializedName("product_type")
    private String product_type;
    @SerializedName("product_page_url")
    private String product_page_url;
    @SerializedName("serpapi_product_page_url")
    private  String serpapi_product_page_url;

    public OrganicResults(String us_item_id, String product_id, String upc, String title, String description, String thumbnail, String rating, String reviews, String special_offer_text, String seller_id, String seller_name, String two_day_shipping, String quantity, PrimaryOffer primaryOffer, PricePerUnit pricePerUnit, String product_type, String product_page_url, String serpapi_product_page_url) {
        this.us_item_id = us_item_id;
        this.product_id = product_id;
        this.upc = upc;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.rating = rating;
        this.reviews = reviews;
        this.special_offer_text = special_offer_text;
        this.seller_id = seller_id;
        this.seller_name = seller_name;
        this.two_day_shipping = two_day_shipping;
        this.quantity = quantity;
        this.primaryOffer = primaryOffer;
        this.pricePerUnit = pricePerUnit;
        this.product_type = product_type;
        this.product_page_url = product_page_url;
        this.serpapi_product_page_url = serpapi_product_page_url;
    }

    public String getUs_item_id() {
        return us_item_id;
    }

    public void setUs_item_id(String us_item_id) {
        this.us_item_id = us_item_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getSpecial_offer_text() {
        return special_offer_text;
    }

    public void setSpecial_offer_text(String special_offer_text) {
        this.special_offer_text = special_offer_text;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getTwo_day_shipping() {
        return two_day_shipping;
    }

    public void setTwo_day_shipping(String two_day_shipping) {
        this.two_day_shipping = two_day_shipping;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public PrimaryOffer getPrimaryOffer() {
        return primaryOffer;
    }

    public void setPrimaryOffer(PrimaryOffer primaryOffer) {
        this.primaryOffer = primaryOffer;
    }

    public PricePerUnit getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(PricePerUnit pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_page_url() {
        return product_page_url;
    }

    public void setProduct_page_url(String product_page_url) {
        this.product_page_url = product_page_url;
    }

    public String getSerpapi_product_page_url() {
        return serpapi_product_page_url;
    }

    public void setSerpapi_product_page_url(String serpapi_product_page_url) {
        this.serpapi_product_page_url = serpapi_product_page_url;
    }
}
