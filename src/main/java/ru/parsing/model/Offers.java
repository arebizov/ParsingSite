package ru.parsing.model;


import javax.persistence.*;

@Entity
@Table(name = "offers")
public class Offers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    private int initId;

    @Column(name = "offer_id")
    private int offerId;

    @Column(name = "name")
    private String name;

    @Column(name = "store_id")
    private int storeID;

    @Column(name = "unit")
    private String unit;


    public Offers() {
    }

    public Offers(int storeID,  String name, int offerId, String unit) {
        this.offerId = offerId;
        this.name = name;
        this.storeID = storeID;
        this.unit = unit;
    }

    public int getInitId() {
        return initId;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public void setInitId(int initId) {
        this.initId = initId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "initId=" + initId +
                ", offerId=" + offerId +
                ", name='" + name + '\'' +
                '}';
    }
}
