package com.example.pizzaDelivery.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Toppings")
public class Toppings {

    @Id
    @Column(name="toppingsID", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String toppingsID;


    @Column (name="toppingsName", length = 255)
    private String toppingsName;
    @Column (name="price", length = 255)
    private Integer price;


    @ManyToMany(mappedBy = "toppings", fetch = FetchType.LAZY)
    private List<PizzaMenu> pizzas;
    public Toppings() {
    }

    public Toppings(String toppingsID, String toppingsName, Integer price) {
        this.toppingsID = toppingsID;
        this.toppingsName = toppingsName;
        this.price = price;
    }

    public String getToppingsID() {
        return toppingsID;
    }

    public void setToppingsID(String toppingsID) {
        this.toppingsID = toppingsID;
    }

    public String getToppingsName() {
        return toppingsName;
    }

    public void setToppingsName(String toppingsName) {
        this.toppingsName = toppingsName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Toppings{" +
                "toppingsID='" + toppingsID + '\'' +
                ", toppingsName='" + toppingsName + '\'' +
                ", price=" + price +
                '}';
    }
}


