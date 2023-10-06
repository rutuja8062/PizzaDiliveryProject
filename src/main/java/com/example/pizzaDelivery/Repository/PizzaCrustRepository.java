package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.PizzaCrust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@EnableJpaRepositories
public interface PizzaCrustRepository extends JpaRepository<PizzaCrust,Integer> {

    List<PizzaCrust> findAll();
}
