package com.example.pizzaDelivery.Repository;

import com.example.pizzaDelivery.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository

public interface UserRepository extends JpaRepository<User,Integer> {


    Optional<User> findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}

