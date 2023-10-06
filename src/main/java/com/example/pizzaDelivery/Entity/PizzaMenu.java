package com.example.pizzaDelivery.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Menu")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class PizzaMenu {
        @Id
        @Column(name="pizza_ID", length = 45)
        @GeneratedValue(strategy = GenerationType.AUTO)
        private String pizzaId;
        @Column (name="pizza_Name", length = 255)
        private String pizzaName;
        @Column (name="Price", length = 255)
        private Integer pizzaPrice;


        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "PizzaCrust",
                joinColumns = @JoinColumn(name = "pizza_id"),
                inverseJoinColumns = @JoinColumn(name = "pizzaCrust")
        )
        private List<PizzaCrust> pizzaCrust;
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "PizzaSize",
                joinColumns = @JoinColumn(name = "pizza_id"),
                inverseJoinColumns = @JoinColumn(name = "size")
        )
        private List<PizzaSize> size;
        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "PizzaToppings",
                joinColumns = @JoinColumn(name = "pizza_id"),
                inverseJoinColumns = @JoinColumn(name = "toppings_id")
        )
        private List<Toppings> toppings;


        public PizzaMenu() {
        }

        public PizzaMenu(String pizzaId, String pizzaName, Integer pizzaPrice) {
                this.pizzaId = pizzaId;
                this.pizzaName = pizzaName;
                this.pizzaPrice = pizzaPrice;
        }

        public String getPizzaId() {
                return pizzaId;
        }

        public void setPizzaId(String pizzaId) {
                this.pizzaId = pizzaId;
        }

        public String getPizzaName() {
                return pizzaName;
        }

        public void setPizzaName(String pizzaName) {
                this.pizzaName = pizzaName;
        }

        public Integer getPizzaPrice() {
                return pizzaPrice;
        }

        public void setPizzaPrice(Integer pizzaPrice) {
                this.pizzaPrice = pizzaPrice;
        }

        @Override
        public String toString() {
                return "Pizza{" +
                        "pizzaId='" + pizzaId + '\'' +
                        ", pizzaName='" + pizzaName + '\'' +
                        ", pizzaPrice=" + pizzaPrice +
                        '}';
        }
}
