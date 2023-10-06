package com.example.pizzaDelivery.Dto;

import java.util.List;

public class OrderDto {

    private String pizzaID;
    private int pizzaQtn;
    private String pizzaSize;
    private String pizzaCrust;
    private List<String> toppingsID;

    public OrderDto() {
    }

    public OrderDto(String pizzaID, String pizzaName, int pizzaQtn, String pizzaSize, String pizzaCrust,
                    List<String> toppingsID) {
        this.pizzaID = pizzaID;
        this.pizzaQtn = pizzaQtn;
        this.pizzaSize = pizzaSize;
        this.pizzaCrust = pizzaCrust;
        this.toppingsID = toppingsID;
    }

    public String getPizzaID() {
        return pizzaID;
    }

    public void setPizzaID(String pizzaID) {
        this.pizzaID = pizzaID;
    }


    public int getPizzaQtn() {
        return pizzaQtn;
    }

    public void setPizzaQtn(int pizzaQtn) {
        this.pizzaQtn = pizzaQtn;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public String getPizzaCrust() {
        return pizzaCrust;
    }

    public void setPizzaCrust(String pizzaCrust) {
        this.pizzaCrust = pizzaCrust;
    }

    public List<String> getToppingsID() {
        return toppingsID;
    }

    public void setToppingsID(List<String> toppingsID) {
        this.toppingsID = toppingsID;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "pizzaID='" + pizzaID + '\'' +
                ", pizzaQtn=" + pizzaQtn +
                ", pizzaSize='" + pizzaSize + '\'' +
                ", pizzaCrust='" + pizzaCrust + '\'' +
                ", toppingsID=" + toppingsID +
                '}';
    }
}
