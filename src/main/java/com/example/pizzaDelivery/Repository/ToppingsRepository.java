package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.PizzaMenu;
import com.example.pizzaDelivery.Entity.Toppings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ToppingsRepository extends JpaRepository<Toppings,String> {

    List<Toppings> findAll();

   // List<Toppings> findByToppingsID(String string);

    //Optional<Object> findByToppingsID(String string);

    //Optional<Toppings> findById(List<String> id);

   // Toppings findAllByToppingsName(String toppings);
    //Optional<Toppings> findByToppingsName(String toppings);
}
