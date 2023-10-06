package com.example.pizzaDelivery.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Size")
public class PizzaSize {
    @Id
    @Column(name="size", length = 255)
    private String size;

    @ManyToMany(mappedBy = "size", fetch = FetchType.LAZY)
    private List<PizzaMenu> pizzas;
    public PizzaSize() {
    }

    public PizzaSize(String size) {

        this.size = size;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PizzaSize{" +
                ", size='" + size + '\'' +
                '}';
    }
}
