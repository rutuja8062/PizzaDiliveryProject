package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.CreateOrder;
import com.example.pizzaDelivery.Entity.PizzaCrust;
import com.example.pizzaDelivery.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@EnableJpaRepositories
@Repository
public interface OrderRepo extends JpaRepository<CreateOrder,Integer> {



}
