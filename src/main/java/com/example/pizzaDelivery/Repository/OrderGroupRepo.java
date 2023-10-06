package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface OrderGroupRepo extends JpaRepository<OrderGroup,Integer> {

}
