package com.example.pizzaDelivery.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "CreateOrder")
public class CreateOrder {


    @Id
    @Column(name="orderID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderID;

    //join Menu And CreateOrder table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pizzaID")
    private PizzaMenu pizzaID;

    @Column (name="pizzaName", length = 255)
    private String pizzaName;

    @Column (name="pizzaQtn", length = 255)
    private int pizzaQtn;

    @Column (name="pizzaSize", length = 255)
    private String pizzaSize;

    @Column (name="pizzaCrust", length = 255)
    private String pizzaCrust;


    //join Menu and toppings table
    @ManyToMany
    @JoinTable(
            name = "order_toppings",
            joinColumns = @JoinColumn(name = "orderID"),
            inverseJoinColumns = @JoinColumn(name = "toppingsID")
    )
    private List<Toppings> toppingsID=null;


    @Column(name = "status", length = 255)
    private String status = "Accepted";

    @Column(name = "amount", length = 255)
    private int amount;

    public CreateOrder() {
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public CreateOrder(PizzaMenu pizzaID, String pizzaName, int pizzaQtn, String pizzaSize,
                       String pizzaCrust, List<Toppings> toppingsID) {
        this.pizzaID = pizzaID;
        this.pizzaName = pizzaName;
        this.pizzaQtn = pizzaQtn;
        this.pizzaSize = pizzaSize;
        this.pizzaCrust = pizzaCrust;
        this.toppingsID = toppingsID;
    }
}
