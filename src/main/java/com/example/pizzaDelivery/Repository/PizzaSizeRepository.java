package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.PizzaCrust;
import com.example.pizzaDelivery.Entity.PizzaSize;
import com.example.pizzaDelivery.Entity.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@EnableJpaRepositories
public interface PizzaSizeRepository extends JpaRepository<PizzaSize,Integer> {

    List<PizzaSize> findAll();
}
