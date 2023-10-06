package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.PizzaMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface PizzaRepository extends JpaRepository<PizzaMenu,String> {

   List<PizzaMenu> findAll();


   Optional<PizzaMenu> findById(String id);

}
