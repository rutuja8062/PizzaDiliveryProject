package com.example.pizzaDelivery.Entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Crust")
public class PizzaCrust {

    @Id
    @Column (name="pizzaCrust", length = 255)
    private String pizzaCrust;

    @ManyToMany(mappedBy = "pizzaCrust", fetch = FetchType.LAZY)
    private List<PizzaMenu> pizzas;


    @Override
    public String toString() {
        return "PizzaCrust{" +
                ", pizzaCrust='" + pizzaCrust + '\'' +
                '}';
    }

    public PizzaCrust(String pizzaCrust) {

        this.pizzaCrust = pizzaCrust;
    }

    public PizzaCrust() {
    }



    public String getPizzaCrust() {
        return pizzaCrust;
    }

    public void setPizzaCrust(String crustName) {
        this.pizzaCrust = crustName;
    }

}
