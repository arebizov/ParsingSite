package ru.parsing.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "pricehistory")
public class PriceHisory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "offer_id")
    private int offerId;

    @Column(name = "price")
    private double price;

    @Column(name = "priceGold")
    private double priceGold;

    @Column(name= "priceDate")
    private Date date;

    public PriceHisory(int storeId, int offerId, double price, double priceGold, Date date) {

        this.storeId = storeId;
        this.offerId = offerId;
        this.price = price;
        this.priceGold = priceGold;
        this.date = date;
    }


    public PriceHisory() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceGold() {
        return priceGold;
    }

    public void setPriceGold(double priceGold) {
        this.priceGold = priceGold;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceHisory that = (PriceHisory) o;
        return storeId == that.storeId && offerId == that.offerId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, offerId, date);
    }
}
