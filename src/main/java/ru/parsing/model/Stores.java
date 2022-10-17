package ru.parsing.model;

import javax.persistence.*;

@Entity
@Table(name = "stores")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "store_name")
    private  String storeName;

    public Stores() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Stores(String storeName) {

        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "Stores{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
