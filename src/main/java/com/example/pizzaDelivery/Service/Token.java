package com.example.pizzaDelivery.Service;

import com.example.pizzaDelivery.Entity.User;

public interface Token {
    String generateToken(User user);
}
