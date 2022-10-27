package ru.parsing;

import java.util.Date;

public class SourceData {




    private String stores;
    private int storeId;

    private  String offerName;

    private int offerId;

    private String unit;
    private double price;
    private Date date;

    private int category;

    public SourceData(String stores, String offerName, String unit, double price, Date date, int category) {
        this.stores = stores;
        this.offerName = offerName;
        this.unit = unit;
        this.price = price;
        this.date= date;
        this.category = category;

    }

    public String getStores() {
        return stores;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStores(String stores) {
        this.stores = stores;

    }


    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
