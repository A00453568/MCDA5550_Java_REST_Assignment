package com.example.hotel_reservation.model;

import javax.persistence.*;

@Entity
@Table(name = "Hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Hotel(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Hotel() {
        this.id = 0;
        this.name = "default";
        this.price = 0;
    }
}
